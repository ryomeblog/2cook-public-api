package tocook.controller.schedule;

import static tocook.common.CommonUtils.*;
import static tocook.common.Functions.*;
import static tocook.constant.ApiErrorMessage.*;

import java.text.*;
import java.util.*;

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
 * API031 - スケジュール編集機能 コントローラ
 */
@RestController
public class EditScheduleController extends BaseController {

    /** サービス */
    @Autowired
    private ScheduleService service;

    private String ERR_NAME= "scheduleDate";

    @PutMapping(ApiUri.SCHEDULE)
    public ApiResponse edit(
            @RequestBody @Validated EditScheduleRequest request,
            BindingResult bindingResult) {
        // バリデーションチェック
        valid(bindingResult);

        // 更新処理
        service.edit(createDto(request));

        // レスポンス返却
        return new ApiResponse(ResultCode.C000);
    }

    ScheduleTblDTO createDto(EditScheduleRequest request) {

        // DTO作成
        ScheduleTblDTO dto = new ScheduleTblDTO();
        dto.setScheduleId(request.getScheduleId());
        try {
            dto.setScheduleDate(toDate(request.getScheduleDate()));
        } catch (ParseException e) {
            RequestParameterException requestParameterException = new RequestParameterException();
            requestParameterException.addErrorParams(ERR_NAME, request.getScheduleDate(), MSG_API_ERR_001);
            throw requestParameterException;
        }
        dto.setScheduleTime(toShort(request.getScheduleTime()));
        dto.setCookId(request.getCookId());
        dto.setUserId(getLoginUserId());
        dto.setUpdateDatetime(new Date());
        dto.setVersion(toShort(request.getVersion()));
        return dto;
    }

}
