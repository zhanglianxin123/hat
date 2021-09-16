package com.qrs.mapper;

import com.qrs.pojo.DTO.EquipmentDTO;
import com.qrs.pojo.DTO.PointDTO;
import com.qrs.pojo.DTO.UserDTO4;
import com.qrs.pojo.Equipment;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author zlx
 * @since 2021-06-21
 */
public interface EquipmentMapper extends BaseMapper<Equipment> {
    /**
     * 查询全部设备,分页
     * @return
     */
    @Select("SELECT h_equipment.*,h_user.`user_name` FROM h_equipment,h_user WHERE h_user.`user_id`=h_equipment.`user_id`")
    List<EquipmentDTO> getList();
    @Select("SELECT h_equipment.*,h_user.`user_name` FROM h_equipment,h_user WHERE h_user.`user_id`=h_equipment.`user_id` and h_equipment.equipment_id=#{equipment_id}")
    List<EquipmentDTO> getSelectById(String equipment_id);

    /**
     * 获得定位人员列表
     * @return
     */
    @Select(" SELECT h_equipment.`user_id`,h_user.`user_name`,h_equipment.`equipment_id`t\n" +
            " FROM h_equipment,h_user\n" +
            " WHERE h_equipment.`user_id`=h_user.`user_id` AND h_equipment.`employ`=1")
    List<UserDTO4> getUserList();


    /**
     * 通过设备id获得用户名
     * @param equipment_id
     * @return
     */
    @Select("SELECT h_user.`user_name`\n" +
            "FROM h_user,h_equipment\n" +
            "WHERE h_user.user_id = h_equipment.user_id\n" +
            "AND h_equipment.equipment_id=#{equipment_id}")
    String getNameByID(int equipment_id);

    /**
     * 获得人员当天定位
     * @param equipment_id
     * @param time
     * @return
     */

}
