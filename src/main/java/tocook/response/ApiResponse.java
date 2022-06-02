package tocook.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import tocook.constant.ResultCode;

/**
 * APIレスポンス
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiResponse {

    /** 結果コード */
    private ResultCode resultCode;
}
