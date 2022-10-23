package tocook.dto;

import lombok.*;
import lombok.experimental.*;
import tocook.dto.generator.*;

/**
 * 「routine_mst」と「routine_eat_tbl」テーブルの結合用DTO
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class RoutineMstDTO extends RoutineMst {

    private String routineEatId;
    private String cookId;
    private Short routineDay;
    private Short routineTime;
}
