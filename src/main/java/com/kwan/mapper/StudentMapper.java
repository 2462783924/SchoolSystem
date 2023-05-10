package com.kwan.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.kwan.entity.Student;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface StudentMapper extends BaseMapper<Student> {
    // 查找所有学生信息
    @Select("select student.* ,class.classname from student,class "+
            "where student.classid = class.classid order by student.id limit #{start},#{size}"
    )
    List<Student> getStudent(Integer start,Integer size);

    // 根据学生姓名查找信息
    @Select("select student.* ,class.classname from student,class "+
            "where student.classid = class.classid and stuname like #{stuname} order by student.id limit #{start},#{size}")
    List<Student> getStudentByname(String stuname,Integer start,Integer size);

    // 根据班级名称查找学生信息
    @Select("select student.* ,class.classname from student,class "+
            "where student.classid = class.classid and classname like #{classname} order by student.id limit #{start},#{size}")
    List<Student> getStudentByclass(String classname,Integer start,Integer size);

    // 统计通过班级名称查找学生的数量
    @Select("select student.* ,class.classname, count(*) as count from student,class "+
            "where student.classid = class.classid and classname like #{classname} order by student.id limit #{start},#{size}")
    Student countstubyClass(String classname,Integer start,Integer size);
}
