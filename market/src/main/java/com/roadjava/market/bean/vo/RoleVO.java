package com.roadjava.market.bean.vo;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 角色
 * @author zhaodaowen
 */
@Data
@Accessors(chain = true)
public class RoleVO {
    private Integer roleId;
    private String roleName;
}
