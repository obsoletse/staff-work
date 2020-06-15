package com.linbin.base.service;

import com.linbin.base.entity.RoleMenu;
import com.linbin.system.service.IBaseService;

/**
 * @Author: LinBin
 * @Date: 2020/4/24 23:40
 * @Description:
 */
public interface RoleMenuService extends IBaseService<RoleMenu> {

    void deleteByRoleId(Integer roleId);
    void deleteByMenuId(Integer menuId);
}
