package com.qrs.service;

import com.qrs.pojo.DTO.PointDTO;
import com.qrs.pojo.SignPoint;
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
public interface ISignPointService extends IService<SignPoint> {
    /**
     * 通过签到区域id获得点列表
     * @param sign_id
     * @return
     */
    List<PointDTO> getListById(Integer sign_id);
}
