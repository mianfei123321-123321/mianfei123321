package com.roadjava.market.bean.req.manager;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * 修改密码请求参数
 * @author 乐之者java
 * @see <a href="http://www.roadjava.com">乐之者java</a>
 */
@Data
public class UpdatePwdReq {
    /**
     * 主键
     */
    @NotNull
    private Long id;
    /**
     * 原始密码
     */
    @NotBlank
    private String originalPwd;
    /**
     * 新密码
     */
    @NotBlank
    private String newPwd;
}
