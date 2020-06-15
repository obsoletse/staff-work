package com.linbin.base.dao;

import com.linbin.base.entity.User;
import com.linbin.system.dao.IBaseDao;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserDao extends IBaseDao{

    List<User> queryUserList(Integer index , Integer pageSize);

    List<User> queryLikeUsers(String userLike , Integer index , Integer pageSize);

    User queryOneByUserName(String username);

    User queryOneByRealName(String realName);

    User queryOneByPhone(String phone);

    void updatePwdByUsername(@Param("username") String username , @Param("password") String password);

    String queryAvatarUrl(String username);

    Integer countTotal();

    Integer countUserLikeTotal(String userLike);

    List<User> getUserList(Integer index , Integer pageSize , String query ,Integer deptId);

    Integer countUserTotal(String query , Integer deptId);

    List<User> getUserList2(Integer index , Integer pageSize , String query ,Integer deptId,Integer userId);

    Integer countUserTotal2(String query , Integer deptId , Integer userId);
}
