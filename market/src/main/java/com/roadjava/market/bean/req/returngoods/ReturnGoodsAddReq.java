package com.roadjava.market.bean.req.returngoods;
import lombok.Data;
import java.util.Date;
import java.util.List;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
/**
 * 退货表新增入参类
 * @author 乐之者java
 * @see <a href="http://www.roadjava.com">乐之者java</a>
 */
@Data
public class ReturnGoodsAddReq {
    /**
     * purchase表主键集合
     */
    @NotEmpty
    private List<Long> purchaseIdList;
}
