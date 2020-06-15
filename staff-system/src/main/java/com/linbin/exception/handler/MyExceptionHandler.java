package com.linbin.exception.handler;

import com.linbin.exception.MyException;
import com.linbin.vo.Result;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: LinBin
 * @Date: 2020/4/19 14:43
 * @Description:
 */
@RestControllerAdvice //处理Controller抛出的异常
public class MyExceptionHandler {
    @ExceptionHandler(MyException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Result MyExceptionHandler(MyException e){
        Result result = new Result();;
        return result.error500(e.getMessage());
    }
}
