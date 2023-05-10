package com.kwan.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("score") // 设置对应的表名称
public class Score {
    private int courseid;
    private String stuno;
    private int score;

    @TableField(exist = false)
    private String coursename;

    @TableField(exist = false)
    private int count;
}
