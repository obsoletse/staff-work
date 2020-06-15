package com.linbin.modules.message.entity;

import lombok.Data;

/**
 * @Author: LinBin
 * @Date: 2020/5/18 18:58
 * @Description:
 */
@Data
public class MessageUser {
    private Integer id;
    private Integer userId;
    private Integer msgId;
    private Integer status;
}
