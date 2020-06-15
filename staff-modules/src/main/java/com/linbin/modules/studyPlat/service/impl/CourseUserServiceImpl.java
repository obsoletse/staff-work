package com.linbin.modules.studyPlat.service.impl;

import com.linbin.modules.studyPlat.dao.CourseUserDao;
import com.linbin.modules.studyPlat.entity.CourseUser;
import com.linbin.modules.studyPlat.service.CourseUserService;
import com.linbin.system.dao.IBaseDao;
import com.linbin.system.impl.IBaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: LinBin
 * @Date: 2020/5/17 16:01
 * @Description:
 */
@Service
public class CourseUserServiceImpl extends IBaseServiceImpl<CourseUser> implements CourseUserService {

    @Autowired
    private CourseUserDao courseUserDao;


    @Override
    public CourseUser queryOne(Integer userId, Integer courseId) {
        return courseUserDao.queryOne(userId,courseId);
    }

    @Override
    public void delOne(Integer userId, Integer courseId) {
        courseUserDao.delOne(userId,courseId);
    }

    @Override
    public IBaseDao getBaseDao() {
        return courseUserDao;
    }

    @Override
    public String getTableName() {
        return "course_user";
    }

    @Override
    public Class<CourseUser> getClassEntity() {
        return CourseUser.class;
    }
}
