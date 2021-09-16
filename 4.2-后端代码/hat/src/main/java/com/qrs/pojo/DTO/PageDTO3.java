package com.qrs.pojo.DTO;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class PageDTO3 {
    @ApiModelProperty(value = "第几页")
    private int current;

    @ApiModelProperty(value = "每页条数")
    private int size;
    @ApiModelProperty(value = "查询信息")
    private String ch;
}

