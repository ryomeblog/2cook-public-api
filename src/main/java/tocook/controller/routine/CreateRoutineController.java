package tocook.controller.routine;

import static tocook.common.CommonUtils.*;
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
 * API025 - ルーティン登録機能 コントローラ
 */
@RestController
public class CreateRoutineController extends BaseController {

    /** サービス */
    @Autowired
    private RoutineService service;

    @PostMapping(ApiUri.ROUTINE)
    public ApiResponseOptional<CreateRoutineResponse> create(
            @RequestBody @Validated CreateRoutineRequest request, BindingResult bindingResult) {
        // バリデーションチェック
        valid(bindingResult);

        // 登録処理
        String id = service.insert(createDto(request), createDtoList(request));

        // レスポンス返却
        return new ApiResponseOptional<>(ResultCode.C000, createResponse(id));
    }

    CreateRoutineResponse createResponse(String id) {
        // レスポンス作成
        return new CreateRoutineResponse(id);
    }

    RoutineMstDTO createDto(CreateRoutineRequest request) {

        // DTO作成
        RoutineMstDTO dto = new RoutineMstDTO();
        dto.setRoutineName(request.getRoutineName());
        dto.setRoutineMaxday(toShort(request.getRoutineMaxday()));
        dto.setUserId(getLoginUserId());
        return dto;
    }

    List<RoutineEatTblDTO> createDtoList(CreateRoutineRequest request) {

        if(isNull(request.getRoutineEatList())) return null;

        // DTO作成
        return request.getRoutineEatList().stream().map(
                routineEat -> {
                    RoutineEatTblDTO dto = new RoutineEatTblDTO();
                    dto.setCookId(routineEat.getCookId());
                    dto.setRoutineDay(toShort(routineEat.getRoutineDay()));
                    dto.setRoutineTime(toShort(routineEat.getRoutineTime()));
                    return dto;
                })
                .collect(Collectors.toList());
    }

}
