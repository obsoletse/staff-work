package com.linbin.exception.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.linbin.constant.CommonConstant;
import lombok.SneakyThrows;
import org.json.JSONObject;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author: LinBin
 * @Date: 2020/4/19 14:46
 * @Description: 解决匿名用户访问资源无权限异常
 */
public class JWTAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @SneakyThrows
    @Override
    public void commence(HttpServletRequest request,
                         HttpServletResponse response,
                         AuthenticationException authException) throws IOException, ServletException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        JSONObject o = new JSONObject();
        o.put("code", CommonConstant.SC_FORBIDDEN);
        o.put("message", "亲！您还未登录！请先登录！");
        response.getWriter().write(o.toString());

    }
}
