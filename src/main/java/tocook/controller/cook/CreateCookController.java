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
 * API012 - 料理登録機能 コントローラ
 */
@RestController
public class CreateCookController extends BaseController {

    /** サービス */
    @Autowired
    private CookService cookService;

    @PostMapping(ApiUri.COOK)
    public ApiResponseOptional<CreateCookResponse> create(
            @RequestBody @Validated CreateCookRequest request, BindingResult bindingResult) {
        // バリデーションチェック
        valid(bindingResult);

        // 登録処理
        String id = cookService.insert(createDto(request), createDtoList(request));

        // レスポンス返却
        return new ApiResponseOptional<>(ResultCode.C000, createResponse(id));
    }

    CreateCookResponse createResponse(String id) {
        // レスポンス作成
        return new CreateCookResponse(id);
    }

    CookMstDTO createDto(CreateCookRequest request) {

        // DTO作成
        CookMstDTO dto = new CookMstDTO();
        dto.setCookName(request.getCookName());
        dto.setCookProcess(request.getCookProcess());
        dto.setCookPrice(toLong(request.getCookPrice()));
        dto.setCookMovie(request.getCookMovie());
        dto.setCookUrl(request.getCookUrl());
        dto.setCookFlg(false);
        dto.setUserId(getLoginUserId());
        return dto;
    }

    List<CookFoodTblDTO> createDtoList(CreateCookRequest request) {

        if(isNull(request.getFoodList())) return null;

        // DTO作成
        return request.getFoodList().stream().map(
                food -> {
                    CookFoodTblDTO dto = new CookFoodTblDTO();
                    dto.setFoodId(food.getFoodId());
                    dto.setUnitValue(toLong(food.getUnitValue()));
                    return dto;
                })
                .collect(Collectors.toList());
    }

}
