package tocook.controller.account;

import static tocook.common.CommonUtils.*;
import static tocook.common.Functions.*;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import tocook.constant.ApiUri;
import tocook.constant.ResultCode;
import tocook.controller.BaseController;
import tocook.dto.generator.CookUserMst;
import tocook.request.account.EditAccountRequest;
import tocook.response.ApiResponse;
import tocook.service.account.AccountService;

/**
 * API006 - ユーザ編集機能 コントローラ
 */
@RestController
public class EditAccountController extends BaseController {

    /** アカウントサービス */
    @Autowired
    private AccountService accountService;

    @PutMapping(ApiUri.ACCOUNT)
    public ApiResponse edit(
            @RequestBody @Validated EditAccountRequest request, BindingResult bindingResult) {
        // バリデーションチェック
        valid(bindingResult);

        // ユーザ更新
        accountService.editUser((createDto(request)));

        // レスポンス返却
        return new ApiResponse(ResultCode.C000);
    }

    CookUserMst createDto(EditAccountRequest request) {
        // DTO作成
        CookUserMst dto = new CookUserMst();
        dto.setUserId(getLoginUserId());
        dto.setUserName(request.getUserName());
        dto.setPassword(passwordEncode(request.getPassword()));
        dto.setUpdateDatetime(new Date());
        dto.setVersion(toShort(request.getVersion()));
        return dto;
    }

}
