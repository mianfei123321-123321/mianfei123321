package com.roadjava.market.bean.req.purchase;
import com.roadjava.market.bean.req.BasePageReq;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.util.Date;
/**
 * 进货表分页查询入参
 * @author 乐之者java
 * @see <a href="http://www.roadjava.com">乐之者java</a>
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class PurchasePageReq extends BasePageReq{
      /**
      * 进货批次号
      */
     private String purchaseNo;
      /**
      * 0:待审核 1:审核通过 2:审核拒绝,3:已退货
      */
     private Integer status;
}
