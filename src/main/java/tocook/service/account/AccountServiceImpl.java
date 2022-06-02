package tocook.service.account;

import static tocook.common.CommonUtils.*;
import static tocook.common.Functions.*;
import static tocook.constant.Constant.*;
import static tocook.constant.IdFormatterConstant.*;

import java.util.*;

import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.stereotype.*;

import tocook.constant.*;
import tocook.dao.*;
import tocook.dto.generator.*;
import tocook.exception.*;

/**
 * ユーザ管理機能 サービス
 */
@Service
public class AccountServiceImpl implements AccountService {

    /** ユーザテーブルMapperImpl */
    @Autowired
    private CookUserMstMapperImpl cookUserMstMapper;

    /** 汎用マスタテーブルMapperImpl */
    @Autowired
    private CommonMstMapperImpl commonMstMapperImpl;

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
    @Override
    public Integer getUserListCount(String userId, String userName, Byte authority) {
        return cookUserMstMapper.selectUserListCount(userId, userName, authority);
    }

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
    @Override
    public List<CookUserMst> getUserList(
            String userId,
            String userName,
            Byte authority,
            Integer orderBy,
            Integer ascOrDesc,
            Integer limit,
            Integer offset) {
        return cookUserMstMapper.selectUserList(userId, userName, authority, orderBy, ascOrDesc, limit, offset);
    }

    /**
     * ユーザIDをキーにユーザ情報を取得し、返却します。
     *
     * @return ユーザ情報
     */
    @Override
    public CookUserMst getUserListByUserId() {
        return cookUserMstMapper.selectByUserId(getLoginUserId());
    }

    /**
     * 秘密の質問情報を取得し、返却します。
     *
     * @return 秘密の質問リスト
     */
    @Override
    public List<CommonMst> getQuestion() {
        return commonMstMapperImpl.selectByGroup(COM_GROUP_01);
    }

    /**
     * アカウント作成処理を行います。
     *
     * @param cookUserMst ユーザ情報
     * @return maxUserId 最大ユーザID
     */
    @Override
    public String insertUser(CookUserMst cookUserMst) {

        // 秘密の質問存在チェック
        secretIdCheck(cookUserMst.getSecretId());

        // 最大値ユーザIDを取得
        String userId = cookUserMstMapper.selectMaxUserId();

        // 最大値ユーザID + 1
        String maxUserId = format(idToValue(userId, USER_ID_PREFIX) + 1, USER_ID_FORMAT);

        // ユーザID作成確認
        if (isNull(maxUserId)) {
            throw new ToCookRuntimeException(HttpStatus.INTERNAL_SERVER_ERROR, ResultCode.C104);
        }

        // 最大のユーザIDを格納
        cookUserMst.setUserId(maxUserId);

        // アカウント追加処理
        cookUserMstMapper.insertSelective(cookUserMst);

        return maxUserId;
    }

    /**
     * アカウント編集処理を行います。
     *
     * @param cookUserMst ユーザ情報
     */
    @Override
    public void editUser(CookUserMst cookUserMst) {

        // ユーザ取得
        CookUserMst getCookUserMst = cookUserMstMapper.selectByUserId(cookUserMst.getUserId());

        if (isNull(getCookUserMst)) {
            // ユーザが存在しない
            throw new ToCookRuntimeException(HttpStatus.NOT_FOUND, ResultCode.C105);
        }

        // 秘密の質問存在チェック
        secretIdCheck(nvl(cookUserMst.getSecretId(), getCookUserMst.getSecretId()));

        if (!eq(getCookUserMst.getVersion(), cookUserMst.getVersion())) {
            // バージョン不一致による排他制御
            throw new ToCookRuntimeException(HttpStatus.CONFLICT, ResultCode.C107);
        }

        if (cookUserMstMapper.updateUser(cookUserMst) == 0) {
            // 不正なDB処理
            throw new ToCookRuntimeException(HttpStatus.INTERNAL_SERVER_ERROR, ResultCode.C109);
        }
    }

    /**
     * アカウントの秘密の質問と答えが同じユーザのパスワードを変更します。
     *
     * @param cookUserMst ユーザ情報
     */
    @Override
    public void editQuestion(CookUserMst cookUserMst) {

        // 秘密の質問存在チェック
        secretIdCheck(cookUserMst.getSecretId());

        // ユーザ取得
        CookUserMst getCookUserMst = cookUserMstMapper.selectByUserId(cookUserMst.getUserId());

        if (isNull(getCookUserMst)) {
            // ユーザが存在しない
            throw new ToCookRuntimeException(HttpStatus.NOT_FOUND, ResultCode.C105);
        }

        // アカウントロックチェック
        if (getCookUserMst.getLock()) { throw new ToCookRuntimeException(HttpStatus.FORBIDDEN, ResultCode.C103); }

        if (!eq(cookUserMst.getSecretId(), getCookUserMst.getSecretId())
                || !passwordMatches(cookUserMst.getSecretAnswer(), getCookUserMst.getSecretAnswer())) {
            // 秘密の質問と答えが一致しない
            throw new ToCookRuntimeException(HttpStatus.NOT_FOUND, ResultCode.C105);
        }

        // バージョン情報を格納
        cookUserMst.setVersion(getCookUserMst.getVersion());

        if (cookUserMstMapper.updatePassword(cookUserMst) == 0) {
            // 不正なDB処理
            throw new ToCookRuntimeException(HttpStatus.INTERNAL_SERVER_ERROR, ResultCode.C109);
        }
    }

    /**
     * アカウント削除処理を行います。
     */
    @Override
    public void deleteUser() {

        // ユーザID取得
        String userId = getLoginUserId();

        // ユーザ情報取得
        CookUserMst cookUserMst = cookUserMstMapper.selectByUserId(userId);

        // ユーザの存在チェック
        if (isNull(cookUserMst)) {
            // ユーザが存在しない
            throw new ToCookRuntimeException(HttpStatus.NOT_FOUND, ResultCode.C105);
        }

        // 削除処理
        if (!cookUserMstMapper.deleteUser(userId)) {
            // 不正なSQL文の検知
            throw new ToCookRuntimeException(HttpStatus.INTERNAL_SERVER_ERROR, ResultCode.C109);
        }
    }

    /**
     * 秘密の質問IDが存在するか確認を行います。
     *
     * @param secretId 秘密の質問ID
     */
    private void secretIdCheck(String secretId) {
        // 食材単位レコード存在確認
        if (isNotNull(secretId)) {
            // レコード存在確認
            if (isNull(commonMstMapperImpl.selectByPrimaryKey(secretId))) {
                throw new ToCookRuntimeException(HttpStatus.NOT_FOUND, ResultCode.C105);
            }
        }
    }

}
