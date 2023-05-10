package com.kwan.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student {
//    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;
    private String stuno;
    private String stuname;
    private String stusex;
    private int classid;
    private String phone;

    @TableField(exist = false)
    private String username;
    @TableField(exist = false)
    private String password;
    @TableField(exist = false)
    private boolean flag;

    //由于 classname 不是 student 数据库表里的字段，因此需要添加 @TableField 注解，并将 exist 属性设置为 false。
    @TableField(exist = false)
    private String classname;
    //查询一个班级及其下面的所有学生，首先对 class 实体类稍作修改，增加 students 集合属性
    @TableField(exist = false)
    private schClass schClass;

    @TableField(exist = false)
    private int count;
}
