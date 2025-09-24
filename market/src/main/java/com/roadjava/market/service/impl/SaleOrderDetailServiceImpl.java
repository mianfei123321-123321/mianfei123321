package com.roadjava.market.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.roadjava.market.bean.entity.SaleOrderDetailDO;
import com.roadjava.market.bean.req.saleorder.SaleOrderDetailAddReq;
import com.roadjava.market.mapper.SaleOrderDetailMapper;
import com.roadjava.market.service.SaleOrderDetailService;
import com.roadjava.market.util.ConvertUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
/**
 * 销售订单明细表业务实现类
 * @author 乐之者java
 * @see <a href="http://www.roadjava.com">乐之者java</a>
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class SaleOrderDetailServiceImpl extends ServiceImpl<SaleOrderDetailMapper,SaleOrderDetailDO> implements SaleOrderDetailService {

}
