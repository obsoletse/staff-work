package com.linbin.exception.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.linbin.constant.CommonConstant;
import com.linbin.utils.JwtTokenUtils;
import lombok.SneakyThrows;
import org.json.JSONObject;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @Author: LinBin
 * @Date: 2020/4/19 14:49
 * @Description: 用来解决认证过的用户访问无权限资源时的异常
 */
public class JWTAccessDeniedHandler implements AccessDeniedHandler {
    @SneakyThrows
    @Override
    public void handle(HttpServletRequest request,
                       HttpServletResponse response,
                       AccessDeniedException e) throws IOException, ServletException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=utf-8");
        JSONObject o = new JSONObject();
        o.put("code", CommonConstant.SC_FORBIDDEN);
        o.put("message", "亲!您暂时没有权限访问该资源哦！！");
        response.getWriter().write(o.toString());

    }
}
