package com.qrs.mapper;

import com.qrs.pojo.DTO.TemperatureDTO;
import com.qrs.pojo.Temperature;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author zlx
 * @since 2021-06-17
 */
public interface TemperatureMapper extends BaseMapper<Temperature> {
    /**
     * 获得列表
     * @param fromIndex
     * @param size
     * @return
     */
    @Select("SELECT h_temperature.*,h_user.`user_name`,h_department.`department_name`\n" +
            "FROM h_temperature,h_user,h_department\n" +
            "WHERE h_temperature.`user_id`=h_user.`user_id`\n" +
            "AND h_user.`department_id`=h_department.`department_id`\n" +
            "LIMIT #{fromIndex},#{size}")
    List<TemperatureDTO> getList(@Param("fromIndex") int fromIndex, @Param("size") int size);

    /**
     * 名字查询
     * @param ch
     * @return
     */
    @Select("SELECT h_temperature.*,h_user.`user_name`,h_department.`department_name`\n" +
            "FROM h_temperature,h_user,h_department\n" +
            "WHERE h_temperature.`user_id`=h_user.`user_id`\n" +
            "AND h_user.`department_id`=h_department.`department_id`\n" +
            "and h_user.user_name=#{ch}")
    List<TemperatureDTO> getListByName(String ch);
}
