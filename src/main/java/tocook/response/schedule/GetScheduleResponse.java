package tocook.response.schedule;

import java.time.*;
import java.util.*;

import lombok.*;
import lombok.experimental.*;

/**
 * API028 - スケジュール検索機能 レスポンス
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetScheduleResponse {

    /** 総件数 */
    private int total;

    /** リスト */
    private List<Schedule> scheduleList;

    @Data
    @NoArgsConstructor
    @Accessors(chain = true)
    public static class Schedule {

        /** スケジュールID */
        private String scheduleId;

        /** 日付 */
        private LocalDate scheduleDate;

        /** 時間 */
        private Short scheduleTime;

        /** スケジュールバージョン */
        private Short scheduleVersion;

        /** 料理ID */
        private String cookId;

        /** 料理名 */
        private String cookName;

        /** 金額 */
        private String cookProcess;

        /** 手順 */
        private Long cookPrice;

        /** 共有フラグ */
        private Boolean cookFlg;

        /** 料理バージョン */
        private Short cookVersion;
    }

}
