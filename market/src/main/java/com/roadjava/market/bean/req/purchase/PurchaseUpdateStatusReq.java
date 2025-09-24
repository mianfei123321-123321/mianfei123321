package com.roadjava.market.bean.req.purchase;

import com.roadjava.market.enums.PurchaseStatusEnum;
import com.roadjava.market.validator.EnumValue;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author zhaodaowen
 */
@Data
public class PurchaseUpdateStatusReq {
    @NotNull
    private Long id;
    /**
     * 进货单状态
     */
    @NotNull
    @EnumValue(clazz = PurchaseStatusEnum.class)
    private Integer status;
}
