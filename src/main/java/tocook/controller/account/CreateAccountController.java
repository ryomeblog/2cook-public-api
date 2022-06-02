package tocook.controller.account;

import static tocook.common.CommonUtils.*;
import static tocook.common.Functions.*;

import org.springframework.beans.factory.annotation.*;
import org.springframework.validation.*;
import org.springframework.validation.annotation.*;
import org.springframework.web.bind.annotation.*;

import tocook.constant.*;
import tocook.controller.*;
import tocook.dto.generator.*;
import tocook.request.account.*;
import tocook.response.*;
import tocook.response.account.*;
import tocook.service.account.*;

/**
 * API005 - ユーザ登録機能 コントローラ
 */
@RestController
public class CreateAccountController extends BaseController {

    /** アカウントサービス */
    @Autowired
    private AccountService accountService;

    @PostMapping(ApiUri.SIGNUP)
    public ApiResponseOptional<CreateAccountResponse> create(
            @RequestBody @Validated CreateAccountRequest request, BindingResult bindingResult) {
        // バリデーションチェック
        valid(bindingResult);

        // ユーザ追加
        String userId = accountService.insertUser(createDto(request));

        // レスポンス返却
        return new ApiResponseOptional<>(ResultCode.C000, toCreateAccountResponse(userId));
    }

    CreateAccountResponse toCreateAccountResponse(String userId) {
        // レスポンス作成
        return new CreateAccountResponse(userId);
    }

    CookUserMst createDto(CreateAccountRequest request) {

        // DTO作成
        CookUserMst dto = new CookUserMst();
        dto.setUserName(request.getUserName());
        dto.setPassword(passwordEncode(request.getPassword()));
        dto.setSecretId(request.getSecretId());
        dto.setSecretAnswer(passwordEncode(request.getSecretAnswer()));
        dto.setLock(false);
        dto.setAuthority(toShort(0));
        return dto;
    }

}
