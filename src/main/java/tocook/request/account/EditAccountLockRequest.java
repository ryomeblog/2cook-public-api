package tocook.request.account;

import javax.validation.constraints.*;

import lombok.*;
import tocook.validations.*;

/**
 * API008 - ユーザロック機能 リクエスト
 */
@Data
public class EditAccountLockRequest {

    /** ユーザID */
    @NotNull
    @Size(min = 12, max = 12)
    private String userId;

    /** ロック */
    @BooleanType
    private String lock;

    /** バージョン */
    @NotNull
    @ShortType(min = 0)
    private String version = "0";
}
