package com.linbin.utils;

import com.alibaba.fastjson.JSONObject;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.*;

/**
 * @Author: LinBin
 * @Date: 2020/4/19 15:00
 * @Description:
 */
public class JwtTokenUtils {
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String TOKEN_HEADER = "Authorization";
    //签证
    private static final String SECRET = "spring_security";
    //签约者
    private static final String ISS = "LinBin";
    // 角色的key
    private static final String ROLE_CLAIMS = "role";
    // 过期时间是3600秒，既是1个小时
    private static final long EXPIRATION = 3600L;
    // 选择了记住我之后的过期时间为1天
    private static final long EXPIRATION_REMEMBER = 24 * 3600L;

    /**
     * 创建Token
     * @param username
     * @param authorities
     * @param isRememberMe
     * @return
     */
    public static String createToken(String username, Collection<? extends GrantedAuthority> authorities , boolean isRememberMe){
        /*判断是否为记住我用户,设置对应的token过期时间*/
        long expiration = isRememberMe ? EXPIRATION_REMEMBER : EXPIRATION;
        HashMap<String, Object> claims = new HashMap<String, Object>();
        claims.put(ROLE_CLAIMS,authorities);
        return Jwts.builder()
                .signWith(SignatureAlgorithm.HS512,SECRET) //设置签证
                .setClaims(claims)//设置自定义需求
                .setIssuer(ISS)//设置签约者
                .setIssuedAt(new Date())
                .setSubject(username)//设置主题名
                .setExpiration(new Date(System.currentTimeMillis() + expiration * 1000))//设置过期时间
                .compact();//简洁发送
    }

    /**
     * 通过Token获取用户名
     * @param token
     * @return
     */
    public static String getUsername(String token){
        return getTokenBody(token).getSubject();
    }

    /**
     * 通过Token获取用户角色
     * @param token
     * @return
     */
    public static Collection<? extends GrantedAuthority> getUserRole(String token){
        List<GrantedAuthority> authorities = new ArrayList<>();
        Object oList = getTokenBody(token).get(ROLE_CLAIMS);
        if (oList instanceof ArrayList<?>){
            for (Object o : (List<?>) oList){
                HashMap hashMap = JSONObject.parseObject(JSONObject.toJSONString(o),HashMap.class);;
                authorities.add(new SimpleGrantedAuthority(String.valueOf(hashMap.get("authority"))));
            }
        }
        return authorities;
    }

    /**
     * 通过Token获取过期时间
     * @param token
     * @return
     */
    public static Date getExpiration(String token){ return getTokenBody(token).getExpiration(); }

    /**
     * 判断是否过期
     * @param token
     * @return
     */
    public static boolean isExpiration(String token) {
        try {
            return getTokenBody(token).getExpiration().before(new Date());//判断过期时间是否在当前时间之前
        } catch (ExpiredJwtException e) {
            return true;
        }
    }
    /**
     * 解密Token获取Token内容
     * @param token
     * @return
     */
    public static Claims getTokenBody(String token){
        return Jwts.parser()
                .setSigningKey(SECRET)
                .parseClaimsJws(token.trim())
                .getBody();
    }
}
