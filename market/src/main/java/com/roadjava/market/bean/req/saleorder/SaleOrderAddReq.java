package com.roadjava.market.bean.req.saleorder;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import java.math.BigDecimal;import java.util.Date;
import java.util.List;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
/**
 * 销售订单表新增入参类
 * @author 乐之者java
 * @see <a href="http://www.roadjava.com">乐之者java</a>
 */
@Data
public class SaleOrderAddReq {
    /**
     * 订单号
     */
    @NotBlank
    private String orderNo;
    /**
     * 商品总价,单位:元
     */
    @NotNull
    private BigDecimal totalAmount;
    /**
     * 创建日期
     */
    @NotNull
    private Date createdTime;

    private List<SaleOrderDetailAddReq> detailList;
}
