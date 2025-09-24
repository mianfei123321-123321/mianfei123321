package com.roadjava.market.service.impl;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.roadjava.market.bean.entity.GoodsDO;
import com.roadjava.market.bean.entity.PurchaseDO;
import com.roadjava.market.bean.entity.ReturnGoodsDO;
import com.roadjava.market.bean.entity.ReturnGoodsDetailDO;
import com.roadjava.market.bean.req.returngoods.RetUpdateStatusReq;
import com.roadjava.market.bean.req.returngoods.ReturnGoodsPageReq;
import com.roadjava.market.bean.res.Result;
import com.roadjava.market.bean.req.returngoods.ReturnGoodsAddReq;
import com.roadjava.market.bean.vo.PurchaseDetailVO;
import com.roadjava.market.bean.vo.PurchaseVO;
import com.roadjava.market.bean.vo.ReturnGoodsDetailVO;
import com.roadjava.market.bean.vo.ReturnGoodsVO;
import com.roadjava.market.constants.Constants;
import com.roadjava.market.context.UserContext;
import com.roadjava.market.enums.PurchaseStatusEnum;
import com.roadjava.market.enums.ReturnGoodsStatusEnum;
import com.roadjava.market.ex.BizEx;
import com.roadjava.market.mapper.PurchaseMapper;
import com.roadjava.market.mapper.ReturnGoodsDetailMapper;
import com.roadjava.market.mapper.ReturnGoodsMapper;
import com.roadjava.market.service.GoodsService;
import com.roadjava.market.service.ReturnGoodsDetailService;
import com.roadjava.market.service.ReturnGoodsService;
import com.roadjava.market.util.NoUtil;
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
 * 退货表业务实现类
 * @author 乐之者java
 * @see <a href="http://www.roadjava.com">乐之者java</a>
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class ReturnGoodsServiceImpl extends ServiceImpl<ReturnGoodsMapper,ReturnGoodsDO> implements ReturnGoodsService {
    @Resource
    private PurchaseMapper purchaseMapper;
    @Resource
    private ReturnGoodsDetailService returnGoodsDetailService;
    @Resource
    private ReturnGoodsDetailMapper returnGoodsDetailMapper;
    @Resource
    private GoodsService goodsService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void add(ReturnGoodsAddReq addReq) {
        List<Long> purchaseIdList = addReq.getPurchaseIdList();
        for (Long purchaseId : purchaseIdList) {
            // 查询对应的进货单
            PurchaseVO purchaseVO = purchaseMapper.selectWithDetail(purchaseId);
            if (!PurchaseStatusEnum.AGREED.getCode().equals(purchaseVO.getStatus())) {
                throw new BizEx("只有审核通过的进货单才能进行退货,进货单:"+purchaseVO.getPurchaseNo()+"状态不满足");
            }
            // 构造退货主表
            ReturnGoodsDO returnGoods = new ReturnGoodsDO();
            returnGoods.setPurchaseId(purchaseVO.getId());
            returnGoods.setRetNo(NoUtil.getNo(Constants.PRE_RET));
            returnGoods.setOperatorId(UserContext.get().getId());
            returnGoods.setReturnDate(new Date());
            save(returnGoods);
            // 退货从表
            for (PurchaseDetailVO purchaseDetail : purchaseVO.getDetailList()) {
                // 构造退货从表
                ReturnGoodsDetailDO returnGoodsDetail = ConvertUtil.convert(purchaseDetail,ReturnGoodsDetailDO.class);
                // id置空
                returnGoodsDetail.setId(null);
                returnGoodsDetail.setRetId(returnGoods.getId());
                returnGoodsDetail.setReturnPrice(purchaseDetail.getBuyPrice());
                returnGoodsDetailService.save(returnGoodsDetail);
            }
        }
    }

    @Override
    public Result<List<ReturnGoodsVO>> queryPage(ReturnGoodsPageReq pageReq) {
        IPage<ReturnGoodsDO> iPage = PageUtil.buildPage(pageReq);
        IPage<ReturnGoodsVO> pageResult = getBaseMapper().selectRetList(iPage, pageReq.getFuzzyRetNo(),pageReq.getStatus());
        List<ReturnGoodsVO> records = pageResult.getRecords();
        if (CollectionUtils.isEmpty(records)) {
            return Result.buildSuccess();
        }
        List<ReturnGoodsVO> voList = ConvertUtil.convert(records, ReturnGoodsVO.class);
        List<Long> retIds = voList.stream()
            .map(ReturnGoodsVO::getId)
            .collect(Collectors.toList());
        List<ReturnGoodsDetailVO> detailList = returnGoodsDetailMapper.selectByRetIds(retIds);
        // 按退货号分组,每组即对应的明细列表
        Map<Long, List<ReturnGoodsDetailVO>> retNo2DetailListMap = detailList.stream()
            .collect(Collectors.groupingBy(ReturnGoodsDetailVO::getRetId));
        voList.forEach(vo -> {
            Optional.ofNullable(ReturnGoodsStatusEnum.getEnum(vo.getStatus()))
                .ifPresent(e -> vo.setStatusDesc(e.getDesc()));
            vo.setDetailList(retNo2DetailListMap.get(vo.getId()));
        });
        // 设置分页信息
        return Result.buildSuccess(voList,pageResult.getTotal());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteById(Long id) {
        ReturnGoodsDO returnGoodsDO = getById(id);
        if (returnGoodsDO == null) {
            throw new BizEx("退货单:" + id + "不存在");
        }
        if (!ReturnGoodsStatusEnum.TO_AUDIT.getCode().equals(returnGoodsDO.getStatus())) {
            throw new BizEx("只有待审核的退货单才能删除");
        }
        // 删除明细
        LambdaQueryWrapper<ReturnGoodsDetailDO> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(ReturnGoodsDetailDO::getRetId,id);
        returnGoodsDetailMapper.delete(queryWrapper);
        removeById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateStatus(RetUpdateStatusReq req) {
        ReturnGoodsDO returnGoodsDO = getById(req.getId());
        returnGoodsDO.setId(req.getId());
        returnGoodsDO.setStatus(req.getStatus());
        updateById(returnGoodsDO);
        //审核通过时扣减商品库存
        if (ReturnGoodsStatusEnum.AGREED.getCode().equals(req.getStatus())) {
            // 查到该退货单的商品明细
            LambdaQueryWrapper<ReturnGoodsDetailDO> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(ReturnGoodsDetailDO::getRetId,req.getId());
            List<ReturnGoodsDetailDO> returnGoodsDetailDOList = returnGoodsDetailMapper.selectList(queryWrapper);
            for (ReturnGoodsDetailDO returnGoodsDetailDO : returnGoodsDetailDOList) {
                Integer retGoodsNum = returnGoodsDetailDO.getNum();
                // 并分别扣减库存
                GoodsDO existedGoods = goodsService.getById(returnGoodsDetailDO.getGoodsId());
                if(existedGoods == null) {
                    continue;
                }
                int remainStock = existedGoods.getStock() - retGoodsNum;
                if (remainStock < 0) {
                    throw new BizEx("审核通过失败:库存不够,无法进行退货");
                }
                existedGoods.setStock(remainStock);
                goodsService.updateById(existedGoods);
            }
            // 更新进货单状态为已退货
            PurchaseDO purchaseToUpdate = new PurchaseDO();
            purchaseToUpdate.setStatus(PurchaseStatusEnum.RETURNED.getCode());
            LambdaUpdateWrapper<PurchaseDO> updateWrapper = new LambdaUpdateWrapper<>();
            updateWrapper.eq(PurchaseDO::getId,returnGoodsDO.getPurchaseId());
            purchaseMapper.update(purchaseToUpdate,updateWrapper);
        }
    }
}
