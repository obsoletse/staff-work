package com.linbin.base.service.impl;

import com.linbin.base.dao.RoleMenuDao;
import com.linbin.base.entity.RoleMenu;
import com.linbin.base.service.RoleMenuService;
import com.linbin.system.dao.IBaseDao;
import com.linbin.system.impl.IBaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Author: LinBin
 * @Date: 2020/4/24 23:41
 * @Description:
 */
@Service
@Transactional
public class RoleMenuServiceImpl extends IBaseServiceImpl<RoleMenu> implements RoleMenuService {
    @Autowired
    private RoleMenuDao roleMenuDao;

    @Override
    public void deleteByRoleId(Integer roleId) {
        roleMenuDao.deleteByRoleId(roleId);
    }

    @Override
    public void deleteByMenuId(Integer menuId) {
        roleMenuDao.deleteByMenuId(menuId);
    }

    @Override
    public IBaseDao getBaseDao() {
        return roleMenuDao;
    }

    @Override
    public String getTableName() {
        return "sys_role_menu";
    }

    @Override
    public Class<RoleMenu> getClassEntity() {
        return RoleMenu.class;
    }
}
