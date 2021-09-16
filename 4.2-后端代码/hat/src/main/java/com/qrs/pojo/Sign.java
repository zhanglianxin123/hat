package com.qrs.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author zlx
 * @since 2021-06-17
 */
@Builder
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("h_sign")
@ApiModel(value="Sign对象", description="")
public class Sign implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "签到区域id")
    private Integer sign_id;

    @ApiModelProperty(value = "签到区域编号")
    private String sign_number;

    @ApiModelProperty(value = "签到区域名称")
    private String sign_name;

    @ApiModelProperty(value = "签到区域备注")
    private String sign_node;



    @ApiModelProperty(value = "更新时间")
    private LocalDateTime update_time;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime create_time;


}
