package com.linbin.modules.studyPlat.dao;

import com.linbin.modules.studyPlat.entity.CourseUser;
import com.linbin.system.dao.IBaseDao;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Author: LinBin
 * @Date: 2020/5/17 16:00
 * @Description:
 */
@Mapper
public interface CourseUserDao extends IBaseDao {
    CourseUser queryOne(Integer userId , Integer courseId);
    void delOne(Integer userId , Integer courseId);
}
