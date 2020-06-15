package com.linbin.filters;


import com.linbin.exception.MyException;
import com.linbin.utils.JwtTokenUtils;
import lombok.extern.slf4j.Slf4j;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;


/**
 * @Author: LinBin
 * @Date: 2020/4/19 15:17
 * @Description:
 */
@Slf4j
public class JWTAuthorizationFilter extends BasicAuthenticationFilter {

    public JWTAuthorizationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    /**
     * 自定义授权
     * @param request
     * @param response
     * @param chain
     * @throws IOException
     * @throws ServletException
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        /*1.获取Token*/
        String tokenHeader = request.getHeader(JwtTokenUtils.TOKEN_HEADER);
        //如果url请求没有tokenHeader直接交给下个过滤器处理，
        if (tokenHeader == null || !tokenHeader.startsWith(JwtTokenUtils.TOKEN_PREFIX)){
            chain.doFilter(request,response);
            return;
        }
        /*如果有就解析token,并设置认证信息*/
        try {
            SecurityContextHolder.getContext().setAuthentication(getAuthentication(tokenHeader));
        } catch (MyException e) {
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json; charset=utf-8");
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            String reason = "认证失败!原因如下:" + e.getMessage();
            response.getWriter().write(new ObjectMapper().writeValueAsString(reason));
            response.getWriter().flush();
            return;
        }
        super.doFilterInternal(request,response,chain);
    }

    private UsernamePasswordAuthenticationToken getAuthentication(String tokenHeader) throws MyException {
        /*解析去掉请求头Bearer */
        String token = tokenHeader.replace(JwtTokenUtils.TOKEN_PREFIX, "");
        boolean expiration = JwtTokenUtils.isExpiration(token);
        if (expiration) {
            throw new MyException("token超时了");
        } else {
            String username = JwtTokenUtils.getUsername(token);
            Collection<? extends GrantedAuthority> role = JwtTokenUtils.getUserRole(token);
            if (username != null) {
                /*返回一个UsernamePasswordAuthenticationToken去进行解析判断*/
                return new UsernamePasswordAuthenticationToken(username, null, role);
            }
        }
        return null;
    }
}
