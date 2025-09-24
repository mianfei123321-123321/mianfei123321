package com.roadjava.market.util;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.convert.Convert;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.roadjava.market.bean.res.Result;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * 类型转换工具类
 *
 * @author 乐之者java
 * @see <a href="http://www.roadjava.com">乐之者java</a>
 */
@Slf4j
public class ConvertUtil {

    /**
     * 转换单个pojo
     */
    public static <T> T convert(Object source, Class<T> targetClazz) {
        return Convert.convert(targetClazz, source);
    }

    /**
     * 转换list
     */
    public static <T> List<T> convert(List<?> sourceList, Class<T> targetClazz) {
        return Convert.toList(targetClazz, sourceList);
    }

    /**
     * 转换mp的iPage对象为Result
     */
    public static <T> Result<List<T>> convert(IPage<?> pageResult, Class<T> targetClazz) {
        // 获取结果集
        List<?> records = pageResult.getRecords();
        if (CollUtil.isEmpty(records)) {
            return Result.buildSuccess(new ArrayList<>(), pageResult.getTotal());
        }
        // 转换records
        IPage<T> page = pageResult.convert(source -> convert(source, targetClazz));
        return Result.buildSuccess(page.getRecords(), page.getTotal());
    }
}
