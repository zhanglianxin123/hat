package com.qrs.onenet.mq.handler;


import com.qrs.utils.module.Message;

/**
 * @Author: kyg
 * @Description:
 * @Date: 2020-07-06 16:36
 **/

public interface MessageHandler {
    void handle(Message message);
}
