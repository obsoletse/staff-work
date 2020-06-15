package com.linbin.modules.attendance.service.impl;

import com.linbin.modules.attendance.dao.AttendanceDao;
import com.linbin.modules.attendance.entity.Attendance;
import com.linbin.modules.attendance.service.AttendanceService;
import com.linbin.system.dao.IBaseDao;
import com.linbin.system.impl.IBaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.TimeZone;

/**
 * @Author: LinBin
 * @Date: 2020/5/13 15:24
 * @Description:
 */
@Service
public class AttendanceServiceImpl extends IBaseServiceImpl<Attendance> implements AttendanceService {

    @Autowired
    private AttendanceDao attendanceDao;


    @Override
    public List<Attendance> getMyAttendanceList(Integer page, Integer pageSize, String username , String queryType, String queryStatus) {
        return attendanceDao.getMyAttendanceList((page-1)*pageSize,pageSize,username,queryType,queryStatus);
    }

    @Override
    public Integer countTotal(String username , String queryType, String queryStatus) {
        return attendanceDao.countTotal(username,queryType,queryStatus);
    }

    @Override
    public void updateApprovalStatus(String username, Long clockTime) {
        attendanceDao.updateApprovalStatus(username,clockTime);
    }

    @Override
    public List<Attendance> isClock(String username , Integer type) {
        long nowTime =System.currentTimeMillis();
        long todayStartTime =nowTime - ((nowTime + TimeZone.getDefault().getRawOffset()) % (24 * 60 * 60 * 1000L));
        long todayEndTime = todayStartTime + 24 * 60 * 60 * 1000 - 1;
        return attendanceDao.isClock(todayStartTime,todayEndTime,username,type);
    }

    @Override
    public Attendance queryOne(String username, Long clockTime, Integer type, Integer status) {
        return attendanceDao.queryOne(username,clockTime,type,status);
    }

    @Override
    public Integer countCardClock(String username, Long time,Long eTime) {
        return attendanceDao.countCardClock(username,time,eTime);
    }

    @Override
    public Integer countCardReplace(String username, Long time,Long eTime) {
        return attendanceDao.countCardReplace(username,time,eTime);
    }

    @Override
    public Integer countCardMissing(String username, Long time,Long eTime) {
        return attendanceDao.countCardMissing(username,time,eTime);
    }

    @Override
    public Integer countLate(String username, Long time,Long eTime) {
        return attendanceDao.countLate(username,time,eTime);
    }

    @Override
    public Integer countLeaveEarly(String username, Long time,Long eTime) {
        return attendanceDao.countLeaveEarly(username,time,eTime);
    }

    @Override
    public IBaseDao getBaseDao() {
        return attendanceDao;
    }

    @Override
    public String getTableName() {
        return "attendance";
    }

    @Override
    public Class<Attendance> getClassEntity() {
        return Attendance.class;
    }
}
