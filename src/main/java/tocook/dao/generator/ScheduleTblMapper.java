package tocook.dao.generator;

import tocook.dto.generator.ScheduleTbl;

public interface ScheduleTblMapper {

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table schedule_tbl
     * @mbg.generated  Tue Jun 14 08:53:12 JST 2022
     */
    int deleteByPrimaryKey(String scheduleId);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table schedule_tbl
     * @mbg.generated  Tue Jun 14 08:53:12 JST 2022
     */
    int insert(ScheduleTbl record);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table schedule_tbl
     * @mbg.generated  Tue Jun 14 08:53:12 JST 2022
     */
    int insertSelective(ScheduleTbl record);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table schedule_tbl
     * @mbg.generated  Tue Jun 14 08:53:12 JST 2022
     */
    ScheduleTbl selectByPrimaryKey(String scheduleId);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table schedule_tbl
     * @mbg.generated  Tue Jun 14 08:53:12 JST 2022
     */
    int updateByPrimaryKeySelective(ScheduleTbl record);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table schedule_tbl
     * @mbg.generated  Tue Jun 14 08:53:12 JST 2022
     */
    int updateByPrimaryKey(ScheduleTbl record);
}