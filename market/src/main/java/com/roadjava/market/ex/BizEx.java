package com.roadjava.market.ex;

import com.roadjava.market.enums.CodeEnum;

/**
 * @author 乐之者java
 * @see <a href="http://www.roadjava.com">乐之者java</a>
 */
public class BizEx extends RuntimeException {
    private Integer code;
    public BizEx(String msg) {
        super(msg);
    }
    public BizEx(CodeEnum codeEnum) {
        super(codeEnum.getMsg());
        this.code = codeEnum.getCode();
    }
    public Integer getCode() {
        return code;
    }
}
