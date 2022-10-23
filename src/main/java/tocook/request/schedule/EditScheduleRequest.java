package tocook.request.schedule;

import javax.validation.constraints.*;

import lombok.*;
import tocook.validations.*;

/**
 * API031 - スケジュール編集機能 リクエスト
 */
@Data
public class EditScheduleRequest {

    /** スケジュールID */
    @NotNull
    @Size(min = 18, max = 18)
    private String scheduleId;

    /** 日付 */
    @DateType
    @Size(min = 10, max = 10)
    private String scheduleDate;

    /** 時間 */
    @ShortType(min = 0, max = 2)
    private String scheduleTime;

    /** 料理ID */
    @Size(min = 12, max = 12)
    private String cookId;

    /** バージョン */
    @NotNull
    @ShortType(min = 0)
    private String version = "0";
}
