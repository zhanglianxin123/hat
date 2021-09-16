package com.qrs.pojo;

import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.google.gson.Gson;
import com.qrs.WebSocket.WebSocket;
import com.qrs.service.IEquipmentService;
import com.qrs.service.IWarningService;
import com.qrs.service.IWarningSwitchService;
import com.qrs.utils.OkHttpUtil2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author zlx
 * @description
 * @Version 1.0
 * @Date 2021/7/2 17:02
 */
@Component
public class WarningUtils {
    @Autowired
    private  IWarningSwitchService warningSwitchService;

    @Autowired
    private  IEquipmentService equipmentService;

    @Autowired
    private  IWarningService warningService;

    @Autowired
    private WebSocket webSocket;

    public  void mag(int magId,int switch_id,int equipment_id,String number,String why){
        UpdateWrapper<WarningSwitch> wrapper = new UpdateWrapper();
        wrapper.eq("switch_id",switch_id).set("switch_state",1);
        warningSwitchService.update(wrapper);

        String name=equipmentService.getNameById(equipment_id);
        Warning warning=Warning.builder().warning_id(magId)
                .user_name(name).warning_number(number).warning_name(why)
                .warning_why(name+"在"+ DateUtil.now()+why).warning_time(DateUtil.now()).build();

        warningService.save(warning);

        //websocket
        Map<String,Object> map=new HashMap<>();
        map.put("warning",warning);
        Gson gson=new Gson();
        webSocket.sendTextMessage("home",gson.toJson(map));

        //下发报警
        if(switch_id==4){
            OkHttpUtil2 ok=new OkHttpUtil2();
            System.out.println(ok.issue("A,1"));
        }
    }
}
