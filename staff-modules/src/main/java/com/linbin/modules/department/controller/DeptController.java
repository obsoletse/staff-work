package com.linbin.modules.department.controller;

import com.linbin.modules.department.entity.Department;
import com.linbin.modules.department.service.DeptService;
import com.linbin.vo.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author: LinBin
 * @Date: 2020/4/9 18:59
 * @Description:
 */
@RestController
@RequestMapping("/dept")
@Transactional
public class DeptController {

    private static final Logger logger = LoggerFactory.getLogger(DeptController.class);
    @Autowired
    private DeptService deptService;

    @GetMapping("/getAllDept")
    public Result<List<Department>> getAllDept(){
        Result<List<Department>> res = new Result<>();
        List<Department> departmentList = deptService.queryAllDept();
        res.setResult(departmentList);
        return res.success("获取成功!");
    }

    @GetMapping("/getSelectDept")
    public Result<List<Department>> getSelectDept(){
        Result<List<Department>> res = new Result<>();
        List<Department> departmentList = deptService.querySelectDept();
        res.setResult(departmentList);
        return res.success("获取成功!");
    }

    @GetMapping("/getDeptInfo")
    public Result<Department> getDeptInfo(@RequestParam(value = "deptId",required = false) Integer id){
        Result<Department> res = new Result<>();
        if (id == null){
            return res.error500("请传入部门Id!");
        }
        Department department = deptService.queryOneById(id);
        if (department == null){
            return  res.error500("查询部门不存在！请检查部门状态");
        }
        res.setResult(department);
        logger.info("----------部门信息如下：----------");
        logger.info(String.valueOf(department));
        return res.success("获取成功！");
    }

    @PostMapping("/updateDept")
    public Result updateMenu(@RequestBody Department department){
        Department dept = deptService.queryOneById(department.getId());
        if (dept.getId().equals(department.getParentId())){
            return Result.error("非法操作！");
        }
        deptService.update(department);
        return Result.ok();
    }

    @PostMapping("/addDept")
    public Result addDept(@RequestBody Department dept){
        Result res = new Result();
        if (deptService.queryOneByName(dept.getDeptName()) != null){
            return res.error500("此部门已存在！");
        }if (deptService.queryOneByNameEn(dept.getDeptNameEn()) != null){
            return res.error500("此英文名称已存在！");
        }
        Integer deptCode = ((int)(Math.random() * 999999 + 1 ));
        dept.setId(null);
        dept.setDeptCode(deptCode.toString());
        deptService.add(dept,"children");
        return res.success("部门添加成功！");
    }

    @GetMapping("/getDeptLike")
    public Result<List<Department>> getDeptLike(@RequestParam String query){
        Result<List<Department>> res = new Result<>();
        List<Department> departmentList = deptService.getDeptLike(query);
        res.setResult(departmentList);
        return res.success("获取成功！");
    }

    @PostMapping("/delSelectDept")
    public Result delSelectDept (@RequestBody List<Integer> idList){
        for (Integer deptId: idList) {
            deptService.delete(deptId);
        }
        return Result.ok();
    }
}
