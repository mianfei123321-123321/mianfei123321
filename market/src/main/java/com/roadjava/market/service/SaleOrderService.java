package com.roadjava.market.service;
import com.baomidou.mybatisplus.extension.service.IService;
import com.roadjava.market.bean.entity.SaleOrderDO;
import com.roadjava.market.bean.req.saleorder.SaleOrderPageReq;
import com.roadjava.market.bean.res.Result;
import com.roadjava.market.bean.req.saleorder.SaleOrderAddReq;
import com.roadjava.market.bean.vo.SaleOrderVO;
import java.util.List;
/**
  * 销售订单表业务类
  * @author 乐之者java
  * @see <a href="http://www.roadjava.com">乐之者java</a>
  */
public interface SaleOrderService extends IService<SaleOrderDO> {
    /**
    * 新增
    */
    void add(SaleOrderAddReq addReq);

    /**
    * 分页查询
    */
    Result<List<SaleOrderVO>> queryPage(SaleOrderPageReq pageReq);

    SaleOrderVO queryDetail(Long orderId);
}
