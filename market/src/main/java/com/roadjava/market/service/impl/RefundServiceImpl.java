package com.roadjava.market.service.impl;
import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.roadjava.market.bean.entity.GoodsDO;
import com.roadjava.market.bean.entity.RefundDO;
import com.roadjava.market.bean.entity.RefundDetailDO;
import com.roadjava.market.bean.entity.SaleOrderDO;
import com.roadjava.market.bean.req.refund.RefundPageReq;
import com.roadjava.market.bean.res.Result;
import com.roadjava.market.bean.req.refund.RefundAddReq;
import com.roadjava.market.bean.vo.*;
import com.roadjava.market.constants.Constants;
import com.roadjava.market.enums.SaleOrderStatusEnum;
import com.roadjava.market.ex.BizEx;
import com.roadjava.market.mapper.RefundDetailMapper;
import com.roadjava.market.mapper.RefundMapper;
import com.roadjava.market.service.GoodsService;
import com.roadjava.market.service.RefundDetailService;
import com.roadjava.market.service.RefundService;
import com.roadjava.market.service.SaleOrderService;
import com.roadjava.market.util.NoUtil;
import com.roadjava.market.util.PageUtil;
import com.roadjava.market.util.ConvertUtil;
import lombok.extern.slf4j.Slf4j;
import lombok.RequiredArgsConstructor;
import cn.hutool.core.util.StrUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 退款表业务实现类
 * @author 乐之者java
 * @see <a href="http://www.roadjava.com">乐之者java</a>
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class RefundServiceImpl extends ServiceImpl<RefundMapper,RefundDO> implements RefundService {
    @Resource
    private SaleOrderService saleOrderService;
    @Resource
    private GoodsService goodsService;
    @Resource
    private RefundDetailService refundDetailService;
    @Resource
    private RefundDetailMapper refundDetailMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void add(RefundAddReq addReq) {
        List<Long> orderIdList = addReq.getOrderIdList();
        for (Long orderId : orderIdList) {
            // 查询对应的订单
            SaleOrderVO saleOrderVO = saleOrderService.queryDetail(orderId);
            if (!SaleOrderStatusEnum.DONE.getStatus().equals(saleOrderVO.getStatus())) {
                throw new BizEx("只有已完成的销售单才能进行退款,销售单:"+orderId+"的状态不是已完成");
            }
            // 构造退款主表
            RefundDO refundDO = buildRefundDo(saleOrderVO);
            save(refundDO);
            // 退款从表
            for (SaleOrderDetailVO saleOrderDetailVO : saleOrderVO.getDetailList()) {
                // 查到该商品并增加库存
                GoodsDO existedGoods = goodsService.getById(saleOrderDetailVO.getGoodsId());
                existedGoods.setStock(existedGoods.getStock() + saleOrderDetailVO.getNum());
                goodsService.updateById(existedGoods);
                // 构造退款从表
                RefundDetailDO refundDetailDO = buildRefundDetailDo(refundDO.getId(),saleOrderDetailVO);
                refundDetailService.save(refundDetailDO);
            }
            // 更新销售单单状态为已退货
            SaleOrderDO saleOrderToUpdate = new SaleOrderDO();
            saleOrderToUpdate.setStatus(SaleOrderStatusEnum.REFUND_ED.getStatus());
            saleOrderToUpdate.setId(saleOrderVO.getId());
            saleOrderService.updateById(saleOrderToUpdate);
        }
    }

    /**
     * 构造退款从表
     */
    private RefundDetailDO buildRefundDetailDo(Long refundId, SaleOrderDetailVO saleOrderDetailVO) {
        RefundDetailDO refundDetailDO = ConvertUtil.convert(saleOrderDetailVO,RefundDetailDO.class);
        // id置空
        refundDetailDO.setId(null);
        refundDetailDO.setRefundId(refundId);
        refundDetailDO.setRefundPrice(saleOrderDetailVO.getSalePrice());
        return refundDetailDO;
    }

    /**
     * 构造退款主表
     */
    private RefundDO buildRefundDo(SaleOrderVO saleOrderVO) {
        RefundDO refundDO = ConvertUtil.convert(saleOrderVO,RefundDO.class);
        // id置空
        refundDO.setId(null);
        refundDO.setOrderId(saleOrderVO.getId());
        refundDO.setRefundNo(NoUtil.getNo(Constants.PRE_REFUND));
        refundDO.setCreatedTime(new Date());
        return refundDO;
    }

    @Override
    public Result<List<RefundVO>> queryPage(RefundPageReq pageReq) {
        // 只需要对主表进行条数统计即可
        Long count = getBaseMapper().queryCount(pageReq);
        if(count == 0) {
            return Result.buildSuccess(Collections.emptyList(),count);
        }
        List<RefundVO> voList = getBaseMapper().queryList(pageReq);
        return Result.buildSuccess(voList,count);
    }
}
