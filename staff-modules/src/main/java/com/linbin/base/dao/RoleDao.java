package com.linbin.base.dao;

import com.linbin.base.entity.Role;
import com.linbin.system.dao.IBaseDao;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Author: LinBin
 * @Date: 2020/4/19 16:57
 * @Description:
 */
@Mapper
public interface RoleDao extends IBaseDao {
    List<String> queryRoleByUserId(Integer userId);

    Integer queryRoleIdByName(String roleName);

    List<Role> queryRoleList(Integer index , Integer pageSize);

    List<Role> getLikeRoles(String roleLike ,Integer index , Integer pageSize);

    Integer countTotal();

    Integer countRoleLikeTotal(String roleLike);

    Role queryOneByCode(String code);

    Role queryOneByName(String name);
}
