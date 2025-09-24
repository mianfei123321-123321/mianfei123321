package com.roadjava.market.mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.roadjava.market.bean.entity.RefundDO;
import com.roadjava.market.bean.req.purchase.PurchasePageReq;
import com.roadjava.market.bean.req.refund.RefundPageReq;
import com.roadjava.market.bean.vo.PurchaseVO;
import com.roadjava.market.bean.vo.RefundVO;

import java.util.List;

/**
 * 退款表mapper类
 * @author 乐之者java
 * @see <a href="http://www.roadjava.com">乐之者java</a>
 */
public interface RefundMapper extends BaseMapper<RefundDO> {
    List<RefundVO> queryList(RefundPageReq req);
    Long queryCount(RefundPageReq req);
}
