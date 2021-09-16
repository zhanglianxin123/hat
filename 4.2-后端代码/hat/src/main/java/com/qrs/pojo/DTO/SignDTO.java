package com.qrs.pojo.DTO;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * 
 * </p>
 *
 * @author zlx
 * @since 2021-06-17
 */
@Data
@AllArgsConstructor
@NoArgsConstructor

public class SignDTO implements Serializable {
    @ApiModelProperty(value = "签到区域id")
    private Integer sign_id;

    @ApiModelProperty(value = "签到区域编号")
    private String sign_number;

    @ApiModelProperty(value = "签到区域名称")
    private String sign_name;

    @ApiModelProperty(value = "签到区域备注")
    private String sign_node;

    @ApiModelProperty(value = "签到点")
    private List<PointDTO> point;

    @ApiModelProperty(value = "中心点")
    private PointDTO centerPoint;


}
