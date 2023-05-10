package com.kwan.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("course") // 设置对应的表名称
public class Course {
    //指定主键使用数据库ID自增策略
    @TableId
    private int  courseid;
    private String coursename;
    @TableField(value = "static")
    private int ifexist;

    @TableField(exist = false)
    private int countstu;

    @TableField(exist = false)
    private List<Teacher> teacher;

    @TableField(exist = false)
    private boolean flag;
}
