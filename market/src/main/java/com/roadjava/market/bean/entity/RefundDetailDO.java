package com.roadjava.market.bean.entity;
import lombok.Data;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.TableName;
/**
 * 退款明细表
 * @author 乐之者java
 * @see <a href="http://www.roadjava.com">乐之者java</a>
 */
@Data
@TableName("refund_detail")
public class RefundDetailDO {
    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
    private Long id;
    /**
     * 退款表主键
     */
    private Long refundId;
    /**
     * 商品表的主键
     */
    private Long goodsId;
    /**
     * 退款价格,单位:元
     */
    private BigDecimal refundPrice;
    /**
     * 销售数量
     */
    private Integer num;
}