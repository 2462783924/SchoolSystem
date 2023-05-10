package com.kwan.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.kwan.entity.schClass;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface ClassMapper extends BaseMapper<schClass> {

    @Select("SELECT * FROM class WHERE classid = #{classid}")
    schClass getClassById(int id);

}
