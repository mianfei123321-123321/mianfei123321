package com.roadjava.market.bean.req.refund;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author zhaodaowen
 */
@Data
public class RefundDetailReq {
    /**
     * 商品id
     */
    private Long goodsId;
    /**
     * 卖出价格
     */
    private BigDecimal price;
    /**
     * 购买数量
     */
    private Integer num;
}
