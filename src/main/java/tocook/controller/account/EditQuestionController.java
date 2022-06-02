package tocook.controller.account;

import static tocook.common.CommonUtils.*;

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
 * API009 - パスワード再設定機能 コントローラ
 */
@RestController
public class EditQuestionController extends BaseController {

    /** アカウントサービス */
    @Autowired
    private AccountService accountService;

    @PutMapping(ApiUri.QUESTION)
    public ApiResponse edit(
            @RequestBody @Validated EditQuestionRequest request, BindingResult bindingResult) {
        // バリデーションチェック
        valid(bindingResult);

        // パスワード更新
        accountService.editQuestion(createDto(request));

        // レスポンス返却
        return new ApiResponse(ResultCode.C000);
    }

    CookUserMst createDto(EditQuestionRequest request) {
        // DTO作成
        CookUserMst dto = new CookUserMst();
        dto.setUserId(request.getUserId());
        dto.setPassword(passwordEncode(request.getNewPassword()));
        dto.setSecretId(request.getSecretId());
        dto.setSecretAnswer(request.getSecretAnswer());
        dto.setUpdateDatetime(new Date());
        return dto;
    }

}
