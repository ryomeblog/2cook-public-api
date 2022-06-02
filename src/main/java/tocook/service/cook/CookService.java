package tocook.service.cook;

import java.util.*;

import org.springframework.stereotype.*;

import tocook.dto.*;

/**
 * 料理管理機能 サービス
 */
@Service
public interface CookService {

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
    Integer getListCount(
            String cookId,
            String cookName,
            String userId,
            String selectUserId,
            Long minPrice,
            Long maxPrice,
            List<String> foodIdList);

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
    List<CookMstDTO> getList(
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
            Integer offset);

    /**
     * IDをキーにレコードを取得し、返却します。
     *
     * @param id 料理ID
     * @param userId ユーザID
     * @return レコード
     */
    CookMstDTO getListById(String id, String userId);

    /**
     * 料理IDをキーに料理食材テーブルのレコードを取得し、返却します。
     *
     * @param id 料理ID
     * @return 料理食材リスト
     */
    List<CookFoodTblDTO> getCookFoodListByCookId(String id);

    /**
     * 登録処理を行います。
     *
     * @param cookMstDTO  料理情報
     * @param cookFoodTblDTOList  料理食材リスト
     * @return 新規ID
     */
    String insert(CookMstDTO cookMstDTO, List<CookFoodTblDTO> cookFoodTblDTOList);

    /**
     * 更新処理を行います。
     *
     * @param cookMstDTO 料理情報
     * @param cookFoodTblDTOList  料理食材リスト
     */
    void edit(CookMstDTO cookMstDTO, List<CookFoodTblDTO> cookFoodTblDTOList);

    /**
     * 削除処理を行います。
     *
     * @param id 料理ID
     */
    void delete(String id);

}
