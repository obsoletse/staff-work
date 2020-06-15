package com.linbin.config;

import com.linbin.exception.handler.JWTAccessDeniedHandler;
import com.linbin.exception.handler.JWTAuthenticationEntryPoint;
import com.linbin.filters.JWTAuthenticationFilter;
import com.linbin.filters.JWTAuthorizationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @Author: LinBin
 * @Date: 2020/4/19 15:37
 * @Description:
 */
@EnableWebSecurity //开启Web安全
@EnableGlobalMethodSecurity(prePostEnabled = true) //开启spring-security注解
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    @Qualifier("userDetailServiceImpl")  //@Qualifier 和 @Resource 都是指定注入时的具体实现类,@Qualifier直接加名称,@Resource (name = "...")
    private UserDetailsService userDetailsService;

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder(){ return new BCryptPasswordEncoder();}

    /**
     * 添加用户认证信息到AuthenticationManager
     * @param auth
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder());
    }

    /**
     * 配置资源拦截和权限
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .cors()
            .and()
            .csrf().disable()//关闭跨站点攻击
            .authorizeRequests() //请求授权
            // 测试用资源，需要验证了的用户才能访问
            .antMatchers("/login","/getCheckCode","/retrievePwd","/updatePwd" ,"/actuator/**","/user/isEnabled").permitAll()
            .antMatchers("/user/**").authenticated()
            .antMatchers("/pro/**").authenticated()
            .antMatchers("/menu/**").authenticated()
            .antMatchers("/dept/**").authenticated()
            .antMatchers("/attendance/**").authenticated()
            .antMatchers("/cardReplace/**").authenticated()
            .antMatchers("/evaluation/**").authenticated()
            .antMatchers("/msg/**").authenticated()
            .antMatchers("/redis/**").authenticated()
            .antMatchers("/email/**").authenticated()
            .antMatchers("/task/**").authenticated()
            .antMatchers("/OA/**").authenticated()
            .antMatchers("/course/**").authenticated()
            .antMatchers("/wage/**").authenticated()
            .anyRequest().permitAll()//其他放行
            .and()
            //添加自定义的认证授权处理
            .addFilter(new JWTAuthenticationFilter(authenticationManager()))
            .addFilter(new JWTAuthorizationFilter(authenticationManager()))
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)//不需要session
            .and()
            .exceptionHandling().authenticationEntryPoint(new JWTAuthenticationEntryPoint())//匿名用户无权限处理
            .accessDeniedHandler(new JWTAccessDeniedHandler()); //登录用户无权限时处理
    }
}
