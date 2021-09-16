package com.qrs.service.impl;

import com.qrs.pojo.DTO.PointDTO;
import com.qrs.pojo.SignPoint;
import com.qrs.mapper.SignPointMapper;
import com.qrs.service.ISignPointService;
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
public class SignPointServiceImpl extends ServiceImpl<SignPointMapper, SignPoint> implements ISignPointService {
    @Resource
    private SignPointMapper signPointMapper;
    /**
     * 通过签到区域id获得点列表
     * @param sign_id
     * @return
     */
    @Override
    public List<PointDTO> getListById(Integer sign_id) {
        return signPointMapper.getListById(sign_id);
    }
}
