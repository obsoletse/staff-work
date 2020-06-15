package com.linbin.base.dao;

import com.linbin.system.dao.IBaseDao;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Author: LinBin
 * @Date: 2020/4/24 23:42
 * @Description:
 */
@Mapper
public interface RoleMenuDao extends IBaseDao {
    void deleteByRoleId(Integer roleId);
    void deleteByMenuId(Integer menuId);
}
