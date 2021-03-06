package tocook.dto.generator;

import java.util.Date;

public class CookFoodTbl {

    /**
     * This field was generated by MyBatis Generator. This field corresponds to the database column cook_food_tbl.cook_food_id
     * @mbg.generated  Tue Jun 14 08:53:12 JST 2022
     */
    private String cookFoodId;
    /**
     * This field was generated by MyBatis Generator. This field corresponds to the database column cook_food_tbl.cook_id
     * @mbg.generated  Tue Jun 14 08:53:12 JST 2022
     */
    private String cookId;
    /**
     * This field was generated by MyBatis Generator. This field corresponds to the database column cook_food_tbl.food_id
     * @mbg.generated  Tue Jun 14 08:53:12 JST 2022
     */
    private String foodId;
    /**
     * This field was generated by MyBatis Generator. This field corresponds to the database column cook_food_tbl.unit_value
     * @mbg.generated  Tue Jun 14 08:53:12 JST 2022
     */
    private Long unitValue;
    /**
     * This field was generated by MyBatis Generator. This field corresponds to the database column cook_food_tbl.user_id
     * @mbg.generated  Tue Jun 14 08:53:12 JST 2022
     */
    private String userId;
    /**
     * This field was generated by MyBatis Generator. This field corresponds to the database column cook_food_tbl.update_datetime
     * @mbg.generated  Tue Jun 14 08:53:12 JST 2022
     */
    private Date updateDatetime;
    /**
     * This field was generated by MyBatis Generator. This field corresponds to the database column cook_food_tbl.version
     * @mbg.generated  Tue Jun 14 08:53:12 JST 2022
     */
    private Short version;

    /**
     * This method was generated by MyBatis Generator. This method returns the value of the database column cook_food_tbl.cook_food_id
     * @return  the value of cook_food_tbl.cook_food_id
     * @mbg.generated  Tue Jun 14 08:53:12 JST 2022
     */
    public String getCookFoodId() {
        return cookFoodId;
    }

    /**
     * This method was generated by MyBatis Generator. This method sets the value of the database column cook_food_tbl.cook_food_id
     * @param cookFoodId  the value for cook_food_tbl.cook_food_id
     * @mbg.generated  Tue Jun 14 08:53:12 JST 2022
     */
    public void setCookFoodId(String cookFoodId) {
        this.cookFoodId = cookFoodId == null ? null : cookFoodId.trim();
    }

    /**
     * This method was generated by MyBatis Generator. This method returns the value of the database column cook_food_tbl.cook_id
     * @return  the value of cook_food_tbl.cook_id
     * @mbg.generated  Tue Jun 14 08:53:12 JST 2022
     */
    public String getCookId() {
        return cookId;
    }

    /**
     * This method was generated by MyBatis Generator. This method sets the value of the database column cook_food_tbl.cook_id
     * @param cookId  the value for cook_food_tbl.cook_id
     * @mbg.generated  Tue Jun 14 08:53:12 JST 2022
     */
    public void setCookId(String cookId) {
        this.cookId = cookId == null ? null : cookId.trim();
    }

    /**
     * This method was generated by MyBatis Generator. This method returns the value of the database column cook_food_tbl.food_id
     * @return  the value of cook_food_tbl.food_id
     * @mbg.generated  Tue Jun 14 08:53:12 JST 2022
     */
    public String getFoodId() {
        return foodId;
    }

    /**
     * This method was generated by MyBatis Generator. This method sets the value of the database column cook_food_tbl.food_id
     * @param foodId  the value for cook_food_tbl.food_id
     * @mbg.generated  Tue Jun 14 08:53:12 JST 2022
     */
    public void setFoodId(String foodId) {
        this.foodId = foodId == null ? null : foodId.trim();
    }

    /**
     * This method was generated by MyBatis Generator. This method returns the value of the database column cook_food_tbl.unit_value
     * @return  the value of cook_food_tbl.unit_value
     * @mbg.generated  Tue Jun 14 08:53:12 JST 2022
     */
    public Long getUnitValue() {
        return unitValue;
    }

    /**
     * This method was generated by MyBatis Generator. This method sets the value of the database column cook_food_tbl.unit_value
     * @param unitValue  the value for cook_food_tbl.unit_value
     * @mbg.generated  Tue Jun 14 08:53:12 JST 2022
     */
    public void setUnitValue(Long unitValue) {
        this.unitValue = unitValue;
    }

    /**
     * This method was generated by MyBatis Generator. This method returns the value of the database column cook_food_tbl.user_id
     * @return  the value of cook_food_tbl.user_id
     * @mbg.generated  Tue Jun 14 08:53:12 JST 2022
     */
    public String getUserId() {
        return userId;
    }

    /**
     * This method was generated by MyBatis Generator. This method sets the value of the database column cook_food_tbl.user_id
     * @param userId  the value for cook_food_tbl.user_id
     * @mbg.generated  Tue Jun 14 08:53:12 JST 2022
     */
    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    /**
     * This method was generated by MyBatis Generator. This method returns the value of the database column cook_food_tbl.update_datetime
     * @return  the value of cook_food_tbl.update_datetime
     * @mbg.generated  Tue Jun 14 08:53:12 JST 2022
     */
    public Date getUpdateDatetime() {
        return updateDatetime;
    }

    /**
     * This method was generated by MyBatis Generator. This method sets the value of the database column cook_food_tbl.update_datetime
     * @param updateDatetime  the value for cook_food_tbl.update_datetime
     * @mbg.generated  Tue Jun 14 08:53:12 JST 2022
     */
    public void setUpdateDatetime(Date updateDatetime) {
        this.updateDatetime = updateDatetime;
    }

    /**
     * This method was generated by MyBatis Generator. This method returns the value of the database column cook_food_tbl.version
     * @return  the value of cook_food_tbl.version
     * @mbg.generated  Tue Jun 14 08:53:12 JST 2022
     */
    public Short getVersion() {
        return version;
    }

    /**
     * This method was generated by MyBatis Generator. This method sets the value of the database column cook_food_tbl.version
     * @param version  the value for cook_food_tbl.version
     * @mbg.generated  Tue Jun 14 08:53:12 JST 2022
     */
    public void setVersion(Short version) {
        this.version = version;
    }
}