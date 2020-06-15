package com.linbin.modules.OA.service.impl;

import com.linbin.modules.OA.dao.TaskDao;
import com.linbin.modules.OA.entity.Task;
import com.linbin.modules.OA.service.TaskService;
import com.linbin.system.dao.IBaseDao;
import com.linbin.system.impl.IBaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: LinBin
 * @Date: 2020/5/10 13:06
 * @Description:
 */
@Service
public class TaskServiceImpl extends IBaseServiceImpl<Task> implements TaskService {

    @Autowired
    private TaskDao taskDao;

    @Override
    public List<Task> getTaskList(Integer page, Integer pageSize, String query, Integer userId) {
        if (query != null){
            query = "%" + query + "%";
        }
        return taskDao.getTaskList((page - 1)*pageSize,pageSize,query,userId);
    }

    @Override
    public Integer countTotal(String query, Integer userId) {
        if (query != null){
            query = "'%" + query + "%'";
        }
        return taskDao.countTotal(query,userId);
    }

    @Override
    public Task getOneTaskById(Integer id) {
        return taskDao.getOneTaskById(id);
    }

    @Override
    public void trustTask(Integer taskId, Integer userId) {
        taskDao.trustTask(taskId,userId);
    }

    @Override
    public IBaseDao getBaseDao() {
        return taskDao;
    }

    @Override
    public String getTableName() {
        return "task";
    }

    @Override
    public Class<Task> getClassEntity() {
        return Task.class;
    }

}
