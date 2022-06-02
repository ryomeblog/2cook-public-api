package tocook.request.account;

import javax.validation.constraints.*;

import lombok.*;
import tocook.validations.*;

/**
 * API006 - ユーザ編集機能 リクエスト
 */
@Data
public class EditAccountRequest {

    /** パスワード */
    @HalfAlphanumeric
    @Size(min = 1, max = 20)
    private String password;

    /** ユーザ名 */
    @Size(min = 1, max = 100)
    private String userName;

    /** バージョン */
    @NotNull
    @ShortType(min = 0)
    private String version = "0";
}

