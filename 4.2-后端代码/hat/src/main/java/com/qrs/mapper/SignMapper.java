package com.qrs.mapper;

import com.qrs.pojo.DTO.UserDTO3;
import com.qrs.pojo.Sign;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.qrs.pojo.SignRecord;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author zlx
 * @since 2021-06-17
 */
public interface SignMapper extends BaseMapper<Sign> {
    /**
     * 获得签到人员列表
     * @return
     */
    @Select("SELECT tmp.*,h_user.`user_name`,h_user.`user_id`\n" +
            "FROM h_user,h_equipment,(SELECT h_signRecord.`equipment_id`,COUNT(*) AS sign_size\n" +
            "FROM h_signRecord,h_equipment\n" +
            "WHERE h_signRecord.`equipment_id`=h_equipment.`equipment_id` \n" +
            "GROUP BY h_signRecord.`equipment_id`) AS tmp\n" +
            "WHERE tmp.equipment_id=h_equipment.`equipment_id`\n" +
            "AND h_equipment.`user_id`=h_user.`user_id`")
    List<UserDTO3> getUserList();

    @Select("SELECT h_signRecord.*\n" +
            "FROM h_signRecord\n" +
            "WHERE h_signRecord.`equipment_id`= #{equipment_id}\n" +
            "ORDER BY h_signRecord.`create_time` DESC")
    List<SignRecord> getListById(Integer equipment_id);
}
