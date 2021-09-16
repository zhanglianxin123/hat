package com.qrs.pojo.DTO;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

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
public class FenceDTO  {

    @ApiModelProperty(value = "围栏id")
    private Integer fence_id;

    @ApiModelProperty(value = "围栏编号")
    private String fence_number;

    @ApiModelProperty(value = "围栏名称")
    private String fence_name;

    @ApiModelProperty(value = "围栏备注")
    private String fence_node;
    @ApiModelProperty(value = "中心点")
    private PointDTO centerPoint;
    @ApiModelProperty(value = "围栏点")
    private List<PointDTO> point;




}
