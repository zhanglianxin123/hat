package com.qrs.service.impl;

import com.qrs.pojo.DTO.TemperatureDTO;
import com.qrs.pojo.Temperature;
import com.qrs.mapper.TemperatureMapper;
import com.qrs.service.ITemperatureService;
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
public class TemperatureServiceImpl extends ServiceImpl<TemperatureMapper, Temperature> implements ITemperatureService {
    @Resource
    private TemperatureMapper temperatureMapper;
    /**
     * 获得列表
     * @param fromIndex
     * @param size
     * @return
     */
    @Override
    public List<TemperatureDTO> getList(int fromIndex, int size) {
        return temperatureMapper.getList(fromIndex,size);
    }

    /**
     * 名字查询
     * @param ch
     * @return
     */
    @Override
    public List<TemperatureDTO> getListByName(String ch) {
        return temperatureMapper.getListByName(ch);
    }
}
