package com.roadjava.market.bean.req.shelf;
import com.roadjava.market.bean.req.BasePageReq;
import lombok.Data;
import lombok.EqualsAndHashCode;
/**
 * 货架分页查询入参
 * @author 乐之者java
 * @see <a href="https://www.roadjava.com">乐之者java</a>
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class ShelfPageReq extends BasePageReq{
      /**
      * 货架编号
      */
     private String shelfNo;
}