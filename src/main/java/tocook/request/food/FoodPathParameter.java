package tocook.request.food;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

/**
 * 食材管理機能 パスパラメータ
 */
@Data
public class FoodPathParameter {

    /** 食材ID */
    @NotNull
    @Size(min = 12, max = 12)
    private String foodId;
}
