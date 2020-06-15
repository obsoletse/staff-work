package com.linbin.modules.studyPlat.service;

import com.linbin.modules.studyPlat.entity.Course;
import com.linbin.system.service.IBaseService;

import java.util.List;

/**
 * @Author: LinBin
 * @Date: 2020/5/1 11:31
 * @Description:
 */
public interface CourseService extends IBaseService<Course> {
    Course queryOneByName(String courseName);
    List<Course> queryCourseList(Integer page, Integer pageSize);
    List<Course> getLikeCourse(String courseLike , Integer page , Integer pageSize);
    Integer countTotal();
    Integer countCourseLikeTotal(String courseLike);
    List<Course> getCourseList(Integer page, Integer pageSize,String query,Integer modality);
    Integer countCourseTotal(String query , Integer modality);
    void updateHeatById(Integer courseId);
    List<Course> getMyCourseList(Integer page, Integer pageSize,Integer userId ,String query,Integer modality);
    Integer countMyCourseTotal(Integer userId ,String query , Integer modality);
    List<Course> getMyCourseList1(Integer userId);
}
