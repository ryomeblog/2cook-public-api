package tocook.dao;

import java.util.*;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.*;

import tocook.dao.generator.*;
import tocook.dto.*;

/**
 * 「schedule_mst」テーブル用DAO
 */

@Repository
public interface ScheduleTblMapperImpl extends ScheduleTblMapper {

    /**
     * 最大IDを取得します。
     *
     * @return ID
     */
    String selectMaxId();

    /**
     * レコードを取得します。
     *
     * @param scheduleId スケジュールID
     * @param userId ユーザID
     * @return レコード
     */
    ScheduleTblDTO selectById(
            @Param("scheduleId") String scheduleId,
            @Param("userId") String userId);

    /**
     * レコードを取得します。
     *
     * @param scheduleId スケジュールID
     * @param userId ユーザID
     * @return リスト
     */
    List<ScheduleTblDTO> selectByDate(
            @Param("scheduleDate") Date scheduleDate,
            @Param("userId") String userId);

    /**
     * リスト件数を取得します。
     *
     * @param scheduleId スケジュールID
     * @param scheduleDate 日付
     * @param scheduleTime 時間
     * @param cookId 料理ID
     * @param userId ユーザID
     * @return リスト件数
     */
    Integer selectListCount(
            @Param("scheduleId") String scheduleId,
            @Param("scheduleDate") Date scheduleDate,
            @Param("scheduleTime") Short scheduleTime,
            @Param("cookId") String cookId,
            @Param("userId") String userId);

    /**
     * リストを取得します。
     *
     * @param scheduleId スケジュールID
     * @param scheduleDate 日付
     * @param scheduleTime 時間
     * @param cookId 料理ID
     * @param userId ユーザID
     * @param orderBy ソート項目
     * @param ascOrDesc 昇順／降順
     * @param limit 最大取得数
     * @param offset オフセット
     * @return リスト
     */
    List<ScheduleTblDTO> selectList(
            @Param("scheduleId") String scheduleId,
            @Param("scheduleDate") Date scheduleDate,
            @Param("scheduleTime") Short scheduleTime,
            @Param("cookId") String cookId,
            @Param("userId") String userId,
            @Param("orderBy") Integer orderBy,
            @Param("ascOrDesc") Integer ascOrDesc,
            @Param("limit") Integer limit,
            @Param("offset") Integer offset);

    /**
     * スケジュールリストをまとめて追加します。
     *
     * @param scheduleTblList スケジュールリスト
     * @return 件数
     */
    Integer insertBulk(@Param("scheduleTblList") List<ScheduleTblDTO> scheduleTblList);

    /**
     * レコードを更新します。
     *
     * @param scheduleMstDTO スケジュール情報
     * @return 更新件数
     */
    Integer updateById(ScheduleTblDTO scheduleMstDTO);

    /**
     * 日付と一致したレコードを削除します。
     *
     * @param userId ユーザID
     * @param scheduleDate 日付
     * @return 削除件数
     */
    Integer deleteByDate(
            @Param("scheduleDate") Date scheduleDate,
            @Param("userId") String userId);
}
