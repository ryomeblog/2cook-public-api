package tocook.controller.food;

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
import tocook.request.food.*;
import tocook.response.*;
import tocook.response.food.*;
import tocook.service.food.*;

/**
 * API018 - 食材検索機能 コントローラ
 */
@RestController
public class GetFoodController extends BaseController {

    /** サービス */
    @Autowired
    private FoodService foodService;

    @GetMapping(ApiUri.FOOD)
    public ApiResponseOptional<GetFoodResponse> get(
            @ModelAttribute @Validated GetFoodRequest request, BindingResult bindingResult) {
        // バリデーションチェック
        valid(bindingResult);

        // リスト総件数
        Integer total = asInt(foodService.getListCount(
                request.getFoodId(),
                request.getFoodName(),
                request.getFoodUnit()));

        // リスト取得
        List<FoodMstDTO> dtoList = foodService.getList(
                request.getFoodId(),
                request.getFoodName(),
                request.getFoodUnit(),
                toInt(request.getOrderBy()),
                toInt(request.getAscOrDesc()),
                toInt(request.getLimit()),
                toInt(request.getOffset()));

        // レスポンス返却
        return new ApiResponseOptional<>(ResultCode.C000, createResponse(total, dtoList));
    }

    GetFoodResponse createResponse(Integer total, List<FoodMstDTO> dtoList) {

        // レスポンス作成
        GetFoodResponse optional = new GetFoodResponse();

        // レスポンス格納
        optional.setTotal(total);
        optional.setFoodList(dtoList.stream().map(
                dto -> new GetFoodByIdResponse()
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
                        .setVersion(dto.getVersion()))
                .collect(Collectors.toList()));

        return optional;
    }

}
