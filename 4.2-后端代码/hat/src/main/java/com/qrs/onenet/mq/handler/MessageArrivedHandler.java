package com.qrs.onenet.mq.handler;


import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.google.gson.Gson;
import com.qrs.WebSocket.WebSocket;
import com.qrs.pojo.*;
import com.qrs.service.*;
import com.qrs.utils.module.Message;
import com.qrs.utils.regionUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: kyg
 * @Description: mq消息处理器
 * @Date: 2020-07-06 16:37
 **/
@Slf4j
@Component
public class MessageArrivedHandler implements MessageHandler {
    @Resource
    private IHardwareDataService hardwareDataService;

    @Autowired
    private ITemperatureService temperatureService;
    @Autowired
    private ITemperatureRecordService temperatureRecordService;

    @Autowired
    private IPositioningService positioningService;

    @Autowired
    private  ISignService signService;

    @Autowired
    private  ISignPointService signPointService;

    @Autowired
    private  ISignRecordService signRecordService;

    @Autowired
    private  IFenceService fenceService;

    @Autowired
    private  IFencePointService fencePointService;

    @Autowired
    private WarningUtils warningUtils;

    @Autowired
    private WebSocket webSocket;



    @Override
    public void handle(Message message) {

        System.out.println(message.toString());
        OkHttpUtil2(message.getMsgId(),message.getBody());

    }




    public  void OkHttpUtil2(Long msgId,String sourceStr) {

        String[] sourceStrArray = sourceStr.split(",");
        if (sourceStrArray[0] != null){
            switch (sourceStrArray[0]) {
                case "E":
                    HardwareData hardwareData=HardwareData.builder()
                            .data_id(Math.toIntExact(msgId))
                            .longitude(Double.valueOf(sourceStrArray[1]))
                            .latitude(Double.valueOf(sourceStrArray[2]))
                            .button(Integer.valueOf(sourceStrArray[3]))
                            .temperature(Float.valueOf(sourceStrArray[4]))
                            .pressure(Integer.valueOf(sourceStrArray[5]))
                            .acceleration_x(Float.valueOf(sourceStrArray[6]))
                            .acceleration_y(Float.valueOf(sourceStrArray[7 ]))
                            .acceleration_z(Float.valueOf(sourceStrArray[8]))
                            .build();

                    disposeHardware(msgId,hardwareData);
                    break;

                default:
                    System.out.println("数据不正确!");
                    break;
            }


        }

    }

    //处理数据
    public  void disposeHardware(long magId,HardwareData hardwareData) {
        System.out.println("处理数据");
        //添加到数据库
        hardwareDataService.save(hardwareData);

        //websocket
        Map<String,Object>map=new HashMap<>();
        map.put("hardware","ture");
        Gson gson=new Gson();
        webSocket.sendTextMessage("home",gson.toJson(map));

        //记录位置，是否进入签到
        sign(magId,hardwareData.getLongitude(),hardwareData.getLatitude(),hardwareData.getPressure());
        //是否进入围栏
        fence(magId,hardwareData.getLongitude(),hardwareData.getLatitude());
        //体温
        temperature(magId,hardwareData.getTemperature());

        //三轴是否跌落
        acceleration(magId,hardwareData.getAcceleration_x(),hardwareData.getAcceleration_y(),hardwareData.getAcceleration_z());

        //是否进行了sos
        button(magId,hardwareData.getButton());



    }

    //记录位置,签到
    public  void  sign(long magId,double lng,double lat,int pressure){

        Positioning positioning= Positioning.builder().equipment_id(1).lat(lat).lng(lng).build();
        positioningService.save(positioning);

        List<Sign> list = signService.list();

        for(Sign sign: list){

            QueryWrapper<SignPoint> wrapper = new QueryWrapper();
            wrapper.eq("sign_id",sign.getSign_id());
            List<SignPoint> list1 = signPointService.list(wrapper);
            Double lngList[]=new Double[list1.size()];
            Double latList[]=new Double[list1.size()];
            for(int i=0;i<list1.size();i++){
                lngList[i]=list1.get(i).getLng();
                latList[i]=list1.get(i).getLat();
            }
            if(regionUtil.isInPolygon(lng,lat,lngList,latList)){//判断是否在签到区域
                SignRecord signRecord=SignRecord.builder()
                        .sign_id(sign.getSign_id()).equipment_id(1)
                        .sign_name(sign.getSign_name()).sign_number(String.valueOf(magId)).build();
                signRecordService.save(signRecord);
                if(pressure==0){
                    //在签到区域且为佩戴安全帽
                    warningUtils.mag(Math.toIntExact(magId),3,1,"Hat"+1,"1号设备脱帽了");
                }
            }
        }


    }
    //围栏
    public  void  fence(long magId,double lng,double lat){
        List<Fence> list = fenceService.list();
        for(Fence fence:list){
            QueryWrapper<FencePoint> wrapper = new QueryWrapper();
            wrapper.eq("fence_id",fence.getFence_id());
            List<FencePoint> list1 = fencePointService.list(wrapper);
            Double lngList[]=new Double[list1.size()];
            Double latList[]=new Double[list1.size()];
            for(int i=0;i<list1.size();i++){
                lngList[i]=list1.get(i).getLng();
                latList[i]=list1.get(i).getLat();
            }
            if(regionUtil.isInPolygon(lng,lat,lngList,latList)){
                //在报警区域进行报警
                warningUtils.mag(Math.toIntExact(magId),4,1,"Fence"+1,"靠近围栏"+fence.getFence_name());
            }
        }


    }
    //体温
    public  void  temperature(long magId,float temperature){
        TemperatureRecord temperatureRecord=TemperatureRecord.builder().equipment_id(1)
                .temperature_id(Math.toIntExact(magId)).temperature(temperature).build();
        temperatureRecordService.save(temperatureRecord);
        Temperature temperature1=Temperature.builder().user_id(1).equipment_id(1)
                .temperature_id(Math.toIntExact(magId)).temperature(temperature).build();
        temperatureService.save(temperature1);
        //体温报警
        if(temperature>38){
            warningUtils.mag(Math.toIntExact(magId),2,1,"Temperature-"+1,"体温异常，达到"+temperature);
        }

    }
    //三轴
    public  void  acceleration(long magId,double x,double y,double z){

        double a=Math.sqrt(x*x+y*y+z*z);
        if(a>9.8){
            warningUtils.mag(Math.toIntExact(magId),5,1,"Fall-"+1,"加数度达到"+a+",可能跌落");
        }
    }

    //sos
    public  void button(long magId,Integer button){
        if(button==1){
            warningUtils.mag(Math.toIntExact(magId),1,1,"SOS-"+1,"在"+ DateUtil.now()+"发出SOS报警");
        }
    }


}
