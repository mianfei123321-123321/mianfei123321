package com.roadjava.market.bean.vo;
import lombok.Data;
import java.math.BigDecimal;
import java.util.List;

/**
  * 商品表视图对象
  * @author 乐之者java
  * @see <a href="http://www.roadjava.com">乐之者java</a>
  */
@Data
public class GoodsVO {
    /**
     * 主键
     */
    private Long id;
    /**
     * 所在货架id
     */
    private Long shelfId;
    /**
     * 货架编号
     */
    private String shelfNo;
    /**
     * 商品名
     */
    private String name;
    /**
     * 产地省市区编码,通过,分隔
     */
    private String bornPlaceCodes;
    /**
     * 产地省市区编码,通过,分隔
     */
    private List<String> bornPlaceCodeList;
    /**
     * 销售价,单位:元
     */
    private BigDecimal salePrice;
    /**
     * 库存
     */
    private Integer stock;
    /**
     * 单位
     */
    private String unit;
    /**
     * 规格
     */
    private String specifications;
}
