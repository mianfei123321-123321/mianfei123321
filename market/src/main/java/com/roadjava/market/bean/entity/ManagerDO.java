package com.roadjava.market.bean.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author 乐之者java
 * @see <a href="http://www.roadjava.com">乐之者java</a>
 */
@Data
@TableName("manager")
public class ManagerDO {
    /**
    * 主键
    */
    @TableId(type = IdType.AUTO)
    private Long id;
    /**
    * 用户名
    */
    private String username;
    /**
    * 姓名
    */
    private String realName;
    /**
    * 密码
    */
    private String pwd;
    /**
    * 性别,M:男F:女
    * @see com.roadjava.market.enums.SexEnum
    */
    private String sex;
    /**
    * 头像存放路径,格式如:resources/type/20230523/123.jpg
    */
    private String avatarPath;
    /**
     * 角色id
     */
    private Integer roleId;
}
