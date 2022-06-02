package tocook.request.cook;

import java.util.*;

import javax.validation.*;
import javax.validation.constraints.*;

import lombok.*;
import tocook.validations.*;

/**
 * API010 - 料理検索機能 リクエスト
 */
@Data
public class GetCookRequest {

    /** 料理ID */
    @Size(min = 12, max = 12)
    private String cookId;

    /** 料理名 */
    @Size(min = 1, max = 200)
    private String cookName;

    /** ユーザID */
    @Size(min = 12, max = 12)
    private String userId;

    /** 最小金額 */
    @LongType(min = 0)
    private String minPrice = "0";

    /** 最大金額 */
    @LongType(min = 0)
    private String maxPrice;

    /** 食材IDリスト */
    @Valid
    private List<@Size(min = 12, max = 12) String> foodIdList;

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
