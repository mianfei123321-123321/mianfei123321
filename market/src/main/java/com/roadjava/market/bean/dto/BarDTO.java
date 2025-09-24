package com.roadjava.market.bean.dto;

import lombok.Data;

/**
 * 柱状图返回视图前的中间中转对象
 * @author zhaodaowen
 * @see <a href="https://www.roadjava.com">乐之者java</a>
 */
@Data
public class BarDTO {
    /**
     * 如: goodsName
     */
    private String xAxisName;
    /**
     * 如: 销量
     */
    private Integer yAxisValueInt;
    /**
     * 如: 金额
     */
    private Double yAxisValueDouble;
}
