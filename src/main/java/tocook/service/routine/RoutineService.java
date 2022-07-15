package tocook.service.routine;

import java.util.*;

import org.springframework.stereotype.*;

import tocook.dto.*;

/**
 * ルーティン管理機能 サービス
 */
@Service
public interface RoutineService {

    /**
     * レコード件数を取得し、返却します。
     *
     * @param routineId ルーティンID
     * @param routineName ルーティン名
     * @return レコード件数
     */
    Integer getListCount(String routineId, String routineName);

    /**
     * リストを取得し、返却します。
     *
     * @param routineId ルーティンID
     * @param routineName ルーティン名
     * @param orderBy ソート項目
     * @param ascOrDesc 昇順／降順
     * @param limit 最大取得数
     * @param offset オフセット
     * @return リスト
     */
    List<RoutineMstDTO> getList(
            String routineId,
            String routineName,
            Integer orderBy,
            Integer ascOrDesc,
            Integer limit,
            Integer offset);

    /**
     * IDをキーにレコードを取得し、返却します。
     *
     * @param id ルーティンID
     * @return レコード
     */
    RoutineMstDTO getListById(String id);

    /**
     * ルーティンIDをキーにルーティン食事テーブルのレコードを取得し、返却します。
     *
     * @param id ルーティンID
     * @return ルーティン食事リスト
     */
    List<RoutineEatTblDTO> getRoutineEatListByRoutineId(String id);

    /**
     * 登録処理を行います。
     *
     * @param routineMstDTO  ルーティン情報
     * @param routineEatTblDTOList  ルーティン食事リスト
     * @return 新規ID
     */
    String insert(RoutineMstDTO routineMstDTO, List<RoutineEatTblDTO> routineEatTblDTOList);

    /**
     * 更新処理を行います。
     *
     * @param routineMstDTO  ルーティン情報
     * @param routineEatTblDTOList  ルーティン食事リスト
     */
    void edit(RoutineMstDTO routineMstDTO, List<RoutineEatTblDTO> routineEatTblDTOList);

    /**
     * 削除処理を行います。
     *
     * @param id ルーティンID
     */
    void delete(String id);

}
