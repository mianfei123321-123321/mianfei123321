package com.roadjava.market.bean.vo;
import lombok.Data;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
  * 退款表视图对象
  * @author 乐之者java
  * @see <a href="http://www.roadjava.com">乐之者java</a>
  */
@Data
public class RefundVO {
    /**
     * 主键
     */
    private Long id;
    /**
     * sale_order表主键
     */
    private String orderId;
    /**
     * sale_order表订单号
     */
    private String orderNo;
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
    private List<RefundDetailVO> detailList;
}
