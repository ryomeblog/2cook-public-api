package tocook.exception.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * エラーパラメータ
 */
@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class ErrorParam {

    /** パラメータ名 */
    private String name;

    /** 値 */
    private Object value;

    /** エラーメッセージ */
    private String message;
}
