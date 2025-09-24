package com.roadjava.market.service;
import com.baomidou.mybatisplus.extension.service.IService;
import com.roadjava.market.bean.entity.RefundDO;
import com.roadjava.market.bean.req.refund.RefundPageReq;
import com.roadjava.market.bean.res.Result;
import com.roadjava.market.bean.req.refund.RefundAddReq;
import com.roadjava.market.bean.vo.RefundVO;
import java.util.List;
/**
  * 退款表业务类
  * @author 乐之者java
  * @see <a href="http://www.roadjava.com">乐之者java</a>
  */
public interface RefundService extends IService<RefundDO> {
    /**
    * 新增
    */
    void add(RefundAddReq addReq);

    /**
    * 分页查询
    */
    Result<List<RefundVO>> queryPage(RefundPageReq pageReq);
}
