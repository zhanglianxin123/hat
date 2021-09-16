package com.qrs.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.qrs.pojo.DTO.EquipmentDTO;
import com.qrs.pojo.DTO.PageDTO;
import com.qrs.pojo.Equipment;
import com.qrs.pojo.RespBean;
import com.qrs.pojo.User;
import com.qrs.service.IEquipmentService;
import com.qrs.service.IUserService;
import com.qrs.utils.PageUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
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
@RequestMapping("//equipment")
@Api(value = "安全帽模块", tags = "hat设备管理接口")
public class EquipmentController {
    @Autowired
    private IEquipmentService equipmentService;

    @Autowired
    private IUserService userService;

    @GetMapping("/getSize")
    @ApiOperation("获得设备数")
    public RespBean getSize(){
        List<Equipment> list = equipmentService.list();
        return RespBean.success("成功",list.size());
    }


    @PostMapping("/getList")
    @ApiOperation("查询全部设备,分页")
    public RespBean getList(@RequestBody PageDTO pageDTO){
        int current=pageDTO.getCurrent();
        int size=pageDTO.getSize();
        List<Object>list=Collections.singletonList(equipmentService.getList());
        List<Object> page = PageUtils.page(list, current, size);
        return RespBean.success("成功",page);
    }

    @PostMapping("/equipment")
    @ApiOperation("查询单个设备")
    public RespBean getListById(@RequestBody String ch){
        String[] split = ch.split("\"");
        ch=split[1];
        List<EquipmentDTO>equipment= equipmentService.getSelectById(ch);
        return RespBean.success("成功",equipment);
    }
    @PostMapping("/add")
    @ApiOperation("添加设备")
    public RespBean add(@RequestBody Equipment equipment){
        QueryWrapper<User> wrapper = new QueryWrapper();
        wrapper.eq("user_id",equipment.getUser_id());
        List<User> list = userService.list(wrapper);
        if(!(list.size()>0)){
            return RespBean.error("用户id不存在");
        }
        if(equipmentService.save(equipment)){
            return RespBean.success("添加成功");
        }
        return RespBean.success("添加失败");
    }
    @PostMapping("delete")
    @ApiOperation("删除设备")
    public RespBean delete(@RequestBody Integer equipment_id){
        QueryWrapper<Equipment>wrapper=new QueryWrapper();
        wrapper.eq("equipment_id",equipment_id);
        if(equipmentService.remove(wrapper)){
            return RespBean.success("删除成功");
        }
        return RespBean.success("删除失败");
    }
    @PostMapping("/update")
    @ApiOperation("更新设备")
    public RespBean update(@RequestBody Equipment equipment){
        QueryWrapper<User> wrapper1 = new QueryWrapper();
        wrapper1.eq("user_id",equipment.getUser_id());
        List<User> list1 = userService.list(wrapper1);
        if(!(list1.size()>0)){
            return RespBean.error("用户id不存在");
        }

        QueryWrapper<Equipment>wrapper=new QueryWrapper();
        wrapper.eq("equipment_id",equipment.getEquipment_id());
        List<Equipment> list = equipmentService.list(wrapper);
        equipmentService.remove(wrapper);
        equipmentService.save(equipment);
        return RespBean.success("更新成功");
    }
}