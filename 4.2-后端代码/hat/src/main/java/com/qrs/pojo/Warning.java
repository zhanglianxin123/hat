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
@TableName("h_warning")
@ApiModel(value="Warning对象", description="")
public class Warning implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "警告id")
    private Integer warning_id;
    @ApiModelProperty(value = "用户名")
    private String user_name;

    @ApiModelProperty(value = "警告编号")
    private String warning_number;

    @ApiModelProperty(value = "警告名称")
    private String warning_name;

    @ApiModelProperty(value = "警告原因")
    private String warning_why;

    @ApiModelProperty(value = "警告时间")
    private String warning_time;

    @ApiModelProperty(value = "警告关闭时间")
    private String shutdown_time;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime update_time;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime create_time;


}
