package com.roadjava.market.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * @author zhaodaowen
 * @see <a href="http://www.roadjava.com">乐之者java</a>
 */
public class DateUtil {
    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    /**
     * 得到近多少天的日期,返回格式 2021-08-01
     * @param nearNum
     * @return
     */
    public static List<String> getDateList(int nearNum) {
        List<String> list = new ArrayList<>();
        LocalDate now = LocalDate.now();
        for (int i = nearNum;i >= 0 ;i--) {
            list.add(formatter.format(now.minusDays(i)));
        }
        return list;
    }
}
