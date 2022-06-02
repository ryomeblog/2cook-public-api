package tocook.dao;

import java.util.*;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.*;

import tocook.dao.generator.*;
import tocook.dto.*;
import tocook.dto.generator.*;

/**
 * 「food_mst」テーブル用DAO
 */

@Repository
public interface FoodMstMapperImpl extends FoodMstMapper {

    /**
     * 最大IDを取得します。
     *
     * @return ID
     */
    String selectMaxId();

    /**
     * レコードを取得します。
     *
     * @param foodId 食材ID
     * @return レコード
     */
    FoodMstDTO selectById(@Param("foodId") String foodId);

    /**
     * レコードを取得します。
     *
     * @param foodId 食材ID
     * @param userId ユーザID
     * @return レコード
     */
    FoodMstDTO selectByIdAndUserId(
            @Param("foodId") String foodId,
            @Param("userId") String userId);

    /**
     * 食材IDリストに一致するリストを取得します。
     *
     * @param foodIdList 食材IDリスト
     * @return リスト
     */
    List<FoodMstDTO> selectByIdList(@Param("foodIdList") List<String> foodIdList);

    /**
     * 食材名と単位に一致するレコードを取得します。
     *
     * @param foodName 食材名
     * @param foodUnit 単位
     * @return リスト
     */
    FoodMstDTO selectByFoodNameAndFoodUnit(
            @Param("foodName") String foodName,
            @Param("foodUnit") String foodUnit);

    /**
     * リスト件数を取得します。
     *
     * @param foodId 食材ID
     * @param foodName 食材名
     * @param foodUnit 単位
     * @param limit 最大取得数
     * @param offset オフセット
     * @return リスト件数
     */
    Integer selectListCount(
            @Param("foodId") String foodId,
            @Param("foodName") String foodName,
            @Param("foodUnit") String foodUnit);

    /**
     * リストを取得します。
     *
     * @param foodId 食材ID
     * @param foodName 食材名
     * @param foodUnit 単位
     * @param orderBy ソート項目
     * @param ascOrDesc 昇順／降順
     * @param limit 最大取得数
     * @param offset オフセット
     * @return リスト
     */
    List<FoodMstDTO> selectList(
            @Param("foodId") String foodId,
            @Param("foodName") String foodName,
            @Param("foodUnit") String foodUnit,
            @Param("orderBy") Integer orderBy,
            @Param("ascOrDesc") Integer ascOrDesc,
            @Param("limit") Integer limit,
            @Param("offset") Integer offset);

    /**
     * レコードを更新します。
     *
     * @param foodMst 食材情報
     * @return 更新件数
     */
    int updateById(FoodMst foodMst);
}
