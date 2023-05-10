package com.kwan.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.kwan.entity.Score;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ScoreMapper extends BaseMapper<Score> {

    @Select("select DISTINCT score.* ,course.coursename from score,course "+
            "where score.courseid = course.courseid and stuno like #{stuno} order by score.stuno,course.courseid limit #{start},#{size}")
    List<Score> getScoreByStuno(String stuno, Integer start, Integer size);

    @Select("select DISTINCT score.* ,course.coursename from score,course "+
            "where score.courseid = course.courseid and course.coursename like #{coursename} order by score.stuno,score.courseid limit #{start},#{size}")
    List<Score> getScoreByCoursename(String coursename, Integer start, Integer size);

    // 统计通过课程名称查找成绩的数量
    @Select("select score.* ,course.coursename, count(DISTINCT stuno) as count from score,course "+
            "where score.courseid = course.courseid and course.coursename like #{coursename}")
    Score countScoreByCourse(String coursename);
}
