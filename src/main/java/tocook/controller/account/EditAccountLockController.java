package tocook.controller.account;

import static tocook.common.Functions.*;

import java.util.*;

import org.springframework.beans.factory.annotation.*;
import org.springframework.validation.*;
import org.springframework.validation.annotation.*;
import org.springframework.web.bind.annotation.*;

import tocook.constant.*;
import tocook.controller.*;
import tocook.dto.generator.*;
import tocook.request.account.*;
import tocook.response.*;
import tocook.service.account.*;

/**
 * API006 - ユーザ編集機能 コントローラ
 */
@RestController
public class EditAccountLockController extends BaseController {

    /** アカウントサービス */
    @Autowired
    private AccountService accountService;

    @PutMapping(ApiUri.ACCOUNT_LOCK)
    public ApiResponse editAccount(
            @RequestBody @Validated EditAccountLockRequest request, BindingResult bindingResult) {
        // バリデーションチェック
        valid(bindingResult);

        // ユーザ更新
        accountService.editUser((createDto(request)));

        // レスポンス返却
        return new ApiResponse(ResultCode.C000);
    }

    CookUserMst createDto(EditAccountLockRequest request) {
        // DTO作成
        CookUserMst dto = new CookUserMst();
        dto.setUserId(request.getUserId());
        dto.setLock(toBoolean(request.getLock()));
        dto.setUpdateDatetime(new Date());
        dto.setVersion(toShort(request.getVersion()));
        return dto;
    }

}
