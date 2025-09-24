package com.roadjava.market.service;
import com.baomidou.mybatisplus.extension.service.IService;
import com.roadjava.market.bean.entity.ShelfDO;
import com.roadjava.market.bean.req.shelf.ShelfPageReq;
import com.roadjava.market.bean.res.Result;
import com.roadjava.market.bean.req.shelf.ShelfAddReq;
import com.roadjava.market.bean.req.shelf.ShelfUpdateReq;
import com.roadjava.market.bean.vo.ShelfVO;
import java.util.List;
/**
  * 货架业务类
  * @author 乐之者java
  * @see <a href="https://www.roadjava.com">乐之者java</a>
  */
public interface ShelfService extends IService<ShelfDO> {
    /**
    * 新增
    */
    void add(ShelfAddReq addReq);

    /**
    * 分页查询
    */
    Result<List<ShelfVO>> queryPage(ShelfPageReq pageReq);

    /**
     * 按主键删除
     */
    void deleteById(Long id);

    /**
    * 按id查询
    */
    ShelfVO queryById(Long id);

    /**
    * 按id更新
    */
    void modifyById(ShelfUpdateReq updateReq);

    /**
    * 查询全部
    */
    List<ShelfVO> queryAll();
}