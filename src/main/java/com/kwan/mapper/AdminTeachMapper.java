package com.kwan.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.kwan.entity.Student;
import com.kwan.entity.Teacher;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface AdminTeachMapper extends BaseMapper<Teacher> {
    @Select("select teacher.* ,course.coursename from teacher,course "+
            "where teacher.teachcourse = course.courseid order by teacher.id limit #{start},#{size}"
    )
    List<Teacher> getTeacher(Integer start, Integer size);

    // 根据教师姓名查找信息
    @Select("select teacher.* ,course.coursename from teacher,course "+
            "where teacher.teachcourse = course.courseid and teachname like #{teachname} order by teacher.id limit #{start},#{size}")
    List<Teacher> getTeacherByname(String teachname, Integer start, Integer size);

    // 根据课程名称查找教师信息
    @Select("select teacher.* ,course.coursename from teacher,course "+
            "where teacher.teachcourse = course.courseid and coursename like #{coursename} order by teacher.id limit #{start},#{size}")
    List<Teacher> getTeacherByCourse(String coursename,Integer start,Integer size);

    // 统计通过课程名称查找教师的数量
    @Select("select teacher.* ,course.coursename, count(*) as count from teacher,course "+
            "where teacher.teachcourse = course.courseid and coursename like #{coursename} order by teacher.id limit #{start},#{size}")
    Teacher countteachbyCourse(String coursename,Integer start,Integer size);
}
