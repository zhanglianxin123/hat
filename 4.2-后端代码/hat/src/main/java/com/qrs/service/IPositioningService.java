package com.qrs.service;

import com.qrs.pojo.DTO.PointDTO;
import com.qrs.pojo.DTO.TimeDTO;
import com.qrs.pojo.Positioning;
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
public interface IPositioningService extends IService<Positioning> {
    /**
     * 获得人员当天定位
     * @param equipment_id
     * @param time
     * @return
     */
    List<PointDTO> getPoint(Integer equipment_id, String time);

    /**
     * 获得历史定位
     * @param equipment_id
     * @return
     */
    List<TimeDTO> getList(Integer equipment_id);
}
