package com.linbin.modules.studyPlat.service.impl;

import com.linbin.modules.studyPlat.dao.CourseDao;
import com.linbin.modules.studyPlat.entity.Course;
import com.linbin.modules.studyPlat.service.CourseService;
import com.linbin.system.dao.IBaseDao;
import com.linbin.system.impl.IBaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: LinBin
 * @Date: 2020/5/1 11:31
 * @Description:
 */
@Service
public class CourseServiceImpl extends IBaseServiceImpl<Course> implements CourseService {
    @Autowired
    private CourseDao courseDao;

    @Override
    public Course queryOneByName(String courseName) {
        return courseDao.queryOneByName(courseName);
    }

    @Override
    public List<Course> queryCourseList(Integer page, Integer pageSize) {
        return courseDao.queryCourseList((page - 1) * pageSize,pageSize);
    }

    @Override
    public List<Course> getLikeCourse(String courseLike, Integer page, Integer pageSize) {
        return courseDao.getLikeCourse(courseLike,(page - 1) * pageSize,pageSize);
    }

    @Override
    public Integer countTotal() {
        return courseDao.countTotal();
    }

    @Override
    public Integer countCourseLikeTotal(String courseLike) {
        return courseDao.countCourseLikeTotal("%" + courseLike + "%");
    }

    @Override
    public List<Course> getCourseList(Integer page, Integer pageSize, String query, Integer modality) {
        if (query != null){
            query = "%" + query + "%";
        }
        return courseDao.getCourseList((page-1)*pageSize,pageSize,query,modality);
    }

    @Override
    public Integer countCourseTotal(String query, Integer modality) {
        if (query != null){
            query = "%" + query + "%";
        }
        return courseDao.countCourseTotal(query,modality);
    }

    @Override
    public List<Course> getMyCourseList(Integer page, Integer pageSize, Integer userId ,String query, Integer modality) {
        if (query != null){
            query = "%" + query + "%";
        }
        return courseDao.getMyCourseList((page-1)*pageSize,pageSize,userId,query,modality);
    }

    @Override
    public Integer countMyCourseTotal(Integer userId , String query, Integer modality) {
        if (query != null){
            query = "%" + query + "%";
        }
        return courseDao.countMyCourseTotal(userId,query,modality);
    }

    @Override
    public List<Course> getMyCourseList1(Integer userId) {
        return courseDao.getMyCourseList1(userId);
    }

    @Override
    public void updateHeatById(Integer courseId) {
        courseDao.updateHeatById(courseId);
    }

    @Override
    public IBaseDao getBaseDao() {
        return courseDao;
    }

    @Override
    public String getTableName() {
        return "study_course";
    }

    @Override
    public Class<Course> getClassEntity() {
        return Course.class;
    }
}
