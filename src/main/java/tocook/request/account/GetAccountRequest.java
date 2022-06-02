package tocook.request.account;

import javax.validation.constraints.*;

import lombok.*;
import tocook.validations.*;

/**
 * API003 - ユーザ検索機能 リクエスト
 */
@Data
public class GetAccountRequest {

    /** ユーザID */
    @Size(min = 12, max = 12)
    @HalfAlphanumeric
    private String userId;

    /** ユーザ名 */
    @Size(min = 1, max = 100)
    private String userName;

    /** 権限 */
    @Pattern(regexp = "[01]")
    private String authority;

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
