package tocook.response.food;

import java.util.*;

import lombok.*;

/**
 * API018 - 食材検索機能 レスポンス
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetFoodResponse {

    /** 総件数 */
    private int total;

    /** リスト */
    private List<GetFoodByIdResponse> foodList;

}
