package tocook.request.routine;

import java.util.*;

import javax.validation.*;
import javax.validation.constraints.*;

import lombok.*;
import tocook.validations.*;

/**
 * API026 - ルーティン編集機能 リクエスト
 */
@Data
public class EditRoutineRequest {

    /** ルーティンID */
    @NotNull
    @Size(min = 15, max = 15)
    private String routineId;

    /** ルーティン名 */
    @Size(min = 1, max = 200)
    private String routineName;

    /** ルーティン最大日数 */
    @ShortType(min = 1, max = 7)
    private String routineMaxday;

    /** バージョン */
    @NotNull
    @ShortType(min = 0)
    private String version = "0";

    /** ルーティン食事リスト */
    @Valid
    private List<RoutineEat> routineEatList;

    @Data
    @AllArgsConstructor
    public static class RoutineEat {

        /** 料理ID */
        @NotNull
        @Size(min = 12, max = 12)
        private String cookId;

        /** ルーティン日数 */
        @ShortType(min = 1, max = 7)
        private String routineDay;

        /** ルーティン時間 */
        @ShortType(min = 0, max = 2)
        private String routineTime;
    }
}
