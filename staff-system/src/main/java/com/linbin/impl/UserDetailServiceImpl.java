package com.linbin.impl;

import com.linbin.base.entity.User;
import com.linbin.base.service.UserService;
import com.linbin.model.JwtUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * @Author: LinBin
 * @Date: 2020/4/19 15:33
 * @Description:
 */
@Service
public class UserDetailServiceImpl implements UserDetailsService {
    @Autowired
    private UserService userService;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //获取用户
        User user = userService.queryOneByUserName(username);
        return new JwtUser(user);
    }
}
