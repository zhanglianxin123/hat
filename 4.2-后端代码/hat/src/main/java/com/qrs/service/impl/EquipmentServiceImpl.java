package com.qrs.service.impl;

import com.qrs.pojo.DTO.EquipmentDTO;
import com.qrs.pojo.DTO.PointDTO;
import com.qrs.pojo.DTO.UserDTO4;
import com.qrs.pojo.Equipment;
import com.qrs.mapper.EquipmentMapper;
import com.qrs.service.IEquipmentService;
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
 * @since 2021-06-21
 */
@Service
public class EquipmentServiceImpl extends ServiceImpl<EquipmentMapper, Equipment> implements IEquipmentService {


   @Resource
   private EquipmentMapper equipmentMapper;
    /**
     * 查询全部设备,分页
     * @return
     */
    @Override
    public List<EquipmentDTO> getList() {
        return equipmentMapper.getList();
    }

    /**
     * 查询单个设备
     * @param equipment_id
     * @return
     */
    @Override
    public List<EquipmentDTO> getSelectById(String equipment_id) {
        return equipmentMapper.getSelectById(equipment_id);
    }

    /**
     * 获得定位人员列表
     * @return
     */
    @Override
    public List<UserDTO4> getUserList() {
        return equipmentMapper.getUserList();
    }

    @Override
    public String getNameById(int equipment_id) {
        return equipmentMapper.getNameByID(equipment_id);
    }


}
