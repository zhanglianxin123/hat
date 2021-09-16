package com.qrs.service.impl;

import com.qrs.pojo.DTO.UserDTO2;
import com.qrs.pojo.User;
import com.qrs.mapper.UserMapper;
import com.qrs.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zlx
 * @since 2021-06-15
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {
    @Resource
    private UserMapper userMapper;
    /**
     * 通过名字查询用户信息
     * @param user_name
     * @return
     */
    @Override
    public List<UserDTO2> getListByName(String user_name) {

        return userMapper.getListByName(user_name);
    }

    /**
     * 获得用户列表
     * @return
     */
    @Override
    public List<UserDTO2> getList() {
        return userMapper.getList();
    }

    /**
     * 通过用户id
     * @param ch
     * @return
     */
    @Override
    public List<UserDTO2> getListById(String ch) {
        return userMapper.getListById(ch);
    }

    @Override
    public List<UserDTO2> getListByName2(String ch) {
         return userMapper.getListByName2(ch);
    }

    @Override
    public List<User> getList1(int fromIndex, int size) {
        return userMapper.getList1(fromIndex,size);
    }
}
