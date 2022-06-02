package tocook.request.login;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;
import tocook.validations.*;

/**
 * API001 - ログイン機能 リクエスト
 */
@Data
public class LoginRequest {

    /** ユーザID */
    @NotNull
    @HalfAlphanumeric
    @Size(min = 12, max = 12)
    private String userId;

    /** パスワード */
    @NotNull
    @HalfAlphanumeric
    @Size(min = 1, max = 20)
    private String password;
}
