package tocook.request.schedule;

import javax.validation.constraints.*;

import lombok.*;
import tocook.validations.*;

/**
 * API028 - スケジュール検索機能 リクエスト
 */
@Data
public class GetScheduleRequest {

    /** スケジュールID */
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

    /** ソート項目 */
    @Pattern(regexp = "[1234]")
    private String orderBy = "1";

    /** 昇順／降順 */
    @Pattern(regexp = "[12]")
    private String ascOrDesc = "1";

    /** 最大取得数 */
    @IntegerType(min = 1, max = 2147483647)
    private String limit = "100";

    /** オフセット */
    @IntegerType(min = 0, max = 2147483647)
    private String offset = "0";
}
