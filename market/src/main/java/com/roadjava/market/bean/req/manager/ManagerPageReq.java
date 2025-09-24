package com.roadjava.market.bean.req.manager;

import com.roadjava.market.bean.req.BasePageReq;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 管理员分页查询入参
 *
 * @author 乐之者java
 * @see <a href="http://www.roadjava.com">乐之者java</a>
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class ManagerPageReq extends BasePageReq {
    /**
     * 用户名
     */
    private String username;
    /**
     * 姓名
     */
    private String realName;
    /**
     * M:男F:女
     */
    private String sex;
}
