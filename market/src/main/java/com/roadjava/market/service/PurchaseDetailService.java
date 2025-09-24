package com.roadjava.market.service;
import com.baomidou.mybatisplus.extension.service.IService;
import com.roadjava.market.bean.entity.PurchaseDetailDO;
import com.roadjava.market.bean.req.purchase.PurchaseAddReq;

import java.util.List;
/**
  * 进货明细表业务类
  * @author 乐之者java
  * @see <a href="http://www.roadjava.com">乐之者java</a>
  */
public interface PurchaseDetailService extends IService<PurchaseDetailDO> {
    /**
    * 新增
    */
    void add(PurchaseAddReq.PurchaseDetail addReq);

    /**
     * 通过进货单id查询进货明细列表
     */
    List<PurchaseDetailDO> listByPurchaseId(Long purchaseId);

    /**
     * 通过进货单id删除进货明细列表
     * @param purchaseId 进货单id
     */
    void removeByPurchaseId(Long purchaseId);
}
