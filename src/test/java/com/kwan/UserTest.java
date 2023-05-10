//package com.kwan;
//
//import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
//import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
//import com.baomidou.mybatisplus.core.metadata.IPage;
//import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
//import com.kwan.dao.UserDao;
//import com.kwan.entity.UserEntity;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@SpringBootTest()
//public class UserTest {
//
//    @Autowired
//    private UserDao userDao;
//    @Autowired
//    private
//
//    // 新增操作
//    @Test
//    void testSave(){
//        UserEntity userEntity = new UserEntity();
//        userEntity.setUsername("test05");
//        userEntity.setPassword("test");
//        userEntity.setStatus(1);
//        userDao.insert(userEntity);
//    }
//
//    // 删除操作
//    @Test
//    void testDelete(){
//        userDao.deleteById(6);
//    }
//
//    // 多个删除
//    @Test
//    void testDeleteMore(){
//        List<Integer> list = new ArrayList<>();
//        list.add(9);
//        list.add(10);
//        userDao.deleteBatchIds(list);
//    }
//
//    // 更新操作
//    @Test
//    void testUpdate(){
//        UserEntity userEntity = new UserEntity();
//        userEntity.setId(3);
//        userEntity.setUsername("test01");
//        userDao.updateById(userEntity);
//    }
//
//    // 查询全部数据
//    @Test
//    void testGetAll(){
//        List<UserEntity> userEntities = userDao.selectList(null);
//        System.out.println(userEntities);
//    }
//
//    // 按id查询
//    @Test
//    void testGetById(){
//        UserEntity userEntity = userDao.selectById(2);
//        System.out.println(userEntity);
//    }
//
//    // 分页查询
//    @Test
//    void testGetByPage(){
//        IPage page = new Page(1,5);//(页数，每页条数)
//        userDao.selectPage(page,null);
//        System.out.println("当前页码值："+page.getCurrent());
//        System.out.println("每页显示数："+page.getSize());
//        System.out.println("一共多少页："+page.getPages());
//        System.out.println("一共多少条："+page.getTotal());
//        System.out.println("数据："+page.getRecords());
//    }
//
//    /**
//     * 按条件查询（对qw进行设定来添加条件）
//     */
//    @Test
//    void testGet(){
//        LambdaQueryWrapper<UserEntity> qw = new LambdaQueryWrapper<UserEntity>();
//        // like模糊匹配
//
//        // id大于2小于8(le:<=  ge:>=  eq:=  between:范围查询)
////        qw.gt(UserEntity::getId,2)
////          .lt(UserEntity::getId,8);
//        // id小于2或者大于8（第一个参数可用于空值判定等）
////        qw.lt(true,UserEntity::getId,2)
////          .or()
////          .gt(UserEntity::getId,8);
//
//        // 查询投影（显示需要的信息）
////        qw.select(UserEntity::getUsername,UserEntity::getStatus);
//
//        // 等匹配查询（可用于登录时候用户名和密码匹配）
//        qw.eq(UserEntity::getUsername,"two")
//          .eq(UserEntity::getPassword,"222222");
//        UserEntity loginUser = userDao.selectOne(qw); // 查询结果只有一条时可使用
//        System.out.println(loginUser);
//
////        List<UserEntity> userEntityList = userDao.selectList(qw);
////        System.out.println(userEntityList);
//    }
//}
