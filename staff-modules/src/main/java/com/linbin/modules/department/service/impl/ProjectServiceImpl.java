package com.linbin.modules.department.service.impl;

import com.linbin.modules.department.dao.ProjectDao;
import com.linbin.modules.department.entity.Project;
import com.linbin.modules.department.service.ProjectService;
import com.linbin.system.dao.IBaseDao;
import com.linbin.system.impl.IBaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: LinBin
 * @Date: 2020/4/10 20:06
 * @Description:
 */
@Service
public class ProjectServiceImpl extends IBaseServiceImpl<Project> implements ProjectService {
    @Autowired
    private ProjectDao projectDao;

    @Override
    public List<Project> queryProList(Integer deptId) {
        return projectDao.queryProList(deptId);
    }

    @Override
    public List<Project> getProList(Integer page, Integer pageSize, String query, Integer deptId) {
        if (query != null){
            query = "%" + query + "%";
        }
        return projectDao.getProList((page - 1) * pageSize,pageSize,query,deptId);
    }

    @Override
    public Integer countTotal(String query, Integer deptId) {
        if (query != null){
            query = "'%" + query + "%'";
        }
        return projectDao.countTotal(query,deptId);
    }

    @Override
    public Project getProById(Integer id) {
        return projectDao.getProById(id);
    }

    @Override
    public void trustPro(Integer proId, Integer deptId) {
        projectDao.trustPro(proId,deptId);
    }

    @Override
    public IBaseDao getBaseDao() {
        return projectDao;
    }

    @Override
    public String getTableName() {
        return "project";
    }

    @Override
    public Class<Project> getClassEntity() {
        return Project.class;
    }
}
