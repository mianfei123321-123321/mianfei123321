package com.roadjava.market.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.roadjava.market.bean.entity.PurchaseDetailDO;
import com.roadjava.market.bean.req.purchase.PurchaseAddReq;
import com.roadjava.market.mapper.PurchaseDetailMapper;
import com.roadjava.market.service.PurchaseDetailService;
import com.roadjava.market.util.ConvertUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 进货明细表业务实现类
 * @author 乐之者java
 * @see <a href="http://www.roadjava.com">乐之者java</a>
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class PurchaseDetailServiceImpl extends ServiceImpl<PurchaseDetailMapper,PurchaseDetailDO> implements PurchaseDetailService {

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void add(PurchaseAddReq.PurchaseDetail addReq) {
        PurchaseDetailDO entity = ConvertUtil.convert(addReq,PurchaseDetailDO.class);
        save(entity);
    }

    @Override
    public List<PurchaseDetailDO> listByPurchaseId(Long purchaseId) {
        // 查到该进货单的商品明细
        LambdaQueryWrapper<PurchaseDetailDO> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(PurchaseDetailDO::getPurchaseId,purchaseId);
        return list(queryWrapper);
    }

    @Override
    public void removeByPurchaseId(Long purchaseId) {
        LambdaQueryWrapper<PurchaseDetailDO> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(PurchaseDetailDO::getPurchaseId,purchaseId);
        remove(queryWrapper);
    }
}
