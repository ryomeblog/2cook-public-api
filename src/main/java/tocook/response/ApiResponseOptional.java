package tocook.response;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import tocook.constant.ResultCode;

/**
 * APIレスポンス
 *
 * @param <T> optionalの型
 */
@AllArgsConstructor
@NoArgsConstructor
public class ApiResponseOptional<T> extends ApiResponse {

    /** optional */
    private T optional;


    /**
     * コンストラクタ
     *
     * @param resultCode 結果コード
     * @param optional
     */
    public ApiResponseOptional(ResultCode resultCode, T optional) {
        this.setResultCode(resultCode);
        this.setOptional(optional);
    }


    public T getOptional() {
        return optional;
    }


    public void setOptional(T optional) {
        this.optional = optional;
    }
}
