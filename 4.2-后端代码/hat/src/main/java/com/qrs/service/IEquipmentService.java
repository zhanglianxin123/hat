package com.qrs.service;

import com.qrs.pojo.DTO.EquipmentDTO;
import com.qrs.pojo.DTO.PointDTO;
import com.qrs.pojo.DTO.UserDTO4;
import com.qrs.pojo.Equipment;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zlx
 * @since 2021-06-21
 */
public interface IEquipmentService extends IService<Equipment> {
    /**
     * 查询全部设备,分页
     * @return
     */
    List<EquipmentDTO> getList();

    /**
     * 查询单个设备
     * @param equipment_id
     * @return
     */
    List<EquipmentDTO> getSelectById(String equipment_id);

    /**
     * 获得定位人员列表
     * @return
     */
    List<UserDTO4> getUserList();

    /**
     * 通过设备id获得用户名
     * @param equipment_id
     * @return
     */
    String getNameById(int equipment_id);
}
