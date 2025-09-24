package com.roadjava.market.service;
import com.baomidou.mybatisplus.extension.service.IService;
import com.roadjava.market.bean.entity.GoodsDO;
import com.roadjava.market.bean.req.goods.GoodsPageReq;
import com.roadjava.market.bean.req.goods.GoodsUpdateReq;
import com.roadjava.market.bean.res.Result;
import com.roadjava.market.bean.req.goods.GoodsAddReq;
import com.roadjava.market.bean.vo.GoodsVO;
import java.util.List;
/**
  * 商品表业务类
  * @author 乐之者java
  * @see <a href="http://www.roadjava.com">乐之者java</a>
  */
public interface GoodsService extends IService<GoodsDO> {
    /**
    * 新增
    */
    void add(GoodsAddReq addReq);

    /**
    * 分页查询
    */
    Result<List<GoodsVO>> queryPage(GoodsPageReq pageReq);

    /**
     * 按主键删除
     */
    void deleteById(Long id);

    List<GoodsVO> queryAll();

    /**
     * 通过id查询
     */
    GoodsVO queryById(Long id);

    void modifyById(GoodsUpdateReq updateReq);
}
