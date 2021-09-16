package com.qrs.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.qrs.pojo.DTO.PageDTO;
import com.qrs.pojo.DTO.PageDTO3;
import com.qrs.pojo.DTO.TemperatureDTO;
import com.qrs.pojo.RespBean;
import com.qrs.pojo.Temperature;
import com.qrs.pojo.TemperatureRecord;
import com.qrs.service.ITemperatureRecordService;
import com.qrs.service.ITemperatureService;
import com.qrs.utils.PageUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author zlx
 * @since 2021-06-17
 */
@RestController
@RequestMapping("//temperature")
@Api(value = "体温接口", tags = "hat人员体温管理接口")
public class TemperatureController {
    @Autowired
    private ITemperatureService temperatureService;

    @Autowired
    private ITemperatureRecordService temperatureRecordService;

    @PostMapping("list")
    @ApiOperation("获得列表")
    public RespBean getList(@RequestBody PageDTO pageDTO){
        int current = pageDTO.getCurrent();
        int size = pageDTO.getSize();
        int fromIndex = (current-1)*size;
        List<Temperature> list = temperatureService.list();
        List<TemperatureDTO> list1 = temperatureService.getList(fromIndex,size);
        Map<String,Object> ob=new HashMap<>();
        ob.put("size",list.size());
        ob.put("list",list1);
        return RespBean.success("成功",ob);
    }

    @PostMapping("getListByName")
    @ApiOperation("名字查询")
    public RespBean getListByName(@RequestBody PageDTO3 pageDTO3){
        int current = pageDTO3.getCurrent();
        int size = pageDTO3.getSize();
        String ch = pageDTO3.getCh();
        int fromIndex = (current-1)*size;
        List<TemperatureDTO> list1 = temperatureService.getListByName(ch);
        List<Object> list2= Collections.singletonList(list1);
        List<Object> page = PageUtils.page(list2,current, size);
        Map<String,Object> ob=new HashMap<>();
        ob.put("size",list1.size());
        ob.put("list",page);
        return RespBean.success("成功",ob);
    }

    @PostMapping("getListByID")
    @ApiOperation("名字设备id查询历史体温")
    public RespBean getListByID(@RequestBody Integer equipment_id){
        List<TemperatureRecord> list=temperatureRecordService.getListByID(equipment_id);
        return RespBean.success("成功",list);
    }

}
