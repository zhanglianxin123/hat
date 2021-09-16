package com.qrs.service;

import com.qrs.pojo.DTO.UserDTO3;
import com.qrs.pojo.Sign;
import com.baomidou.mybatisplus.extension.service.IService;
import com.qrs.pojo.SignRecord;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zlx
 * @since 2021-06-17
 */
public interface ISignService extends IService<Sign> {
    /**
     * 获得签到人员列表
     * @return
     */
    List<UserDTO3> getUserList();

    /**
     * 通过设备id获得签到记录
     * @param equipment_id
     * @return
     */
    List<SignRecord> getListById(Integer equipment_id);
}
