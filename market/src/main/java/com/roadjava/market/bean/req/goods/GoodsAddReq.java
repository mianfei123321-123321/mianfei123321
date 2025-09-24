package com.roadjava.market.bean.req.goods;
import lombok.Data;
import java.math.BigDecimal;
import java.util.List;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
/**
 * 商品表新增入参类
 * @author 乐之者java
 * @see <a href="http://www.roadjava.com">乐之者java</a>
 */
@Data
public class GoodsAddReq {
    /**
     * 所在货架id
     */
    @NotNull
    private Long shelfId;
    /**
     * 商品名
     */
    @NotBlank
    private String name;
    /**
     * 产地省市区编码,通过,分隔
     */
    private List<String> bornPlaceCodeList;
    /**
     * 销售价,单位:元
     */
    @NotNull
    private BigDecimal salePrice;
    /**
     * 单位
     */
    @NotBlank
    private String unit;
    /**
     * 规格
     */
    @NotBlank
    private String specifications;
}
