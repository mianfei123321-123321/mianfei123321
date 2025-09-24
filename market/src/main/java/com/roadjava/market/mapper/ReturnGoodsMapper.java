package com.roadjava.market.mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.roadjava.market.bean.entity.ReturnGoodsDO;
import com.roadjava.market.bean.req.returngoods.ReturnGoodsPageReq;
import com.roadjava.market.bean.vo.ReturnGoodsVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 退货表mapper类
 * @author 乐之者java
 * @see <a href="http://www.roadjava.com">乐之者java</a>
 */
public interface ReturnGoodsMapper extends BaseMapper<ReturnGoodsDO> {

    IPage<ReturnGoodsVO> selectRetList(IPage<ReturnGoodsDO> iPage,@Param("fuzzyRetNo") String fuzzyRetNo,@Param("status") Integer status);
}
