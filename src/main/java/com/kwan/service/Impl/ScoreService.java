package com.kwan.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kwan.common.Result;
import com.kwan.entity.Course;
import com.kwan.entity.Score;
import com.kwan.entity.Student;
import com.kwan.mapper.CourseMapper;
import com.kwan.mapper.ScoreMapper;
import com.kwan.mapper.StudentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
public class ScoreService {

    @Autowired
    private ScoreMapper scoreMapper;

    // 根据学号获取所有成绩信息
    public Result<List<Score>> pageScore(String stuno, Integer start, Integer size) {
        List<Score> scores = scoreMapper.getScoreByStuno("%" + stuno + "%", start, size);
        return Result.success(scores);
    }

    // 根据课程获取所有成绩信息
    public Result<List<Score>> findByCourse(String coursename, Integer start, Integer size) {
        List<Score> scores = scoreMapper.getScoreByCoursename("%" + coursename + "%", start, size);
        return Result.success(scores);
    }

    // 统计通过课程名称查找成绩的数量
    public Result<Score> countScoreByCourse(String coursename) {

        Score scores = scoreMapper.countScoreByCourse("%" + coursename + "%");

        return Result.success(scores);
    }

    @Autowired
    CourseMapper courseMapper;

    // 统计成绩信息数（条件统计）
    public Result<IPage> countScore(String column, String val, Integer start, Integer size) {
        IPage page = new Page(start, size);
        QueryWrapper<Score> qw = new QueryWrapper();

        if (column == null || column.equals("")) {
            scoreMapper.selectPage(page, null);
        } else {
            qw.select("distinct *").like(column, val);
            scoreMapper.selectPage(page, qw);
        }
        return Result.success(page);
    }

    // 删除成绩
    public Result deleteScore(Integer stuno, Integer courseid) {
        QueryWrapper<Score> qw = new QueryWrapper();
        qw.eq("stuno", stuno).eq("courseid", courseid);
        scoreMapper.delete(qw);
        return Result.success("删除成功");
    }

    @Autowired
    StudentMapper studentMapper;
    /**
     * 添加成绩
     */
    public Result insertScore(HttpServletRequest request, @RequestBody Score score) {

        QueryWrapper<Student> stu = new QueryWrapper();
        stu.eq("stuno",score.getStuno());
        Student stuno = studentMapper.selectOne(stu);
        if (stuno == null){
            return Result.error("学生不存在");
        }

        QueryWrapper<Course> course = new QueryWrapper();
        course.eq("courseid",score.getCourseid());
        Course courseid = courseMapper.selectOne(course);
        if (courseid == null){
            return Result.error("课程不存在");
        }

        QueryWrapper<Score> qw = new QueryWrapper();
        qw.select("distinct *").eq("courseid", score.getCourseid()).eq("stuno", score.getStuno());
        Score s = scoreMapper.selectOne(qw);

        if (s != null) {
            return Result.error("成绩信息重复");
        }
        scoreMapper.insert(score);

        return Result.success("学生：" + score.getStuno() + " 课程：" + score.getCoursename() + " —— 已添加成功");
    }

    /**
     * 更新成绩信息
     *
     * @param request
     * @return
     */
    public Result<Score> updateScore(HttpServletRequest request, @RequestBody Score score) {
        QueryWrapper<Score> qw = new QueryWrapper();
        qw.select("distinct *").eq("courseid", score.getCourseid()).eq("stuno", score.getStuno());
        scoreMapper.update(score,qw);
        return Result.success(score);
    }
}
