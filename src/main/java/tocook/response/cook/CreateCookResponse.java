package tocook.response.cook;

import lombok.*;

/**
 * API012 - 料理登録機能 レスポンス
 */
@Data
@AllArgsConstructor
public class CreateCookResponse {

    /** 料理ID */
    private String cookId;
}
