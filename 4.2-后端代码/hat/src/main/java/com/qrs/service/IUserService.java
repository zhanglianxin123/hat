package com.qrs.service;

import com.qrs.pojo.DTO.UserDTO2;
import com.qrs.pojo.User;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zlx
 * @since 2021-06-15
 */
public interface IUserService extends IService<User> {
    /**
     * 通过名字查询用户信息
     * @param user_name
     * @return
     */
    List<UserDTO2> getListByName(String user_name);

    /**
     * 获得用户列表
     * @return
     */
    List<UserDTO2> getList();

    /**
     * 通过用户id
     * @param ch
     * @return
     */
    List<UserDTO2> getListById(String ch);

    List<UserDTO2> getListByName2(String ch);


    List<User> getList1(int fromIndex, int size);
}
