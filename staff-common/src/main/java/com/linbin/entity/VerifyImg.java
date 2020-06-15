package com.linbin.entity;

import lombok.Data;


/**
 * 验证码图片实体
 */
@Data
public class VerifyImg {
    private String img;
    private String code;
    private Long sendTime;
}
