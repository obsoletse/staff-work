package com.linbin.base.service;

import com.linbin.base.entity.Role;
import com.linbin.system.service.IBaseService;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;

/**
 * @Author: LinBin
 * @Date: 2020/4/19 16:59
 * @Description:
 */
public interface RoleService extends IBaseService<Role> {

    List<String> queryRoleByUserId(Integer userId);

    Integer queryRoleIdByName(String roleName);

    List<Role> queryRoleList(Integer page , Integer pageSize);

    List<Role> getLikeRoles(String roleLike ,Integer page , Integer pageSize);

    Integer countTotal();

    Integer countRoleLikeTotal(String roleLike);

    Role queryOneByCode(String code);

    Role queryOneByName(String name);
}
