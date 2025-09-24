package com.roadjava.market.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.roadjava.market.bean.entity.RefundDetailDO;
import com.roadjava.market.mapper.RefundDetailMapper;
import com.roadjava.market.service.RefundDetailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
/**
 * 退款明细表业务实现类
 * @author 乐之者java
 * @see <a href="http://www.roadjava.com">乐之者java</a>
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class RefundDetailServiceImpl extends ServiceImpl<RefundDetailMapper,RefundDetailDO> implements RefundDetailService {

}
