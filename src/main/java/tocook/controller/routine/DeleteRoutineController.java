package tocook.controller.routine;

import org.springframework.beans.factory.annotation.*;
import org.springframework.validation.*;
import org.springframework.validation.annotation.*;
import org.springframework.web.bind.annotation.*;

import tocook.constant.*;
import tocook.controller.*;
import tocook.request.routine.*;
import tocook.response.*;
import tocook.service.routine.*;

/**
 * API027 - ルーティン削除機能 コントローラ
 */
@RestController
public class DeleteRoutineController extends BaseController {

    /** サービス */
    @Autowired
    private RoutineService service;

    @DeleteMapping(ApiUri.ROUTINE_ID)
    public ApiResponse deleteById(@ModelAttribute @Validated RoutinePathParameter request,
            BindingResult bindingResult) {
        // バリデーションチェック
        valid(bindingResult);

        // 削除処理
        service.delete(request.getRoutineId());

        // レスポンス返却
        return new ApiResponse(ResultCode.C000);
    }

}
