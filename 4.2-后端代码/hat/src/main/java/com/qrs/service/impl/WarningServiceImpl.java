package com.qrs.service.impl;

import com.qrs.pojo.Warning;
import com.qrs.mapper.WarningMapper;
import com.qrs.service.IWarningService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
public class WarningServiceImpl extends ServiceImpl<WarningMapper, Warning> implements IWarningService {
    @Autowired
    private WarningMapper warningMapper;
    @Override
    public List<Warning> getByWhy(String why) {
        return warningMapper.getByWhy(why);
    }
}
