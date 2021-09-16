package com.qrs.service.impl;

import com.qrs.pojo.TemperatureRecord;
import com.qrs.mapper.TemperatureRecordMapper;
import com.qrs.service.ITemperatureRecordService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zlx
 * @since 2021-06-17
 */
@Service
public class TemperatureRecordServiceImpl extends ServiceImpl<TemperatureRecordMapper, TemperatureRecord> implements ITemperatureRecordService {
    @Resource
    private TemperatureRecordMapper temperatureRecordMapper;
    /**
     * 名字设备id查询历史体温
     * @param equipment_id
     * @return
     */
    @Override
    public List<TemperatureRecord> getListByID(Integer equipment_id) {
        return temperatureRecordMapper.getListByID(equipment_id);
    }
}
