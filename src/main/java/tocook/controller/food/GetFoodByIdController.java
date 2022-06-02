package tocook.controller.food;

import static tocook.common.Functions.*;

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
 * API019 - 食材詳細検索機能 コントローラ
 */
@RestController
public class GetFoodByIdController extends BaseController {

    /** サービス */
    @Autowired
    private FoodService foodService;

    @GetMapping(ApiUri.FOOD_ID)
    public ApiResponseOptional<GetFoodByIdResponse> getById(@ModelAttribute @Validated FoodPathParameter request,
            BindingResult bindingResult) {
        // バリデーションチェック
        valid(bindingResult);

        // レコード取得
        FoodMstDTO foodMst = foodService.getListById(request.getFoodId());

        // レスポンス返却
        return new ApiResponseOptional<>(ResultCode.C000, createResponse(foodMst));
    }

    GetFoodByIdResponse createResponse(FoodMstDTO dto) {

        if (isNull(dto)) return new GetFoodByIdResponse();

        return new GetFoodByIdResponse()
                .setFoodId(dto.getFoodId())
                .setFoodName(dto.getFoodName())
                .setFoodUnit(dto.getFoodUnit())
                .setOthers1(dto.getOthers1())
                .setOthers2(dto.getOthers2())
                .setOthers3(dto.getOthers3())
                .setOthers4(dto.getOthers4())
                .setOthers5(dto.getOthers5())
                .setCommonValue1(dto.getCommonValue1())
                .setCommonValue2(dto.getCommonValue2())
                .setVersion(dto.getVersion());
    }

}
