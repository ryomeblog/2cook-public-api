package tocook.constant;

/**
 * 各APIのURL一覧
 */
public class ApiUri {

    /** 認証機能 */
    public static final String LOGIN = "/user/login";
    public static final String LOGOUT = "/user/logout";

    /** ユーザ管理機能 */
    public static final String SIGNUP = "/signup";
    public static final String SEARCH = "/user/search";
    public static final String ACCOUNT = "/account";
    public static final String ACCOUNT_LOCK = "/account/lock";
    public static final String QUESTION = "/question";

    /** 食材管理機能 */
    public static final String FOOD = "/food";
    public static final String FOOD_ID = "/food/{foodId}";

    /** 料理管理機能 */
    public static final String COOK = "/cook";
    public static final String COOK_ID = "/cook/{cookId}";

    /** ルーティン管理機能 */
    public static final String ROUTINE = "/routine";
    public static final String ROUTINE_ID = "/routine/{routineId}";

    /** スケジュール管理機能 */
    public static final String SCHEDULE = "/schedule";
    public static final String SCHEDULE_DATE = "/schedule/{scheduleDate}";
    public static final String SCHEDULE_DELETE_ID = "/schedule/delete/{scheduleId}";
    public static final String SCHEDULE_ROUTINE = "/schedule/routine/";

}
