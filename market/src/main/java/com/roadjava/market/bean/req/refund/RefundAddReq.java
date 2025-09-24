package com.roadjava.market.bean.req.refund;
import lombok.Data;
import java.math.BigDecimal;import java.util.Date;
import java.util.List;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
/**
 * 退款表新增入参类
 * @author 乐之者java
 * @see <a href="http://www.roadjava.com">乐之者java</a>
 */
@Data
public class RefundAddReq {
    /**
     * 订单表主键集合
     */
    @NotEmpty
    private List<Long> orderIdList;
}
