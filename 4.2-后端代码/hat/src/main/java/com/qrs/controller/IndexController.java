package com.qrs.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.qrs.pojo.*;
import com.qrs.pojo.DTO.FenceDTO;
import com.qrs.pojo.DTO.PointDTO;
import com.qrs.pojo.DTO.PointDTO2;
import com.qrs.pojo.DTO.UserDTO4;
import com.qrs.service.IEquipmentService;
import com.qrs.service.IPositioningService;
import com.qrs.service.IUserService;
import com.qrs.service.IWarningService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author zlx
 * @description
 * @Version 1.0
 * @Date 2021/6/24 19:41
 */
@RestController
@RequestMapping("//index")
@Api(value = "首页接口", tags = "hat首页接口")
public class IndexController {
    @Autowired
    private IPositioningService positioningService;
    @Autowired
    private IEquipmentService equipmentService;

    @Autowired
    private IUserService userService;
    @Autowired
    private IWarningService warningService;

    @GetMapping("/getPoint")
    @ApiOperation("获得人员定位列表")
    public RespBean getPoint(){
        QueryWrapper<Equipment> wrapper=new QueryWrapper<>();
        wrapper.eq("employ",1);
        List<Equipment> list = equipmentService.list(wrapper);
        List<PointDTO2> list1=new ArrayList<>();
        for(Equipment equipment:list){
            QueryWrapper<Positioning> wrapper1=new QueryWrapper<>();
            wrapper1.eq("equipment_id",equipment.getEquipment_id());
            List<Positioning> list2 = positioningService.list(wrapper1);
            Positioning positioning = list2.get(list2.size() - 1);
            PointDTO2 pointDTO2=new PointDTO2(positioning.getEquipment_id(),new PointDTO(positioning.getLng(),positioning.getLat()));
            list1.add(pointDTO2);
        }
        return RespBean.success("成功",list1);
    }

    @PostMapping("/getById")
    @ApiOperation("通过id获得信息")
    public RespBean getPoint(@RequestBody Integer user_id){
        QueryWrapper<User> wrapper=new QueryWrapper<>();
        wrapper.eq("user_id",user_id);
        List<User> list = userService.list(wrapper);
        QueryWrapper<Equipment> wrapper2=new QueryWrapper<>();
        wrapper2.eq("user_id",user_id);
        List<Equipment> list1 = equipmentService.list(wrapper2);

        UserDTO4 userDTO4= UserDTO4.builder()
                .user_id(user_id)
                .user_name(list.get(0).getUser_name())
                .equipment_id(list1.get(0).getEquipment_id())
                .build();
        return  RespBean.success("成功",userDTO4);
    }

    @GetMapping("/getWaring")
    @ApiOperation("获得警告")
    public RespBean getWaring(){
        QueryWrapper<Warning> wrapper=new QueryWrapper<>();
        wrapper.last("limit 10");
        List<Warning> list = warningService.list(wrapper);
        return RespBean.success("成功",list);
    }

    @GetMapping("/get2")
    @ApiOperation("近七天报警统计")
    public RespBean get2(){
        Map<String,Object> map=new HashMap<>();
        List<Equipment> list = equipmentService.list();
        QueryWrapper<Equipment> wrapper=new QueryWrapper<>();
        wrapper.eq("employ",1);
        List<Equipment> list1 = equipmentService.list(wrapper);
        map.put("size",list.size());
        map.put("online",list1.size()/(float)list.size());
        List<Integer>list2=new ArrayList<>();
        list2.add(10);
        list2.add(8);
        list2.add(12);
        list2.add(15);
        list2.add(11);
        list2.add(6);
        list2.add(5);
        map.put("list",list2);
        return RespBean.success("成功",map);
    }


}
