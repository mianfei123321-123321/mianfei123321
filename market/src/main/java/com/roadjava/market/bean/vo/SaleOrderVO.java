package com.roadjava.market.bean.vo;
import lombok.Data;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
  * 销售订单表视图对象
  * @author 乐之者java
  * @see <a href="http://www.roadjava.com">乐之者java</a>
  */
@Data
public class SaleOrderVO {
    /**
     * 主键
     */
    private Long id;
    /**
     * 订单号
     */
    private String orderNo;
    /**
     * 商品总价,单位:元
     */
    private BigDecimal totalAmount;
    /**
     * 0:已完成 1:已退款
     * @see com.roadjava.market.enums.SaleOrderStatusEnum
     */
    private Integer status;
    /**
     * 状态描述
     */
    private String statusDesc;

    /**
     * 买入日期
     */
    private Date createdTime;

    private List<SaleOrderDetailVO> detailList;
}
