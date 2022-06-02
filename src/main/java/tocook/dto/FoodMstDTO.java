package tocook.dto;

import lombok.*;
import lombok.experimental.*;
import tocook.dto.generator.*;

/**
 * 「food_mst」と「common_mst」テーブルの結合用DTO
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class FoodMstDTO extends FoodMst {

    private String commonValue1;

    private String commonValue2;

}
