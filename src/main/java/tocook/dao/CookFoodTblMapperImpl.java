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
public interface CookFoodTblMapperImpl extends CookFoodTblMapper {

    /**
     * 最大IDを取得します。
     *
     * @return ID
     */
    String selectMaxId();

    /**
     * レコードを取得します。
     *
     * @param cookId 料理ID
     * @return リスト
     */
    List<CookFoodTblDTO> selectByCookId(@Param("cookId") String foodId);

    /**
     * 料理食材リストをまとめて追加します。
     *
     * @param cookFoodList 料理食材リスト
     * @return 件数
     */
    Integer insertBulk(@Param("cookFoodList") List<CookFoodTblDTO> cookFoodList);

    /**
     * 料理IDをキーにレコードを削除します。
     *
     * @param cookId 料理ID
     * @return 削除件数
     */
    int deleteByCookId(@Param("cookId") String cookId);
}
