package com.linbin.modules.attendance.controller;

import com.linbin.base.entity.User;
import com.linbin.base.service.UserService;
import com.linbin.modules.attendance.entity.Attendance;
import com.linbin.modules.attendance.service.AttendanceService;
import com.linbin.modules.attendance.service.CardReplaceService;
import com.linbin.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * @Author: LinBin
 * @Date: 2020/5/13 15:25
 * @Description:
 */
@RestController
@RequestMapping("/attendance")
@Transactional
public class AttendanceController {

    @Autowired
    private AttendanceService attendanceService;

    @Autowired
    private UserService userService;

    @Autowired
    private CardReplaceService cardReplaceService;

    @GetMapping("/getMyAttendanceList")
    public Result<HashMap> getMyAttendanceList(@RequestParam("page") Integer page , @RequestParam("pageSize") Integer pageSize , @RequestParam("username") String username,
                                               @RequestParam( value = "queryType" ,required = false) String queryType ,
                                               @RequestParam( value = "queryStatus" ,required = false) String queryStatus){
        Result<HashMap> res = new Result<>();
        List<Attendance> attendanceList = attendanceService.getMyAttendanceList(page,pageSize,username,queryType,queryStatus);
        Integer total = attendanceService.countTotal(username,queryType,queryStatus);
        HashMap map = new HashMap();
        map.put("attendanceList",attendanceList);
        map.put("total",total);
        res.setResult(map);
        return res.success("个人考勤列表获取成功！");
    }



    @PostMapping("/clockCard")
    public Result<Object> clockCard(@RequestBody Attendance attendance){
        attendance.setIsApproval(0);
        attendanceService.add(attendance,null);
        return Result.ok("打卡成功");
    }

    @GetMapping("/isClock")
    public Result isClock(@RequestParam("username") String username , @RequestParam("type") Integer type){
        HashMap<String, Boolean> map = new HashMap<String, Boolean>();
        List<Attendance> attendance = attendanceService.isClock(username,type);
        if (!attendance.isEmpty()){
            map.put("isClock",true);
        }else {
            map.put("isClock",false);
        }
        return Result.ok(map);
    }

    @GetMapping("/getStatisticsList")
    public Result getStatisticsList(@RequestParam("page") Integer page , @RequestParam("pageSize") Integer pageSize ,
                                    @RequestParam("deptId") Integer deptId , @RequestParam("time") Long time,
                                    @RequestParam("eTime") Long eTime){
        HashMap hashMap = new HashMap();
        List<HashMap> list = new ArrayList<>();
        List<User> users = userService.queryAll();
        for (User user : users){
            if (!user.getDeptId().equals(deptId) || user.getIsEnabled() != 1){
                continue;
            }
            HashMap map = new HashMap();
            //姓名
            map.put("name",user.getRealName());
            //正常打卡次数
            Integer cardClock = attendanceService.countCardClock(user.getUsername(),time,eTime);
            map.put("cardClock",cardClock);
            //补卡次数
            Integer cardReplace = attendanceService.countCardReplace(user.getUsername(),time,eTime);
            map.put("cardReplace",cardReplace);
            //缺卡次数
            Integer cardMissing = attendanceService.countCardMissing(user.getUsername(),time,eTime);
            map.put("cardMissing",cardMissing);
            //迟到天数
            Integer late = attendanceService.countLate(user.getUsername(),time,eTime);
            map.put("late",late);
            //早退天数
            Integer leaveEarly = attendanceService.countLeaveEarly(user.getUsername(),time,eTime);
            map.put("leaveEarly",leaveEarly);
            list.add(map);
        }
        if (page * pageSize > list.size()){
            hashMap.put("statisticsList",list.subList((page-1) * pageSize,list.size()));
        }else {
            hashMap.put("statisticsList",list.subList((page-1) * pageSize,page * pageSize));
        }
        hashMap.put("total",list.size());
        return Result.ok(hashMap);
    }
}
