package com.roadjava.market.bean.vo;

import lombok.Data;

/**
 * @author 乐之者java
 * @see <a href="http://www.roadjava.com">乐之者java</a>
 */
@Data
public class ManagerVO {
    /**
     * 主键
     */
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
     * @see com.roadjava.market.enums.RoleEnum
     */
    private Integer roleId;
    /**
     * 角色名称
     */
    private String roleName;
}
