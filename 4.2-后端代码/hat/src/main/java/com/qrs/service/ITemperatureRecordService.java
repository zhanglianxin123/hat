package com.qrs.service;

import com.qrs.pojo.TemperatureRecord;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zlx
 * @since 2021-06-17
 */
public interface ITemperatureRecordService extends IService<TemperatureRecord> {
    /**
     * 名字设备id查询历史体温
     * @param equipment_id
     * @return
     */
    List<TemperatureRecord> getListByID(Integer equipment_id);
}
