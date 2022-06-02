package tocook.exception;

import org.springframework.http.HttpStatus;

import tocook.constant.ResultCode;

/**
 * ToCookRuntimeException
 */
public class ToCookRuntimeException extends RuntimeException {

    /** 結果コード */
    private ResultCode resultCode;

    /** HTTPステータスコード */
    private HttpStatus httpStatus;

    /**
     * コンストラクタ
     *
     * @param e cause
     */
    public ToCookRuntimeException(Throwable e) {
        super.initCause(e);
    }

    /**
     * コンストラクタ
     *
     * @param httpStatus HTTPステータスコード
     * @param resultCode 結果コード
     */
    public ToCookRuntimeException(HttpStatus httpStatus, ResultCode resultCode) {
        this.resultCode = resultCode;
        this.httpStatus = httpStatus;
    }

    /**
     * 結果コードを取得します。
     *
     * @return 結果コード
     */
    public ResultCode getResultCode() {
        return resultCode;
    }

    /**
     * HTTPステータスコードを取得します。
     *
     * @return HTTPステータスコード
     */
    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}
