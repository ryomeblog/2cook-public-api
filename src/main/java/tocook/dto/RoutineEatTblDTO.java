package tocook.dto;

import lombok.*;
import lombok.experimental.*;
import tocook.dto.generator.*;

/**
 * 「routine_eat_tbl」と「cook_mst」テーブルの結合用DTO
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class RoutineEatTblDTO extends RoutineEatTbl {

    private String cookName;
    private String cookProcess;
    private Long cookPrice;
    private String cookMovie;
    private String cookUrl;
    private Boolean cookFlg;
}
