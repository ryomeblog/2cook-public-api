package tocook.request.schedule;

import javax.validation.constraints.*;

import lombok.*;
import tocook.validations.*;

/**
 * API035 - ルーティンスケジュール登録機能 パラメータ
 */
@Data
public class RoutineForSchedulePathParameter {

    /** ルーティンID */
    @NotNull
    @Size(min = 15, max = 15)
    private String routineId;

    /** 日付 */
    @DateType
    @Size(min = 10, max = 10)
    private String scheduleDate;
}
