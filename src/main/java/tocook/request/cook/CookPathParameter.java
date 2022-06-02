package tocook.request.cook;

import javax.validation.constraints.*;

import lombok.*;

/**
 * 料理管理機能 パスパラメータ
 */
@Data
public class CookPathParameter {

    /** 料理ID */
    @NotNull
    @Size(min = 12, max = 12)
    private String cookId;
}
