package tocook.response.account;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * API005 - ユーザ登録機能 レスポンス
 */
@Data
@AllArgsConstructor
public class CreateAccountResponse {

    /** ユーザID */
    private String userId;
}
