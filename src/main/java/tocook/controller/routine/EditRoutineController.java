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
import tocook.service.routine.*;

/**
 * API026 - ルーティン編集機能 コントローラ
 */
@RestController
public class EditRoutineController extends BaseController {

    /** サービス */
    @Autowired
    private RoutineService service;

    @PutMapping(ApiUri.ROUTINE)
    public ApiResponse edit(
            @RequestBody @Validated EditRoutineRequest request, BindingResult bindingResult) {
        // バリデーションチェック
        valid(bindingResult);

        // 更新処理
        service.edit(createDto(request), createDtoList(request));

        // レスポンス返却
        return new ApiResponse(ResultCode.C000);
    }

    RoutineMstDTO createDto(EditRoutineRequest request) {

        // DTO作成
        RoutineMstDTO dto = new RoutineMstDTO();
        dto.setRoutineId(request.getRoutineId());
        dto.setRoutineName(request.getRoutineName());
        dto.setRoutineMaxday(toShort(request.getRoutineMaxday()));
        dto.setUserId(getLoginUserId());
        dto.setVersion(toShort(request.getVersion()));
        return dto;
    }

    List<RoutineEatTblDTO> createDtoList(EditRoutineRequest request) {

        if(isNull(request.getRoutineEatList())) return null;

        // DTO作成
        return request.getRoutineEatList().stream().map(
                routineEat -> {
                    RoutineEatTblDTO dto = new RoutineEatTblDTO();
                    dto.setCookId(routineEat.getCookId());
                    dto.setRoutineDay(toShort(routineEat.getRoutineDay()));
                    dto.setRoutineTime(toShort(routineEat.getRoutineTime()));
                    return dto;
                }).collect(Collectors.toList());
    }

}
