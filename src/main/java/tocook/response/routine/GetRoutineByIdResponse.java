package tocook.response.routine;

import java.util.*;

import lombok.*;
import lombok.experimental.*;

/**
 * API024 - ルーティン詳細検索機能 レスポンス
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class GetRoutineByIdResponse {

    /** ルーティンID */
    private String routineId;

    /** ルーティン名 */
    private String routineName;

    /** ルーティン最大日数 */
    private Short routineMaxday;

    /** バージョン */
    private Short version;

    /** ルーティン食事リスト */
    private List<RoutineEat> routineEatList;

    @Data
    @NoArgsConstructor
    @Accessors(chain = true)
    public static class RoutineEat {

        /** ルーティン食事ID */
        private String routineEatId;

        /** ルーティンID */
        private String routineId;

        /** ルーティン日数 */
        private Short routineDay;

        /** ルーティン時間 */
        private Short routineTime;

        /** 料理ID */
        private String cookId;

        /** その他2 */
        private String cookName;

        /** 共有フラグ */
        private Boolean cookFlg;

        /** ユーザID */
        private String userId;

        /** バージョン */
        private Short version;
    }



}
