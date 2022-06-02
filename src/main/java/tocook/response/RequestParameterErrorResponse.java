package tocook.response;

import java.util.List;

import lombok.Data;
import tocook.exception.entity.ErrorParam;

/**
 * リクエストパラメータエラー レスポンス
 */
@Data
public class RequestParameterErrorResponse {

    /** エラーリスト */
    private List<ErrorParam> errorParameterList;
}
