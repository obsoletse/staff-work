package com.linbin.base.service.impl;

import com.linbin.base.dao.UserDao;
import com.linbin.base.entity.User;
import com.linbin.base.service.UserService;
import com.linbin.system.dao.IBaseDao;
import com.linbin.system.impl.IBaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


/**
 * @Author: LinBin
 * @Date: 2020/3/28 14:06
 * @Description:
 */
@Service
@Transactional
public class UserServiceImpl extends IBaseServiceImpl<User> implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public List<User> queryUserList(Integer page , Integer pageSize) {
        List<User> users = userDao.queryUserList((page - 1) * pageSize,pageSize);
        return users;
    }

    @Override
    public List<User> getLikeUsers(String userLike , Integer page , Integer pageSize) {
        List<User> users = userDao.queryLikeUsers(userLike , (page - 1) * pageSize , pageSize);
        return users;
    }

    @Override
    public User queryOneByUserName(String username) {
        User user = userDao.queryOneByUserName(username);
        return user;
    }

    @Override
    public User queryOneByRealName(String realName) {
        return userDao.queryOneByRealName(realName);
    }

    @Override
    public User queryOneByPhone(String phone) {
        User user = userDao.queryOneByPhone(phone);
        return user;
    }

    @Override
    public void updatePwdByUserName(String username,String password) {
        userDao.updatePwdByUsername(username,password);
        return;
    }

    @Override
    public List<User> getUserList(Integer page, Integer pageSize, String query, Integer deptId) {
        if (query != null){
            query = "%" + query + "%";
        }
        return userDao.getUserList((page - 1) * pageSize,pageSize,query,deptId);
    }

    @Override
    public Integer countUserTotal(String query, Integer deptId) {
        if (query != null){
            query = "%" + query + "%";
        }
        return userDao.countUserTotal(query,deptId);
    }

    @Override
    public List<User> getUserList2(Integer page, Integer pageSize, String query, Integer deptId,Integer userId) {
        if (query != null){
            query = "%" + query + "%";
        }
        return userDao.getUserList2((page - 1) * pageSize,pageSize,query,deptId,userId);
    }

    @Override
    public Integer countUserTotal2(String query, Integer deptId, Integer userId) {
        if (query != null){
            query = "%" + query + "%";
        }
        return userDao.countUserTotal2(query,deptId,userId);
    }

    @Override
    public Class<User> getClassEntity() {
        return User.class;
    }

    @Override
    public String queryAvatarUrl(String username) {
        return userDao.queryAvatarUrl(username);
    }

    @Override
    public Integer countTotal() {
        return userDao.countTotal();
    }

    @Override
    public Integer countUserLikeTotal(String userLike) {
        return userDao.countUserLikeTotal(userLike);
    }

    @Override
    public IBaseDao getBaseDao() {
        return userDao;
    }

    @Override
    public String getTableName() {
        return "sys_user";
    }
}
