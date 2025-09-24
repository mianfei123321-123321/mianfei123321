package com.roadjava.market.service;
import com.baomidou.mybatisplus.extension.service.IService;
import com.roadjava.market.bean.entity.SupplierDO;
import com.roadjava.market.bean.req.supplier.SupplierPageReq;
import com.roadjava.market.bean.res.Result;
import com.roadjava.market.bean.req.supplier.SupplierAddReq;
import com.roadjava.market.bean.req.supplier.SupplierUpdateReq;
import com.roadjava.market.bean.vo.SupplierVO;
import java.util.List;
/**
  * 供应商表业务类
  * @author 乐之者java
  * @see <a href="http://www.roadjava.com">乐之者java</a>
  */
public interface SupplierService extends IService<SupplierDO> {
    /**
    * 新增
    */
    void add(SupplierAddReq addReq);

    /**
    * 分页查询
    */
    Result<List<SupplierVO>> queryPage(SupplierPageReq pageReq);

    /**
     * 按主键删除
     */
    void deleteById(Long id);

    /**
    * 按id查询
    */
    SupplierVO queryById(Long id);

    /**
    * 按id更新
    */
    void modifyById(SupplierUpdateReq updateReq);

    /**
    * 查询全部
    */
    List<SupplierVO> queryAll();
}