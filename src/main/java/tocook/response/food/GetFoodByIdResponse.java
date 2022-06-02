package tocook.response.food;

import lombok.*;
import lombok.experimental.*;

/**
 * API019 - 食材詳細検索機能 レスポンス
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class GetFoodByIdResponse {

    /** 食材ID */
    private String foodId;

    /** 食材名 */
    private String foodName;

    /** 単位 */
    private String foodUnit;

    /** その他1 */
    private String others1;

    /** その他2 */
    private String others2;

    /** その他3 */
    private String others3;

    /** その他4 */
    private String others4;

    /** その他5 */
    private String others5;

    /** 単位名 */
    private String commonValue1;

    /** 単位名（表示） */
    private String commonValue2;

    /** バージョン */
    private Short version;

}
