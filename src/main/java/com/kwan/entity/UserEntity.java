package com.kwan.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data // lombook自动生成对应方法（）不含构造方法
@NoArgsConstructor
@AllArgsConstructor
@TableName("user") // 设置对应的表名称
public class UserEntity {
    //表字段与方法属性名字不一致时 @TableFileld(value = "")
    //方法属性在数据库没有定义时，查询结果没有该字段 @TableFileld(exist = false)
    //设定属性不参与查询，如密码字段，查询结果有该字段但是结果为空 @TableFiled(value = "", select = false)

    @TableId(value = "id",type = IdType.AUTO)
    private Integer id; // 唯一标识符
    private String username; // 用户名
    private String password; // 密码
    private Integer userrank; // 身份标识（0：学生  1：学生管理员 2：教师 3：管理员）
    private Integer status; // 状态码（0：禁用  1：可用）

    @TableField(exist = false)
    private boolean flag;
}
