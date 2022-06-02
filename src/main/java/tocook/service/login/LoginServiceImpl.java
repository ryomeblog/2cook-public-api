package tocook.service.login;

import static tocook.common.CommonUtils.*;
import static tocook.common.Functions.*;
import static tocook.constant.Constant.*;

import java.util.*;

import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.security.authentication.*;
import org.springframework.security.core.*;
import org.springframework.security.core.context.*;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.*;
import org.springframework.transaction.annotation.*;

import tocook.constant.*;
import tocook.dao.*;
import tocook.dto.generator.*;
import tocook.exception.*;

/**
 * 認証機能 サービス
 */
@Service
@Transactional(rollbackFor=Exception.class)
public class LoginServiceImpl implements LoginService {

    /** AuthenticationManager */
    @Autowired
    private AuthenticationManager authManager;

    /** ユーザテーブルMapperImpl */
    @Autowired
    private CookUserMstMapperImpl cookUserMstMapper;

    /**
     * ログイン処理を行います。
     *
     * @param userId  ユーザID
     * @param password パスワード
     */
    @Override
    public CookUserMst login(String userId, String password) {
        // ユーザ情報取得
        CookUserMst user = cookUserMstMapper.selectByUserId(userId);

        // ユーザIDのNULLチェック
        if (isNull(user)) { throw new UsernameNotFoundException("ユーザは存在しません"); }

        // アカウントロックチェック
        if (user.getLock()) { throw new ToCookRuntimeException(HttpStatus.FORBIDDEN, ResultCode.C103); }

        // パスワードが一致するか確認
        if(!passwordMatches(password, user.getPassword())) {
            // パスワード不一致

            // ログイン失敗回数をカウントアップ
            user.setErrorPassword(toShort(user.getErrorPassword() + 1));
            if(LOGIN_ERROR_MAX_COUNT < user.getErrorPassword()) {
                // ログイン失敗回数を初期化
                user.setErrorPassword(toShort(0));
                // アカウントロック
                user.setLock(true);
                user.setUpdateDatetime(new Date());
            }

            throw new UsernameNotFoundException("パスワードが不正です");
        }else {
            // パスワード一致

            // ログイン失敗回数を初期化
            user.setErrorPassword(toShort(0));
        }

        // アカウント情報更新
        cookUserMstMapper.updateUser(user);

        // ログイン処理
        Authentication auth = new UsernamePasswordAuthenticationToken(
                userId,
                password);
        Authentication result = authManager.authenticate(auth);
        SecurityContextHolder.getContext().setAuthentication(result);

        return user;
    }

    /**
     * ログアウト処理を行います。
     *
     * @param userId  ユーザID
     * @param password パスワード
     */
    @Override
    public void logout() {

        // ログアウト処理
        SecurityContextHolder.clearContext();
    }

    /**
     * ログインしているユーザ情報を取得します。
     *
     * @return ユーザ情報
     */
    @Override
    public CookUserMst getLoginUserId() {
        return cookUserMstMapper.selectByUserId(SecurityContextHolder.getContext().getAuthentication().getName());
    }

}
