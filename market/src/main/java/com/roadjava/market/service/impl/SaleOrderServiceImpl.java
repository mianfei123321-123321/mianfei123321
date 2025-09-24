package com.roadjava.market.service.impl;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.collection.ListUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.roadjava.market.bean.entity.GoodsDO;
import com.roadjava.market.bean.entity.SaleOrderDO;
import com.roadjava.market.bean.entity.SaleOrderDetailDO;
import com.roadjava.market.bean.req.saleorder.SaleOrderDetailAddReq;
import com.roadjava.market.bean.req.saleorder.SaleOrderPageReq;
import com.roadjava.market.bean.res.Result;
import com.roadjava.market.bean.req.saleorder.SaleOrderAddReq;
import com.roadjava.market.bean.vo.SaleOrderDetailVO;
import com.roadjava.market.bean.vo.SaleOrderVO;
import com.roadjava.market.enums.SaleOrderStatusEnum;
import com.roadjava.market.ex.BizEx;
import com.roadjava.market.mapper.SaleOrderDetailMapper;
import com.roadjava.market.mapper.SaleOrderMapper;
import com.roadjava.market.service.GoodsService;
import com.roadjava.market.service.SaleOrderDetailService;
import com.roadjava.market.service.SaleOrderService;
import com.roadjava.market.util.PageUtil;
import com.roadjava.market.util.ConvertUtil;
import lombok.extern.slf4j.Slf4j;
import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 销售订单表业务实现类
 * @author 乐之者java
 * @see <a href="http://www.roadjava.com">乐之者java</a>
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class SaleOrderServiceImpl extends ServiceImpl<SaleOrderMapper,SaleOrderDO> implements SaleOrderService {
    @Resource
    private GoodsService goodsService;
    @Resource
    private SaleOrderDetailService saleOrderDetailService;
    @Resource
    private SaleOrderDetailMapper saleOrderDetailMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void add(SaleOrderAddReq addReq) {
        SaleOrderDO entity = ConvertUtil.convert(addReq, SaleOrderDO.class);
        save(entity);

        for (SaleOrderDetailAddReq saleOrderDetailReq : addReq.getDetailList()) {
            Long goodsId = saleOrderDetailReq.getGoodsId();
            // 查到商品并扣减库存
            GoodsDO existedGoods = goodsService.getById(goodsId);
            if (existedGoods.getStock() < saleOrderDetailReq.getNum()) {
                throw new BizEx("商品:"+existedGoods.getName()+"库存不足,无法生成销售单");
            }
            existedGoods.setStock(existedGoods.getStock() - saleOrderDetailReq.getNum());
            goodsService.updateById(existedGoods);
            // 插入销售明细
            SaleOrderDetailDO detailDO = ConvertUtil.convert(saleOrderDetailReq,SaleOrderDetailDO.class);
            detailDO.setOrderId(entity.getId());
            saleOrderDetailService.save(detailDO);
        }
    }

    @Override
    public Result<List<SaleOrderVO>> queryPage(SaleOrderPageReq pageReq) {
        IPage<SaleOrderVO> pageResult = this.pageWithDetail(pageReq);
        return Result.buildSuccess(pageResult.getRecords(),pageResult.getTotal());
    }


    private IPage<SaleOrderVO> pageWithDetail(SaleOrderPageReq req) {
        // 查询销售单
        IPage<SaleOrderDO> iPage = PageUtil.buildPage(req);
        LambdaQueryWrapper<SaleOrderDO> lqw = Wrappers.<SaleOrderDO>lambdaQuery()
            .like(StrUtil.isNotBlank(req.getOrderNo()),SaleOrderDO::getOrderNo,req.getOrderNo())
            .orderByDesc(SaleOrderDO::getId);
        IPage<SaleOrderDO> pageResult = page(iPage,lqw);
        IPage<SaleOrderVO> ret = pageResult.convert(entity -> ConvertUtil.convert(entity,SaleOrderVO.class));
        List<SaleOrderVO> voList = ret.getRecords();
        if (CollUtil.isEmpty(voList)) {
            return ret;
        }
        List<Long> orderIds = voList.stream()
            .map(SaleOrderVO::getId)
            .collect(Collectors.toList());
        List<SaleOrderDetailVO> detailList = saleOrderDetailMapper.selectByOrderIds(orderIds);
        // 按销售单id分组,每组即对应的明细列表
        Map<Long, List<SaleOrderDetailVO>> retNo2DetailListMap = detailList.stream()
            .collect(Collectors.groupingBy(SaleOrderDetailVO::getOrderId));
        voList.forEach(vo -> {
            vo.setDetailList(retNo2DetailListMap.get(vo.getId()));
            // 设置状态描述
            Optional.ofNullable(SaleOrderStatusEnum.getEnum(vo.getStatus()))
                .ifPresent(e -> vo.setStatusDesc(e.getDesc()));
        });
        return ret;
    }

    @Override
    public SaleOrderVO queryDetail(Long orderId) {
        SaleOrderDO saleOrderDo = getById(orderId);
        SaleOrderVO vo = ConvertUtil.convert(saleOrderDo, SaleOrderVO.class);
        List<SaleOrderDetailVO> detailList = saleOrderDetailMapper.selectByOrderIds(ListUtil.of(saleOrderDo.getId()));
        vo.setDetailList(detailList);
        return vo;
    }
}
