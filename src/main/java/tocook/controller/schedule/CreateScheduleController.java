package tocook.controller.schedule;

import static tocook.common.CommonUtils.*;
import static tocook.common.Functions.*;
import static tocook.constant.ApiErrorMessage.*;

import java.text.*;

import org.springframework.beans.factory.annotation.*;
import org.springframework.validation.*;
import org.springframework.validation.annotation.*;
import org.springframework.web.bind.annotation.*;

import tocook.constant.*;
import tocook.controller.*;
import tocook.dto.*;
import tocook.exception.*;
import tocook.request.schedule.*;
import tocook.response.*;
import tocook.service.schedule.*;

/**
 * API030 - スケジュール登録機能 コントローラ
 */
@RestController
public class CreateScheduleController extends BaseController {

    /** サービス */
    @Autowired
    private ScheduleService service;

    private String ERR_NAME= "scheduleDate";

    @PostMapping(ApiUri.SCHEDULE)
    public ApiResponse create(
            @RequestBody @Validated CreateScheduleRequest request, BindingResult bindingResult) {
        // バリデーションチェック
        valid(bindingResult);

        // 登録処理
        service.insert(createDto(request), request.getCookIdList());

        // レスポンス返却
        return new ApiResponse(ResultCode.C000);
    }

    ScheduleTblDTO createDto(CreateScheduleRequest request) {

        // DTO作成
        ScheduleTblDTO dto = new ScheduleTblDTO();

        try {
            dto.setScheduleDate(toDate(request.getScheduleDate()));
        } catch (ParseException e) {
            RequestParameterException requestParameterException = new RequestParameterException();
            requestParameterException.addErrorParams(ERR_NAME, request.getScheduleDate(), MSG_API_ERR_001);
            throw requestParameterException;
        }
        dto.setScheduleTime(toShort(request.getScheduleTime()));
        dto.setUserId(getLoginUserId());
        return dto;
    }

}
