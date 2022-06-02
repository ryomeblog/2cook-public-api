package tocook.service.account;

import java.util.*;

import org.springframework.stereotype.*;

import tocook.dto.generator.*;

/**
 * ユーザ管理機能 サービス
 */
@Service
public interface AccountService {

    /**
     * ユーザ件数を取得し、返却します。
     *
     * @param userId ユーザID
     * @param userName ユーザ名
     * @param authority 権限
     * @param limit 最大取得数
     * @param offset オフセット
     * @return ユーザ件数
     */
    Integer getUserListCount(
            String userId,
            String userName,
            Byte authority);

    /**
     * ユーザ情報を取得し、返却します。
     *
     * @param userId ユーザID
     * @param userName ユーザ名
     * @param authority 権限
     * @param orderBy ソート項目
     * @param ascOrDesc 昇順／降順
     * @param limit 最大取得数
     * @param offset オフセット
     * @return ユーザ情報リスト
     */
    List<CookUserMst> getUserList(
            String userId,
            String userName,
            Byte authority,
            Integer orderBy,
            Integer ascOrDesc,
            Integer limit,
            Integer offset);

    /**
     * ユーザIDをキーにユーザ情報を取得し、返却します。
     *
     * @return ユーザ情報
     */
    CookUserMst getUserListByUserId();

    /**
     * 秘密の質問情報を取得し、返却します。
     *
     * @return 秘密の質問リスト
     */
    List<CommonMst> getQuestion();

    /**
     * アカウント作成処理を行います。
     *
     * @param cookUserMst ユーザ情報
     * @return 最大ユーザID
     */
    String insertUser(CookUserMst cookUserMst);

    /**
     * アカウント編集処理を行います。
     *
     * @param cookUserMst ユーザ情報
     */
    void editUser(CookUserMst cookUserMst);

    /**
     * アカウントの秘密の質問と答えが同じユーザのパスワードを変更します。
     *
     * @param cookUserMst ユーザ情報
     */
    void editQuestion(CookUserMst cookUserMst);

    /**
     * アカウント削除処理を行います。
     */
    void deleteUser();

}
