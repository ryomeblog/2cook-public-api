package tocook.response.routine;

import lombok.*;

/**
 * API025 - ルーティン登録機能 レスポンス
 */
@Data
@AllArgsConstructor
public class CreateRoutineResponse {

    /** ルーティンID */
    private String routineId;
}
