package tocook.constant;

/**
 * ApiErrorMessage Constant
 */
public class ApiErrorMessage {

    /** 入力された日付がyyyy/MM/dd形式になっていない */
    public static final String MSG_API_ERR_001 = "yyyy/MM/dd形式で入力して下さい";

    /** 半角数字ではない または 数値の範囲になっていない */
    public static final String MSG_API_ERR_003 = "半角数字ではない または 指定の範囲になっていません";

    /** 重複している値があります */
    public static final String MSG_API_ERR_004 = "重複している値があります";

    /** 最大日数超過 */
    public static final String MSG_API_ERR_005 = "最大日数を超過しています";
}
