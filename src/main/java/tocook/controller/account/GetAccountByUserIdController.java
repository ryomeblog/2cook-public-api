package tocook.controller.account;

import static tocook.common.CommonUtils.*;
import static tocook.common.Functions.*;

import org.springframework.beans.factory.annotation.*;
import org.springframework.web.bind.annotation.*;

import tocook.constant.*;
import tocook.controller.*;
import tocook.dto.generator.*;
import tocook.response.*;
import tocook.response.account.*;
import tocook.service.account.*;

/**
 * API004 - ユーザ詳細検索機能 コントローラ
 */
@RestController
public class GetAccountByUserIdController extends BaseController {

    /** アカウントサービス */
    @Autowired
    private AccountService accountService;

    @GetMapping(ApiUri.ACCOUNT)
    public ApiResponseOptional<GetAccountByUserIdResponse> getAccountByUserId() {

        // ユーザ詳細情報取得
        CookUserMst dto = accountService.getUserListByUserId();

        // レスポンス返却
        return new ApiResponseOptional<>(ResultCode.C000, createResponse(dto));
    }

    GetAccountByUserIdResponse createResponse(CookUserMst dto) {

        if(isNull(dto)) return new GetAccountByUserIdResponse();

        return new GetAccountByUserIdResponse()
                .setUserId(dto.getUserId())
                .setUserName(dto.getUserName())
                .setAuthority(getRole(dto.getAuthority()))
                .setVersion(dto.getVersion());
    }

}
