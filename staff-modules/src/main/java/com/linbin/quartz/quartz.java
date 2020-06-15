package com.linbin.quartz;

import com.linbin.base.entity.User;
import com.linbin.base.service.UserService;
import com.linbin.modules.attendance.entity.Attendance;
import com.linbin.modules.attendance.service.AttendanceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.Date;
import java.util.List;
import java.util.TimeZone;

/**
 * @Author: LinBin
 * @Date: 2020/5/13 21:37
 * @Description:
 */
@Configuration      //1.主要用于标记配置类，兼备Component的效果。
@EnableScheduling   // 2.开启定时任务
@Slf4j
public class quartz {

    @Autowired
    private UserService userService;

    @Autowired
    private AttendanceService attendanceService;

    @Scheduled(cron = "0 36 12 ? * MON-FRI")
    public void InsertStartWorkMissCard(){
        log.info("------上班卡缺卡插入定时任务开始------");
        log.info("------开始时间：" + new Date());
        List<User> userList = userService.queryAll();
        long nowTime =System.currentTimeMillis();
        long todayStartTime =nowTime - ((nowTime + TimeZone.getDefault().getRawOffset()) % (24 * 60 * 60 * 1000L));
        long todayEndTime = todayStartTime + 12 * 60 * 60 * 1000;
        for (User user : userList) {
            Integer isEnabled = user.getIsEnabled();
            if (isEnabled == 0){
                continue;//跳过本次循环
            }
            String username = user.getUsername();
            //判断当前当前人是否打了上班卡
            List<Attendance> StartWork = attendanceService.isClock(username,1);
            if (StartWork.isEmpty()){
                Attendance attendance = new Attendance();
                attendance.setUsername(username);
                attendance.setRealName(user.getRealName());
                attendance.setClockTime(todayEndTime);
                attendance.setType(1);
                attendance.setStatus(0);
                attendance.setIsReplace(0);
                attendance.setIsApproval(0);
                attendanceService.add(attendance,null);
            }
        }
        log.info("------结束时间：" + new Date());
    }

    @Scheduled(cron = "0 30 22 ? * MON-FRI")
    public void InsertEndWorkMissCard(){
        log.info("------下班缺卡插入定时任务开始------");
        log.info("------开始时间：" + new Date());
        List<User> userList = userService.queryAll();
        long nowTime =System.currentTimeMillis();
        long todayStartTime =nowTime - ((nowTime + TimeZone.getDefault().getRawOffset()) % (24 * 60 * 60 * 1000L));
        long todayEndTime = todayStartTime + 24 * 60 * 60 * 1000 - 1;
        for (User user : userList) {
            String username = user.getUsername();
            Integer isEnabled = user.getIsEnabled();
            if (isEnabled == 0){
                continue;//跳过本次循环
            }
            //判断当前当前人是否打了下班卡
            List<Attendance> endWork = attendanceService.isClock(username,2);
            if (endWork.isEmpty()){
                Attendance attendance = new Attendance();
                attendance.setUsername(username);
                attendance.setRealName(user.getRealName());
                attendance.setClockTime(todayEndTime);
                attendance.setType(2);
                attendance.setStatus(0);
                attendance.setIsApproval(0);
                attendance.setIsReplace(0);
                attendanceService.add(attendance,null);
            }
        }
        log.info("------结束时间：" + new Date());
    }
}
