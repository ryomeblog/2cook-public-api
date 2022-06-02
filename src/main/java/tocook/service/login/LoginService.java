package tocook.service.login;

import org.springframework.stereotype.Service;

import tocook.dto.generator.CookUserMst;

/**
 * 認証機能 サービス
 */
@Service
public interface LoginService {

    /**
     * ログイン処理を行います。
     *
     * @param userId  ユーザID
     * @param password パスワード
     */
    CookUserMst login(String userId, String password);

    /**
     * ログアウト処理を行います。
     */
    void logout();

    /**
     * ログインしているユーザ情報を取得します。
     *
     * @return ユーザ情報
     */
    CookUserMst getLoginUserId();

}
