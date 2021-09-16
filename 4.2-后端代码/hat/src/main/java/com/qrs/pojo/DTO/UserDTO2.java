package com.qrs.pojo.DTO;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 
 * </p>
 *
 * @author zlx
 * @since 2021-06-15
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("h_user")
@ApiModel(value="User对象", description="")
public class UserDTO2 implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "工号")
    private Integer user_id;

    @ApiModelProperty(value = "姓名")
    private String user_name;

    @ApiModelProperty(value = "部门id")
    private Integer department_id;

    @ApiModelProperty(value = "部门名称")
    private String department_name;

    @ApiModelProperty(value = "职务")
    private String role;

    @ApiModelProperty(value = "密码")
    private String user_password;

    @ApiModelProperty(value = "性别")
    private String sex;

    @ApiModelProperty(value = "身份证号")
    private String id_card;

    @ApiModelProperty(value = "家庭住址")
    private String address;

    @ApiModelProperty(value = "电话号码")
    private String phone;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime update_time;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime create_time;


}
