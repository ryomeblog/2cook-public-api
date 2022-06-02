package tocook.dao;

import java.util.*;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.*;

import tocook.dao.generator.*;
import tocook.dto.*;

/**
 * 「cook_mst」テーブル用DAO
 */

@Repository
public interface CookMstMapperImpl extends CookMstMapper {

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
     * @param userId ユーザID
     * @return レコード
     */
    CookMstDTO selectById(@Param("cookId") String cookId, @Param("userId") String userId);

    /**
     * レコードを取得します。
     *
     * @param cookId 料理ID
     * @param userId ユーザID
     * @return レコード
     */
    CookMstDTO selectByIdAndUserId(
            @Param("cookId") String cookId,
            @Param("userId") String userId);

    /**
     * リスト件数を取得します。
     *
     * @param cookId 料理ID
     * @param cookName 料理名
     * @param userId ユーザID
     * @param selectUserId 検索ユーザID
     * @param minPrice 最小金額
     * @param maxPrice 最大金額
     * @param foodIdList 食材IDリスト
     * @return リスト件数
     */
    Integer selectListCount(
            @Param("cookId") String cookId,
            @Param("cookName") String cookName,
            @Param("userId") String userId,
            @Param("selectUserId") String selectUserId,
            @Param("minPrice") Long minPrice,
            @Param("maxPrice") Long maxPrice,
            @Param("foodIdList") List<String> foodIdList);

    /**
     * リストを取得します。
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
    List<CookMstDTO> selectList(
            @Param("cookId") String cookId,
            @Param("cookName") String cookName,
            @Param("userId") String userId,
            @Param("selectUserId") String selectUserId,
            @Param("minPrice") Long minPrice,
            @Param("maxPrice") Long maxPrice,
            @Param("foodIdList") List<String> foodIdList,
            @Param("orderBy") Integer orderBy,
            @Param("ascOrDesc") Integer ascOrDesc,
            @Param("limit") Integer limit,
            @Param("offset") Integer offset);

    /**
     * 料理IDリストに一致するリストを取得します。
     *
     * @param cookIdList 料理IDリスト
     * @param userId ユーザID
     * @return リスト
     */
    List<FoodMstDTO> selectByIdList(
            @Param("cookIdList") List<String> cookIdList,
            @Param("userId") String userId);

    /**
     * レコードを更新します。
     *
     * @param cookMst 料理情報
     * @return 更新件数
     */
    int updateById(CookMstDTO cookMstDTO);
}
