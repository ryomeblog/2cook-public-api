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
 * API024 - ルーティン詳細検索機能 コントローラ
 */
@RestController
public class GetRoutineByIdController extends BaseController {

    /** サービス */
    @Autowired
    private RoutineService service;

    @GetMapping(ApiUri.ROUTINE_ID)
    public ApiResponseOptional<GetRoutineByIdResponse> getById(@ModelAttribute @Validated RoutinePathParameter request,
            BindingResult bindingResult) {
        // バリデーションチェック
        valid(bindingResult);

        // レコード取得
        RoutineMstDTO dto = service.getListById(request.getRoutineId());

        // ルーティン食事リスト取得
        List<RoutineEatTblDTO> routineEatList = service.getRoutineEatListByRoutineId(request.getRoutineId());

        // レスポンス返却
        return new ApiResponseOptional<>(ResultCode.C000, createResponse(dto, routineEatList));
    }

    GetRoutineByIdResponse createResponse(RoutineMstDTO dto, List<RoutineEatTblDTO> routineEatList) {

        if (isNull(dto)) return new GetRoutineByIdResponse();

        return new GetRoutineByIdResponse()
                .setRoutineId(dto.getRoutineId())
                .setRoutineName(dto.getRoutineName())
                .setRoutineMaxday(dto.getRoutineMaxday())
                .setVersion(dto.getVersion())
                .setRoutineEatList(routineEatList.stream().map(
                        routineEat -> {
                            return new GetRoutineByIdResponse.RoutineEat()
                                    .setRoutineEatId(routineEat.getRoutineEatId())
                                    .setRoutineId(routineEat.getRoutineId())
                                    .setRoutineDay(routineEat.getRoutineDay())
                                    .setRoutineTime(routineEat.getRoutineTime())
                                    .setCookId(routineEat.getCookId())
                                    .setCookName(routineEat.getCookName())
                                    .setCookFlg(routineEat.getCookFlg())
                                    .setUserId(routineEat.getUserId())
                                    .setVersion(routineEat.getVersion());
                        })
                        .collect(Collectors.toList()));
    }

}
