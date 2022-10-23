package tocook.controller.schedule;

import static tocook.common.Functions.*;
import static tocook.constant.ApiErrorMessage.*;
import static tocook.constant.Constant.*;

import java.text.*;
import java.util.*;
import java.util.stream.*;

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
import tocook.response.schedule.*;
import tocook.service.schedule.*;

/**
 * API029 - スケジュール詳細検索機能 コントローラ
 */
@RestController
public class GetScheduleByDateController extends BaseController {

    /** サービス */
    @Autowired
    private ScheduleService service;

    private String ERR_NAME= "scheduleDate";

    @GetMapping(ApiUri.SCHEDULE_DATE)
    public ApiResponseOptional<GetScheduleByDateResponse> getScheduleByDate(
            @ModelAttribute @Validated ScheduleDatePathParameter request,
            BindingResult bindingResult) {
        // バリデーションチェック
        valid(bindingResult);

        // レコード取得
        List<ScheduleTblDTO> dtoList;
        try {
            dtoList = service.getListByDate(toDate(request.getScheduleDate(), DATE_NUMBER_FORMAT));
        } catch (ParseException e) {
            RequestParameterException requestParameterException = new RequestParameterException();
            requestParameterException.addErrorParams(ERR_NAME, request.getScheduleDate(), MSG_API_ERR_001);
            throw requestParameterException;
        }

        // レスポンス返却
        return new ApiResponseOptional<>(ResultCode.C000, createResponse(dtoList));
    }

    GetScheduleByDateResponse createResponse(List<ScheduleTblDTO> dtoList) {

        // レスポンス作成
        GetScheduleByDateResponse optional = new GetScheduleByDateResponse();
        optional.setScheduleList(dtoList.stream().map(
                dto -> new GetScheduleByDateResponse.Schedule()
                        .setScheduleId(dto.getScheduleId())
                        .setScheduleDate(toLocalDate(dto.getScheduleDate()))
                        .setScheduleTime(dto.getScheduleTime())
                        .setScheduleVersion(dto.getVersion())
                        .setCookId(dto.getCookId())
                        .setCookName(dto.getCookName())
                        .setCookProcess(dto.getCookProcess())
                        .setCookPrice(dto.getCookPrice())
                        .setCookFlg(dto.getCookFlg())
                        .setCookVersion(dto.getCookVersion()))
                .collect(Collectors.toList()));

        return optional;
    }

}
