package com.linbin.modules.OA.dao;

import com.linbin.modules.OA.entity.Task;
import com.linbin.system.dao.IBaseDao;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Author: LinBin
 * @Date: 2020/5/10 13:05
 * @Description:
 */
@Mapper
public interface TaskDao extends IBaseDao {
    List<Task> getTaskList(Integer index, Integer pageSize, String query, Integer userId);
    Integer countTotal(String query,Integer userId);
    Task getOneTaskById(Integer id);
    void trustTask(Integer taskId , Integer userId);
}
