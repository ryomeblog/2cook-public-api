package tocook.controller.routine;

import static tocook.common.Functions.*;

import java.util.*;
import java.util.stream.*;

import org.springframework.beans.factory.annotation.*;
import org.springframework.validation.*;
import org.springframework.validation.annotation.*;
import org.springframework.web.bind.annotation.*;

import tocook.constant.*;
import tocook.controller.*;
import tocook.dto.*;
import tocook.request.routine.*;
import tocook.response.*;
import tocook.response.routine.*;
import tocook.service.routine.*;

/**
 * API023 - ルーティン検索機能 コントローラ
 */
@RestController
public class GetRoutineController extends BaseController {

    /** サービス */
    @Autowired
    private RoutineService service;

    @GetMapping(ApiUri.ROUTINE)
    public ApiResponseOptional<GetRoutineResponse> get(
            @ModelAttribute @Validated GetRoutineRequest request, BindingResult bindingResult) {
        // バリデーションチェック
        valid(bindingResult);

        // リスト総件数
        Integer total = asInt(service.getListCount(request.getRoutineId(), request.getRoutineName()));

        // リスト取得
        List<RoutineMstDTO> dtoList = service.getList(
                request.getRoutineId(),
                request.getRoutineName(),
                toInt(request.getOrderBy()),
                toInt(request.getAscOrDesc()),
                toInt(request.getLimit()),
                toInt(request.getOffset()));

        // レスポンス返却
        return new ApiResponseOptional<>(ResultCode.C000, createResponse(total, dtoList));
    }

    GetRoutineResponse createResponse(Integer total, List<RoutineMstDTO> dtoList) {

        // レスポンス作成
        GetRoutineResponse optional = new GetRoutineResponse();

        // レスポンス格納
        optional.setTotal(total);
        optional.setRoutineList(dtoList.stream().map(
                dto -> new GetRoutineResponse.Routine()
                        .setRoutineId(dto.getRoutineId())
                        .setRoutineName(dto.getRoutineName())
                        .setRoutineMaxday(dto.getRoutineMaxday())
                        .setVersion(dto.getVersion()))
                .collect(Collectors.toList()));

        return optional;
    }

}
