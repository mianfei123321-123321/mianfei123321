package com.roadjava.market.bean.entity;
import lombok.Data;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.math.BigDecimal;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableName;
/**
 * 销售订单表
 * @author 乐之者java
 * @see <a href="http://www.roadjava.com">乐之者java</a>
 */
@Data
@TableName("sale_order")
public class SaleOrderDO {
    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
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
     */
    private Integer status;
    /**
     * 创建日期
     */
    private Date createdTime;
}