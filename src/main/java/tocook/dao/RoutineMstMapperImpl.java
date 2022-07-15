package tocook.dao;

import java.util.*;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.*;

import tocook.dao.generator.*;
import tocook.dto.*;

/**
 * 「routine_mst」テーブル用DAO
 */

@Repository
public interface RoutineMstMapperImpl extends RoutineMstMapper {

    /**
     * 最大IDを取得します。
     *
     * @return ID
     */
    String selectMaxId();

    /**
     * レコードを取得します。
     *
     * @param routineId ルーティンID
     * @param userId ユーザID
     * @return レコード
     */
    RoutineMstDTO selectById(
            @Param("routineId") String routineId,
            @Param("userId") String userId);

    /**
     * リスト件数を取得します。
     *
     * @param routineId 料理ID
     * @param routineName 料理名
     * @param userId ユーザID
     * @return リスト件数
     */
    Integer selectListCount(
            @Param("routineId") String routineId,
            @Param("routineName") String routineName,
            @Param("userId") String userId);

    /**
     * リストを取得します。
     *
     * @param routineId 料理ID
     * @param routineName 料理名
     * @param userId ユーザID
     * @param orderBy ソート項目
     * @param ascOrDesc 昇順／降順
     * @param limit 最大取得数
     * @param offset オフセット
     * @return リスト
     */
    List<RoutineMstDTO> selectList(
            @Param("routineId") String routineId,
            @Param("routineName") String routineName,
            @Param("userId") String userId,
            @Param("orderBy") Integer orderBy,
            @Param("ascOrDesc") Integer ascOrDesc,
            @Param("limit") Integer limit,
            @Param("offset") Integer offset);
}
