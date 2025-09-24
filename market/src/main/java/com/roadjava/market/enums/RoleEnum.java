package com.roadjava.market.enums;

import lombok.Getter;

/**
 * 用户角色
 * @author zhaodaowen
 * @see <a href="http://www.roadjava.com">乐之者java</a>
 */
@Getter
public enum RoleEnum {
    ADMIN(1,"超级管理员","具备所有的权限"),
    WAREHOUSE_KEEPER(2,"仓库管理员","仓库管理员"),
    SALE(3,"销售","销售"),
    FINANCE(4,"财务","财务管理人员");

    RoleEnum(Integer roleId,String roleName, String roleDesc) {
        this.roleId = roleId;
        this.roleName = roleName;
        this.roleDesc = roleDesc;
    }
    private final Integer roleId;
    private final String roleName;
    private final String roleDesc;

    public static RoleEnum getEnum(Integer roleId) {
        if (roleId == null) {
            return null;
        }
        for (RoleEnum e : values()) {
            if (e.roleId.equals(roleId)) {
                return e;
            }
        }
        return null;
    }
}
