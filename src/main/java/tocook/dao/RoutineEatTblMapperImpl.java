package tocook.dao;

import java.util.*;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.*;

import tocook.dao.generator.*;
import tocook.dto.*;

/**
 * 「cook_food_tbl」テーブル用DAO
 */

@Repository
public interface RoutineEatTblMapperImpl extends RoutineEatTblMapper {

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
     * @return リスト
     */
    List<RoutineEatTblDTO> selectByRoutineId(@Param("routineId") String routineId);

    /**
     * ルーティン料理リストをまとめて追加します。
     *
     * @param routineEatList ルーティン料理リスト
     * @return 件数
     */
    Integer insertBulk(@Param("routineEatList") List<RoutineEatTblDTO> routineEatList);

    /**
     * ルーティンIDをキーにレコードを削除します。
     *
     * @param routineEatId ルーティンID
     * @return 削除件数
     */
    int deleteByRoutineId(@Param("routineId") String routineId);
}
