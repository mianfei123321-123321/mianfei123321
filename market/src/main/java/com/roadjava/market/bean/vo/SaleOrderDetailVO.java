package com.roadjava.market.bean.vo;
import lombok.Data;
import java.math.BigDecimal;
/**
  * 销售订单明细表视图对象
  * @author 乐之者java
  * @see <a href="http://www.roadjava.com">乐之者java</a>
  */
@Data
public class SaleOrderDetailVO {
    /**
     * 主键
     */
    private Long id;
    /**
     * 订单表主键
     */
    private Long orderId;
    /**
     * 商品表的主键
     */
    private Long goodsId;
    /**
     * 商品名
     */
    private String goodsName;
    private String unit;
    /**
     * 销售价格,单位:元
     */
    private BigDecimal salePrice;
    /**
     * 销售数量
     */
    private Integer num;
}
