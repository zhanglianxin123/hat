package com.qrs.utils;

import com.alibaba.fastjson.JSONObject;
import com.qrs.utils.module.Command;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

/**
 * @Author zlx
 * @description  封装的OneNeT下发接口，修改接口内的数据
 * @Version 1.0
 * @Date 2021/6/22 18:51
 */
@Slf4j
@Component
public class OkHttpUtil2 {
    private final OkHttpClient client=new OkHttpClient();
    //下发指令
    public String issue(String cmd){
        //构建url
        HashMap<String, String> urlParams = new HashMap<>();
        urlParams.put("imei", "868474041800077");
        urlParams.put("timeout", "5");
        urlParams.put("obj_id", "3200");
        urlParams.put("obj_inst_id", "0");
        urlParams.put("mode", "2");
        String url = parseUrl(urlParams);
        //构建body中的 json 字符串
        HashMap<String, List> map = new HashMap<>();
        List<Command> commands = new ArrayList<>();
        // 资源 ID 为 5750
        commands.add(new Command(5750, Objects.requireNonNull(cmd)));
        map.put("data", commands);
        String params = JSONObject.toJSONString(map);

        //发送命令
        String responseBody = "";

        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), params);
        Request request = new Request.Builder()
                .url(url)
                .post(requestBody)
                .addHeader("Content-Type", "application/json")
                .addHeader("Host", "api.heclouds.com")
                .addHeader("api-key", "EN1DN=nC416xuqQcF9t9aczln5k=")
                .build();
        System.out.println(new String(responseBody.getBytes()));
        try (Response response = this.client.newCall(request).execute()) {
            if (response.isSuccessful()) {
                return Objects.requireNonNull(response.body()).string();
            }
        } catch (Exception e) {
            log.error("{}", e);
        }
        return  responseBody;

    }
    private String parseUrl(HashMap<String, String> params) {
        StringBuilder sb = new StringBuilder("http://api.heclouds.com/nbiot?");
        for (String key : params.keySet()) {
            sb.append(key).append("=").append(params.get(key)).append("&");
        }
        return sb.deleteCharAt(sb.length() - 1).toString();
    }
}
