package com.linbin.modules.OA.entity;

import lombok.Data;

/**
 * @Author: LinBin
 * @Date: 2020/5/11 18:00
 * @Description:
 */
@Data
public class InBox {
    private Integer id;

    private Integer inSender;

    private String inSenderName;

    private Integer inReceiver;

    private String title;

    private String content;

    private Long receiveTime;

    private Integer receiveStatus;

    private Integer emailStatus;
}
