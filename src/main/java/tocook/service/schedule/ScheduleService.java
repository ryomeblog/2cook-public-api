package tocook.service.schedule;

import java.util.*;

import org.springframework.stereotype.*;

import tocook.dto.*;

/**
 * スケジュール管理機能 サービス
 */
@Service
public interface ScheduleService {

    /**
     * レコード件数を取得し、返却します。
     *
     * @param scheduleId スケジュールID
     * @param scheduleDate 日付
     * @param scheduleTime 時間
     * @param cookId 料理ID
     * @return レコード件数
     */
    Integer getListCount(
            String scheduleId,
            Date scheduleDate,
            Short scheduleTime,
            String cookId);

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
    List<ScheduleTblDTO> getList(
            String scheduleId,
            Date scheduleDate,
            Short scheduleTime,
            String cookId,
            Integer orderBy,
            Integer ascOrDesc,
            Integer limit,
            Integer offset);

    /**
     * IDをキーにレコードを取得し、返却します。
     *
     * @param id スケジュールID
     * @return レコード
     */
    ScheduleTblDTO getListById(String id);

    /**
     * 日付をキーにレコードを取得し、返却します。
     *
     * @param scheduleDate 日付
     * @return リスト
     */
    public List<ScheduleTblDTO> getListByDate(Date scheduleDate);

    /**
     * 登録処理を行います。
     *
     * @param scheduleTblDTO  スケジュール情報
     * @param cookIdList  料理IDリスト
     * @return 新規ID
     */
    void insert(ScheduleTblDTO scheduleTblDTO, List<String> cookIdList);

    /**
     * ルーティンから登録処理を行います。
     *
     * @param routineId  ルーティンID
     * @param scheduleDate  日付
     * @return 新規ID
     */
    void insertRoutineForSchedule(String routineId, Date scheduleDate);

    /**
     * 更新処理を行います。
     *
     * @param scheduleTblDTO  スケジュール情報
     */
    void edit(ScheduleTblDTO scheduleTblDTO);

    /**
     * 削除処理を行います。
     *
     * @param id スケジュールID
     */
    void delete(String id);

    /**
     * 削除処理を行います。
     *
     * @param date 日付
     */
    void deleteByDate(Date date);

}
