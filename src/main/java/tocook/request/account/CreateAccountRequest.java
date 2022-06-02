package tocook.request.account;

import javax.validation.constraints.*;

import lombok.*;
import tocook.validations.*;

/**
 * API005 - ユーザ登録機能 リクエスト
 */
@Data
public class CreateAccountRequest {

    /** パスワード */
    @NotNull
    @HalfAlphanumeric
    @Size(min = 1, max = 20)
    private String password;

    /** ユーザ名 */
    @NotNull
    @Size(min = 1, max = 100)
    private String userName;

    /** 秘密の質問 */
    @NotNull
    @HalfAlphanumeric
    @Size(min = 7, max = 7)
    private String secretId;

    /** 秘密の答え */
    @NotNull
    @HalfAlphanumeric
    @Size(min = 1, max = 200)
    private String secretAnswer;
}
