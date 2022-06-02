package tocook.service.food;

import static tocook.common.CommonUtils.*;
import static tocook.common.Functions.*;
import static tocook.constant.Constant.*;
import static tocook.constant.IdFormatterConstant.*;

import java.util.*;

import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.stereotype.*;
import org.springframework.transaction.annotation.*;

import tocook.constant.*;
import tocook.dao.*;
import tocook.dto.*;
import tocook.exception.*;

/**
 * 食材管理機能 サービス
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class FoodServiceImpl implements FoodService {

    /** 食材テーブルMapperImpl */
    @Autowired
    private FoodMstMapperImpl foodMstMapperImpl;

    /** 汎用マスタテーブルMapperImpl */
    @Autowired
    private CommonMstMapperImpl commonMstMapperImpl;

    /**
     * 件数を取得し、返却します。
     *
     * @param foodId 食材ID
     * @param foodName 食材名
     * @param authority 権限
     * @param limit 最大取得数
     * @param offset オフセット
     * @return 食材件数
     */
    @Override
    public Integer getListCount(
            String foodId,
            String foodName,
            String foodUnit) {
        return foodMstMapperImpl.selectListCount(foodId, foodName, foodUnit);
    }

    /**
     * リストを取得し、返却します。
     *
     * @param foodId 食材ID
     * @param foodName 食材名
     * @param authority 権限
     * @param orderBy ソート項目
     * @param ascOrDesc 昇順／降順
     * @param limit 最大取得数
     * @param offset オフセット
     * @return 食材情報リスト
     */
    @Override
    public List<FoodMstDTO> getList(
            String foodId,
            String foodName,
            String foodUnit,
            Integer orderBy,
            Integer ascOrDesc,
            Integer limit,
            Integer offset) {
        return foodMstMapperImpl.selectList(foodId, foodName, foodUnit, orderBy, ascOrDesc, limit, offset);
    }

    /**
     * IDをキーにレコードを取得し、返却します。
     *
     * @param id 食材ID
     * @return レコード
     */
    @Override
    public FoodMstDTO getListById(String id) {
        return foodMstMapperImpl.selectById(id);
    }

    /**
     * 登録処理を行います。
     *
     * @param foodMstDTO  食材情報
     * @return 新規ID
     */
    @Override
    public String insert(FoodMstDTO foodMstDTO) {

        // 食材単位レコード存在確認
        foodNameAndFoodUnitCheck(foodMstDTO.getFoodName(), foodMstDTO.getFoodUnit());

        // 最大値IDを取得
        String id = foodMstMapperImpl.selectMaxId();

        // 最大値ID + 1
        String maxId = format(idToValue(id, FOOD_ID_PREFIX) + 1, FOOD_ID_FORMAT);

        // 最大ID作成確認
        if (isNull(maxId)) {
            throw new ToCookRuntimeException(HttpStatus.INTERNAL_SERVER_ERROR, ResultCode.C104);
        }

        // 最大IDを格納
        foodMstDTO.setFoodId(maxId);

        // 登録処理
        foodMstMapperImpl.insertSelective(foodMstDTO);

        return maxId;
    }

    /**
     * 更新処理を行います。
     *
     * @param foodMstDTO  食材情報
     */
    @Override
    public void edit(FoodMstDTO foodMstDTO) {

        // レコード取得
        FoodMstDTO getFoodMstDTO = foodMstMapperImpl.selectByIdAndUserId(foodMstDTO.getFoodId(), foodMstDTO.getUserId());

        // 食材単位レコード存在確認
        foodNameAndFoodUnitCheck(nvl(foodMstDTO.getFoodName(), getFoodMstDTO.getFoodName()), foodMstDTO.getFoodUnit());

        if (isNull(getFoodMstDTO)) {
            // レコードが存在しない
            throw new ToCookRuntimeException(HttpStatus.NOT_FOUND, ResultCode.C105);
        }

        if (!eq(getFoodMstDTO.getVersion(), foodMstDTO.getVersion())) {
            // バージョン不一致による排他制御
            throw new ToCookRuntimeException(HttpStatus.CONFLICT, ResultCode.C107);
        }

        if (foodMstMapperImpl.updateById(foodMstDTO) == 0) {
            // 不正なDB処理
            throw new ToCookRuntimeException(HttpStatus.INTERNAL_SERVER_ERROR, ResultCode.C109);
        }
    }

    /**
     * 削除処理を行います。
     *
     * @param id 食材ID
     */
    @Override
    public void delete(String id) {

        // 削除レコード取得
        FoodMstDTO foodMstDTO = foodMstMapperImpl.selectByIdAndUserId(id, getLoginUserId());

        // 存在チェック
        if (isNull(foodMstDTO)) {
            // 存在しない場合
            throw new ToCookRuntimeException(HttpStatus.NOT_FOUND, ResultCode.C105);
        }

        // 削除処理
        if (foodMstMapperImpl.deleteByPrimaryKey(id) != 1) {
            // 不正なSQL文の検知
            throw new ToCookRuntimeException(HttpStatus.INTERNAL_SERVER_ERROR, ResultCode.C109);
        }
    }

    /**
     * 重複する食材名と存在しない食材単位がないか確認を行います。
     *
     * @param foodName 食材名
     * @param foodUnit 単位
     */
    private void foodNameAndFoodUnitCheck(String foodName, String foodUnit) {
        // 食材単位レコード存在確認
        if (isNotNull(foodUnit)) {
            // レコード存在確認
            if (isNull(commonMstMapperImpl.selectByPrimaryKey(foodUnit))) {
                throw new ToCookRuntimeException(HttpStatus.NOT_FOUND, ResultCode.C105);
            }

            // 既に存在する食材レコードか確認
            if (!eq(foodUnit, FOOD_ID_OTHERS) &&
                    isNotNull(foodMstMapperImpl.selectByFoodNameAndFoodUnit(foodName, foodUnit))) {
                throw new ToCookRuntimeException(HttpStatus.BAD_REQUEST, ResultCode.C106);
            }
        }
    }

}
