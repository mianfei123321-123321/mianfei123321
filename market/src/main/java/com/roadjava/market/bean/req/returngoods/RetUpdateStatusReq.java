package com.roadjava.market.bean.req.returngoods;

import com.roadjava.market.enums.ReturnGoodsStatusEnum;
import com.roadjava.market.validator.EnumValue;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author zhaodaowen
 */
@Data
public class RetUpdateStatusReq {
    @NotNull
    private Long id;
    /**
     * 退货单状态
     */
    @NotNull
    @EnumValue(clazz = ReturnGoodsStatusEnum.class)
    private Integer status;
}
