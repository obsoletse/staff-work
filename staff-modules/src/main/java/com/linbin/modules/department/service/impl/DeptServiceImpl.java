package com.linbin.modules.department.service.impl;

import com.linbin.modules.department.dao.DeptDao;
import com.linbin.modules.department.entity.Department;
import com.linbin.modules.department.service.DeptService;
import com.linbin.system.dao.IBaseDao;
import com.linbin.system.impl.IBaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: LinBin
 * @Date: 2020/4/9 19:00
 * @Description:
 */
@Service
public class DeptServiceImpl extends IBaseServiceImpl<Department> implements DeptService {
    @Autowired
    private DeptDao deptDao;

    @Override
    public List<Department> queryAllDept() {
        return deptDao.queryAllDept();
    }

    @Override
    public List<Department> querySelectDept() {
        return deptDao.querySelectDept();
    }

    @Override
    public List<Department> getDeptLike(String query) {
        return deptDao.getDeptLike("%" + query + "%");
    }

    @Override
    public Department queryOneByName(String deptName) {
        return deptDao.queryOneByName(deptName);
    }

    @Override
    public Department queryOneByNameEn(String deptEnName) {
        return deptDao.queryOneByNameEn(deptEnName);
    }

    @Override
    public Class<Department> getClassEntity() {
        return Department.class;
    }

    @Override
    public IBaseDao getBaseDao() {
        return deptDao;
    }

    @Override
    public String getTableName() {
        return "sys_dept";
    }
}
