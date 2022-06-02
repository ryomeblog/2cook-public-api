package tocook.response.cook;

import java.util.*;

import lombok.*;
import lombok.experimental.*;

/**
 * API010 - 料理検索機能 レスポンス
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetCookResponse {

    /** 総件数 */
    private int total;

    /** リスト */
    private List<Cook> cookList;

    @Data
    @NoArgsConstructor
    @Accessors(chain = true)
    public static class Cook {

        /** 料理ID */
        private String cookId;

        /** 料理名 */
        private String cookName;

        /** 手順 */
        private String cookProcess;

        /** 金額 */
        private Long cookPrice;

        /** 共有フラグ */
        private Boolean cookFlg;

        /** ユーザID */
        private String userId;

        /** バージョン */
        private Short version;
    }

}
