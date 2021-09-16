package com.qrs.pojo.DTO;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author zlx
 * @description
 * @Version 1.0
 * @Date 2021/6/25 19:46
 */
@Data
public class PointDTO3 {
    @ApiModelProperty(value = "设备id")
    private Integer equipment_id;

    @ApiModelProperty(value = "时间")
    private String time;
}
