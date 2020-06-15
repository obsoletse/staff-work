package com.linbin.modules.OA.controller;

import com.linbin.modules.OA.entity.Task;
import com.linbin.modules.OA.service.TaskService;
import com.linbin.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

/**
 * @Author: LinBin
 * @Date: 2020/5/10 13:03
 * @Description:
 */
@RequestMapping("/task")
@RestController
@Transactional
public class taskController {

    @Autowired
    private TaskService taskService;

    @PostMapping("/addTask")
    public Result add(@RequestBody Task task){
        taskService.add(task,"taskCreatorName,taskWorkerName,proName");
        return Result.ok("任务添加成功！");
    }

    @GetMapping("/getTaskList")
    public Result<HashMap> getTaskList(@RequestParam("page") Integer page , @RequestParam("pageSize") Integer pageSize ,
                                      @RequestParam( value = "query" ,required = false) String query , @RequestParam("userId") Integer userId){
        Result<HashMap> res = new Result<>();
        List<Task> taskList = taskService.getTaskList(page,pageSize,query,userId);
        Integer total = taskService.countTotal(query,userId);
        HashMap map = new HashMap();
        map.put("taskList",taskList);
        map.put("total",total);
        res.setResult(map);
        return res.success("任务列表获取成功！");
    }

    @PostMapping("/delSelectTask")
    public Result delSelectTask(@RequestBody List<Task> delTaskList){
        for (Task task : delTaskList){
            taskService.delete(task.getId());
        }
        return Result.ok();
    }

    @GetMapping("/getOneTask")
    public Result<Task> getOneTask(@RequestParam Integer id){
        Result<Task> res = new Result<>();
        Task task = taskService.queryOneById(id);
        res.setResult(task);
        return res.success("任务信息获取成功！");
    }

    @PostMapping("/updateTask")
    public Result updateUser(@RequestBody Task task){
        taskService.update(task);
        return Result.ok("修改成功！");
    }

    @PostMapping("/trustTask")
    public Result trustTask(@RequestBody HashMap map){
        Integer userId = (Integer) map.get("userId");
        Integer taskId = (Integer) map.get("taskId");
        taskService.trustTask(taskId,userId);
        return Result.ok();
    }
}
