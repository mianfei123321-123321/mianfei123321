package com.roadjava.market.bean.dto;

import lombok.Data;

/**
 * 定义了返回给前端的token是由哪些信息生成的
 * @author 乐之者java
 * @see <a href="http://www.roadjava.com">乐之者java</a>
 */
@Data
public class TokenDTO {

    /**
     * 用户id
     */
    private Long id;
    /**
     * 用户名
     */
    private String username;
    /**
     * 头像存放路径,格式如:resources/type/20230523/123.jpg
     */
    private String avatarPath;
    /**
     * 角色id
     */
    private Integer roleId;
}
