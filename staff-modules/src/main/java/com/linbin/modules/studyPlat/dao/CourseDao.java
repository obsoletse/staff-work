package com.linbin.modules.studyPlat.dao;

import com.linbin.modules.studyPlat.entity.Course;
import com.linbin.system.dao.IBaseDao;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Author: LinBin
 * @Date: 2020/5/1 11:30
 * @Description:
 */
@Mapper
public interface CourseDao extends IBaseDao {
    Course queryOneByName(String courseName);
    List<Course> queryCourseList(Integer index, Integer pageSize);
    List<Course> getLikeCourse(String courseLike , Integer index , Integer pageSize);
    Integer countTotal();
    Integer countCourseLikeTotal(String courseLike);
    List<Course> getCourseList(Integer index, Integer pageSize, String query,Integer modality);
    Integer countCourseTotal( String query , Integer modality);
    void updateHeatById(Integer courseId);
    List<Course> getMyCourseList(Integer index, Integer pageSize, Integer userId , String query,Integer modality);
    Integer countMyCourseTotal(Integer userId , String query , Integer modality);
    List<Course> getMyCourseList1(Integer userId);
}
