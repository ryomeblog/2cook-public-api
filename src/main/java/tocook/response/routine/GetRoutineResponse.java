package tocook.response.routine;

import java.util.*;

import lombok.*;
import lombok.experimental.*;

/**
 * API023 - ルーティン検索機能 レスポンス
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetRoutineResponse {

    /** 総件数 */
    private int total;

    /** リスト */
    private List<Routine> routineList;

    @Data
    @NoArgsConstructor
    @Accessors(chain = true)
    public static class Routine {

        /** ルーティンID */
        private String routineId;

        /** ルーティン名 */
        private String routineName;

        /** ルーティン最大日数 */
        private Short routineMaxday;

        /** バージョン */
        private Short version;
    }

}
