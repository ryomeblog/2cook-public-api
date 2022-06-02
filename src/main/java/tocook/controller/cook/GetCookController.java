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
 * API010 - 料理検索機能 コントローラ
 */
@RestController
public class GetCookController extends BaseController {

    /** サービス */
    @Autowired
    private CookService service;

    @GetMapping(ApiUri.COOK)
    public ApiResponseOptional<GetCookResponse> get(
            @ModelAttribute @Validated GetCookRequest request, BindingResult bindingResult) {
        // バリデーションチェック
        valid(bindingResult);

        // リスト総件数
        Integer total = asInt(service.getListCount(
                request.getCookId(),
                request.getCookName(),
                getLoginUserId(),
                request.getUserId(),
                toLong(request.getMinPrice()),
                toLong(request.getMaxPrice()),
                request.getFoodIdList()));

        // リスト取得
        List<CookMstDTO> dtoList = service.getList(
                request.getCookId(),
                request.getCookName(),
                getLoginUserId(),
                request.getUserId(),
                toLong(request.getMinPrice()),
                toLong(request.getMaxPrice()),
                request.getFoodIdList(),
                toInt(request.getOrderBy()),
                toInt(request.getAscOrDesc()),
                toInt(request.getLimit()),
                toInt(request.getOffset()));

        // レスポンス返却
        return new ApiResponseOptional<>(ResultCode.C000, createResponse(total, dtoList));
    }

    GetCookResponse createResponse(Integer total, List<CookMstDTO> dtoList) {

        // レスポンス作成
        GetCookResponse optional = new GetCookResponse();

        // レスポンス格納
        optional.setTotal(total);
        optional.setCookList(dtoList.stream().map(
                dto -> new GetCookResponse.Cook()
                        .setCookId(dto.getCookId())
                        .setCookName(dto.getCookName())
                        .setCookProcess(dto.getCookProcess())
                        .setCookPrice(dto.getCookPrice())
                        .setCookFlg(dto.getCookFlg())
                        .setUserId(dto.getUserId())
                        .setVersion(dto.getVersion()))
                .collect(Collectors.toList()));

        return optional;
    }

}
