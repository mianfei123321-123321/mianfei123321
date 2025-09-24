package com.roadjava.market.util;


import com.roadjava.market.bean.req.BasePageReq;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

/**
 * 分页工具类
 * @author 乐之者java
 * @see <a href="http://www.roadjava.com">乐之者java</a>
 */
public class PageUtil {
    /**
     * 构建IPage类型的对象
     */
    public static <T> IPage<T> buildPage(BasePageReq pageReq) {
        return new Page<>(pageReq.getPageNow(),pageReq.getPageSize());
    }
}
