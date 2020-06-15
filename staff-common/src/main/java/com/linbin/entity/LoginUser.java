package com.linbin.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author: LinBin
 * @Date: 2020/3/27 20:49
 * @Description:
 */
@Data
public class LoginUser{
    private String username;
    private String password;
    private Integer rememberMe;
}
