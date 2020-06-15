package com.linbin.base.service;

import com.linbin.base.entity.User;
import com.linbin.system.service.IBaseService;


import java.util.List;

/**
 * @Author: LinBin
 * @Date: 2020/3/28 14:03
 * @Description:
 */
public interface UserService extends IBaseService<User> {

    List<User> queryUserList(Integer page, Integer pageSize);

    List<User> getLikeUsers(String userLike , Integer page , Integer pageSize);

    User queryOneByUserName(String username);

    User queryOneByRealName(String realName);

    User queryOneByPhone(String phone);

    void updatePwdByUserName(String username,String password);

    String queryAvatarUrl(String username);

    Integer countTotal();

    Integer countUserLikeTotal(String userLike);

    List<User> getUserList(Integer page , Integer pageSize , String query ,Integer deptId);

    Integer countUserTotal(String query , Integer deptId);

    List<User> getUserList2(Integer page , Integer pageSize , String query ,Integer deptId,Integer userId);

    Integer countUserTotal2(String query , Integer deptId,Integer userId);
}
