package com.roadjava.market.bean.req.purchase;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
/**
 * 进货表更新入参类
 * @author 乐之者java
 * @see <a href="http://www.roadjava.com">乐之者java</a>
 */
@Data
public class PurchaseUpdateReq {
    /**
    * 主键
    */
    @NotNull
    private Long id;
    /**
     * 经办人id
     */
    @NotNull
    private Long operatorId;

    @NotEmpty
    @Valid
    private List<PurchaseDetail> detailList;

    @Data
    public static class PurchaseDetail {
        /**
         * 商品表的主键
         */
        @NotNull
        private Long goodsId;
        /**
         * 采购价,单位:元
         */
        @NotNull
        private BigDecimal buyPrice;
        /**
         * 采购数量
         */
        @NotNull
        private Integer num;
        /**
         * 供应商表的主键
         */
        @NotNull
        private Long supplierId;
    }
}
