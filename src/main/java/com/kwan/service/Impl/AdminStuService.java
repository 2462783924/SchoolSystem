package com.kwan.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kwan.common.Result;
import com.kwan.entity.Student;
import com.kwan.entity.schClass;
import com.kwan.mapper.AdminStuMapper;
import com.kwan.mapper.ClassMapper;
import com.kwan.mapper.StudentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
public class AdminStuService {
    @Autowired
    StudentMapper studentMapper;
    @Autowired
    ClassMapper classMapper;

    // 获取所有学生信息（学生表+班级表）
    public Result<List<Student>> findAllStudent(Integer start, Integer size) {
        List<Student> student = studentMapper.getStudent(start, size);
        return Result.success(student);
    }


    // 统计学生信息数（条件统计）
    @Autowired
    private AdminStuMapper adminStuMapper;

    public long countStudent(String column, String val, Integer start, Integer size) {
        IPage page = new Page(start, size);
//        adminStuMapper.selectPage(page, null);
        QueryWrapper qw = new QueryWrapper();

        if (column == null || column.equals("")) {
            adminStuMapper.selectPage(page, null);
        } else {
            qw.like(column, val);
            adminStuMapper.selectPage(page, qw);
        }
        return page.getTotal();
    }

    // 通过学生姓名查找
    public Result<List<Student>> getstubyName(String name, Integer start, Integer size) {
        List<Student> student = studentMapper.getStudentByname("%" + name + "%", start, size);
        return Result.success(student);
    }

    // 通过班级查找学生信息
    public Result<List<Student>> getstubyClass(String classname, Integer start, Integer size) {
        List<Student> student = studentMapper.getStudentByclass("%" + classname + "%", start, size);
        return Result.success(student);
    }

    // 更新学生信息
    public Result<Student> update(HttpServletRequest request, @RequestBody Student student) {
        String classname = student.getClassname();
        QueryWrapper<schClass> qw = new QueryWrapper();
        qw.eq("classname", classname);
        schClass schclass = classMapper.selectOne(qw);

        QueryWrapper<Student> qws = new QueryWrapper();
        qws.eq("stuno", student.getStuno());
        Student s = studentMapper.selectOne(qws);
        if (s != null) {
            if (!s.getId().equals(student.getId())) {
                return Result.error("学生学号已存在");
            }
        }

        if (schclass == null) {
            return Result.error("课程名称不存在");
        }

        student.setClassid(schclass.getClassid());
        adminStuMapper.updateById(student);

        return Result.success(student);
    }

//    // 删除学生信息
//    public Result delete(Integer stuid){
//        studentMapper.deleteById(stuid);
//        return Result.success("删除成功");
//    }

}
