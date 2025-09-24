package com.roadjava.market.bean.vo;
import lombok.Data;
import java.math.BigDecimal;
/**
  * 进货明细表视图对象
  * @author 乐之者java
  * @see <a href="http://www.roadjava.com">乐之者java</a>
  */
@Data
public class PurchaseDetailVO {
    /**
     * 主键
     */
    private Long id;
    /**
     * purchase表主键
     */
    private Long purchaseId;
    /**
     * 商品表的主键
     */
    private Long goodsId;
    private String goodsName;
    private String unit;
    /**
     * 售价,单位:元
     */
    private BigDecimal salePrice;
    /**
     * 采购价,单位:元
     */
    private BigDecimal buyPrice;
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
