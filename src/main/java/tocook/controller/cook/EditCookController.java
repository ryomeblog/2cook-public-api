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
import tocook.service.cook.*;

/**
 * API013 - 料理編集機能 コントローラ
 */
@RestController
public class EditCookController extends BaseController {

    /** サービス */
    @Autowired
    private CookService cookService;

    @PutMapping(ApiUri.COOK)
    public ApiResponse edit(
            @RequestBody @Validated EditCookRequest request, BindingResult bindingResult) {
        // バリデーションチェック
        valid(bindingResult);

        // 更新処理
        cookService.edit(createDto(request), createDtoList(request));

        // レスポンス返却
        return new ApiResponse(ResultCode.C000);
    }

    CookMstDTO createDto(EditCookRequest request) {

        // DTO作成
        CookMstDTO dto = new CookMstDTO();
        dto.setCookId(request.getCookId());
        dto.setCookName(request.getCookName());
        dto.setCookProcess(request.getCookProcess());
        dto.setCookPrice(toLong(request.getCookPrice()));
        dto.setCookMovie(request.getCookMovie());
        dto.setCookUrl(request.getCookUrl());
        dto.setCookFlg(toBoolean(request.getCookFlg()));
        dto.setUserId(getLoginUserId());
        dto.setUpdateDatetime(new Date());
        dto.setVersion(toShort(request.getVersion()));
        return dto;
    }

    List<CookFoodTblDTO> createDtoList(EditCookRequest request) {

        if(isNull(request.getFoodList())) return null;

        // DTO作成
        return request.getFoodList().stream().map(
                food -> {
                    CookFoodTblDTO dto = new CookFoodTblDTO();
                    dto.setFoodId(food.getFoodId());
                    dto.setUnitValue(toLong(food.getUnitValue()));
                    dto.setUserId(getLoginUserId());
                    return dto;
                })
                .collect(Collectors.toList());
    }

}
