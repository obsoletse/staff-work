package com.linbin.base.service.impl;

import com.linbin.base.dao.RoleDao;
import com.linbin.base.entity.Role;
import com.linbin.base.service.RoleService;
import com.linbin.system.dao.IBaseDao;
import com.linbin.system.impl.IBaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author: LinBin
 * @Date: 2020/4/19 16:59
 * @Description:
 */
@Service
@Transactional
public class RoleServiceImpl extends IBaseServiceImpl<Role> implements RoleService {
    @Autowired
    private RoleDao roleDao;

    @Override
    public List<String> queryRoleByUserId(Integer userId) {
        return roleDao.queryRoleByUserId(userId);
    }

    @Override
    public Integer queryRoleIdByName(String roleName) {
        return roleDao.queryRoleIdByName(roleName);
    }

    @Override
    public List<Role> queryRoleList(Integer page, Integer pageSize) {
        return roleDao.queryRoleList((page - 1) * pageSize,pageSize);
    }

    @Override
    public List<Role> getLikeRoles(String roleLike, Integer page, Integer pageSize) {
        return roleDao.getLikeRoles(roleLike,(page - 1) * pageSize,pageSize);
    }

    @Override
    public Integer countRoleLikeTotal(String roleLike) {
        return roleDao.countRoleLikeTotal(roleLike);
    }

    @Override
    public Integer countTotal() {
        return roleDao.countTotal();
    }

    @Override
    public Role queryOneByCode(String code) {
        return roleDao.queryOneByCode(code);
    }

    @Override
    public Role queryOneByName(String name) {
        return roleDao.queryOneByName(name);
    }

    @Override
    public IBaseDao getBaseDao() {
        return roleDao;
    }

    @Override
    public String getTableName() {
        return "sys_role";
    }

    @Override
    public Class<Role> getClassEntity() {
        return Role.class;
    }

}
