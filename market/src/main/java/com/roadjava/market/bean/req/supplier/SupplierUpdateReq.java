package com.roadjava.market.bean.req.supplier;
import lombok.Data;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
/**
 * 供应商表更新入参类
 * @author 乐之者java
 * @see <a href="http://www.roadjava.com">乐之者java</a>
 */
@Data
public class SupplierUpdateReq {
    /**
    * 主键
    */
    @NotNull
    private Long id;
    /**
    * 供应商名
    */
    @NotBlank
    private String name;
}