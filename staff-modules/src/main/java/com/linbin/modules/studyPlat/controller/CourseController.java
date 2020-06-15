package com.linbin.modules.studyPlat.controller;

import com.linbin.modules.studyPlat.entity.Course;
import com.linbin.modules.studyPlat.entity.CourseUser;
import com.linbin.modules.studyPlat.service.CourseService;
import com.linbin.modules.studyPlat.service.CourseUserService;
import com.linbin.vo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

/**
 * @Author: LinBin
 * @Date: 2020/4/30 22:47
 * @Description:
 */
@Transactional
@RestController
@RequestMapping("/course")
@Slf4j
public class CourseController {
    @Autowired
    private CourseService courseService;

    @Autowired
    private CourseUserService courseUserService;

    @GetMapping("/getCourseList")
    public Result<HashMap> getCourseList(@RequestParam("page") Integer page, @RequestParam("pageSize") Integer pageSize){
        Result<HashMap> res = new Result<>();
        List<Course> courseList = courseService.queryCourseList(page,pageSize);
        Integer total = courseService.countTotal();
        HashMap map = new HashMap();
        map.put("courseList",courseList);
        map.put("total",total);
        res.setResult(map);
        return res.success("获取课程列表成功！");
    }

    @PostMapping("/addCourse")
    public Result addCourse(@RequestBody Course course){
        Result res = new Result();
        if (courseService.queryOneByName(course.getCourseName()) != null){
            return res.error500("此课程已经存在！");
        }
        course.setHeat(0);
        courseService.add(course,null);
        return res.success("课程添加成功！");
    }

    @GetMapping("/getOneCourse")
    public Result getOneCourse(@RequestParam Integer courseId){
        Course course = courseService.queryOneById(courseId);
        return Result.ok(course);
    }

    @PostMapping("/updateCourse")
    public Result updateCourse(@RequestBody Course course){
        courseService.update(course);
        return Result.ok("修改成功！");
    }

    @PostMapping("/delSelectCourse")
    public Result delSelectCourse(@RequestBody List<Course> list){
        for (Course course : list){
            courseService.delete(course.getId());
        }
        return Result.ok();
    }

    @DeleteMapping("/delOneCourse")
    public Result delOneCourse(@RequestParam Integer courseId){
        courseService.delete(courseId);
        return Result.ok();
    }

    @GetMapping("/getLikeCourses")
    public Result<HashMap> getLikeCourses(@RequestParam("courseLike") String courseLike , @RequestParam("page") Integer page,@RequestParam("pageSize") Integer pageSize){
        Result<HashMap> res = new Result<>();
        List<Course> courseList = courseService.getLikeCourse("%" + courseLike + "%" , page ,pageSize);
        HashMap map = new HashMap();
        map.put("courseList",courseList);
        map.put("total",courseService.countCourseLikeTotal("%" + courseLike + "%"));
        res.setResult(map);
        return res.success("获取用户列表成功！");
    }

    @GetMapping("/getAllCourse")
    public Result<HashMap> getAllCourse(@RequestParam("page") Integer page , @RequestParam("pageSize") Integer pageSize ,
                                        @RequestParam( value = "query" ,required = false) String query ,
                                        @RequestParam( value = "modality" ,required = false) Integer modality){
        Result<HashMap> res = new Result<>();
        List<Course> courseList = courseService.getCourseList(page,pageSize,query,modality);
        Integer total = courseService.countCourseTotal(query,modality);
        HashMap map = new HashMap();
        map.put("courseList",courseList);
        map.put("total",total);
        res.setResult(map);
        return res.success("获取课程列表成功！");
    }

    @PostMapping("/addMyCourse")
    public Result addMyCourse(@RequestBody CourseUser courseUser){
        courseUserService.add(courseUser,null);
        courseService.updateHeatById(courseUser.getCourseId());
        return Result.ok();
    }

    @PostMapping("/isEnter")
    public Result isEnter(@RequestBody CourseUser courseUser){
        CourseUser courseUser1 = courseUserService.queryOne(courseUser.getUserId(),courseUser.getCourseId());
        if (courseUser1 != null){
            return Result.ok(true);
        }
        return Result.ok(false);
    }

    @GetMapping("/getMyCourseList")
    public Result<HashMap> getMyCourseList(@RequestParam("page") Integer page , @RequestParam("pageSize") Integer pageSize ,@RequestParam("userId") Integer userId,
                                        @RequestParam( value = "query" ,required = false) String query ,
                                        @RequestParam( value = "modality" ,required = false) Integer modality){
        Result<HashMap> res = new Result<>();
        List<Course> courseList = courseService.getMyCourseList(page,pageSize,userId,query,modality);
        Integer total = courseService.countMyCourseTotal(userId,query,modality);
        HashMap map = new HashMap();
        map.put("courseList",courseList);
        map.put("total",total);
        res.setResult(map);
        return res.success("获取课程列表成功！");
    }

    @DeleteMapping("/delMyCourse")
    public Result delMyCourse(@RequestParam("userId") Integer userId, @RequestParam("courseId") Integer courseId){
        courseUserService.delOne(userId,courseId);
        return Result.ok();
    }

    @GetMapping("/getMyCourseList1")
    public Result<List<Course>> getMyCourseList1(@RequestParam("userId") Integer userId){
        Result<List<Course>> res = new Result<>();
        List<Course> courseList = courseService.getMyCourseList1(userId);
        res.setResult(courseList);
        return res.success("获取课程列表成功！");
    }
}
