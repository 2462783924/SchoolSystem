package com.kwan.controller;

import com.kwan.common.Result;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
@RestController
@RequestMapping("/logout")
//解决跨域问题
@CrossOrigin(originPatterns = "*", allowCredentials = "true", allowedHeaders = "*")
public class Logout {
    @PostMapping("/userlogout") //前端发送post请求
    public Result userLogout(HttpServletRequest request){
        request.getSession().removeAttribute("userId");
        return Result.success("退出成功");
    }
}
