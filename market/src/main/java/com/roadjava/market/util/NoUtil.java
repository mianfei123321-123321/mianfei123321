package com.roadjava.market.util;

/**
 * 生成编号
 * @author zhaodaowen
 * @see <a href="https://www.roadjava.com">乐之者java</a>
 */
public class NoUtil {

    public static String getNo(String prefix) {
        return prefix + System.nanoTime();
    }
}
