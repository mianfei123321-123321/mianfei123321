package com.roadjava.market.service.impl;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.roadjava.market.bean.entity.ShelfDO;
import com.roadjava.market.bean.req.shelf.ShelfPageReq;
import com.roadjava.market.bean.res.Result;
import com.roadjava.market.bean.req.shelf.ShelfAddReq;
import com.roadjava.market.bean.req.shelf.ShelfUpdateReq;
import com.roadjava.market.bean.vo.ShelfVO;
import com.roadjava.market.mapper.ShelfMapper;
import com.roadjava.market.service.ShelfService;
import com.roadjava.market.util.PageUtil;
import com.roadjava.market.util.ConvertUtil;
import lombok.extern.slf4j.Slf4j;
import lombok.RequiredArgsConstructor;
import cn.hutool.core.util.StrUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Objects;
/**
 * 货架业务实现类
 * @author 乐之者java
 * @see <a href="https://www.roadjava.com">乐之者java</a>
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class ShelfServiceImpl extends ServiceImpl<ShelfMapper,ShelfDO> implements ShelfService {

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void add(ShelfAddReq addReq) {
        ShelfDO entity = ConvertUtil.convert(addReq,ShelfDO.class);
        save(entity);
    }

    @Override
    public Result<List<ShelfVO>> queryPage(ShelfPageReq pageReq) {
        IPage<ShelfDO> iPage = PageUtil.buildPage(pageReq);
        LambdaQueryWrapper<ShelfDO> lqw = new LambdaQueryWrapper<>();
        // 拼接查询条件
        lqw.like(StrUtil.isNotBlank(pageReq.getShelfNo()),ShelfDO::getShelfNo,pageReq.getShelfNo());
        lqw.orderByDesc(ShelfDO::getId);
        IPage<ShelfDO> page = page(iPage, lqw);
        return ConvertUtil.convert(page,ShelfVO.class);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteById(Long id) {
        removeById(id);
    }

    @Override
    public ShelfVO queryById(Long id) {
        ShelfDO entity = getById(id);
        return ConvertUtil.convert(entity,ShelfVO.class);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void modifyById(ShelfUpdateReq updateReq) {
        ShelfDO entity = ConvertUtil.convert(updateReq,ShelfDO.class);
        updateById(entity);
    }

    @Override
    public List<ShelfVO> queryAll() {
        LambdaQueryWrapper<ShelfDO> lqw = new LambdaQueryWrapper<>();
        lqw.orderByDesc(ShelfDO::getId);
        List<ShelfDO> list = list(lqw);
        return ConvertUtil.convert(list,ShelfVO.class);
    }
}