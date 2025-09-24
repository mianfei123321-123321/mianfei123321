package com.roadjava.market.enums;

import lombok.Getter;

/**
 * 进货单状态
 * @author zhaodaowen
 * @see <a href="http://www.roadjava.com">乐之者java</a>
 */
@Getter
public enum PurchaseStatusEnum {
    TO_AUDIT(0,"待审核"),
    AGREED(1,"审核通过"),
    REJECTED(2,"审核拒绝"),
    RETURNED(3,"已退货")
    ;
    PurchaseStatusEnum(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }
    private final Integer code;
    private final String desc;

    public static PurchaseStatusEnum getEnum(Integer status) {
        if (status == null) {
            return null;
        }
        for (PurchaseStatusEnum e : values()) {
            if (e.code.equals(status)) {
                return e;
            }
        }
        return null;
    }
}
