package tocook.request.cook;

import java.util.*;

import javax.validation.*;
import javax.validation.constraints.*;

import org.hibernate.validator.constraints.*;

import lombok.*;
import tocook.validations.*;

/**
 * API012 - 料理登録機能 リクエスト
 */
@Data
public class CreateCookRequest {

    /** 料理名 */
    @NotNull
    @Size(min = 1, max = 200)
    private String cookName;

    /** 手順 */
    @NotNull
    @Size(min = 1, max = 4000)
    private String cookProcess;

    /** 金額 */
    @NotNull
    @LongType
    private String cookPrice = "0";

    /** 動画URL */
    @URL
    @Size(min = 0, max = 4000)
    private String cookMovie;

    /** その他URL */
    @URL
    @Size(min = 0, max = 4000)
    private String cookUrl;

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
