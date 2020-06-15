package com.linbin.base.dao;

import com.linbin.system.dao.IBaseDao;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Author: LinBin
 * @Date: 2020/4/24 15:57
 * @Description:
 */
@Mapper
public interface UserRoleDao extends IBaseDao {
    void deleteRoleByUserId(Integer userId);
    void deleteByRoleId(Integer roleId);
}
