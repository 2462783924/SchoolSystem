package com.kwan;

import com.kwan.controller.AdminStuController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class AdminTest {
    @Autowired
    private AdminStuController countController;
//    @Autowired
//    private UserDao userDao;
//    @Test
//    public void adminTest(){
////        adminStuController.test()
////        adminStuController.testMany();
////        System.out.println( adminStuController.testMany());
////        adminStuController.CountStudent();
//    }
@Test
    void testGetByPage(){
//        IPage page = new Page(1,5);//(页数，每页条数)
//        userDao.selectPage(page,null);
//        System.out.println("当前页码值："+page.getCurrent());
//        System.out.println("每页显示数："+page.getSize());
//        System.out.println("一共多少页："+page.getPages());
//        System.out.println("一共多少条："+page.getTotal());
//        System.out.println("数据："+page.getRecords());

//        System.out.println(countController.countStudent(1,4));
    }
}
