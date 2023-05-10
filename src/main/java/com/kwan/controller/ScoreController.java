package com.kwan.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.kwan.common.Result;
import com.kwan.entity.Course;
import com.kwan.entity.Score;
import com.kwan.service.Impl.ScoreService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/score")
//解决跨域问题
@CrossOrigin(originPatterns = "*", allowCredentials = "true", allowedHeaders = "*")
public class ScoreController {

    @Autowired
    private ScoreService scoreService;

    @PostMapping("/pagescore")
    @ResponseBody
    // 分页查询成绩
    public Result<List<Score>> pageScore(String stuno, Integer start, Integer size) {
        Integer page = (start - 1) * size;
        return scoreService.pageScore(stuno, page, size);
    }

    @PostMapping("/findByCourse")
    @ResponseBody
    // 通过课程名查询
    public Result<List<Score>> findByCourse(String coursename, Integer start, Integer size) {
        Integer page = (start - 1) * size;
        return scoreService.findByCourse(coursename, page, size);
    }

    @PostMapping("/countscorebyCourse")
    @ResponseBody
    // 统计通过课程名称查找成绩的数量
    public Result<Score> countscorebyCourse(String coursename) {
        return scoreService.countScoreByCourse(coursename);
    }

    @PostMapping("/countscore")
    @ResponseBody
    // 条件统计成绩信息数
    public Result<IPage> countScore(String column, String val, Integer start, Integer size) {
        return scoreService.countScore(column, val, start, size);
    }

    @PostMapping("/deletescore")
    @ResponseBody
    // 删除成绩
    public Result deleteCourse(Integer stuno, Integer courseid) {
        return scoreService.deleteScore(stuno, courseid);
    }

    @PostMapping("/insertscore")
    @ResponseBody
    // 添加成绩
    public Result insertScore(HttpServletRequest request, @RequestBody Score score){
        return scoreService.insertScore(request,score);
    }

    @PostMapping("/updatescore")
    @ResponseBody
    // 更新成绩
    public Result<Score> updateCourse(HttpServletRequest request, @RequestBody Score score) {
        return scoreService.updateScore(request,score);
    }
}
