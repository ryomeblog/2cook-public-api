package tocook.controller.food;

import static tocook.common.CommonUtils.*;
import static tocook.common.Functions.*;

import java.util.*;

import org.springframework.beans.factory.annotation.*;
import org.springframework.validation.*;
import org.springframework.validation.annotation.*;
import org.springframework.web.bind.annotation.*;

import tocook.constant.*;
import tocook.controller.*;
import tocook.dto.*;
import tocook.request.food.*;
import tocook.response.*;
import tocook.service.food.*;

/**
 * API021 - 食材編集機能 コントローラ
 */
@RestController
public class EditFoodController extends BaseController {

    /** サービス */
    @Autowired
    private FoodService foodService;

    @PutMapping(ApiUri.FOOD)
    public ApiResponse edit(
            @RequestBody @Validated EditFoodRequest request, BindingResult bindingResult) {
        // バリデーションチェック
        valid(bindingResult);

        // 更新処理
        foodService.edit((createDto(request)));

        // レスポンス返却
        return new ApiResponse(ResultCode.C000);
    }

    FoodMstDTO createDto(EditFoodRequest request) {
        // DTO作成
        FoodMstDTO dto = new FoodMstDTO();
        dto.setFoodId(request.getFoodId());
        dto.setFoodName(request.getFoodName());
        dto.setFoodUnit(request.getFoodUnit());
        dto.setOthers1(request.getOthers1());
        dto.setOthers2(request.getOthers2());
        dto.setOthers3(request.getOthers3());
        dto.setOthers4(request.getOthers4());
        dto.setOthers5(request.getOthers5());
        dto.setUserId(getLoginUserId());
        dto.setUpdateDatetime(new Date());
        dto.setVersion(toShort(request.getVersion()));
        return dto;
    }

}
