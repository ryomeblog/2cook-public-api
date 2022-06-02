package tocook.dto;

import lombok.*;
import lombok.experimental.*;
import tocook.dto.generator.*;

/**
 * 「cook_mst」と「food_mst」テーブルの結合用DTO
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class CookMstDTO extends CookMst {

    private String commonValue1;
    private String commonValue2;

}
