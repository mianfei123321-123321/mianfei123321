package com.roadjava.market.bean.entity;
import lombok.Data;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.math.BigDecimal;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableName;
/**
 * 退款表
 * @author 乐之者java
 * @see <a href="http://www.roadjava.com">乐之者java</a>
 */
@Data
@TableName("refund")
public class RefundDO {
    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
    private Long id;
    /**
     * sale_order表主键
     */
    private Long orderId;
    /**
     * 退款编号
     */
    private String refundNo;
    /**
     * 退款总金额,单位:元
     */
    private BigDecimal totalAmount;
    /**
     * 创建日期
     */
    private Date createdTime;
}
