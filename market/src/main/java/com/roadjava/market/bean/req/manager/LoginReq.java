package com.roadjava.market.bean.req.manager;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * 登录请求参数
 * @author 乐之者java
 * @see <a href="http://www.roadjava.com">乐之者java</a>
 */
@Data
public class LoginReq {
    /**
     * 用户名
     */
    @NotBlank
    private String username;
    /**
     * 密码
     */
    @NotBlank
    private String pwd;
}
