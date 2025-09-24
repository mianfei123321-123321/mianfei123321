package com.roadjava.market.enums;

import lombok.Getter;

/**
 * 性别枚举
 * @author 乐之者java
 * @see <a href="https://www.roadjava.com">乐之者java</a>
 */
@Getter
public enum SexEnum {
    MALE("M","男"),
    FEMALE("F","女");

    private final String code;
    private final String desc;
    SexEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    /**
     * 通过枚举对象的code获取对应的描述
     */
    public static String getDescByCode(String code) {
        for (SexEnum e : values()) {
            if (e.code.equals(code)) {
                return e.desc;
            }
        }
        return null;
    }
}
