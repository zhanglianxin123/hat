package com.qrs.mapper;

import com.qrs.pojo.DTO.PointDTO;
import com.qrs.pojo.DTO.TimeDTO;
import com.qrs.pojo.Positioning;
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
public interface PositioningMapper extends BaseMapper<Positioning> {

    @Select("SELECT lng,lat\n" +
            "FROM h_positioning\n" +
            "WHERE equipment_id=#{equipment_id} AND DATE_FORMAT(create_time,'%Y-%m-%d') = #{time}")
    List<PointDTO> getPoint(@Param(value = "equipment_id") Integer equipment_id, @Param(value = "time") String time);

    @Select("SELECT DISTINCT  DATE_FORMAT(create_time,'%Y-%m-%d') as times\n" +
            "FROM h_positioning\n" +
            "WHERE equipment_id=#{equipment_id}")
    List<TimeDTO> getList(Integer equipment_id);
}
