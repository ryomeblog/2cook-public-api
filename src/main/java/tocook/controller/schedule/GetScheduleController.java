package tocook.controller.schedule;

import static tocook.common.Functions.*;
import static tocook.constant.ApiErrorMessage.*;

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
 * API028 - スケジュール検索機能 コントローラ
 */
@RestController
public class GetScheduleController extends BaseController {

    /** サービス */
    @Autowired
    private ScheduleService service;

    private String ERR_NAME= "scheduleDate";

    @GetMapping(ApiUri.SCHEDULE)
    public ApiResponseOptional<GetScheduleResponse> getSchedule(
            @ModelAttribute @Validated GetScheduleRequest request,
            BindingResult bindingResult) {
        // バリデーションチェック
        valid(bindingResult);

        // リスト総件数
        Integer total = 0;

        // スケジュールリスト
        List<ScheduleTblDTO> dtoList = new ArrayList<>();

        try {
            // リスト総件数
            total = asInt(service.getListCount(
                    request.getScheduleId(),
                    toDate(request.getScheduleDate()),
                    toShort(request.getScheduleTime()),
                    request.getCookId()));

            // リスト取得
            dtoList = service.getList(
                    request.getScheduleId(),
                    toDate(request.getScheduleDate()),
                    toShort(request.getScheduleTime()),
                    request.getCookId(),
                    toInt(request.getOrderBy()),
                    toInt(request.getAscOrDesc()),
                    toInt(request.getLimit()),
                    toInt(request.getOffset()));
        } catch (ParseException e) {
            RequestParameterException requestParameterException = new RequestParameterException();
            requestParameterException.addErrorParams(ERR_NAME, request.getScheduleDate(), MSG_API_ERR_001);
            throw requestParameterException;
        }

        // レスポンス返却
        return new ApiResponseOptional<>(ResultCode.C000, createResponse(total, dtoList));
    }

    GetScheduleResponse createResponse(Integer total, List<ScheduleTblDTO> dtoList) {

        // レスポンス作成
        GetScheduleResponse optional = new GetScheduleResponse();

        // レスポンス格納
        optional.setTotal(total);
        optional.setScheduleList(dtoList.stream().map(
                dto -> new GetScheduleResponse.Schedule()
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
