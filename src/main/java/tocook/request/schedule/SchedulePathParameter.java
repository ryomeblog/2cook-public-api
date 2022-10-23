package tocook.request.schedule;

import javax.validation.constraints.*;

import lombok.*;

/**
 * スケジュール管理機能 パスパラメータ
 */
@Data
public class SchedulePathParameter {

    /** スケジュールID */
    @NotNull
    @Size(min = 18, max = 18)
    private String scheduleId;
}
