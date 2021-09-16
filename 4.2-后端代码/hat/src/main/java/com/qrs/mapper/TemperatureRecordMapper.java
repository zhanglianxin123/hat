package com.qrs.mapper;

import com.qrs.pojo.TemperatureRecord;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
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
public interface TemperatureRecordMapper extends BaseMapper<TemperatureRecord> {
    /**
     * 名字设备id查询历史体温
     * @param equipment_id
     * @return
     */
    @Select("SELECT * FROM h_temperatureRecord WHERE equipment_id=#{equipment_id} ORDER BY create_time DESC")
    List<TemperatureRecord> getListByID(Integer equipment_id);
}
