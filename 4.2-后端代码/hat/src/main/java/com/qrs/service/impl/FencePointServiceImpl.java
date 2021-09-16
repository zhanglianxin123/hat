package com.qrs.service.impl;

import com.qrs.pojo.DTO.PointDTO;
import com.qrs.pojo.FencePoint;
import com.qrs.mapper.FencePointMapper;
import com.qrs.service.IFencePointService;
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
 * @since 2021-06-23
 */
@Service
public class FencePointServiceImpl extends ServiceImpl<FencePointMapper, FencePoint> implements IFencePointService {
    @Resource
    private FencePointMapper fencePointMapper;
    /**
     * 通过围栏id获得点列表
     * @param fence_id
     * @return
     */
    @Override
    public List<PointDTO> getListByID(Integer fence_id) {
        return fencePointMapper.getListByID(fence_id);
    }
}
