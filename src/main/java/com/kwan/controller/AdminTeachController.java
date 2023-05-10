package com.kwan.controller;

import com.kwan.common.Result;
import com.kwan.entity.Teacher;
import com.kwan.service.Impl.AdminTeachService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Slf4j
@RestController

@RequestMapping("/teach")
//解决跨域问题
@CrossOrigin(originPatterns = "*", allowCredentials = "true", allowedHeaders = "*")
public class AdminTeachController {

    @Autowired
    private AdminTeachService adminTeachService;

    @PostMapping("/allteach")
    @ResponseBody
    // teacher表和course表结合的分页查询学生信息
    public Result<List<Teacher>> AdmihTeacher(Integer start, Integer size) {
        Integer page = (start - 1) * size;
        return adminTeachService.findAllTeacher(page, size);
    }


    @PostMapping("/countteacher")
    @ResponseBody
    // 条件统计教师信息数
    public long countTeacher(String column, String val, Integer start, Integer size) {
        return adminTeachService.countTeacher(column, val, start, size);
    }

    @PostMapping("/getteachbyname")
    @ResponseBody
    // 通过姓名查询学生
    public Result<List<Teacher>> getTeachbyName(String name, Integer start, Integer size) {
        Integer page = (start - 1) * size;
        return adminTeachService.getteachbyName(name, page, size);
    }

    @PostMapping("/getteachbycoursename")
    @ResponseBody
    // 通过课程名称查询教师
    public Result<List<Teacher>> getteachbyCourse(String name, Integer start, Integer size) {
        Integer page = (start - 1) * size;
        return adminTeachService.getteachbyCourse(name, page, size);
    }

    @PostMapping("/teachupdate")
    @ResponseBody
    // 更新教师信息
    public Result<Teacher> updateStudent(HttpServletRequest request, @RequestBody Teacher teacher) {
        return adminTeachService.update(request, teacher);
    }
//
//    @PostMapping("/delete")
//    @ResponseBody
//    // 删除学生
//    public Result deleteStudent(Integer stuid) {
//        return adminStuService.delete(stuid);
//    }

}
