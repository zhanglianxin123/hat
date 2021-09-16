package com.qrs.service.impl;

import com.qrs.pojo.DTO.PointDTO;
import com.qrs.pojo.DTO.TimeDTO;
import com.qrs.pojo.Positioning;
import com.qrs.mapper.PositioningMapper;
import com.qrs.service.IPositioningService;
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
public class PositioningServiceImpl extends ServiceImpl<PositioningMapper, Positioning> implements IPositioningService {

    @Resource
    private PositioningMapper positioningMapper;
    @Override
    public List<PointDTO> getPoint(Integer equipment_id, String time) {
        return positioningMapper.getPoint(equipment_id,time);
    }

    /**
     * 获得历史定位
     * @param equipment_id
     * @return
     */
    @Override
    public List<TimeDTO> getList(Integer equipment_id) {
        return positioningMapper.getList(equipment_id);
    }
}
