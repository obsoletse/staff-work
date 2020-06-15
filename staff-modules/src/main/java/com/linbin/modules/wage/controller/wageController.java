package com.linbin.modules.wage.controller;

import com.linbin.modules.wage.entity.Wage;
import com.linbin.modules.wage.service.WageService;
import com.linbin.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

/**
 * @Author: LinBin
 * @Date: 2020/5/14 19:34
 * @Description:
 */
@RestController
@RequestMapping("/wage")
@Transactional
public class wageController {

    @Autowired
    private WageService wageService;

    @GetMapping("/getMyWageList")
    public Result<HashMap> getMyAttendanceList(@RequestParam("page") Integer page , @RequestParam("pageSize") Integer pageSize , @RequestParam("realName") String realName,
                                               @RequestParam( value = "queryYear" ,required = false) Integer queryYear ,
                                               @RequestParam( value = "queryMonth" ,required = false) Integer queryMonth){
        Result<HashMap> res = new Result<>();
        List<Wage> wageList = wageService.getMyWageList(page,pageSize,realName,queryYear,queryMonth);
        Integer total = wageService.countTotal(realName,queryYear,queryMonth);
        HashMap map = new HashMap();
        map.put("wageList",wageList);
        map.put("total",total);
        res.setResult(map);
        return res.success("工资单列表获取成功！");
    }
}
