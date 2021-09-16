package com.qrs.onenet.mq.runner;


import com.qrs.onenet.mq.MqClient;
import com.qrs.onenet.mq.handler.MessageHandler;
import com.qrs.onenet.mq.MqClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * @Author: kyg
 * @Description:
 * @Date: 2020-07-08 17:07
 **/
@Component
public class MqttRunner implements CommandLineRunner {

    @Autowired
    MessageHandler handler;



    @Override
    public void run(String... args) throws Exception {
        MqClient mqClient = new MqClient(handler);
        mqClient.connect();
    }

}
