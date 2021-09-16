package com.qrs.mapper;

import com.qrs.pojo.DTO.UserDTO2;
import com.qrs.pojo.User;
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
 * @since 2021-06-15
 */
public interface UserMapper extends BaseMapper<User> {
    /**
     *通过名字查询用户信息
     * @param user_name
     * @return
     */
    @Select("SELECT h_user.*,h_department.`department_name` FROM h_user,h_department " +
            "WHERE h_user.`department_id`=h_department.`department_id` and h_user.user_name like(#{user_name}) ")
    List<UserDTO2> getListByName(@Param("user_name") String user_name);

    /**
     * 获得用户列表
     * @return
     */
    @Select("SELECT h_user.*,h_department.`department_name` FROM h_user,h_department WHERE h_user.`department_id`=h_department.`department_id` ")
    List<UserDTO2> getList();

    /**
     * 通过用户id
     * @param ch
     * @return
     */
    @Select("SELECT h_user.*,h_department.`department_name` FROM h_user,h_department " +
            "WHERE h_user.`department_id`=h_department.`department_id` and h_user.user_id like(#{ch})")
    List<UserDTO2> getListById(String ch);

    @Select("SELECT h_user.*,h_department.`department_name` FROM h_user,h_department " +
            "WHERE h_user.`department_id`=h_department.`department_id` and h_user.user_name like(#{user_name}) ")
    List<UserDTO2> getListByName2(String user_name);

    @Select("select * from h_user limit #{fromIndex},#{size} ")
    List<User> getList1(@Param("fromIndex") int fromIndex,@Param("size") int size);
}
