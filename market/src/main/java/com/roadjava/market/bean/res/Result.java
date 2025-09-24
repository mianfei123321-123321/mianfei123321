package com.roadjava.market.bean.res;


import com.roadjava.market.enums.CodeEnum;
import lombok.Data;

/**
 * 统一返回结果
 * @author 乐之者java
 * @see <a href="http://www.roadjava.com">乐之者java</a>
 */
@Data
public class Result<T> {
    /**
     * 响应码
     */
    private Integer code = CodeEnum.SUCCESS.getCode();
    /**
     * 响应消息
     */
    private String msg = CodeEnum.SUCCESS.getMsg();
    /**
     * 业务数据
     */
    private T data;
    /**
     * 业务数据的总条数
     */
    private Long total;
    /**
     * 是否成功
     */
    private Boolean success = Boolean.TRUE;
    private Result(){}

    /**
     * 成功类的方法
     */
    public static <T> Result<T> buildSuccess() {
        return new Result<>();
    }
    public static <T> Result<T> buildSuccess(T t) {
        Result<T> result = buildSuccess();
        result.setData(t);
        return result;
    }
    public static <T> Result<T> buildSuccess(T t,Long total) {
        Result<T> result = buildSuccess(t);
        result.setTotal(total);
        return result;
    }
    /**
     * 失败类的方法
     */
    public static <T> Result<T> buildFailure(String msg) {
        Result<T> result = new Result<>();
        result.setCode(null);
        result.setMsg(msg);
        return result;
    }
    public static <T> Result<T> buildFailure(Integer code, String msg) {
        Result<T> result = new Result<>();
        result.setCode(code);
        result.setMsg(msg);
        return result;
    }

    public static <T> Result<T> buildFailure(CodeEnum codeEnum) {
        Result<T> result = new Result<>();
        result.setCode(codeEnum.getCode());
        result.setMsg(codeEnum.getMsg());
        return result;
    }

    public Boolean getSuccess() {
        return CodeEnum.SUCCESS.getCode().equals(code) ;
    }
}
