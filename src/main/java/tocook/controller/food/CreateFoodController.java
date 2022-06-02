package tocook.controller.food;

import static tocook.common.CommonUtils.*;

import org.springframework.beans.factory.annotation.*;
import org.springframework.validation.*;
import org.springframework.validation.annotation.*;
import org.springframework.web.bind.annotation.*;

import tocook.constant.*;
import tocook.controller.*;
import tocook.dto.*;
import tocook.request.food.*;
import tocook.response.*;
import tocook.response.food.*;
import tocook.service.food.*;

/**
 * API020 - 食材登録機能 コントローラ
 */
@RestController
public class CreateFoodController extends BaseController {

    /** サービス */
    @Autowired
    private FoodService foodService;

    @PostMapping(ApiUri.FOOD)
    public ApiResponseOptional<CreateFoodResponse> create(
            @RequestBody @Validated CreateFoodRequest request, BindingResult bindingResult) {
        // バリデーションチェック
        valid(bindingResult);

        // 登録処理
        String id = foodService.insert(createDto(request));

        // レスポンス返却
        return new ApiResponseOptional<>(ResultCode.C000, createResponse(id));
    }

    CreateFoodResponse createResponse(String id) {
        // レスポンス作成
        return new CreateFoodResponse(id);
    }

    FoodMstDTO createDto(CreateFoodRequest request) {

        // DTO作成
        FoodMstDTO dto = new FoodMstDTO();
        dto.setFoodName(request.getFoodName());
        dto.setFoodUnit(request.getFoodUnit());
        dto.setOthers1(request.getOthers1());
        dto.setOthers2(request.getOthers2());
        dto.setOthers3(request.getOthers3());
        dto.setOthers4(request.getOthers4());
        dto.setOthers5(request.getOthers5());
        dto.setUserId(getLoginUserId());
        return dto;
    }

}
