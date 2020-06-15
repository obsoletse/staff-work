package com.linbin.base.service.impl;

import com.linbin.base.dao.UserRoleDao;
import com.linbin.base.entity.UserRole;
import com.linbin.base.service.UserRoleService;
import com.linbin.system.dao.IBaseDao;
import com.linbin.system.impl.IBaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: LinBin
 * @Date: 2020/4/24 15:55
 * @Description:
 */
@Service
public class UserRoleServiceImpl extends IBaseServiceImpl<UserRole> implements UserRoleService {

    @Autowired
    private UserRoleDao userRoleDao;

    @Override
    public void deleteRoleByUserId(Integer userId) {
        userRoleDao.deleteRoleByUserId(userId);
    }

    @Override
    public void deleteByRoleId(Integer roleId) {
        userRoleDao.deleteByRoleId(roleId);
    }

    @Override
    public IBaseDao getBaseDao() {
        return userRoleDao;
    }

    @Override
    public String getTableName() {
        return "sys_user_role";
    }

    @Override
    public Class<UserRole> getClassEntity() {
        return UserRole.class;
    }
}
