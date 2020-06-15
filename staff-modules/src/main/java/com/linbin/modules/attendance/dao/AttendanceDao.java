package com.linbin.modules.attendance.dao;

import com.linbin.modules.attendance.entity.Attendance;
import com.linbin.system.dao.IBaseDao;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Author: LinBin
 * @Date: 2020/5/13 15:23
 * @Description:
 */
@Mapper
public interface AttendanceDao extends IBaseDao {
    List<Attendance> isClock(Long todayStartTime, Long todayEndTime, String username, Integer type);

    void updateApprovalStatus(String username, Long clockTime);

    List<Attendance> getMyAttendanceList(Integer index, Integer pageSize, String username, String queryType, String queryStatus);

    Integer countTotal(String username, String queryType, String queryStatus);

    Attendance queryOne(String username, Long clockTime, Integer type, Integer status);

    Integer countCardClock(String username, Long time, Long eTime);

    Integer countCardReplace(String username, Long time, Long eTime);

    Integer countCardMissing(String username, Long time, Long eTime);

    Integer countLate(String username, Long time, Long eTime);

    Integer countLeaveEarly(String username, Long time, Long eTime);
}
