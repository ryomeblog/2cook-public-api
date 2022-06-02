package tocook.service.food;

import java.util.*;

import org.springframework.stereotype.*;

import tocook.dto.*;

/**
 * 食材管理機能 サービス
 */
@Service
public interface FoodService {

    /**
     * 件数を取得し、返却します。
     *
     * @param foodId 食材ID
     * @param foodName 食材名
     * @param authority 単位
     * @param limit 最大取得数
     * @param offset オフセット
     * @return レコード件数
     */
    Integer getListCount(
            String foodId,
            String foodName,
            String foodUnit);

    /**
     * リストを取得し、返却します。
     *
     * @param foodId 食材ID
     * @param foodName 食材名
     * @param authority 単位
     * @param orderBy ソート項目
     * @param ascOrDesc 昇順／降順
     * @param limit 最大取得数
     * @param offset オフセット
     * @return リスト
     */
    List<FoodMstDTO> getList(
            String foodId,
            String foodName,
            String foodUnit,
            Integer orderBy,
            Integer ascOrDesc,
            Integer limit,
            Integer offset);

    /**
     * IDをキーにレコードを取得し、返却します。
     *
     * @param id 食材ID
     * @return レコード
     */
    FoodMstDTO getListById(String id);

    /**
     * 登録処理を行います。
     *
     * @param foodMst  食材情報
     * @return 新規ID
     */
    String insert(FoodMstDTO foodMst);

    /**
     * 更新処理を行います。
     *
     * @param foodMst 食材情報
     */
    void edit(FoodMstDTO foodMst);

    /**
     * 削除処理を行います。
     *
     * @param id 食材ID
     */
    void delete(String id);

}
