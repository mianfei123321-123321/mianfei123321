package com.roadjava.market.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.roadjava.market.bean.entity.GoodsDO;
import com.roadjava.market.bean.req.goods.GoodsPageReq;
import com.roadjava.market.bean.req.goods.GoodsUpdateReq;
import com.roadjava.market.bean.res.Result;
import com.roadjava.market.bean.req.goods.GoodsAddReq;
import com.roadjava.market.bean.vo.GoodsVO;
import com.roadjava.market.bean.vo.PurchaseVO;
import com.roadjava.market.constants.Constants;
import com.roadjava.market.enums.PurchaseStatusEnum;
import com.roadjava.market.mapper.GoodsMapper;
import com.roadjava.market.service.GoodsService;
import com.roadjava.market.util.PageUtil;
import com.roadjava.market.util.ConvertUtil;
import lombok.extern.slf4j.Slf4j;
import lombok.RequiredArgsConstructor;
import cn.hutool.core.util.StrUtil;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 商品表业务实现类
 *
 * @author 乐之者java
 * @see <a href="http://www.roadjava.com">乐之者java</a>
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class GoodsServiceImpl extends ServiceImpl<GoodsMapper, GoodsDO> implements GoodsService {

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void add(GoodsAddReq addReq) {
        GoodsDO entity = ConvertUtil.convert(addReq, GoodsDO.class);
        Optional.ofNullable(addReq.getBornPlaceCodeList())
            .ifPresent(codeList -> {
                entity.setBornPlaceCodes(String.join(Constants.COMMA, codeList));
            });
        save(entity);
    }

    @Override
    public Result<List<GoodsVO>> queryPage(GoodsPageReq pageReq) {
        Long count = getBaseMapper().queryCount(pageReq);
        if (count == 0) {
            return Result.buildSuccess(Collections.emptyList(), count);
        }
        List<GoodsVO> voList = getBaseMapper().queryList(pageReq);
        populateBean(voList);
        return Result.buildSuccess(voList, count);
    }

    private void populateBean(List<GoodsVO> voList) {
        if (CollectionUtils.isEmpty(voList)) {
            return;
        }
        for (GoodsVO goodsVO : voList) {
            Optional.ofNullable(goodsVO.getBornPlaceCodes())
                .ifPresent(codes -> {
                    goodsVO.setBornPlaceCodeList(Stream.of(codes.split(Constants.COMMA)).collect(Collectors.toList()));
                });
        }
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteById(Long id) {
        removeById(id);
    }

    @Override
    public List<GoodsVO> queryAll() {
        LambdaQueryWrapper<GoodsDO> lqw = new LambdaQueryWrapper<>();
        lqw.orderByDesc(GoodsDO::getId);
        List<GoodsDO> list = list(lqw);
        return ConvertUtil.convert(list, GoodsVO.class);
    }

    @Override
    public GoodsVO queryById(Long id) {
        GoodsVO goodsVO = getBaseMapper().selectWithDetail(id);
        Optional.ofNullable(goodsVO.getBornPlaceCodes())
            .ifPresent(codes -> {
                goodsVO.setBornPlaceCodeList(Stream.of(codes.split(Constants.COMMA)).collect(Collectors.toList()));
            });
        return goodsVO;
    }

    @Override
    public void modifyById(GoodsUpdateReq updateReq) {
        GoodsDO goodsDO = ConvertUtil.convert(updateReq, GoodsDO.class);
        Optional.ofNullable(updateReq.getBornPlaceCodeList())
            .ifPresent(codeList -> {
                goodsDO.setBornPlaceCodes(String.join(Constants.COMMA, codeList));
            });
        updateById(goodsDO);
    }
}
