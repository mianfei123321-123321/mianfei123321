package com.roadjava.market.bean.req.saleorder;

import com.roadjava.market.bean.req.BasePageReq;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 销售订单表分页查询入参
 *
 * @author 乐之者java
 * @see <a href="http://www.roadjava.com">乐之者java</a>
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class SaleOrderPageReq extends BasePageReq {
    /**
     * 订单号
     */
    private String orderNo;
}
