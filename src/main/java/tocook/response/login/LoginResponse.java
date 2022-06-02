package tocook.response.login;

import lombok.*;

/**
 * API001 - ログイン機能 レスポンス
 */
@Data
@AllArgsConstructor
public class LoginResponse {

    /** ユーザID */
    private String userId;

    /** ユーザ名 */
    private String userName;

    /** 権限 */
    private String authority;

    /** バージョン */
    private Short version;
}
