package com.kwan.controller;

import com.kwan.common.Result;
import com.kwan.entity.Student;
import com.kwan.entity.Teacher;
import com.kwan.entity.UserEntity;
import com.kwan.service.Impl.UserServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestController

@RequestMapping("/user")

//解决跨域问题
@CrossOrigin(originPatterns = "*", allowCredentials = "true", allowedHeaders = "*")
public class UserController {

    @Autowired
    private UserServiceImpl userService;

    /**
     * 登录方法
     *
     * @param request
     * @param userEntity
     * @return
     */
    @PostMapping("/login") //前端发送post请求
    //前端点击登录之后返回json数据(username,password)
    public Result<UserEntity> login(HttpServletRequest request, @RequestBody UserEntity userEntity) {
        return userService.login(request, userEntity);
    }


    @PostMapping("/adminuser")
    @ResponseBody
    // 分页查询用户信息
    public Result adminUser(Integer start, Integer size,String username) {
        return userService.adminUser(start, size,username);
    }

    @PostMapping("/updateuser")
    @ResponseBody
    // 更新用户
    public Result<UserEntity> updateUser(HttpServletRequest request, @RequestBody UserEntity userEntity) {
        return userService.updateUser(request,userEntity);
    }

    @PostMapping("/deleteuser")
    @ResponseBody
    // 删除用户
    public Result deleteUser(Integer id) {
        return userService.delete(id);
    }

    @PostMapping("/insertstu")
    @ResponseBody
    // 添加学生用户
    public Result insertStudent(HttpServletRequest request, @RequestBody Student student){
        return userService.insertStudent(request,student);
    }

    @PostMapping("/insertteach")
    @ResponseBody
    // 添加教师用户
    public Result insertTeacher(HttpServletRequest request, @RequestBody Teacher teacher){
        return userService.insertTeacher(request,teacher);
    }
}
