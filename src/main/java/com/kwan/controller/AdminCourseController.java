package com.kwan.controller;

import com.kwan.common.Result;
import com.kwan.entity.Course;
import com.kwan.service.Impl.AdminCourseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Slf4j
@RestController

@RequestMapping("/admincourse")
//解决跨域问题
@CrossOrigin(originPatterns = "*", allowCredentials = "true", allowedHeaders = "*")
public class AdminCourseController {
    @Autowired
    private AdminCourseService adminCourseService;

    @PostMapping("/getcoursebyName")
    @ResponseBody
    // 统计指定课程名的数量
    public Result countCourse(String coursename, Integer start, Integer size) {
        Integer page = (start - 1) * size;
        return adminCourseService.countCourse(coursename,page,size);
    }

    @PostMapping("/findcourseName")
    @ResponseBody
    // 查找全部课程
    public List<Course> findCourse() {
        return adminCourseService.findCourse();
    }

    @PostMapping("/pageCourse")
    @ResponseBody
    // 分页查询课程信息
    public Result adminCourse(Integer start, Integer size,String coursename) {
        return adminCourseService.pageCourse(start, size,coursename);
    }

    @PostMapping("/updatecourse")
    @ResponseBody
    // 更新课程
    public Result<Course> updateCourse(HttpServletRequest request, @RequestBody Course course) {
        return adminCourseService.updateCourse(request,course);
    }

    @PostMapping("/deletecourse")
    @ResponseBody
    // 删除课程
    public Result deleteCourse(Integer courseid) {
        return adminCourseService.delete(courseid);
    }

    @PostMapping("/insertcourse")
    @ResponseBody
    // 添加课程
    public Result insertCourse(HttpServletRequest request, @RequestBody Course course){
        return adminCourseService.insertCourse(request,course);
    }

}
