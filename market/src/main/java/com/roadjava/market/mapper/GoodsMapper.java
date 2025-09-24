package com.roadjava.market.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.roadjava.market.bean.entity.GoodsDO;
import com.roadjava.market.bean.req.goods.GoodsPageReq;
import com.roadjava.market.bean.vo.GoodsVO;

import java.util.List;

/**
 * 商品表mapper类
 * @author 乐之者java
 * @see <a href="http://www.roadjava.com">乐之者java</a>
 */
public interface GoodsMapper extends BaseMapper<GoodsDO> {
    List<GoodsVO> queryList(GoodsPageReq req);
    Long queryCount(GoodsPageReq req);

    GoodsVO selectWithDetail(Long goodsId);
}
