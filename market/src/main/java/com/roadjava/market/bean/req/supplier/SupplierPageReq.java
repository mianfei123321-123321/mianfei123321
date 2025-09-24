package com.roadjava.market.bean.req.supplier;
import com.roadjava.market.bean.req.BasePageReq;
import lombok.Data;
import lombok.EqualsAndHashCode;
/**
 * 供应商表分页查询入参
 * @author 乐之者java
 * @see <a href="http://www.roadjava.com">乐之者java</a>
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class SupplierPageReq extends BasePageReq{
      /**
      * 供应商名
      */
     private String name;
}