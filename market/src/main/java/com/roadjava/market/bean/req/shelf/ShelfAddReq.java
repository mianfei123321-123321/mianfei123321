package com.roadjava.market.bean.req.shelf;
import lombok.Data;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
/**
 * 货架新增入参类
 * @author 乐之者java
 * @see <a href="https://www.roadjava.com">乐之者java</a>
 */
@Data
public class ShelfAddReq {
    /**
     * 货架编号
     */
    @NotBlank
    private String shelfNo;
}