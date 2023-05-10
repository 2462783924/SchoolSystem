package com.kwan.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kwan.common.Result;
import com.kwan.entity.Course;
import com.kwan.entity.Teacher;
import com.kwan.mapper.AdminTeachMapper;
import com.kwan.mapper.CourseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
public class AdminCourseService {
    @Autowired
    private AdminTeachMapper teachMapper;

    // 获取统计指定课程名的数量
    public Result countCourse(String coursename, Integer start, Integer size) {
        Teacher teacher = teachMapper.countteachbyCourse("%" + coursename + "%", start, size);
        return Result.success(teacher);
    }

    @Autowired
    private CourseMapper courseMapper;

    // 查找全部班级
    public List<Course> findCourse() {
        return courseMapper.selectList(null);
    }

    /**
     * 查找课程，并分页显示
     *
     * @param start
     * @param size
     * @param coursename
     * @return
     */
    public Result<IPage> pageCourse(Integer start, Integer size, String coursename) {
        IPage page = new Page(start, size);
        QueryWrapper<Course> qw = new QueryWrapper();
        if (coursename == null) {
            coursename = "";
        }
        qw.like("coursename", coursename);
        courseMapper.selectPage(page, qw);
        List<Course> courses = page.getRecords();
        return Result.success(page);
    }

    /**
     * 更新课程信息
     *
     * @param request
     * @param course
     * @return
     */
    public Result<Course> updateCourse(HttpServletRequest request, @RequestBody Course course) {
        QueryWrapper<Course> qw = new QueryWrapper();
        qw.eq("coursename", course.getCoursename());
        Course c = courseMapper.selectOne(qw);
        if (c != null) {
            if (course.getCourseid() != c.getCourseid())
                return Result.error("课程名称重复");
        }

        // 判断启动/禁用
        if (course.isFlag()) {
            course.setIfexist(1);
        } else {
            course.setIfexist(0);
        }
        courseMapper.updateById(course);
        return Result.success(course);
    }

    /**
     * 删除课程信息
     *
     * @param courseid
     * @return
     */
    public Result delete(Integer courseid) {
        courseMapper.deleteById(courseid);
        return Result.success("删除成功");
    }

    /**
     * 添加课程
     */
    public Result insertCourse(HttpServletRequest request, @RequestBody Course course) {

        QueryWrapper<Course> qw = new QueryWrapper();
        qw.eq("coursename", course.getCoursename());
        Course c = courseMapper.selectOne(qw);
        if (c != null) {
            return Result.error("课程名称重复");
        }
        if (course.isFlag()) {
            course.setIfexist(1);
        } else {
            course.setIfexist(0);
        }
        courseMapper.insert(course);
        return Result.success("课程：\'" + course.getCoursename() + "\'——已添加成功");
    }
}
