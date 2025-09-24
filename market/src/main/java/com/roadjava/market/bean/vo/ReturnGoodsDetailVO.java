package com.roadjava.market.bean.vo;
import lombok.Data;
import java.math.BigDecimal;
/**
  * 进货明细表视图对象
  * @author 乐之者java
  * @see <a href="http://www.roadjava.com">乐之者java</a>
  */
@Data
public class ReturnGoodsDetailVO {
    /**
     * 主键
     */
    private Long id;
    /**
     * 退货表主键
     */
    private Long retId;
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
     * 退货价格,单位:元
     */
    private BigDecimal returnPrice;
    /**
     * 采购数量
     */
    private Integer num;
    /**
     * 供应商表的主键
     */
    private Long supplierId;
    /**
     * 供应商名
     */
    private String supplierName;
}
