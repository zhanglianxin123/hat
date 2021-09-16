package com.qrs.service;

import com.qrs.pojo.DTO.TemperatureDTO;
import com.qrs.pojo.Temperature;
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
public interface ITemperatureService extends IService<Temperature> {
    /**
     * 获得列表
     * @param fromIndex
     * @param size
     * @return
     */
    List<TemperatureDTO> getList(int fromIndex, int size);

    /**
     * 名字查询
     * @param ch
     * @return
     */
    List<TemperatureDTO> getListByName(String ch);
}
