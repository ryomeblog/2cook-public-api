package tocook.request.routine;

import javax.validation.constraints.*;

import lombok.*;
import tocook.validations.*;

/**
 * API023 - ルーティン検索機能 リクエスト
 */
@Data
public class GetRoutineRequest {

    /** ルーティンID */
    @Size(min = 15, max = 15)
    private String routineId;

    /** ルーティン名 */
    @Size(min = 1, max = 200)
    private String routineName;

    /** ソート項目 */
    @Pattern(regexp = "[12]")
    private String orderBy = "1";

    /** 昇順／降順 */
    @Pattern(regexp = "[12]")
    private String ascOrDesc = "1";

    /** 最大取得数 */
    @IntegerType(min = 1, max = 2147483647)
    private String limit = "100";

    /** オフセット */
    @IntegerType(min = 0, max = 2147483647)
    private String offset = "0";
}
