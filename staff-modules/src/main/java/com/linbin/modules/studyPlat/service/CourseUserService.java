package com.linbin.modules.studyPlat.service;

import com.linbin.modules.studyPlat.entity.CourseUser;
import com.linbin.system.service.IBaseService;

/**
 * @Author: LinBin
 * @Date: 2020/5/17 16:01
 * @Description:
 */
public interface CourseUserService extends IBaseService<CourseUser> {
    CourseUser queryOne(Integer userId , Integer courseId);
    void delOne(Integer userId , Integer courseId);
}
