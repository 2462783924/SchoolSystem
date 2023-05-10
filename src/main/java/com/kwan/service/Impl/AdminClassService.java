package com.kwan.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kwan.common.Result;
import com.kwan.entity.Student;
import com.kwan.entity.schClass;
import com.kwan.mapper.ClassMapper;
import com.kwan.mapper.StudentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
public class AdminClassService {
    @Autowired
    private StudentMapper studentMapper;
    // 获取统计指定班级名的数量
    public Result countClass(String classname, Integer start, Integer size) {
            Student student = studentMapper.countstubyClass("%"+classname+"%",start,size);
        return Result.success(student);
    }

    @Autowired
    private ClassMapper classMapper;
    // 查找全部班级
    public List<schClass> findClass(){
        return classMapper.selectList(null);
    }

    /**
     * 查找班级，并分页显示
     * @param start
     * @param size
     * @param classname
     * @return
     */
    public Result<IPage> pageClass(Integer start, Integer size, String classname) {
        IPage page = new Page(start, size);
        QueryWrapper<schClass> qw = new QueryWrapper();
        if (classname==null){
            classname="";
        }
        qw.like("classname", classname);
        classMapper.selectPage(page, qw);
        List<schClass> schClasses = page.getRecords();
        return Result.success(page);
    }

    /**
     * 更新班级信息
     * @param request
     * @param schclass
     * @return
     */
    public Result<schClass> updateClass(HttpServletRequest request, @RequestBody schClass schclass){

        // 判断启动/禁用
        if(schclass.isFlag()){
            schclass.setIfexist(1);
        }else{
            schclass.setIfexist(0);
        }
        classMapper.updateById(schclass);
        return Result.success(schclass);
    }

    /**
     * 删除班级信息
     * @param classid
     * @return
     */
    public Result delete(Integer classid){
        classMapper.deleteById(classid);
        return Result.success("删除成功");
    }

    /**
     * 添加班级
     */
    public Result insertClass(HttpServletRequest request, @RequestBody schClass schclass){

        if (schclass.isFlag()){
            schclass.setIfexist(1);
        }else
        {
            schclass.setIfexist(0);
        }

        classMapper.insert(schclass);

        return Result.success("班级：\'"+schclass.getClassname()+"\'——已添加成功");
    }
}
