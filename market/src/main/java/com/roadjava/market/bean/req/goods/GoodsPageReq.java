package com.roadjava.market.bean.req.goods;
import com.roadjava.market.bean.req.BasePageReq;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.math.BigDecimal;
/**
 * 商品表分页查询入参
 * @author 乐之者java
 * @see <a href="http://www.roadjava.com">乐之者java</a>
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class GoodsPageReq extends BasePageReq{
      /**
      * 商品名
      */
     private String name;
}
