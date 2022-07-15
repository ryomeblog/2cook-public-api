package tocook.controller.schedule;

import org.springframework.beans.factory.annotation.*;
import org.springframework.validation.*;
import org.springframework.validation.annotation.*;
import org.springframework.web.bind.annotation.*;

import tocook.constant.*;
import tocook.controller.*;
import tocook.request.schedule.*;
import tocook.response.*;
import tocook.service.schedule.*;

/**
 * API032 - スケジュール削除機能 コントローラ
 */
@RestController
public class DeleteScheduleController extends BaseController {

    /** サービス */
    @Autowired
    private ScheduleService service;

    @DeleteMapping(ApiUri.SCHEDULE_DELETE_ID)
    public ApiResponse deleteScheduleByDate(
            @ModelAttribute @Validated SchedulePathParameter request,
            BindingResult bindingResult) {
        // バリデーションチェック
        valid(bindingResult);

        // 削除処理
        service.delete(request.getScheduleId());

        // レスポンス返却
        return new ApiResponse(ResultCode.C000);
    }

}
