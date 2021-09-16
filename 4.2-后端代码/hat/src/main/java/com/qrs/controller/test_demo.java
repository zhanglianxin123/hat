package com.qrs.controller;

import cn.hutool.core.date.DateUtil;
import com.google.gson.Gson;
import com.qrs.WebSocket.WebSocket;
import com.qrs.onenet.mq.handler.MessageHandler;
import com.qrs.pojo.RespBean;
import com.qrs.utils.module.Message;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;



/**
 * @Author zlx
 * @description
 * @Version 1.0
 * @Date 2021/7/11 19:40
 */
@RestController
@Api(value = "测试接口", tags = "hat测试接口")
public class test_demo {
    @Autowired
    private MessageHandler handler;
    @Autowired
    private WebSocket socket;

    @PostMapping("/onenet")
    @ApiOperation(value = "参数onenet")
    public void onenet(Long id,String msg){
        //F,1,23,3,18
        Message message= Message.builder().body(msg).msgId(id).timestamp(DateUtil.now()).build();
        handler.handle(message);
    }


    @GetMapping("/websocket")
    @ApiOperation(value = "测试websocket")
    public void web(){
        Gson gson = new Gson();
        socket.sendTextMessage("home",gson.toJson(RespBean.success("成功")));
    }
}
