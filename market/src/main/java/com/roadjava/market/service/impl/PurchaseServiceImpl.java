package com.roadjava.market.service.impl;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.roadjava.market.bean.entity.GoodsDO;
import com.roadjava.market.bean.entity.PurchaseDO;
import com.roadjava.market.bean.entity.PurchaseDetailDO;
import com.roadjava.market.bean.req.purchase.*;
import com.roadjava.market.bean.res.Result;
import com.roadjava.market.bean.vo.PurchaseVO;
import com.roadjava.market.enums.PurchaseStatusEnum;
import com.roadjava.market.ex.BizEx;
import com.roadjava.market.mapper.PurchaseMapper;
import com.roadjava.market.service.GoodsService;
import com.roadjava.market.service.PurchaseDetailService;
import com.roadjava.market.service.PurchaseService;
import com.roadjava.market.service.ReturnGoodsService;
import com.roadjava.market.util.ConvertUtil;
import lombok.extern.slf4j.Slf4j;
import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * 进货表业务实现类
 * @author 乐之者java
 * @see <a href="http://www.roadjava.com">乐之者java</a>
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class PurchaseServiceImpl extends ServiceImpl<PurchaseMapper,PurchaseDO> implements PurchaseService {

    @Resource
    private PurchaseDetailService purchaseDetailService;
    @Resource
    private GoodsService goodsService;
    @Resource
    private ReturnGoodsService returnGoodsService;
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void add(PurchaseAddReq addReq) {
        PurchaseDO entity = ConvertUtil.convert(addReq,PurchaseDO.class);
        save(entity);
        List<PurchaseDetailDO> detailDoList = new ArrayList<>();
        // 插入进货明细
        for (PurchaseAddReq.PurchaseDetail purchaseDetail : addReq.getDetailList()) {
            PurchaseDetailDO detailDO = ConvertUtil.convert(purchaseDetail,PurchaseDetailDO.class);
            // 设置外键
            detailDO.setPurchaseId(entity.getId());
            detailDoList.add(detailDO);
        }
        purchaseDetailService.saveBatch(detailDoList);
    }

    @Override
    public Result<List<PurchaseVO>> queryPage(PurchasePageReq pageReq) {
        // 只需要对主表进行条数统计即可
        Long count = getBaseMapper().queryCount(pageReq);
        if(count == 0) {
            return Result.buildSuccess(Collections.emptyList(),count);
        }
        List<PurchaseVO> voList = getBaseMapper().queryList(pageReq);
        populateBean(voList);
        return Result.buildSuccess(voList,count);
    }

    private void populateBean(List<PurchaseVO> voList) {
        if (CollectionUtils.isNotEmpty(voList)) {
            voList.forEach(vo -> Optional.ofNullable(PurchaseStatusEnum.getEnum(vo.getStatus()))
                .ifPresent(e -> vo.setStatusDesc(e.getDesc())));
        }
    }
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteById(Long id) {
        PurchaseDO purchaseDO = getById(id);
        if (purchaseDO == null) {
            throw new BizEx("进货单:" + id + "不存在");
        }
        if (!PurchaseStatusEnum.TO_AUDIT.getCode().equals(purchaseDO.getStatus())) {
            throw new BizEx("只有待审核的进货单才能删除");
        }
        // 删除明细
        purchaseDetailService.removeByPurchaseId(id);
        // 删除进货
        removeById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateStatus(PurchaseUpdateStatusReq req) {
        PurchaseDO purchase2update = getById(req.getId());
        purchase2update.setStatus(req.getStatus());
        updateById(purchase2update);
        //审核通过时增加商品库存
        if (PurchaseStatusEnum.AGREED.getCode().equals(req.getStatus())) {
            List<PurchaseDetailDO> purchaseDetailDOList = purchaseDetailService.listByPurchaseId(req.getId());
            for (PurchaseDetailDO purchaseDetailDO : purchaseDetailDOList) {
                // 并分别增加库存
                GoodsDO existedGoods = goodsService.getById(purchaseDetailDO.getGoodsId());
                if(existedGoods == null) {
                    continue;
                }
                existedGoods.setStock(existedGoods.getStock() + purchaseDetailDO.getNum());
                goodsService.updateById(existedGoods);
            }
        }
    }

    @Override
    public PurchaseVO queryById(Long id) {
        return getBaseMapper().selectWithDetail(id);
    }

    @Override
    public void modifyById(PurchaseUpdateReq updateReq) {
        PurchaseDO entity = getById(updateReq.getId());
        if (!PurchaseStatusEnum.TO_AUDIT.getCode().equals(entity.getStatus())
            && !PurchaseStatusEnum.REJECTED.getCode().equals(entity.getStatus())) {
            throw new BizEx("只有待审核或已拒绝的进货单才能修改");
        }
        entity.setOperatorId(updateReq.getOperatorId());
        // 修改后状态改为待审核
        entity.setStatus(PurchaseStatusEnum.TO_AUDIT.getCode());
        updateById(entity);
        // 删除明细
        purchaseDetailService.removeByPurchaseId(updateReq.getId());
        List<PurchaseDetailDO> detailDoList = new ArrayList<>();
        // 插入进货明细
        for (PurchaseUpdateReq.PurchaseDetail purchaseDetail : updateReq.getDetailList()) {
            PurchaseDetailDO detailDO = ConvertUtil.convert(purchaseDetail,PurchaseDetailDO.class);
            // 设置外键
            detailDO.setPurchaseId(entity.getId());
            detailDoList.add(detailDO);
        }
        purchaseDetailService.saveBatch(detailDoList);
    }
}
