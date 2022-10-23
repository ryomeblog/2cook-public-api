package tocook.request.schedule;

import javax.validation.constraints.*;

import lombok.*;
import tocook.validations.*;

/**
 * スケジュール管理機能 パスパラメータ
 */
@Data
public class ScheduleDatePathParameter {

    /** 日付 */
    @NotNull
    @NumberType
    @Size(min = 8, max = 8)
    private String scheduleDate;
}
