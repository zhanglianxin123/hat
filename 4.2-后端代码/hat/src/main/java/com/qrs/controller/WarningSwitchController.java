package com.qrs.controller;


import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.qrs.pojo.DTO.WarningSwitchDTO;
import com.qrs.pojo.RespBean;
import com.qrs.pojo.User;
import com.qrs.pojo.Warning;
import com.qrs.pojo.WarningSwitch;
import com.qrs.service.IWarningService;
import com.qrs.service.IWarningSwitchService;
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
 * @since 2021-06-25
 */
@RestController
@Api(value = "安全帽报警开关模块" ,tags = "hat报警开关接口")
@RequestMapping("//warning-switch")
public class WarningSwitchController {
    @Autowired
    private IWarningSwitchService warningSwitchService;
    @Autowired
    private IWarningService warningService;

    @GetMapping("/getList")
    @ApiOperation(value = "获得开关列表")
    public RespBean getList(){
        List<WarningSwitch> list = warningSwitchService.list();
        return  RespBean.success("成功",list);
    }


    @PostMapping("/setBYid")
    @ApiOperation(value = "修改状态")
    public RespBean setById(@RequestBody WarningSwitchDTO warningSwitchDTO){
        UpdateWrapper<WarningSwitch> wrapper = new UpdateWrapper();
        wrapper.eq("switch_id",warningSwitchDTO.getSwitch_id())
        .set("switch_state",warningSwitchDTO.getSwitch_state());


        QueryWrapper<WarningSwitch> wrapper1 = new QueryWrapper();
        wrapper1.eq("switch_id",warningSwitchDTO.getSwitch_id());
        List<WarningSwitch> list1 = warningSwitchService.list(wrapper1);
        String why=list1.get(0).getSwitch_name();

        List<Warning> list2 = warningService.getByWhy("%"+why+"%");

        UpdateWrapper<Warning> wrapper3 = new UpdateWrapper();
        wrapper3.eq("id",list2.get(list2.size()-1).getId())
                .set("shutdown_time", DateUtil.now());
        warningService.update(wrapper3);
        if(warningSwitchService.update(wrapper)){
            return  RespBean.success("成功");
        }
        return  RespBean.success("失败");
    }


    @GetMapping("/getListWarning")
    @ApiOperation(value = "获得警报列表")
    public RespBean getListWarning(){
        List<Warning> list = warningService.list();
        return  RespBean.success("成功",list);
    }


}
