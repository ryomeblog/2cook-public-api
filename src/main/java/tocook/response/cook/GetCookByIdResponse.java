package tocook.response.cook;

import java.util.*;

import lombok.*;
import lombok.experimental.*;

/**
 * API011 - 料理詳細検索機能 レスポンス
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class GetCookByIdResponse {

    /** 料理ID */
    private String cookId;

    /** 料理名 */
    private String cookName;

    /** 金額 */
    private String cookProcess;

    /** 手順 */
    private Long cookPrice;

    /** 共有フラグ */
    private Boolean cookFlg;

    /** ユーザID */
    private String userId;

    /** バージョン */
    private Short version;

    /** リスト */
    private List<Food> foodList;

    @Data
    @NoArgsConstructor
    @Accessors(chain = true)
    public static class Food {

        /** 食材ID */
        private String foodId;

        /** 食材名 */
        private String foodName;

        /** 単位 */
        private String foodUnit;

        /** 単位値 */
        private Long unitValue;

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



}
