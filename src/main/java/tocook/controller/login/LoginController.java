package tocook.controller.login;

import static tocook.common.CommonUtils.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import tocook.constant.ApiUri;
import tocook.constant.ResultCode;
import tocook.controller.BaseController;
import tocook.dto.generator.CookUserMst;
import tocook.request.login.LoginRequest;
import tocook.response.ApiResponseOptional;
import tocook.response.login.LoginResponse;
import tocook.service.login.LoginService;

/**
 * API001 - ログイン機能 コントローラ
 */
@RestController
public class LoginController extends BaseController {

    /** ログインサービス */
    @Autowired
    private LoginService loginService;

    @PostMapping(ApiUri.LOGIN)
    public ApiResponseOptional<LoginResponse> login(
            @RequestBody @Validated LoginRequest body, BindingResult bindingResult,
            HttpServletRequest request, HttpServletResponse response) {
        // バリデーションチェック
        valid(bindingResult);

        // ログイン処理
        CookUserMst cookUserMst = loginService.login(body.getUserId(), body.getPassword());

        // レスポンス返却
        return new ApiResponseOptional<>(ResultCode.C000, toLoginResponse(cookUserMst));
    }

    LoginResponse toLoginResponse(CookUserMst cookUserMst) {
        // レスポンス作成
        return new LoginResponse(
                cookUserMst.getUserId(),
                cookUserMst.getUserName(),
                getRole(cookUserMst.getAuthority()),
                cookUserMst.getVersion());
    }

}
