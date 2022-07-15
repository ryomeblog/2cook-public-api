package tocook.service.schedule;

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
 * スケジュール管理機能 サービス
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class ScheduleServiceImpl implements ScheduleService {

    /** スケジュールテーブルMapperImpl */
    @Autowired
    private ScheduleTblMapperImpl scheduleTblMapperImpl;

    /** 料理テーブルMapperImpl */
    @Autowired
    private CookMstMapperImpl cookMstMapperImpl;

    /** ルーティン食事テーブルMapperImpl */
    @Autowired
    private RoutineEatTblMapperImpl routineEatTblMapperImpl;

    /**
     * レコード件数を取得し、返却します。
     *
     * @param scheduleId スケジュールID
     * @param scheduleDate 日付
     * @param scheduleTime 時間
     * @param cookId 料理ID
     * @return レコード件数
     */
    @Override
    public Integer getListCount(
            String scheduleId,
            Date scheduleDate,
            Short scheduleTime,
            String cookId) {
        return scheduleTblMapperImpl.selectListCount(
                scheduleId,
                scheduleDate,
                scheduleTime,
                cookId,
                getLoginUserId());
    }

    /**
     * リストを取得し、返却します。
     *
     * @param scheduleId スケジュールID
     * @param scheduleDate 日付
     * @param scheduleTime 時間
     * @param cookId 料理ID
     * @param orderBy ソート項目
     * @param ascOrDesc 昇順／降順
     * @param limit 最大取得数
     * @param offset オフセット
     * @return リスト
     */
    @Override
    public List<ScheduleTblDTO> getList(
            String scheduleId,
            Date scheduleDate,
            Short scheduleTime,
            String cookId,
            Integer orderBy,
            Integer ascOrDesc,
            Integer limit,
            Integer offset) {
        return scheduleTblMapperImpl.selectList(
                scheduleId,
                scheduleDate,
                scheduleTime,
                cookId,
                getLoginUserId(),
                orderBy,
                ascOrDesc,
                limit,
                offset);
    }

    /**
     * IDをキーにレコードを取得し、返却します。
     *
     * @param id スケジュールID
     * @return レコード
     */
    @Override
    public ScheduleTblDTO getListById(String id) {
        return scheduleTblMapperImpl.selectById(id, getLoginUserId());
    }

    /**
     * 日付をキーにレコードを取得し、返却します。
     *
     * @param scheduleDate 日付
     * @return リスト
     */
    @Override
    public List<ScheduleTblDTO> getListByDate(Date scheduleDate) {
        return scheduleTblMapperImpl.selectByDate(scheduleDate, getLoginUserId());
    }

    /**
     * 登録処理を行います。
     *
     * @param scheduleTblDTO  スケジュール情報
     * @param cookIdList  料理IDリスト
     * @return 新規ID
     */
    @Override
    public void insert(ScheduleTblDTO scheduleTblDTO, List<String> cookIdList) {

        // 料理IDリスト存在確認
        if (isNotEmpty(cookIdList)) {

            // 料理IDの存在確認
            cookIdCheck(cookIdList);

            // 最大値IDを取得
            Integer maxScheduleId = idToValue(scheduleTblMapperImpl.selectMaxId(), SCHEDULE_ID_PREFIX);

            // スケジュールリスト登録処理
            scheduleTblMapperImpl.insertBulk(Streams.mapWithIndex(
                    cookIdList.stream(), (cookId, index) -> {

                        // 最大値スケジュールID + 1
                        String addMaxScheduleId = format(maxScheduleId + index + 1, SCHEDULE_ID_FORMAT);

                        // 最大ID作成確認
                        if (isNull(addMaxScheduleId)) {
                            throw new ToCookRuntimeException(HttpStatus.INTERNAL_SERVER_ERROR, ResultCode.C104);
                        }

                        ScheduleTblDTO insertScheduleTblDTO = new ScheduleTblDTO();
                        insertScheduleTblDTO.setScheduleId(addMaxScheduleId);
                        insertScheduleTblDTO.setScheduleDate(scheduleTblDTO.getScheduleDate());
                        insertScheduleTblDTO.setScheduleTime(scheduleTblDTO.getScheduleTime());
                        insertScheduleTblDTO.setCookId(cookId);
                        insertScheduleTblDTO.setUserId(scheduleTblDTO.getUserId());
                        return insertScheduleTblDTO;
                    }).collect(Collectors.toList()));
        }
    }

    /**
     * ルーティンから登録処理を行います。
     *
     * @param routineId  ルーティンID
     * @return 新規ID
     */
    @Override
    public void insertRoutineForSchedule(String routineId, Date scheduleDate) {

        // ルーティン食事リスト取得
        List<RoutineEatTblDTO> routineEatTblDTOList = routineEatTblMapperImpl.selectByRoutineId(routineId);

        // 料理IDリスト存在確認
        if (isNotEmpty(routineEatTblDTOList)) {

            // 最大値IDを取得
            Integer maxScheduleId = idToValue(scheduleTblMapperImpl.selectMaxId(), SCHEDULE_ID_PREFIX);

            // スケジュールリスト登録処理
            scheduleTblMapperImpl.insertBulk(Streams.mapWithIndex(
                    routineEatTblDTOList.stream(), (routineEatTblDTO, index) -> {

                        // 最大値スケジュールID + 1
                        String addMaxScheduleId = format(maxScheduleId + index + 1, SCHEDULE_ID_FORMAT);

                        // 最大ID作成確認
                        if (isNull(addMaxScheduleId)) {
                            throw new ToCookRuntimeException(HttpStatus.INTERNAL_SERVER_ERROR, ResultCode.C104);
                        }

                        ScheduleTblDTO insertScheduleTblDTO = new ScheduleTblDTO();
                        insertScheduleTblDTO.setScheduleId(addMaxScheduleId);
                        insertScheduleTblDTO.setScheduleDate(
                                addDayDate(scheduleDate, routineEatTblDTO.getRoutineDay()));
                        insertScheduleTblDTO.setScheduleTime(routineEatTblDTO.getRoutineTime());
                        insertScheduleTblDTO.setCookId(routineEatTblDTO.getCookId());
                        insertScheduleTblDTO.setUserId(getLoginUserId());
                        return insertScheduleTblDTO;
                    }).collect(Collectors.toList()));
        }
    }

    /**
     * 更新処理を行います。
     *
     * @param scheduleTblDTO  スケジュール情報
     */
    @Override
    public void edit(ScheduleTblDTO scheduleTblDTO) {
        // レコード取得
        ScheduleTblDTO getScheduleTblDTO = scheduleTblMapperImpl.selectById(
                scheduleTblDTO.getScheduleId(),
                scheduleTblDTO.getUserId());

        // レコードが存在しない
        if (isNull(getScheduleTblDTO)) throw new ToCookRuntimeException(HttpStatus.NOT_FOUND, ResultCode.C105);

        // 料理ID存在チェック
        if (isNull(cookMstMapperImpl.selectById(
                scheduleTblDTO.getCookId(),
                scheduleTblDTO.getUserId()))) {
            // 料理IDが存在しない
            throw new ToCookRuntimeException(HttpStatus.NOT_FOUND, ResultCode.C105);
        }

        if (!eq(getScheduleTblDTO.getVersion(), scheduleTblDTO.getVersion())) {
            // バージョン不一致による排他制御
            throw new ToCookRuntimeException(HttpStatus.CONFLICT, ResultCode.C107);
        }

        if (scheduleTblMapperImpl.updateById(scheduleTblDTO) == 0) {
            // 不正なDB処理
            throw new ToCookRuntimeException(HttpStatus.INTERNAL_SERVER_ERROR, ResultCode.C109);
        }
    }

    /**
     * 削除処理を行います。
     *
     * @param id スケジュールID
     */
    @Override
    public void delete(String id) {
        // 削除レコード取得
        ScheduleTblDTO scheduleTblDTO = scheduleTblMapperImpl.selectById(id, getLoginUserId());

        // 存在チェック
        if (isNull(scheduleTblDTO)) {
            // 存在しない場合
            throw new ToCookRuntimeException(HttpStatus.NOT_FOUND, ResultCode.C105);
        }

        // 削除処理
        if (scheduleTblMapperImpl.deleteByPrimaryKey(id) != 1) {
            // 不正なSQL文の検知
            throw new ToCookRuntimeException(HttpStatus.INTERNAL_SERVER_ERROR, ResultCode.C109);
        }
    }

    /**
     * 削除処理を行います。
     *
     * @param date 日付
     */
    @Override
    public void deleteByDate(Date date) {
        // 削除レコード取得
        List<ScheduleTblDTO> scheduleTblDTO = scheduleTblMapperImpl.selectByDate(date, getLoginUserId());

        // 存在チェック
        if (isNull(scheduleTblDTO)) {
            // 存在しない場合
            throw new ToCookRuntimeException(HttpStatus.NOT_FOUND, ResultCode.C105);
        }

        // 削除処理
        scheduleTblMapperImpl.deleteByDate(date, getLoginUserId());
    }

    /**
     * 存在しない料理IDがないか確認を行います。
     *
     * @param requestCookIdList 料理IDリスト
     */
    private void cookIdCheck(List<String> requestCookIdList) {
        // 料理IDリストを取得（重複削除）
        List<String> cookIdList = requestCookIdList.stream().distinct().collect(Collectors.toList());

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
