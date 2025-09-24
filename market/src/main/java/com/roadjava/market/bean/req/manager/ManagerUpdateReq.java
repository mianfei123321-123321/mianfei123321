package com.roadjava.market.bean.req.manager;

import com.roadjava.market.enums.SexEnum;
import com.roadjava.market.validator.EnumValue;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * 个人信息修改请求参数
 * @author 乐之者java
 * @see <a href="http://www.roadjava.com">乐之者java</a>
 */
@Data
public class ManagerUpdateReq {
    /**
     * 主键
     */
    @NotNull
    private Long id;
    /**
     * 姓名
     */
    @NotBlank
    private String realName;
    /**
     * 性别,M:男F:女
     * @see com.roadjava.market.enums.SexEnum
     */
    @NotBlank
    @EnumValue(clazz = SexEnum.class)
    private String sex;
    /**
     * 头像存放路径,格式如:resources/type/20230523/123.jpg
     */
    private String avatarPath;
}
