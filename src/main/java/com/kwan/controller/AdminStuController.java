package com.kwan.controller;

import com.kwan.common.Result;
import com.kwan.entity.Student;
import com.kwan.service.Impl.AdminStuService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Slf4j
@RestController

@RequestMapping("/admin")
//解决跨域问题
@CrossOrigin(originPatterns = "*", allowCredentials = "true", allowedHeaders = "*")
public class AdminStuController {

    @Autowired
    private AdminStuService adminStuService;

    @PostMapping("/adminstu")
    @ResponseBody
    // student表和class表结合的分页查询学生信息
    public Result<List<Student>> AdmihStudent(Integer start, Integer size) {
        Integer page = (start - 1) * size;
        return adminStuService.findAllStudent(page, size);
    }

    @Autowired
    private AdminStuService adminStuMapper;

    @PostMapping("/countstu")
    @ResponseBody
    // 条件统计学生信息数
    public long countStudent(String column, String val, Integer start, Integer size) {
        return adminStuMapper.countStudent(column, val, start, size);
    }

    @PostMapping("/getstubyname")
    @ResponseBody
    // 通过姓名查询学生
    public Result<List<Student>> getstubyName(String name, Integer start, Integer size) {
        Integer page = (start - 1) * size;
        return adminStuMapper.getstubyName(name, page, size);
    }

    @PostMapping("/getstubyclassname")
    @ResponseBody
    // 通过班级名称查询学生
    public Result<List<Student>> getstubyClass(String name, Integer start, Integer size) {
        Integer page = (start - 1) * size;
        return adminStuMapper.getstubyClass(name, page, size);
    }

    @PostMapping("/update")
    @ResponseBody
    // 更新学生信息
    public Result<Student> updateStudent(HttpServletRequest request, @RequestBody Student student) {
        return adminStuService.update(request, student);
    }

//    @PostMapping("/delete")
//    @ResponseBody
//    // 删除学生
//    public Result deleteStudent(Integer stuid) {
//        return adminStuService.delete(stuid);
//    }

}
