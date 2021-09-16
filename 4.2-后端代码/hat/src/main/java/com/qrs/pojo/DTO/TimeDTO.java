package com.qrs.pojo.DTO;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author zlx
 * @description
 * @Version 1.0
 * @Date 2021/6/26 11:15
 */
@Data
public class TimeDTO {
    @ApiModelProperty(value = "时间")
    private String times;
}
