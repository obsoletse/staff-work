package com.linbin.modules.department.service;

import com.linbin.modules.department.entity.Department;
import com.linbin.system.service.IBaseService;

import java.util.List;

/**
 * @Author: LinBin
 * @Date: 2020/4/9 18:59
 * @Description:
 */
public interface DeptService extends IBaseService<Department> {
    List<Department> queryAllDept();
    List<Department> querySelectDept();
    List<Department> getDeptLike(String query);
    Department queryOneByName(String deptName);
    Department queryOneByNameEn(String deptEnName);
}
