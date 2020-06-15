package com.linbin.modules.department.dao;

import com.linbin.modules.department.entity.Department;
import com.linbin.system.dao.IBaseDao;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Author: LinBin
 * @Date: 2020/4/9 19:03
 * @Description:
 */
@Mapper
public interface DeptDao extends IBaseDao {
    List<Department> queryAllDept();
    List<Department> querySelectDept();
    List<Department> getDeptLike(String deptLike);
    Department queryOneByName(String deptName);
    Department queryOneByNameEn(String deptEnName);
}
