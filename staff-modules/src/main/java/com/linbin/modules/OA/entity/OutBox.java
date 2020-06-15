package com.linbin.modules.OA.entity;

import lombok.Data;

/**
 * @Author: LinBin
 * @Date: 2020/5/11 18:00
 * @Description:
 */
@Data
public class OutBox {
    private Integer id;

    private Integer outSender;

    private String outReceiver;

    private Long sendTime;

    private String title;

    private String content;

    private Integer status;
}
