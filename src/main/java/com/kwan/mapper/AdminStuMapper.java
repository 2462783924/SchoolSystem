package com.kwan.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.kwan.entity.Student;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AdminStuMapper extends BaseMapper<Student> {
}
