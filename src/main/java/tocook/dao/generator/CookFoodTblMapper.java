package tocook.dao.generator;

import tocook.dto.generator.CookFoodTbl;

public interface CookFoodTblMapper {

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table cook_food_tbl
     * @mbg.generated  Tue Jun 14 08:53:12 JST 2022
     */
    int deleteByPrimaryKey(String cookFoodId);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table cook_food_tbl
     * @mbg.generated  Tue Jun 14 08:53:12 JST 2022
     */
    int insert(CookFoodTbl record);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table cook_food_tbl
     * @mbg.generated  Tue Jun 14 08:53:12 JST 2022
     */
    int insertSelective(CookFoodTbl record);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table cook_food_tbl
     * @mbg.generated  Tue Jun 14 08:53:12 JST 2022
     */
    CookFoodTbl selectByPrimaryKey(String cookFoodId);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table cook_food_tbl
     * @mbg.generated  Tue Jun 14 08:53:12 JST 2022
     */
    int updateByPrimaryKeySelective(CookFoodTbl record);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table cook_food_tbl
     * @mbg.generated  Tue Jun 14 08:53:12 JST 2022
     */
    int updateByPrimaryKey(CookFoodTbl record);
}