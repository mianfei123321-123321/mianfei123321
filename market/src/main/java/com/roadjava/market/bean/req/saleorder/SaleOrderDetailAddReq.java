package com.roadjava.market.bean.req.saleorder;
import lombok.Data;
import java.math.BigDecimal;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
/**
 * 销售订单明细表新增入参类
 * @author 乐之者java
 * @see <a href="http://www.roadjava.com">乐之者java</a>
 */
@Data
public class SaleOrderDetailAddReq {
    /**
     * 商品表的主键
     */
    @NotNull
    private Long goodsId;
    /**
     * 销售价格,单位:元
     */
    @NotNull
    private BigDecimal salePrice;
    /**
     * 销售数量
     */
    @NotNull
    private Integer num;
}
