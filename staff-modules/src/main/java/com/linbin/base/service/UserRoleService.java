package com.linbin.base.service;

import com.linbin.base.entity.UserRole;
import com.linbin.system.service.IBaseService;

/**
 * @Author: LinBin
 * @Date: 2020/4/24 15:54
 * @Description:
 */
public interface UserRoleService extends IBaseService<UserRole> {

    void deleteRoleByUserId(Integer userId);

    void deleteByRoleId(Integer roleId);
}
