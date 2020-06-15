package com.linbin.modules.attendance.service;

import com.linbin.modules.attendance.entity.Attendance;
import com.linbin.system.service.IBaseService;

import java.util.List;

/**
 * @Author: LinBin
 * @Date: 2020/5/13 15:23
 * @Description:
 */
public interface AttendanceService extends IBaseService<Attendance> {
    List<Attendance> isClock(String username , Integer type);
    void updateApprovalStatus(String username , Long clockTime);
    List<Attendance> getMyAttendanceList(Integer page ,Integer pageSize , String username , String queryType , String queryStatus);
    Integer countTotal(String username , String queryType , String queryStatus);
    Attendance queryOne(String username , Long clockTime , Integer type, Integer status);
    Integer countCardClock(String username ,Long time,Long eTime);
    Integer countCardReplace(String username ,Long time,Long eTime);
    Integer countCardMissing(String username ,Long time,Long eTime);
    Integer countLate(String username ,Long time,Long eTime);
    Integer countLeaveEarly(String username ,Long time,Long eTime);
}
