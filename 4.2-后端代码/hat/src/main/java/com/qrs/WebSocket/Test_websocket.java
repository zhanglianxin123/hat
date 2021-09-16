package com.qrs.WebSocket;

import cn.hutool.json.JSON;
import cn.hutool.json.JSONUtil;
import com.google.gson.Gson;
import com.qrs.pojo.RespBean;
import com.qrs.utils.regionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author zlx
 * @description
 * @Version 1.0
 * @Date 2021/6/10 15:27
 */
@RestController
public class Test_websocket {
    @Autowired
    WebSocket webSocket;

    @RequestMapping(value = "/socket",method = RequestMethod.POST)
    public void aaabb(){
        Gson gson = new Gson();
        JSON s = JSONUtil.parse(RespBean.success("成功"));
        webSocket.sendAllMessage(gson.toJson(RespBean.success("成功")));
    }

    @RequestMapping(value = "/socket2",method = RequestMethod.POST)
    public void aaa(){
        webSocket.sendAllMessage("gggg");
    }


    @GetMapping("/test_poing")
    public void test_point(){
        Double lng[]={114.92175589389002,114.92681197608854,114.92739682141747,114.92198228563026};
        Double lat[]={25.84578805363681,25.84580512617925,25.842023497192898,25.841938131696836};
        double x=114.93049847134071;
        double y=25.844162175450105;
        System.out.println(regionUtil.isInPolygon(x, y, lng, lat));
    }
}
