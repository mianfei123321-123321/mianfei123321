package com.roadjava.market.mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.roadjava.market.bean.entity.SaleOrderDetailDO;
import com.roadjava.market.bean.vo.SaleOrderDetailVO;

import java.util.List;

/**
 * 销售订单明细表mapper类
 * @author 乐之者java
 * @see <a href="http://www.roadjava.com">乐之者java</a>
 */
public interface SaleOrderDetailMapper extends BaseMapper<SaleOrderDetailDO> {

    List<SaleOrderDetailVO> selectByOrderIds(List<Long> orderIds);
}
