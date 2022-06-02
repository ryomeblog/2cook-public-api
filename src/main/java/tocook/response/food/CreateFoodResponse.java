package tocook.response.food;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * API020 - 食材登録機能 レスポンス
 */
@Data
@AllArgsConstructor
public class CreateFoodResponse {

    /** 食材ID */
    private String foodId;
}
