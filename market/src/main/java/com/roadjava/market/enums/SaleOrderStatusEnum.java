package com.roadjava.market.enums;

import lombok.Getter;

/**
 * 销售单状态
 * @author zhaodaowen
 * @see <a href="https://www.roadjava.com">乐之者java</a>
 */
@Getter
public enum SaleOrderStatusEnum {
    DONE(0,"已完成"),
    REFUND_ED(1,"已退款");

    SaleOrderStatusEnum(Integer status, String desc) {
        this.status = status;
        this.desc = desc;
    }
    private final Integer status;
    private final String desc;

    public static SaleOrderStatusEnum getEnum(Integer status) {
        if (status == null) {
            return null;
        }
        for (SaleOrderStatusEnum e : values()) {
            if (e.status.equals(status)) {
                return e;
            }
        }
        return null;
    }
}
