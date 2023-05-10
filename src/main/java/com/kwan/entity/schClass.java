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
@TableName("class") // 设置对应的表名称
public class schClass {
    //指定主键使用数据库ID自增策略
    @TableId
    private int  classid;
    private String classname;
    @TableField(value = "static")
    private int ifexist;

    @TableField(exist = false)
    private List<Student> students;

    @TableField(exist = false)
    private boolean flag;
}
