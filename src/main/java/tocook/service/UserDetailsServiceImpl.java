package tocook.service;

import static tocook.common.CommonUtils.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import tocook.dao.generator.CookUserMstMapper;
import tocook.dto.generator.CookUserMst;

@Service
@Component("UserDetailsServiceImpl")
public class UserDetailsServiceImpl implements UserDetailsService {

    /** ユーザテーブルMapperImpl */
    @Autowired
    private CookUserMstMapper cookUserMstMapper;

    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {

        // ユーザIDのNULLチェック
        if (username == null) { throw new UsernameNotFoundException("ユーザIDが不正です"); }

        // ユーザ情報取得
        CookUserMst cookUser = cookUserMstMapper.selectByPrimaryKey(username);

        // ユーザIDのNULLチェック
        if (cookUser == null) { throw new UsernameNotFoundException("ユーザは存在しません"); }

        // 権限を取得
        String role = getRole(cookUser.getAuthority());

        return new User(cookUser.getUserId(), cookUser.getPassword(), AuthorityUtils.createAuthorityList(role));
    }

}
