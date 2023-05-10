//package com.kwan.mapper;
//
//import com.baomidou.mybatisplus.core.mapper.BaseMapper;
//import com.kwan.entity.Student;
//import org.apache.ibatis.annotations.*;
//import org.springframework.stereotype.Repository;
//
//@Mapper
//@Repository
//public interface StudentMapperOne extends BaseMapper<Student> {
//    //@Results 注解来映射查询结果集到实体类属性
//    @Results({
//            @Result(column = "classid", property = "classid"),
//            @Result(column = "classid", property = "schClass",
//                    one = @One(select = "com.kwan.mapper.ClassMapper.selectById"))
//    })
//    @Select("SELECT * FROM student WHERE id = #{id}")
//    Student getStudentById(int id);
//}
