package com.linbin.modules.department.dao;

import com.linbin.modules.department.entity.Project;
import com.linbin.system.dao.IBaseDao;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Author: LinBin
 * @Date: 2020/4/10 19:57
 * @Description:
 */
@Mapper
public interface ProjectDao extends IBaseDao {
    List<Project> queryProList(Integer deptId);
    List<Project> getProList(Integer index,Integer pageSize,String query,Integer deptId);
    Integer countTotal(String query,Integer deptId);
    Project getProById(Integer id);
    void trustPro(Integer proId , Integer deptId);
}
