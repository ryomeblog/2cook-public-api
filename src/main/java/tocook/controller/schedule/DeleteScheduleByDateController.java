package tocook.controller.schedule;

import static tocook.common.Functions.*;
import static tocook.constant.ApiErrorMessage.*;
import static tocook.constant.Constant.*;

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
 * API033 - スケジュール日付削除機能 コントローラ
 */
@RestController
public class DeleteScheduleByDateController extends BaseController {

    /** サービス */
    @Autowired
    private ScheduleService service;

    private String ERR_NAME= "scheduleDate";

    @DeleteMapping(ApiUri.SCHEDULE_DATE)
    public ApiResponse deleteById(
            @ModelAttribute @Validated ScheduleDatePathParameter request,
            BindingResult bindingResult) {
        // バリデーションチェック
        valid(bindingResult);

        // 削除処理
        try {
            service.deleteByDate(toDate(request.getScheduleDate(), DATE_NUMBER_FORMAT));
        } catch (ParseException e) {
            RequestParameterException requestParameterException = new RequestParameterException();
            requestParameterException.addErrorParams(ERR_NAME, request.getScheduleDate(), MSG_API_ERR_001);
            throw requestParameterException;
        }

        // レスポンス返却
        return new ApiResponse(ResultCode.C000);
    }

}
