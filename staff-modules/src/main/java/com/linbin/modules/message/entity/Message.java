package com.linbin.modules.message.entity;

import lombok.Data;

/**
 * @Author: LinBin
 * @Date: 2020/5/18 17:30
 * @Description:
 */
@Data
public class Message {
    private Integer id;
    private String title;
    private String content;
    private Integer type;
    private Long submitTime;
    private Long sendTime;
    private Integer status;
}
