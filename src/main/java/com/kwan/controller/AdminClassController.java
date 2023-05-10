package com.kwan.controller;

import com.kwan.common.Result;
import com.kwan.entity.schClass;
import com.kwan.service.Impl.AdminClassService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Slf4j
@RestController

@RequestMapping("/adminclass")
//解决跨域问题
@CrossOrigin(originPatterns = "*", allowCredentials = "true", allowedHeaders = "*")
public class AdminClassController {
    @Autowired
    private AdminClassService adminClassService;

    @PostMapping("/getclassbyName")
    @ResponseBody
    // 统计指定班级名的数量
    public Result countClass(String classname, Integer start, Integer size) {
        Integer page = (start - 1) * size;
        return adminClassService.countClass(classname,page,size);
    }

    @PostMapping("/findName")
    @ResponseBody
    // 查找全部班级
    public List<schClass> findClass() {
        return adminClassService.findClass();
    }

    @PostMapping("/pageClass")
    @ResponseBody
    // 分页查询班级信息
    public Result adminClass(Integer start, Integer size,String classname) {
        return adminClassService.pageClass(start, size,classname);
    }

    @PostMapping("/updateclass")
    @ResponseBody
    // 更新班级
    public Result<schClass> updateCourse(HttpServletRequest request, @RequestBody schClass schclass) {
        return adminClassService.updateClass(request,schclass);
    }

    @PostMapping("/deleteclass")
    @ResponseBody
    // 删除班级
    public Result deleteClass(Integer classid) {
        return adminClassService.delete(classid);
    }

    @PostMapping("/insertclass")
    @ResponseBody
    // 添加班级
    public Result insertClass(HttpServletRequest request, @RequestBody schClass schclass){
        return adminClassService.insertClass(request,schclass);
    }
}
