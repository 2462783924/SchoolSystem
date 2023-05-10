package com.kwan.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kwan.common.Result;
import com.kwan.entity.*;
import com.kwan.mapper.*;
import com.kwan.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletRequest;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, UserEntity> implements UserService {

    @Autowired
    private UserService userService;

    /**
     * 登录验证功能
     *
     * @param request
     * @param userEntity
     * @return
     */
    public Result<UserEntity> login(HttpServletRequest request, @RequestBody UserEntity userEntity) {

        // 1 将页面提交的密码password进行md5加密处理
        String password = userEntity.getPassword();
//        password = DigestUtils.md5DigestAsHex(password.getBytes());

        // 2 根据页面提交的用户名username查询数据库
        LambdaQueryWrapper<UserEntity> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(UserEntity::getUsername, userEntity.getUsername()); // 等匹配查询
        //数据库设计时username字段设置了唯一约束
        UserEntity user = userService.getOne(queryWrapper);

        // 3 如果没有查询到则返回登录失败结果
        if (user == null) {
            return Result.error("登录失败");
        }

        // 4 密码比对，如果不一致则返回登录失败结果
        if (!user.getPassword().equals(password)) {
            return Result.error("登录失败");
        }

        // 5 查看员工状态，如果为已禁用状态，则返回员工已禁用结果
        if (user.getStatus() == 0) {
            return Result.error("账号已禁用");
        }

        // 6 登录成功，将员工id存入Session并返回登录成功结果
        request.getSession().setAttribute("userId", user);
        return Result.success(user);
    }

    /**
     * 查找user表，并分页显示
     * @param start
     * @param size
     * @param username
     * @return
     */
    public Result<IPage> adminUser(Integer start, Integer size, String username) {
        IPage page = new Page(start, size);
        QueryWrapper<UserEntity> qw = new QueryWrapper();
        qw.like("username", username);
        userService.getBaseMapper().selectPage(page, qw);
        return Result.success(page);
    }

    /**
     * 更新用户信息
     * @param request
     * @param userEntity
     * @return
     */
    public Result<UserEntity> updateUser(HttpServletRequest request, @RequestBody UserEntity userEntity){
        // 1 将页面提交的密码password进行md5加密处理
        String password = userEntity.getPassword();
//        password = DigestUtils.md5DigestAsHex(password.getBytes());

        // 2 根据页面提交的用户名username查询数据库
        LambdaQueryWrapper<UserEntity> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(UserEntity::getUsername, userEntity.getUsername()); // 等匹配查询
        //数据库设计时username字段设置了唯一约束
        UserEntity user = userService.getOne(queryWrapper);

        // 3 查看是否用相同用户名
        if (user != null) { // 查看是否相同id的用户名
            if(!user.getId().equals(userEntity.getId())){
                return Result.error("用户名已存在");
            }
        }

        // 判断启动/禁用
        if(userEntity.isFlag()){
            userEntity.setStatus(1);
        }else{
            userEntity.setStatus(0);
        }

        userService.updateById(userEntity);
        return Result.success(userEntity);
    }

    /**
     * 删除用户信息
     * @param id
     * @return
     */
    public Result delete(Integer id){
        userService.removeById(id);
        return Result.success("删除成功");
    }

    @Autowired
    ClassMapper classMapper;
    @Autowired
    StudentMapper studentMapper;
    @Transactional // 使用事务管理
    // 添加学生用户
    public Result insertStudent(HttpServletRequest request, @RequestBody Student student){

        String classname = student.getClassname();
        QueryWrapper<schClass> qw = new QueryWrapper();
        qw.eq("classname", classname);
        schClass schclass = classMapper.selectOne(qw);

        QueryWrapper<Student> qws = new QueryWrapper();
        qws.eq("stuno", student.getStuno());
        Student s = studentMapper.selectOne(qws);

        QueryWrapper qwu = new QueryWrapper();
        qwu.eq("username",student.getUsername());
        UserEntity user = userService.getOne(qwu);

        if (user!=null){
            if (!user.getId().equals(student.getId())){
                return Result.error("用户名已存在");
            }
        }

        if (s != null) {
            if (!s.getId().equals(student.getId())) {
                return Result.error("学生学号已存在");
            }
        }

        if (schclass == null) {
            return Result.error("课程名称不存在");
        }

        student.setClassid(schclass.getClassid());

        UserEntity userEntity = new UserEntity();
        userEntity.setUsername(student.getUsername());
        userEntity.setPassword(student.getPassword());
        userEntity.setUserrank(0);
        if (student.isFlag()){
            userEntity.setStatus(1);
        }else{
            userEntity.setStatus(0);
        }

        userService.save(userEntity);

        QueryWrapper<UserEntity> qu = new QueryWrapper();
        qu.eq("username", userEntity.getUsername());
        UserEntity u = userService.getOne(qu);
        student.setId(u.getId());

        studentMapper.insert(student);

        return Result.success("用户："+userEntity.getUsername()+"已添加成功");
    }

    @Autowired
    CourseMapper courseMapper;
    @Autowired
    AdminTeachMapper adminTeachMapper;
    @Transactional // 使用事务管理
    // 添加教师用户
    public Result insertTeacher(HttpServletRequest request, @RequestBody Teacher teacher){

        String teachcourse = teacher.getCoursename();
        QueryWrapper<Course> qw = new QueryWrapper();
        qw.eq("coursename", teachcourse);
        Course course = courseMapper.selectOne(qw);

        QueryWrapper<Teacher> qws = new QueryWrapper();
        qws.eq("teachno", teacher.getTeachno());
        Teacher t = adminTeachMapper.selectOne(qws);

        QueryWrapper qwu = new QueryWrapper();
        qwu.eq("username",teacher.getUsername());
        UserEntity user = userService.getOne(qwu);

        if (user!=null){
            if (!user.getId().equals(teacher.getId())){
                return Result.error("用户名已存在");
            }
        }

        if (t != null) {
            if (!t.getId().equals(teacher.getId())) {
                return Result.error("教师编号已存在");
            }
        }

        if (course == null) {
            return Result.error("课程名称不存在");
        }

        teacher.setTeachcourse(course.getCourseid());

        UserEntity userEntity = new UserEntity();
        userEntity.setUsername(teacher.getUsername());
        userEntity.setPassword(teacher.getPassword());
        userEntity.setUserrank(2);

        if (teacher.isFlag()){
            userEntity.setStatus(1);
        }else{
            userEntity.setStatus(0);
        }

        userService.save(userEntity);

        QueryWrapper<UserEntity> qu = new QueryWrapper();
        qu.eq("username", userEntity.getUsername());
        UserEntity u = userService.getOne(qu);
        teacher.setId(u.getId());

        adminTeachMapper.insert(teacher);

        return Result.success("用户："+userEntity.getUsername()+"已添加成功");
    }
}
