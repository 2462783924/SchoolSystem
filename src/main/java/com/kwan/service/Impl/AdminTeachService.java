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
public class AdminTeachService {
    @Autowired
    AdminTeachMapper teachMapper;

    @Autowired
    CourseMapper courseMapper;

    // 获取所有教师信息（教师表+课程表）
    public Result<List<Teacher>> findAllTeacher(Integer start, Integer size) {
        List<Teacher> teacher = teachMapper.getTeacher(start,size);
        return Result.success(teacher);
    }

    // 统计教师信息数（条件统计）
    public long countTeacher(String column, String val, Integer start, Integer size) {
        IPage page = new Page(start, size);
        QueryWrapper qw = new QueryWrapper();

        if (column == null || column.equals("")) {
            teachMapper.selectPage(page,null);
        } else {
            qw.like(column, val);
            teachMapper.selectPage(page, qw);
        }
        return page.getTotal();
    }

    // 通过教师姓名查找
    public Result<List<Teacher>> getteachbyName(String name, Integer start, Integer size) {
        List<Teacher> teacher = teachMapper.getTeacherByname("%" + name + "%", start, size);
        return Result.success(teacher);
    }

    // 通过班级查找学生信息
    public Result<List<Teacher>> getteachbyCourse(String course, Integer start, Integer size) {
        List<Teacher> teacher = teachMapper.getTeacherByCourse("%" + course + "%", start, size);
        return Result.success(teacher);
    }

    // 更新教师信息
    public Result<Teacher> update(HttpServletRequest request, @RequestBody Teacher teacher) {
        String coursename = teacher.getCoursename();
        QueryWrapper<Course> qw = new QueryWrapper();
        qw.eq("coursename", coursename);
        Course course = courseMapper.selectOne(qw);

        QueryWrapper<Teacher> qws = new QueryWrapper();
        qws.eq("teachno", teacher.getTeachno());
        Teacher t = teachMapper.selectOne(qws);
        if (t != null) {
            if (!t.getId().equals(teacher.getId())) {
                return Result.error("学生学号已存在");
            }
        }

        if (course == null) {
            return Result.error("课程名称不存在");
        }

        teacher.setTeachcourse(course.getCourseid());
        teachMapper.updateById(teacher);

        return Result.success(teacher);
    }

}
