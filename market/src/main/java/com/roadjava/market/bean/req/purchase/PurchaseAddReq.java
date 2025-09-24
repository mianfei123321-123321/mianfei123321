package com.roadjava.market.bean.req.purchase;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
/**
 * 进货表新增入参类
 * @author 乐之者java
 * @see <a href="http://www.roadjava.com">乐之者java</a>
 */
@Data
public class PurchaseAddReq {
    /**
     * 进货批次号
     */
    @NotBlank
    private String purchaseNo;
    /**
     * 经办人id
     */
    @NotNull
    private Long operatorId;
    /**
     * 进货日期
     */
    @NotNull
    private Date purchaseDate;

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
