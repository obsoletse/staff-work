package com.linbin.constant;

import org.springframework.http.HttpStatus;

/**
 * @Author: LinBin
 * @Date: 2020/3/27 18:19
 * @Description:
 */
public class CommonConstant {
    /*系统状态量*/
    public static final Integer SC_OK_200 = 200;
    public static final Integer SC_INTERNAL_SERVER_ERROR_500 = 500; //系统异常
    public static final Integer SC_NOT_ACCEPTABLE = 406;//认证不通过
    public static final Integer SC_BAD_REQUEST = 400;//错误请求
    public static final Integer SC_UNAUTHORIZED = 401;//匿名无权限处理
    public static final Integer SC_FORBIDDEN = 403;//登录无权限处理
    public static final Integer SC_REQUEST_TIMEOUT = 408;//请求超时
}
