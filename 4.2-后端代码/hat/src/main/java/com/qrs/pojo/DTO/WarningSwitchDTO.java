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
 * @since 2021-06-25
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class WarningSwitchDTO implements Serializable {


    @ApiModelProperty(value = "报警开关id")
    private Integer switch_id;



    @ApiModelProperty(value = "开关状态")
    private Integer switch_state;



}
