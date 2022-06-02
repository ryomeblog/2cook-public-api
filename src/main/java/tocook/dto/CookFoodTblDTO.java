package tocook.dto;

import lombok.*;
import tocook.dto.generator.*;

/**
 * 「cook_food_tbl」、「food_mst」、「common_mst」テーブルの結合用DTO
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CookFoodTblDTO extends CookFoodTbl {

    private String foodName;

    private String foodUnit;

    private String others1;

    private String others2;

    private String others3;

    private String others4;

    private String others5;

    private String commonValue1;

    private String commonValue2;

}
