package tocook.controller.food;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RestController;

import tocook.constant.ApiUri;
import tocook.constant.ResultCode;
import tocook.controller.BaseController;
import tocook.request.food.FoodPathParameter;
import tocook.response.ApiResponse;
import tocook.service.food.FoodService;

/**
 * API022 - 食材削除機能 コントローラ
 */
@RestController
public class DeleteFoodController extends BaseController {

    /** サービス */
    @Autowired
    private FoodService foodService;

    @DeleteMapping(ApiUri.FOOD_ID)
    public ApiResponse deleteById(@ModelAttribute @Validated FoodPathParameter request, BindingResult bindingResult) {
        // バリデーションチェック
        valid(bindingResult);

        // 削除処理
        foodService.delete(request.getFoodId());

        // レスポンス返却
        return new ApiResponse(ResultCode.C000);
    }

}
