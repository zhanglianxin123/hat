package com.qrs.pojo.DTO;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author zlx
 * @since 2021-06-23
 */
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PointDTO2 implements Serializable {
    @ApiModelProperty(value = "用户id")
    private Integer user_id;

    @ApiModelProperty(value = "点")
    private PointDTO point;


}
