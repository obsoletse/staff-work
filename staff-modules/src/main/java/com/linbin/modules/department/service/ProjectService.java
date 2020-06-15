package com.linbin.modules.department.service;

import com.linbin.modules.department.entity.Project;
import com.linbin.system.service.IBaseService;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;

/**
 * @Author: LinBin
 * @Date: 2020/4/10 20:05
 * @Description:
 */
public interface ProjectService extends IBaseService<Project> {
    List<Project> queryProList(Integer deptId);

    List<Project> getProList(Integer page,Integer pageSize,String query,Integer deptId);

    Integer countTotal(String query,Integer deptId);

    Project getProById(Integer id);

    void trustPro(Integer proId , Integer deptId);
}
