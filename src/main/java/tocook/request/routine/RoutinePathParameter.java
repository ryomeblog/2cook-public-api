package tocook.request.routine;

import javax.validation.constraints.*;

import lombok.*;

/**
 * ルーティン管理機能 パスパラメータ
 */
@Data
public class RoutinePathParameter {

    /** ルーティンID */
    @NotNull
    @Size(min = 15, max = 15)
    private String routineId;
}
