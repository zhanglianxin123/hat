package com.qrs.pojo.DTO;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Author zlx
 * @description
 * @Version 1.0
 * @Date 2021/6/16 9:58
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class PageDTO {
    @ApiModelProperty(value = "第几页")
    private int current;

    @ApiModelProperty(value = "每页条数")
    private int size;
}
