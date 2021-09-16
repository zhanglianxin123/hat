package com.qrs.pojo.DTO;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
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
@Builder
@Data

public class UserDTO4 implements Serializable {


    @ApiModelProperty(value = "工号")
    private Integer user_id;

    @ApiModelProperty(value = "姓名")
    private String user_name;

    @ApiModelProperty(value = "设备id")
    private Integer equipment_id;






}
