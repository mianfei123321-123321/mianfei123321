package com.roadjava.market.service.impl;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.roadjava.market.bean.entity.SupplierDO;
import com.roadjava.market.bean.req.supplier.SupplierPageReq;
import com.roadjava.market.bean.res.Result;
import com.roadjava.market.bean.req.supplier.SupplierAddReq;
import com.roadjava.market.bean.req.supplier.SupplierUpdateReq;
import com.roadjava.market.bean.vo.SupplierVO;
import com.roadjava.market.mapper.SupplierMapper;
import com.roadjava.market.service.SupplierService;
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
 * 供应商表业务实现类
 * @author 乐之者java
 * @see <a href="http://www.roadjava.com">乐之者java</a>
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class SupplierServiceImpl extends ServiceImpl<SupplierMapper,SupplierDO> implements SupplierService {

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void add(SupplierAddReq addReq) {
        SupplierDO entity = ConvertUtil.convert(addReq,SupplierDO.class);
        save(entity);
    }

    @Override
    public Result<List<SupplierVO>> queryPage(SupplierPageReq pageReq) {
        IPage<SupplierDO> iPage = PageUtil.buildPage(pageReq);
        LambdaQueryWrapper<SupplierDO> lqw = new LambdaQueryWrapper<>();
        // 拼接查询条件
        lqw.like(StrUtil.isNotBlank(pageReq.getName()),SupplierDO::getName,pageReq.getName());
        lqw.orderByDesc(SupplierDO::getId);
        IPage<SupplierDO> page = page(iPage, lqw);
        return ConvertUtil.convert(page,SupplierVO.class);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteById(Long id) {
        removeById(id);
    }

    @Override
    public SupplierVO queryById(Long id) {
        SupplierDO entity = getById(id);
        return ConvertUtil.convert(entity,SupplierVO.class);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void modifyById(SupplierUpdateReq updateReq) {
        SupplierDO entity = ConvertUtil.convert(updateReq,SupplierDO.class);
        updateById(entity);
    }

    @Override
    public List<SupplierVO> queryAll() {
        LambdaQueryWrapper<SupplierDO> lqw = new LambdaQueryWrapper<>();
        lqw.orderByDesc(SupplierDO::getId);
        List<SupplierDO> list = list(lqw);
        return ConvertUtil.convert(list,SupplierVO.class);
    }
}