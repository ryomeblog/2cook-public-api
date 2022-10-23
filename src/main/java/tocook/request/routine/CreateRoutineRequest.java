package tocook.request.routine;

import java.util.*;

import javax.validation.*;
import javax.validation.constraints.*;

import lombok.*;
import tocook.validations.*;

/**
 * API025 - ルーティン登録機能 リクエスト
 */
@Data
public class CreateRoutineRequest {

    /** ルーティン名 */
    @NotNull
    @Size(min = 1, max = 200)
    private String routineName;

    /** ルーティン最大日数 */
    @NotNull
    @ShortType(min = 1, max = 7)
    private String routineMaxday;

    /** ルーティン食事リスト */
    @Valid
    private List<RoutineEat> routineEatList;

    @Data
    @AllArgsConstructor
    public static class RoutineEat {

        /** 料理ID */
        @Size(min = 12, max = 12)
        private String cookId;

        /** ルーティン日数 */
        @LongType(min = 1, max = 7)
        private String routineDay;

        /** ルーティン時間 */
        @ShortType(min = 0, max = 2)
        private String routineTime;
    }
}
