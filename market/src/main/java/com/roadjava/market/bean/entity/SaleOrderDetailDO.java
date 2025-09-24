package com.roadjava.market.bean.entity;
import lombok.Data;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.TableName;
/**
 * 销售订单明细表
 * @author 乐之者java
 * @see <a href="http://www.roadjava.com">乐之者java</a>
 */
@Data
@TableName("sale_order_detail")
public class SaleOrderDetailDO {
    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
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
     * 销售价格,单位:元
     */
    private BigDecimal salePrice;
    /**
     * 销售数量
     */
    private Integer num;
}