package tocook.response.account;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * API003 - ユーザ検索機能 レスポンス
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetAccountResponse {

    /** 総件数 */
    private int total;

    /** ユーザリスト */
    private List<User> userList;

    @Data
    @Accessors(chain = true)
    public static class User {

        /** ユーザID */
        private String userId;

        /** ユーザ名 */
        private String userName;

        /** 権限 */
        private String authority;

        /** バージョン */
        private Short version;

    }
}
