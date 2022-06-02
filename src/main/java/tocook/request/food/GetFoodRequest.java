package tocook.request.food;

import javax.validation.constraints.*;

import lombok.*;
import tocook.validations.*;

/**
 * API018 - 食材検索機能 リクエスト
 */
@Data
public class GetFoodRequest {

    /** 食材ID */
    @Size(min = 12, max = 12)
    private String foodId;

    /** 食材名 */
    @Size(min = 1, max = 100)
    private String foodName;

    /** 単位 */
    @HalfAlphanumeric
    @Size(min = 7, max = 7)
    private String foodUnit;

    /** ソート項目 */
    @Pattern(regexp = "[123]")
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
