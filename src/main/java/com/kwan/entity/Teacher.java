package com.kwan.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Teacher {
    private Integer id;
    private String teachno;
    private String teachname;
    private String teachsex;
    private int teachcourse;
    private String phone;

    @TableField(exist = false)
    private String username;
    @TableField(exist = false)
    private String password;
    @TableField(exist = false)
    private boolean flag;

    @TableField(exist = false)
    private String coursename;

    @TableField(exist = false)
    private int count;
}
