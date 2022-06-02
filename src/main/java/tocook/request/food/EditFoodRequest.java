package tocook.request.food;

import javax.validation.constraints.*;

import lombok.*;
import tocook.validations.*;

/**
 * API021 - 食材編集機能 リクエスト
 */
@Data
public class EditFoodRequest {

    /** 食材ID */
    @NotNull
    @HalfAlphanumeric
    @Size(min = 12, max = 12)
    private String foodId;

    /** 食材名 */
    @Size(min = 1, max = 100)
    private String foodName;

    /** 単位 */
    @HalfAlphanumeric
    @Size(min = 7, max = 7)
    private String foodUnit;

    /** その他1 */
    @Size(min = 1, max = 100)
    private String others1;

    /** その他2 */
    @Size(min = 1, max = 100)
    private String others2;

    /** その他3 */
    @Size(min = 1, max = 100)
    private String others3;

    /** その他4 */
    @Size(min = 1, max = 100)
    private String others4;

    /** その他5 */
    @Size(min = 1, max = 100)
    private String others5;

    /** バージョン */
    @NotNull
    @ShortType(min = 0)
    private String version = "0";
}
