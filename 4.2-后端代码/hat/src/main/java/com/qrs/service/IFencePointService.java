package com.qrs.service;

import com.qrs.pojo.DTO.PointDTO;
import com.qrs.pojo.FencePoint;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zlx
 * @since 2021-06-23
 */
public interface IFencePointService extends IService<FencePoint> {
    /**
     * 通过围栏id获得点列表
     * @param fence_id
     * @return
     */
    List<PointDTO> getListByID(Integer fence_id);
}
