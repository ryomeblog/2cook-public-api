package tocook.service.routine;

import static tocook.common.CommonUtils.*;
import static tocook.common.Functions.*;
import static tocook.constant.ApiErrorMessage.*;
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
 * ルーティン管理機能 サービス
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class RoutineServiceImpl implements RoutineService {

    /** ルーティンテーブルMapperImpl */
    @Autowired
    private RoutineMstMapperImpl routineMstMapperImpl;

    /** 料理テーブルMapperImpl */
    @Autowired
    private CookMstMapperImpl cookMstMapperImpl;

    /** ルーティン食事テーブルMapperImpl */
    @Autowired
    private RoutineEatTblMapperImpl routineEatTblMapperImpl;

    /** エラー変数名 */
    String ERR_NAME = "routineEatList";

    /**
     * レコード件数を取得し、返却します。
     *
     * @param routineId ルーティンID
     * @param routineName ルーティン名
     * @return レコード件数
     */
    @Override
    public Integer getListCount(String routineId, String routineName) {
        return routineMstMapperImpl.selectListCount(routineId, routineName, getLoginUserId());
    }

    /**
     * リストを取得し、返却します。
     *
     * @param routineId ルーティンID
     * @param routineName ルーティン名
     * @param orderBy ソート項目
     * @param ascOrDesc 昇順／降順
     * @param limit 最大取得数
     * @param offset オフセット
     * @return リスト
     */
    @Override
    public List<RoutineMstDTO> getList(
            String routineId,
            String routineName,
            Integer orderBy,
            Integer ascOrDesc,
            Integer limit,
            Integer offset) {
        return routineMstMapperImpl.selectList(
                routineId,
                routineName,
                getLoginUserId(),
                orderBy,
                ascOrDesc,
                limit,
                offset);
    }

    /**
     * IDをキーにレコードを取得し、返却します。
     *
     * @param id ルーティンID
     * @return レコード
     */
    @Override
    public RoutineMstDTO getListById(String id) {
        return routineMstMapperImpl.selectById(id, getLoginUserId());
    }

    /**
     * ルーティンIDをキーにルーティン食事テーブルのレコードを取得し、返却します。
     *
     * @param id ルーティンID
     * @return ルーティン食事リスト
     */
    @Override
    public List<RoutineEatTblDTO> getRoutineEatListByRoutineId(String id) {
        return routineEatTblMapperImpl.selectByRoutineId(id);
    }

    /**
     * 登録処理を行います。
     *
     * @param routineMstDTO  ルーティン情報
     * @param routineEatTblDTOList  ルーティン食事リスト
     * @return 新規ID
     */
    @Override
    public String insert(RoutineMstDTO routineMstDTO, List<RoutineEatTblDTO> routineEatTblDTOList) {
        // 最大値IDを取得
        String id = routineMstMapperImpl.selectMaxId();

        // 最大値ID + 1
        String maxId = format(idToValue(id, ROUTINE_ID_PREFIX) + 1, ROUTINE_ID_FORMAT);

        // 最大ID作成確認
        if (isNull(maxId)) {
            throw new ToCookRuntimeException(HttpStatus.INTERNAL_SERVER_ERROR, ResultCode.C104);
        }

        // 最大IDを格納
        routineMstDTO.setRoutineId(maxId);

        // 登録処理
        routineMstMapperImpl.insertSelective(routineMstDTO);

        // ルーティン食事リスト存在確認
        if (isNotEmpty(routineEatTblDTOList)) {

            // 料理IDの存在確認
            cookIdCheck(routineEatTblDTOList);

            // ルーティン最大日数超過確認
            for (RoutineEatTblDTO dto : routineEatTblDTOList) {
                if (routineMstDTO.getRoutineMaxday() < dto.getRoutineDay()) {
                    // ルーティン最大日数超過
                    RequestParameterException e = new RequestParameterException();
                    e.addErrorParams(ERR_NAME, dto.getRoutineDay(), MSG_API_ERR_005);
                    throw e;
                }
            }

            // 最大値IDを取得
            Integer reId = idToValue(routineEatTblMapperImpl.selectMaxId(), ROUTINE_EAT_ID_PREFIX);

            // ルーティン食事リスト登録処理
            routineEatTblMapperImpl.insertBulk(Streams.mapWithIndex(
                    routineEatTblDTOList.stream(), (retDTO, index) -> {

                        // 最大値ルーティン食事ID + 1
                        String maxRoutineEatTblId = format(reId + index + 1, ROUTINE_EAT_ID_FORMAT);

                        // 最大ID作成確認
                        if (isNull(maxRoutineEatTblId)) {
                            throw new ToCookRuntimeException(HttpStatus.INTERNAL_SERVER_ERROR, ResultCode.C104);
                        }

                        RoutineEatTblDTO insertRetDTO = new RoutineEatTblDTO();
                        insertRetDTO.setRoutineEatId(maxRoutineEatTblId);
                        insertRetDTO.setRoutineId(routineMstDTO.getRoutineId());
                        insertRetDTO.setRoutineDay(retDTO.getRoutineDay());
                        insertRetDTO.setRoutineTime(retDTO.getRoutineTime());
                        insertRetDTO.setCookId(retDTO.getCookId());
                        insertRetDTO.setUserId(routineMstDTO.getUserId());
                        return insertRetDTO;
                    }).collect(Collectors.toList()));
        }

        return maxId;
    }

    /**
     * 更新処理を行います。
     *
     * @param routineMstDTO  ルーティン情報
     * @param routineEatTblDTOList  ルーティン食事リスト
     */
    @Override
    public void edit(RoutineMstDTO routineMstDTO, List<RoutineEatTblDTO> routineEatTblDTOList) {
        // レコード取得
        RoutineMstDTO getRoutineMstDTO = routineMstMapperImpl.selectById(routineMstDTO.getRoutineId(), getLoginUserId());

        // レコードが存在しない
        if (isNull(getRoutineMstDTO)) throw new ToCookRuntimeException(HttpStatus.NOT_FOUND, ResultCode.C105);

        if (!eq(getRoutineMstDTO.getVersion(), routineMstDTO.getVersion())) {
            // バージョン不一致による排他制御
            throw new ToCookRuntimeException(HttpStatus.CONFLICT, ResultCode.C107);
        }

        if (routineMstMapperImpl.updateByPrimaryKeySelective(routineMstDTO) == 0) {
            // 不正なDB処理
            throw new ToCookRuntimeException(HttpStatus.INTERNAL_SERVER_ERROR, ResultCode.C109);
        }

        // 削除処理
        routineEatTblMapperImpl.deleteByRoutineId(routineMstDTO.getRoutineId());

        // ルーティン食事リスト存在確認
        if (isNotEmpty(routineEatTblDTOList)) {

            // 料理IDの存在確認
            cookIdCheck(routineEatTblDTOList);

            // ルーティン最大日数超過確認
            for (RoutineEatTblDTO dto : routineEatTblDTOList) {
                if (routineMstDTO.getRoutineMaxday() < dto.getRoutineDay()) {
                    // ルーティン最大日数超過
                    RequestParameterException e = new RequestParameterException();
                    e.addErrorParams(ERR_NAME, dto.getRoutineDay(), MSG_API_ERR_005);
                    throw e;
                }
            }

            // 最大値IDを取得
            Integer reId = idToValue(routineEatTblMapperImpl.selectMaxId(), ROUTINE_EAT_ID_FORMAT);

            // ルーティン食事リスト登録処理
            routineEatTblMapperImpl.insertBulk(Streams.mapWithIndex(
                    routineEatTblDTOList.stream(), (retDTO, index) -> {

                        // 最大値ルーティン食事ID + 1
                        String maxRoutineEatTblId = format(reId + index + 1, ROUTINE_EAT_ID_FORMAT);

                        // 最大ID作成確認
                        if (isNull(maxRoutineEatTblId)) {
                            throw new ToCookRuntimeException(HttpStatus.INTERNAL_SERVER_ERROR, ResultCode.C104);
                        }

                        RoutineEatTblDTO insertRetDTO = new RoutineEatTblDTO();
                        insertRetDTO.setRoutineEatId(maxRoutineEatTblId);
                        insertRetDTO.setRoutineId(routineMstDTO.getRoutineId());
                        insertRetDTO.setRoutineDay(retDTO.getRoutineDay());
                        insertRetDTO.setRoutineTime(retDTO.getRoutineTime());
                        insertRetDTO.setCookId(retDTO.getCookId());
                        insertRetDTO.setUserId(routineMstDTO.getUserId());
                        return insertRetDTO;
                    }).collect(Collectors.toList()));
        }
    }

    /**
     * 削除処理を行います。
     *
     * @param id ルーティンID
     */
    @Override
    public void delete(String id) {
        // 削除レコード取得
        RoutineMstDTO routineMstDTO = routineMstMapperImpl.selectById(id, getLoginUserId());

        // 存在チェック
        if (isNull(routineMstDTO)) {
            // 存在しない場合
            throw new ToCookRuntimeException(HttpStatus.NOT_FOUND, ResultCode.C105);
        }

        // 削除処理
        if (routineMstMapperImpl.deleteByPrimaryKey(id) != 1) {
            // 不正なSQL文の検知
            throw new ToCookRuntimeException(HttpStatus.INTERNAL_SERVER_ERROR, ResultCode.C109);
        }
    }

    /**
     * 存在しない料理IDがないか
     * 重複した料理IDがないか確認を行います。
     *
     * @param routineEatTblDTOList ルーティン食事リスト
     */
    private void cookIdCheck(List<RoutineEatTblDTO> routineEatTblDTOList) {
        // 料理IDリストを取得
        List<String> cookIdList = routineEatTblDTOList.stream().map(
                dto -> dto.getCookId()).distinct().collect(Collectors.toList());

        // 料理IDが存在するか確認
        if (isNotEmpty(cookIdList)
                && cookMstMapperImpl.selectByIdList(
                        cookIdList,
                        getLoginUserId()).size() != cookIdList.size()) {
            // レコードが存在しない
            throw new ToCookRuntimeException(HttpStatus.NOT_FOUND, ResultCode.C105);
        }
    }

}
