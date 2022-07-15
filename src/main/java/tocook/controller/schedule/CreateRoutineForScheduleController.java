package tocook.controller.schedule;

import static tocook.common.Functions.*;
import static tocook.constant.ApiErrorMessage.*;

import java.text.*;

import org.springframework.beans.factory.annotation.*;
import org.springframework.validation.*;
import org.springframework.validation.annotation.*;
import org.springframework.web.bind.annotation.*;

import tocook.constant.*;
import tocook.controller.*;
import tocook.exception.*;
import tocook.request.schedule.*;
import tocook.response.*;
import tocook.service.schedule.*;

/**
 * API035 - ルーティンスケジュール登録機能 コントローラ
 */
@RestController
public class CreateRoutineForScheduleController extends BaseController {

    /** サービス */
    @Autowired
    private ScheduleService service;

    private String ERR_NAME= "scheduleDate";

    @PostMapping(ApiUri.SCHEDULE_ROUTINE)
    public ApiResponse create(
            @RequestBody @Validated RoutineForSchedulePathParameter request,
            BindingResult bindingResult) {
        // バリデーションチェック
        valid(bindingResult);

        // 登録処理
        try {
            service.insertRoutineForSchedule(request.getRoutineId(), toDate(request.getScheduleDate()));
        } catch (ParseException e) {
            RequestParameterException requestParameterException = new RequestParameterException();
            requestParameterException.addErrorParams(ERR_NAME, request.getScheduleDate(), MSG_API_ERR_001);
            throw requestParameterException;
        }

        // レスポンス返却
        return new ApiResponse(ResultCode.C000);
    }

}
