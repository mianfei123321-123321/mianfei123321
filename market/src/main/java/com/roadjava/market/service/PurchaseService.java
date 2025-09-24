package com.roadjava.market.service;
import com.baomidou.mybatisplus.extension.service.IService;
import com.roadjava.market.bean.entity.PurchaseDO;
import com.roadjava.market.bean.req.purchase.PurchasePageReq;
import com.roadjava.market.bean.req.purchase.PurchaseUpdateStatusReq;
import com.roadjava.market.bean.res.Result;
import com.roadjava.market.bean.req.purchase.PurchaseAddReq;
import com.roadjava.market.bean.req.purchase.PurchaseUpdateReq;
import com.roadjava.market.bean.vo.PurchaseVO;
import java.util.List;
/**
  * 进货表业务类
  * @author 乐之者java
  * @see <a href="http://www.roadjava.com">乐之者java</a>
  */
public interface PurchaseService extends IService<PurchaseDO> {
    /**
    * 新增
    */
    void add(PurchaseAddReq addReq);

    /**
    * 分页查询
    */
    Result<List<PurchaseVO>> queryPage(PurchasePageReq pageReq);

    /**
     * 按主键删除,只有{@link com.roadjava.market.enums.PurchaseStatusEnum#TO_AUDIT}状态
     * 的才能删除
     */
    void deleteById(Long id);


    void updateStatus(PurchaseUpdateStatusReq req);

    PurchaseVO queryById(Long id);

    /**
     *
     * 只有{@link com.roadjava.market.enums.PurchaseStatusEnum#TO_AUDIT}
     * 或{@link com.roadjava.market.enums.PurchaseStatusEnum#REJECTED}状态的
     * 才能修改
     */
    void modifyById(PurchaseUpdateReq updateReq);
}
