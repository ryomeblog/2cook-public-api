package tocook.controller.account;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RestController;

import tocook.constant.ApiUri;
import tocook.constant.ResultCode;
import tocook.controller.BaseController;
import tocook.response.ApiResponse;
import tocook.service.account.AccountService;

/**
 * API007 - ユーザ削除機能 コントローラ
 */
@RestController
public class DeleteAccountController extends BaseController {

    /** アカウントサービス */
    @Autowired
    private AccountService accountService;

    @DeleteMapping(ApiUri.ACCOUNT)
    public ApiResponse deleteById() {

        // ユーザ削除
        accountService.deleteUser();

        // レスポンス返却
        return new ApiResponse(ResultCode.C000);
    }

}
