package com.roadjava.market.bean.req.refund;
import com.roadjava.market.bean.req.BasePageReq;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.math.BigDecimal;import java.util.Date;
/**
 * 退款表分页查询入参
 * @author 乐之者java
 * @see <a href="http://www.roadjava.com">乐之者java</a>
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class RefundPageReq extends BasePageReq{
    private String fuzzyRefundNo;
}
