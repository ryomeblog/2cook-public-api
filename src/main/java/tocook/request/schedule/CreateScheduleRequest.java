package tocook.request.schedule;

import java.util.*;

import javax.validation.*;
import javax.validation.constraints.*;

import lombok.*;
import tocook.validations.*;

/**
 * API030 - スケジュール登録機能 リクエスト
 */
@Data
public class CreateScheduleRequest {

    /** 日付 */
    @NotNull
    @DateType
    @Size(min = 10, max = 10)
    private String scheduleDate;

    /** 時間 */
    @NotNull
    @ShortType(min = 0, max = 2)
    private String scheduleTime;

    /** 料理IDリスト */
    @Valid
    private List<@NotNull @Size(min = 12, max = 12) String> cookIdList;
}
