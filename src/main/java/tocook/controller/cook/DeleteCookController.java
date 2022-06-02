package tocook.controller.cook;

import org.springframework.beans.factory.annotation.*;
import org.springframework.validation.*;
import org.springframework.validation.annotation.*;
import org.springframework.web.bind.annotation.*;

import tocook.constant.*;
import tocook.controller.*;
import tocook.request.cook.*;
import tocook.response.*;
import tocook.service.cook.*;

/**
 * API014 - 料理削除機能 コントローラ
 */
@RestController
public class DeleteCookController extends BaseController {

    /** サービス */
    @Autowired
    private CookService cookService;

    @DeleteMapping(ApiUri.COOK_ID)
    public ApiResponse deleteById(@ModelAttribute @Validated CookPathParameter request, BindingResult bindingResult) {
        // バリデーションチェック
        valid(bindingResult);

        // 削除処理
        cookService.delete(request.getCookId());

        // レスポンス返却
        return new ApiResponse(ResultCode.C000);
    }

}
