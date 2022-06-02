package tocook.response.account;

import java.util.*;

import lombok.*;
import lombok.experimental.*;

/**
 * API034 - 秘密の質問検索機能 レスポンス
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetQuestionResponse {

    /** 秘密の質問リスト */
    private List<Question> questionList;

    @Data
    @Accessors(chain = true)
    public static class Question {

        /** 汎用マスタID */
        private String commonId;

        /** 汎用マスタグループ */
        private String commonGroup;

        /** 汎用マスタ名 */
        private String commonName;

        /** 汎用マスタ値1 */
        private String commonValue1;

        /** 汎用マスタ値2 */
        private String commonValue2;

    }
}
