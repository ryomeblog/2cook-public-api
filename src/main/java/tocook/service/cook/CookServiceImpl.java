package tocook.service.cook;

import static tocook.common.CommonUtils.*;
import static tocook.common.Functions.*;
import static tocook.constant.IdFormatterConstant.*;

import java.util.*;
import java.util.stream.*;

import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.stereotype.*;
import org.springframework.transaction.annotation.*;

import com.google.common.collect.Streams;

import tocook.constant.*;
import tocook.dao.*;
import tocook.dto.*;
import tocook.exception.*;

/**
 * 料理管理機能 サービス
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class CookServiceImpl implements CookService {

    /** 料理テーブルMapperImpl */
    @Autowired
    private CookMstMapperImpl cookMstMapperImpl;

    /** 食材テーブルMapperImpl */
    @Autowired
    private FoodMstMapperImpl foodMstMapperImpl;

    /** 料理食材テーブルMapperImpl */
    @Autowired
    private CookFoodTblMapperImpl cookFoodTblMapperImpl;

    /**
     * レコード件数を取得し、返却します。
     *
     * @param cookId 料理ID
     * @param cookName 料理名
     * @param userId ユーザID
     * @param selectUserId 検索ユーザID
     * @param minPrice 最小金額
     * @param maxPrice 最大金額
     * @param foodIdList 食材IDリスト
     * @return レコード件数
     */
    @Override
    public Integer getListCount(
            String cookId,
            String cookName,
            String userId,
            String selectUserId,
            Long minPrice,
            Long maxPrice,
            List<String> foodIdList) {

        return cookMstMapperImpl.selectListCount(
                cookId,
                cookName,
                userId,
                selectUserId,
                minPrice,
                maxPrice,
                foodIdList);
    }

    /**
     * リストを取得し、返却します。
     *
     * @param cookId 料理ID
     * @param cookName 料理名
     * @param userId ユーザID
     * @param selectUserId 検索ユーザID
     * @param minPrice 最小金額
     * @param maxPrice 最大金額
     * @param foodIdList 食材IDリスト
     * @param orderBy ソート項目
     * @param ascOrDesc 昇順／降順
     * @param limit 最大取得数
     * @param offset オフセット
     * @return リスト
     */
    @Override
    public List<CookMstDTO> getList(
            String cookId,
            String cookName,
            String userId,
            String selectUserId,
            Long minPrice,
            Long maxPrice,
            List<String> foodIdList,
            Integer orderBy,
            Integer ascOrDesc,
            Integer limit,
            Integer offset) {
        return cookMstMapperImpl.selectList(
                cookId,
                cookName,
                userId,
                selectUserId,
                minPrice,
                maxPrice,
                foodIdList,
                orderBy,
                ascOrDesc,
                limit,
                offset);
    }

    /**
     * IDをキーにレコードを取得し、返却します。
     *
     * @param id 料理ID
     * @param userId ユーザID
     * @return レコード
     */
    @Override
    public CookMstDTO getListById(String id, String userId) {
        return cookMstMapperImpl.selectById(id, userId);
    }

    /**
     * IDをキーにレコードを取得し、返却します。
     *
     * @param id 料理ID
     * @return レコード
     */
    @Override
    public List<CookFoodTblDTO> getCookFoodListByCookId(String id) {
        return cookFoodTblMapperImpl.selectByCookId(id);
    }

    /**
     * 登録処理を行います。
     *
     * @param cookMstDTO  料理情報
     * @param cookFoodTblDTOList  料理食材リスト
     * @return 新規ID
     */
    @Override
    public String insert(CookMstDTO cookMstDTO, List<CookFoodTblDTO> cookFoodTblDTOList) {
        // 最大値IDを取得
        String id = cookMstMapperImpl.selectMaxId();

        // 最大値ID + 1
        String maxId = format(idToValue(id, COOK_ID_PREFIX) + 1, COOK_ID_FORMAT);

        // 最大ID作成確認
        if (isNull(maxId)) {
            throw new ToCookRuntimeException(HttpStatus.INTERNAL_SERVER_ERROR, ResultCode.C104);
        }

        // 最大IDを格納
        cookMstDTO.setCookId(maxId);

        // 登録処理
        cookMstMapperImpl.insertSelective(cookMstDTO);

        // 料理食材リスト存在確認
        if (isNotEmpty(cookFoodTblDTOList)) {

            // 食材IDの存在確認
            foodIdCheck(cookFoodTblDTOList);

            // 最大値IDを取得
            Integer cfId = idToValue(cookFoodTblMapperImpl.selectMaxId(), CF_ID_PREFIX);

            // 料理食材登録処理
            cookFoodTblMapperImpl.insertBulk(Streams.mapWithIndex(
                    cookFoodTblDTOList.stream(), (cftDTO, index) -> {

                        // 最大値料理食材ID + 1
                        String maxCookFoodId = format(cfId + index + 1, CF_ID_FORMAT);

                        // 最大ID作成確認
                        if (isNull(maxCookFoodId)) {
                            throw new ToCookRuntimeException(HttpStatus.INTERNAL_SERVER_ERROR, ResultCode.C104);
                        }

                        CookFoodTblDTO insertCftDTO = new CookFoodTblDTO();
                        insertCftDTO.setCookFoodId(maxCookFoodId);
                        insertCftDTO.setCookId(cookMstDTO.getCookId());
                        insertCftDTO.setFoodId(cftDTO.getFoodId());
                        insertCftDTO.setUnitValue(cftDTO.getUnitValue());
                        insertCftDTO.setUserId(cookMstDTO.getUserId());

                        return insertCftDTO;
                    }
            ).collect(Collectors.toList()));
        }

        return maxId;
    }

    /**
     * 更新処理を行います。
     *
     * @param cookMstDTO  料理情報
     * @param cookFoodTblDTOList  料理食材リスト
     */
    @Override
    public void edit(CookMstDTO cookMstDTO, List<CookFoodTblDTO> cookFoodTblDTOList) {
        // レコード取得
        CookMstDTO getCookMstDTO = cookMstMapperImpl.selectByIdAndUserId(cookMstDTO.getCookId(), getLoginUserId());

        // レコードが存在しない
        if (isNull(getCookMstDTO)) throw new ToCookRuntimeException(HttpStatus.NOT_FOUND, ResultCode.C105);

        if (!eq(getCookMstDTO.getVersion(), cookMstDTO.getVersion())) {
            // バージョン不一致による排他制御
            throw new ToCookRuntimeException(HttpStatus.CONFLICT, ResultCode.C107);
        }

        if (cookMstMapperImpl.updateById(cookMstDTO) == 0) {
            // 不正なDB処理
            throw new ToCookRuntimeException(HttpStatus.INTERNAL_SERVER_ERROR, ResultCode.C109);
        }

        // 削除処理
        cookFoodTblMapperImpl.deleteByCookId(cookMstDTO.getCookId());

        // 料理食材リスト存在確認
        if (isNotEmpty(cookFoodTblDTOList)) {

            // 食材IDの存在確認
            foodIdCheck(cookFoodTblDTOList);

            // 最大値IDを取得
            Integer cfId = idToValue(cookFoodTblMapperImpl.selectMaxId(), CF_ID_PREFIX);

            // 料理食材登録処理
            cookFoodTblMapperImpl.insertBulk(Streams.mapWithIndex(
                    cookFoodTblDTOList.stream(), (cftDTO, index) -> {

                        // 最大値料理食材ID + 1
                        String maxCookFoodId = format(cfId + index + 1, CF_ID_FORMAT);

                        // 最大ID作成確認
                        if (isNull(maxCookFoodId)) {
                            throw new ToCookRuntimeException(HttpStatus.INTERNAL_SERVER_ERROR, ResultCode.C104);
                        }

                        CookFoodTblDTO insertCftDTO = new CookFoodTblDTO();
                        insertCftDTO.setCookFoodId(maxCookFoodId);
                        insertCftDTO.setCookId(cookMstDTO.getCookId());
                        insertCftDTO.setFoodId(cftDTO.getFoodId());
                        insertCftDTO.setUnitValue(cftDTO.getUnitValue());
                        insertCftDTO.setUserId(cookMstDTO.getUserId());

                        return insertCftDTO;
                    }
            ).collect(Collectors.toList()));
        }
    }

    /**
     * 削除処理を行います。
     *
     * @param id 料理ID
     */
    @Override
    public void delete(String id) {
        // 削除レコード取得
        CookMstDTO cookMstDTO = cookMstMapperImpl.selectByIdAndUserId(id, getLoginUserId());

        // 存在チェック
        if (isNull(cookMstDTO)) {
            // 存在しない場合
            throw new ToCookRuntimeException(HttpStatus.NOT_FOUND, ResultCode.C105);
        }

        // 削除処理
        if (cookMstMapperImpl.deleteByPrimaryKey(id) != 1) {
            // 不正なSQL文の検知
            throw new ToCookRuntimeException(HttpStatus.INTERNAL_SERVER_ERROR, ResultCode.C109);
        }
    }

    /**
     * 存在しない食材IDがないか
     * 重複した食材IDがないか確認を行います。
     *
     * @param cookFoodTblDTOList 料理食材リスト
     */
    private void foodIdCheck(List<CookFoodTblDTO> cookFoodTblDTOList) {
        // 食材IDリストを取得
        List<String> foodIdList = cookFoodTblDTOList.stream()
                .map(dto -> dto.getFoodId()).collect(Collectors.toList());

        // 食材IDが存在するか確認
        if (isNotEmpty(foodIdList)
                && foodMstMapperImpl.selectByIdList(foodIdList).size() != cookFoodTblDTOList.size()) {
            // レコードが存在しない
            throw new ToCookRuntimeException(HttpStatus.NOT_FOUND, ResultCode.C105);
        }

        // 重複レコードチェック
        if (foodIdList.size() != cookFoodTblDTOList.stream()
                .map(dto -> dto.getFoodId()).distinct().collect(Collectors.toList()).size()) {
            // 重複レコードが存在する
            throw new ToCookRuntimeException(HttpStatus.BAD_REQUEST, ResultCode.C106);
        }
    }

}
