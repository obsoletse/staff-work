package com.linbin.modules.department.controller;

import com.linbin.modules.department.entity.Project;
import com.linbin.modules.department.service.ProjectService;
import com.linbin.vo.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @Author: LinBin
 * @Date: 2020/4/10 18:10
 * @Description:
 */
@RestController
@RequestMapping("/pro")
@Transactional
public class ProjectController {
    public static final Logger logger = LoggerFactory.getLogger(ProjectController.class);

    @Autowired
    private ProjectService projectService;

    @GetMapping("/getProList")
    public Result<HashMap> getProList(@RequestParam("page") Integer page , @RequestParam("pageSize") Integer pageSize ,
                                      @RequestParam( value = "query" ,required = false) String query , @RequestParam("deptId") Integer deptId){
        Result<HashMap> res = new Result<>();
        List<Project> proList = projectService.getProList(page,pageSize,query,deptId);
        Integer total = projectService.countTotal(query,deptId);
        HashMap map = new HashMap();
        map.put("proList",proList);
        map.put("total",total);
        res.setResult(map);
        return res.success("项目列表获取成功！");
    }

    @PostMapping("/addPro")
    public Result add(@RequestBody Project project){
        projectService.add(project,"depart");
        return Result.ok("添加成功！");
    }

    @PostMapping("/delSelectPros")
    public Result delSelectPros(@RequestBody List<Project> delProList){
        for (Project project : delProList){
            projectService.delete(project.getId());
        }
        return Result.ok();
    }

    @GetMapping("/getOneProject")
    public Result<Project> getOneProject(@RequestParam Integer id){
        Result<Project> res = new Result<>();
        Project project = projectService.getProById(id);
        res.setResult(project);
        return res.success("项目信息获取成功！");
    }

    @PostMapping("/updatePro")
    public Result updateUser(@RequestBody Project project){
        projectService.update(project);
        return Result.ok("修改成功！");
    }

    @PostMapping("/trustPro")
    public Result trustPro(@RequestBody HashMap map){
        Integer proId = (Integer) map.get("proId");
        Integer deptId = (Integer) map.get("deptId");
        projectService.trustPro(proId,deptId);
        return Result.ok();
    }

    @GetMapping("/getMyProject")
    public Result<List<Project>> getMyProject(@RequestParam("deptId") Integer deptId){
        Result<List<Project>> res = new Result<>();
        List<Project> projectList = projectService.queryProList(deptId);
        logger.info("----------个人项目信息如下----------");
        logger.info(projectList.toString());
        res.setResult(projectList);
        return res;
    }

    @GetMapping("/getSelectPro")
    public Result getSelectPro(@RequestParam("deptId") Integer deptId){
        Result<List<Project>> res = new Result<>();
        List<Project> projectList = projectService.queryProList(deptId);
        res.setResult(projectList);
        return res;
    }

    @GetMapping("/getAllPro")
    public Result getAllPro(){
        Result<List<Project>> res = new Result<>();
        List<Project> projectList = projectService.queryAll();
        res.setResult(projectList);
        return res;
    }

}
