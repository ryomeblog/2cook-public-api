package tocook.controller.cook;

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
import tocook.request.cook.*;
import tocook.response.*;
import tocook.response.cook.*;
import tocook.service.cook.*;

/**
 * API011 - 料理詳細検索機能 コントローラ
 */
@RestController
public class GetCookByIdController extends BaseController {

    /** サービス */
    @Autowired
    private CookService service;

    @GetMapping(ApiUri.COOK_ID)
    public ApiResponseOptional<GetCookByIdResponse> getById(@ModelAttribute @Validated CookPathParameter request,
            BindingResult bindingResult) {
        // バリデーションチェック
        valid(bindingResult);

        // レコード取得
        CookMstDTO dto = service.getListById(request.getCookId(), getLoginUserId());

        // 料理食材リスト取得
        List<CookFoodTblDTO> foodList = service.getCookFoodListByCookId(request.getCookId());

        // レスポンス返却
        return new ApiResponseOptional<>(ResultCode.C000, createResponse(dto, foodList));
    }

    GetCookByIdResponse createResponse(CookMstDTO dto, List<CookFoodTblDTO> foodList) {

        if (isNull(dto)) return new GetCookByIdResponse();

        return new GetCookByIdResponse()
                .setCookId(dto.getCookId())
                .setCookName(dto.getCookName())
                .setCookProcess(dto.getCookProcess())
                .setCookPrice(dto.getCookPrice())
                .setCookFlg(dto.getCookFlg())
                .setUserId(dto.getUserId())
                .setVersion(dto.getVersion())
                .setFoodList(foodList.stream().map(
                        food -> {
                            return new GetCookByIdResponse.Food()
                                    .setFoodId(food.getFoodId())
                                    .setFoodName(food.getFoodName())
                                    .setFoodUnit(food.getFoodUnit())
                                    .setUnitValue(food.getUnitValue())
                                    .setOthers1(food.getOthers1())
                                    .setOthers2(food.getOthers2())
                                    .setOthers3(food.getOthers3())
                                    .setOthers4(food.getOthers4())
                                    .setOthers5(food.getOthers5())
                                    .setCommonValue1(food.getCommonValue1())
                                    .setCommonValue2(food.getCommonValue2())
                                    .setVersion(food.getVersion());
                        })
                        .collect(Collectors.toList()));
    }

}
