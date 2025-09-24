package com.roadjava.market.ex.handler;

import com.roadjava.market.bean.res.Result;
import com.roadjava.market.enums.CodeEnum;
import com.roadjava.market.ex.BizEx;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 统一异常处理
 * @author 乐之者java
 * @see <a href="http://www.roadjava.com">乐之者java</a>
 */
@RestControllerAdvice
@Slf4j
public class ExHandler {
    /**
     * 处理BindException类型的异常
     * 出现时机:
     * Content-Type:application/x-www-form-urlencoded
     */
    @ExceptionHandler(BindException.class)
    public Result<String> handleEx(BindException e) {
        log.error("参数绑定异常",e);
        // 获取到没通过校验的字段详情
        List<FieldError> fieldErrors = e.getFieldErrors();
        return Result.buildFailure(spliceErrMsg(fieldErrors));
    }

    /**
     * 处理MethodArgumentNotValidException类型的异常
     * 出现时机:
     * Content-Type:application/json + 后台使用 @RequestBody
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Result<String> handleEx(MethodArgumentNotValidException e) {
        log.error("入参校验失败",e);
        BindingResult bindingResult = e.getBindingResult();
        // 获取到没通过校验的字段详情
        List<FieldError> fieldErrors = bindingResult.getFieldErrors();
        return Result.buildFailure(spliceErrMsg(fieldErrors));
    }

    /**
     * 注解 @Validated 写在类上+入参带有jsr303注解校验出错
     */
    @ExceptionHandler(ConstraintViolationException.class)
    public Result<String> handleEx(ConstraintViolationException e) {
        log.error("违反约束:",e);
        Set<ConstraintViolation<?>> set = e.getConstraintViolations();
        String str = set.stream().map(v -> "属性:" + v.getPropertyPath() +
                ",传来的值是:"
                + v.getInvalidValue() + ",校验不通过,原因:" + v.getMessage())
                .collect(Collectors.joining(";"));
        return Result.buildFailure(str);
    }

    /**
    * 出现时机:使用{@link org.springframework.web.bind.annotation.RequestParam}
    * 设置required=true,但有没传时
    */
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public Result<String> handleEx(MissingServletRequestParameterException e) {
        log.error("缺少必填属性",e);
        // 获取到没通过校验的字段详情
        String parameterName = e.getParameterName();
        return Result.buildFailure("缺少必填属性:" + parameterName);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public Result<String> handleEx(IllegalArgumentException e) {
        log.error("参数非法",e);
        return Result.buildFailure(CodeEnum.PARAM_ERR);
    }

    @ExceptionHandler(BadSqlGrammarException.class)
    public Result<String> handleEx(BadSqlGrammarException e) {
        log.error("数据库异常",e);
        return Result.buildFailure(CodeEnum.DB_ERR);
    }

    @ExceptionHandler(DuplicateKeyException.class)
    public Result<String> handleEx(DuplicateKeyException e) {
        log.error("唯一性约束校验失败",e);
        return Result.buildFailure(CodeEnum.DUPLICATE_KEY_ERR);
    }

    @ExceptionHandler(BizEx.class)
    public Result<String> handleEx(BizEx e) {
        log.error("业务异常",e);
        return Result.buildFailure(e.getCode(),e.getMessage());
    }

    /**
     * 处理未精准匹配的异常
     */
    @ExceptionHandler(Exception.class)
    public Result<String> handleEx(Exception e) {
        log.error("处理未精准匹配的异常",e);
        return Result.buildFailure(CodeEnum.SYS_ERR);
    }

    private String spliceErrMsg(List<FieldError> fieldErrors) {
        return fieldErrors.stream().map(v -> "属性:" + v.getField() +
                ",传来的值是:"
                + v.getRejectedValue() + ",校验不通过,原因:" + v.getDefaultMessage())
                .collect(Collectors.joining(";"));
    }
}
