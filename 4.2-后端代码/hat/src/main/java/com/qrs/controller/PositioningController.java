package com.qrs.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.qrs.pojo.DTO.PointDTO;
import com.qrs.pojo.DTO.PointDTO3;
import com.qrs.pojo.DTO.TimeDTO;
import com.qrs.pojo.DTO.UserDTO4;
import com.qrs.pojo.Positioning;
import com.qrs.pojo.RespBean;
import com.qrs.service.IEquipmentService;
import com.qrs.service.IPositioningService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author zlx
 * @since 2021-06-17
 */
@RestController
@RequestMapping("//positioning")
@Api(value = "定位接口", tags = "hat定位接口")
public class PositioningController {
    @Autowired
    private IPositioningService positioningService;

    @Autowired
    private IEquipmentService equipmentService;

    @GetMapping("/getUserList")
    @ApiOperation("获得定位人员列表")
    private RespBean getList(){
        List<UserDTO4> list=equipmentService.getUserList();
        return RespBean.success("成功",list);
    }

    @PostMapping("/getPoint")
    @ApiOperation("获得人员当天定位")
    private RespBean getPoint(@RequestBody PointDTO3 pointDTO3){
        List<PointDTO> list=positioningService.getPoint(pointDTO3.getEquipment_id(),pointDTO3.getTime());
        return RespBean.success("成功",list);
    }

    @PostMapping("/historicalPositioning")
    @ApiOperation("获得历史定位")
    private RespBean getHistoricalPositioning(@RequestBody Integer equipment_id){
        List<TimeDTO> list = positioningService.getList(equipment_id);
        return RespBean.success("成功",list);

    }

}
