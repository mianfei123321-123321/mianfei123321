package com.roadjava.market.mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.roadjava.market.bean.entity.PurchaseDO;
import com.roadjava.market.bean.req.purchase.PurchasePageReq;
import com.roadjava.market.bean.vo.PurchaseVO;

import java.util.List;

/**
 * 进货表mapper类
 * @author 乐之者java
 * @see <a href="http://www.roadjava.com">乐之者java</a>
 */
public interface PurchaseMapper extends BaseMapper<PurchaseDO> {
    List<PurchaseVO> queryList(PurchasePageReq req);
    Long queryCount(PurchasePageReq req);

    PurchaseVO selectWithDetail(Long purchaseId);
}
