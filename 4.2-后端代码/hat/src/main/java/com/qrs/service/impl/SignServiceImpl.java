package com.qrs.service.impl;

import com.qrs.pojo.DTO.UserDTO3;
import com.qrs.pojo.Sign;
import com.qrs.mapper.SignMapper;
import com.qrs.pojo.SignRecord;
import com.qrs.service.ISignService;
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
public class SignServiceImpl extends ServiceImpl<SignMapper, Sign> implements ISignService {

    @Resource
    private SignMapper signMapper;
    /**
     * 获得签到人员列表
     * @return
     */
    @Override
    public List<UserDTO3> getUserList() {
        return signMapper.getUserList();
    }

    /**
     * 通过设备id获得签到记录
     * @param equipment_id
     * @return
     */
    @Override
    public List<SignRecord> getListById(Integer equipment_id) {
        return signMapper.getListById(equipment_id);
    }
}
