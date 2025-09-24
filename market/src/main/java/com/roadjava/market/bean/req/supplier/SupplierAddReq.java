package com.roadjava.market.bean.req.supplier;
import lombok.Data;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
/**
 * 供应商表新增入参类
 * @author 乐之者java
 * @see <a href="http://www.roadjava.com">乐之者java</a>
 */
@Data
public class SupplierAddReq {
    /**
     * 供应商名
     */
    @NotBlank
    private String name;
}