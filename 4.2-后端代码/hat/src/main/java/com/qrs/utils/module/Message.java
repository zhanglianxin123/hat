package com.qrs.utils.module;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author yuanweimin
 * @date 19/06/20 19:03
 * @description 接收 onenet 的数据解析实体
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Message {
    private Long msgId;
    private String timestamp;
    private String body;
}
