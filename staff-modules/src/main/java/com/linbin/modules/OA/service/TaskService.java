package com.linbin.modules.OA.service;

import com.linbin.modules.OA.entity.Task;
import com.linbin.system.service.IBaseService;

import java.util.List;

/**
 * @Author: LinBin
 * @Date: 2020/5/10 13:06
 * @Description:
 */
public interface TaskService extends IBaseService<Task> {
    List<Task> getTaskList(Integer page,Integer pageSize,String query,Integer userId);
    Integer countTotal(String query,Integer userId);
    Task getOneTaskById(Integer id);
    void trustTask(Integer taskId , Integer userId);
}
