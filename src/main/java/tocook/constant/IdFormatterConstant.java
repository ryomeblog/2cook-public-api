package tocook.constant;

/**
 * ID発行時利用定数クラス
 */
public class IdFormatterConstant {

    /** ユーザID prefix */
    public static final String USER_ID_PREFIX = "user";

    /** ユーザID フォーマット */
    public static final String USER_ID_FORMAT = USER_ID_PREFIX + "%08d";

    /** 食事ID prefix */
    public static final String FOOD_ID_PREFIX = "food";

    /** 食事ID フォーマット */
    public static final String FOOD_ID_FORMAT = FOOD_ID_PREFIX + "%08d";

    /** 料理ID prefix */
    public static final String COOK_ID_PREFIX = "cook";

    /** 料理ID フォーマット */
    public static final String COOK_ID_FORMAT = COOK_ID_PREFIX + "%08d";

    /** 料理食材ID prefix */
    public static final String CF_ID_PREFIX = "cf";

    /** 料理食材ID フォーマット */
    public static final String CF_ID_FORMAT = CF_ID_PREFIX + "%010d";

    /** ルーティンID prefix */
    public static final String ROUTINE_ID_PREFIX = "routine";

    /** ルーティンID フォーマット */
    public static final String ROUTINE_ID_FORMAT = ROUTINE_ID_PREFIX + "%08d";

    /** ルーティン食事ID prefix */
    public static final String ROUTINE_EAT_ID_PREFIX = "routine_eat";

    /** ルーティン食事ID フォーマット */
    public static final String ROUTINE_EAT_ID_FORMAT = ROUTINE_EAT_ID_PREFIX + "%09d";

    /** スケジュールID prefix */
    public static final String SCHEDULE_ID_PREFIX = "schedule";

    /** スケジュールID フォーマット */
    public static final String SCHEDULE_ID_FORMAT = SCHEDULE_ID_PREFIX + "%010d";

}
