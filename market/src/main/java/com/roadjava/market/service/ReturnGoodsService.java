package com.roadjava.market.service;
import com.baomidou.mybatisplus.extension.service.IService;
import com.roadjava.market.bean.entity.ReturnGoodsDO;
import com.roadjava.market.bean.req.returngoods.RetUpdateStatusReq;
import com.roadjava.market.bean.req.returngoods.ReturnGoodsPageReq;
import com.roadjava.market.bean.res.Result;
import com.roadjava.market.bean.req.returngoods.ReturnGoodsAddReq;
import com.roadjava.market.bean.vo.ReturnGoodsVO;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
/**
  * 退货表业务类
  * @author 乐之者java
  * @see <a href="http://www.roadjava.com">乐之者java</a>
  */
public interface ReturnGoodsService extends IService<ReturnGoodsDO> {
    /**
    * 新增
    */
    void add(ReturnGoodsAddReq addReq);

    /**
    * 分页查询
    */
    Result<List<ReturnGoodsVO>> queryPage(ReturnGoodsPageReq pageReq);

    /**
     * 按主键删除
     */
    void deleteById(Long id);

    void updateStatus(RetUpdateStatusReq req);
}
