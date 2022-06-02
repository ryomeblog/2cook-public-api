package tocook.request.food;

import javax.validation.constraints.*;

import lombok.*;
import tocook.validations.*;

/**
 * API020 - 食材登録機能 リクエスト
 */
@Data
public class CreateFoodRequest {

    /** 食材名 */
    @NotNull
    @Size(min = 1, max = 100)
    private String foodName;

    /** 単位 */
    @NotNull
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
}
