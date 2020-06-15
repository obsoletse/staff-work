package com.linbin.filters;

import com.linbin.constant.CommonConstant;
import com.linbin.entity.LoginUser;
import com.linbin.model.JwtUser;
import com.linbin.utils.JwtTokenUtils;
import com.linbin.utils.RedisUtils;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.json.JSONObject;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;

/**
 * @Author: LinBin
 * @Date: 2020/4/19 15:05
 * @Description: 自定义用户认证处理器
 */
@Slf4j
public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private RedisUtils redisUtils = new RedisUtils();

    //设置记住我
    private ThreadLocal<Integer> rememberMe = new ThreadLocal<>(); //ThreadLocal<Integer>是一个线程内部存储类，对数据存储只有在当前线程才可以取到
    private AuthenticationManager authenticationManager;

    public JWTAuthenticationFilter(AuthenticationManager authenticationManager){
        this.authenticationManager = authenticationManager;
        super.setFilterProcessesUrl("/login");//设置自定义登录路径
    }

    /**
     * 自定义尝试认证
     * @param request
     * @param response
     * @return
     * @throws AuthenticationException
     */
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        /*1.从输入流中获取用户信息*/
        try{
            LoginUser loginUser = new ObjectMapper().readValue(request.getInputStream(),LoginUser.class);
            rememberMe.set(loginUser.getRememberMe());
            return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginUser.getUsername(),loginUser.getPassword()));
        } catch (JsonParseException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 认证成功，生成token并且设置权限
     * @param request
     * @param response
     * @param chain
     * @param authResult
     * @throws IOException
     * @throws ServletException
     */
    @SneakyThrows
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        JwtUser jwtUser = (JwtUser) authResult.getPrincipal();
        log.info("JwtUser = { username:" + jwtUser.getUsername() + ", roles:" + jwtUser.getAuthorities());
        String token = null;
        if (redisUtils.isKeyExits(jwtUser.getUsername() + "_token")){
            token = String.valueOf(redisUtils.getStringKey(jwtUser.getUsername() + "_token"));
        }else {
            boolean isRemember = rememberMe.get() == 1;//设置记住我
            /*设置权限*/
            Collection<? extends GrantedAuthority> authorities = jwtUser.getAuthorities();

            /*生成token*/
            token = JwtTokenUtils.createToken(jwtUser.getUsername(),authorities,isRemember);
            /*存储到redis数据库*/
            redisUtils.setStringKey(jwtUser.getUsername() + "_token",token);
            Integer seconds = isRemember ? 24 * 3600 : 3600;
            redisUtils.setExpiration(jwtUser.getUsername() + "_token",seconds);
        }

        /*返回Token*/
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/json; charset=utf-8");
        PrintWriter writer = response.getWriter();
        JSONObject o = new JSONObject();
        o.put("code", CommonConstant.SC_OK_200);
        o.put("token", JwtTokenUtils.TOKEN_PREFIX + token);
        o.put("expiration", JwtTokenUtils.getExpiration(token).getTime());
        o.put("message", "认证成功！");
        log.info("----------用户Token令牌信息如下:----------");
        log.info("token: " + token);
        log.info("expiration: " + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(JwtTokenUtils.getExpiration(token).getTime())));
        writer.write(o.toString());
    }

    /**
     * 认证失败,抛出异常
     * @param request
     * @param response
     * @param failed
     * @throws IOException
     * @throws ServletException
     */
    @SneakyThrows
    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/json; charset=utf-8");
        PrintWriter writer = response.getWriter();
        JSONObject o = new JSONObject();
        o.put("code", CommonConstant.SC_NOT_ACCEPTABLE);
        o.put("token", "");
        o.put("message", "认证失败！用户名或密码错误。");
        writer.write(o.toString());
    }
}
