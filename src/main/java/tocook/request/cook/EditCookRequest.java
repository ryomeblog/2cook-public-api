package tocook.request.cook;

import java.util.*;

import javax.validation.*;
import javax.validation.constraints.*;

import org.hibernate.validator.constraints.*;

import lombok.*;
import tocook.validations.*;

/**
 * API013 - 料理編集機能 リクエスト
 */
@Data
public class EditCookRequest {

    /** 料理ID */
    @NotNull
    @Size(min = 12, max = 12)
    private String cookId;

    /** 料理名 */
    @Size(min = 1, max = 200)
    private String cookName;

    /** 手順 */
    @Size(min = 1, max = 4000)
    private String cookProcess;

    /** 金額 */
    @LongType
    private String cookPrice;

    /** 動画URL */
    @URL
    @Size(min = 0, max = 4000)
    private String cookMovie;

    /** その他URL */
    @URL
    @Size(min = 0, max = 4000)
    private String cookUrl;

    /** 共有フラグ */
    @BooleanType
    private String cookFlg = "false";

    /** バージョン */
    @NotNull
    @ShortType(min = 0)
    private String version = "0";

    /** 食材リスト */
    @Valid
    private List<Food> foodList;

    @Data
    @AllArgsConstructor
    public static class Food {

        /** 食材ID */
        @Size(min = 12, max = 12)
        private String foodId;

        /** 単位 */
        @LongType(min = 0)
        private String unitValue;
    }
}
