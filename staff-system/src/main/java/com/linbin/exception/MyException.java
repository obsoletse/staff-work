package com.linbin.exception;

/**
 * @Author: LinBin
 * @Date: 2020/4/19 14:38
 * @Description: 封装自定义异常处理类
 */
public class MyException extends Exception{
    public MyException(String msg){
        super(msg);
    }
}
