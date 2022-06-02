package tocook.controller.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import tocook.constant.ApiUri;
import tocook.constant.ResultCode;
import tocook.controller.BaseController;
import tocook.response.ApiResponse;
import tocook.service.login.LoginService;

/**
 * API002 - ログアウト機能 コントローラ
 */
@RestController
public class LogoutController extends BaseController {

    /** ログインサービス */
    @Autowired
    private LoginService loginService;

    @PostMapping(ApiUri.LOGOUT)
    public ApiResponse logout() {

        // ログアウト処理
        loginService.logout();

        // レスポンス返却
        return new ApiResponse(ResultCode.C000);
    }
}
