package com.roadjava.market.bean.req.manager;

import com.roadjava.market.enums.RoleEnum;
import com.roadjava.market.enums.SexEnum;
import com.roadjava.market.validator.EnumValue;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * 管理员新增入参类
 * @author 乐之者java
 * @see <a href="http://www.roadjava.com">乐之者java</a>
 */
@Data
public class ManagerAddReq {
    /**
     * 用户名
     */
    @NotBlank
    private String username;
    /**
     * 姓名
     */
    @NotBlank
    private String realName;
    /**
     * 密码
     */
    @NotBlank
    private String pwd;
    /**
     * M:男F:女
     */
    @NotBlank
    @EnumValue(clazz = SexEnum.class)
    private String sex;
    /**
     * 头像存放路径,格式如:resources/type/20230523/123.jpg
     */
    private String avatarPath;
    @NotNull
    @EnumValue(clazz = RoleEnum.class,field = "roleId")
    private Integer roleId;
}
