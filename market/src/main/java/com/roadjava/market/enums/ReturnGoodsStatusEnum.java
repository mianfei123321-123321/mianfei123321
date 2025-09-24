package com.roadjava.market.enums;

import lombok.Getter;

/**
 * 退货状态
 * @author zhaodaowen
 * @see <a href="http://www.roadjava.com">乐之者java</a>
 */
@Getter
public enum ReturnGoodsStatusEnum {
    TO_AUDIT(0,"待审核"),
    AGREED(1,"审核通过"),
    REJECTED(2,"审核拒绝");
    ReturnGoodsStatusEnum(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }
    private final Integer code;
    private final String desc;

    public static ReturnGoodsStatusEnum getEnum(Integer status) {
        if (status == null) {
            return null;
        }
        for (ReturnGoodsStatusEnum e : values()) {
            if (e.code.equals(status)) {
                return e;
            }
        }
        return null;
    }
}
