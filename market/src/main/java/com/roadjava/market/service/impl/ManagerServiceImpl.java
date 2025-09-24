package com.roadjava.market.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.roadjava.market.bean.dto.TokenDTO;
import com.roadjava.market.bean.entity.ManagerDO;
import com.roadjava.market.bean.res.Result;
import com.roadjava.market.bean.req.manager.LoginReq;
import com.roadjava.market.bean.req.manager.ManagerUpdateReq;
import com.roadjava.market.bean.req.manager.ManagerAddReq;
import com.roadjava.market.bean.req.manager.ManagerPageReq;
import com.roadjava.market.bean.req.manager.UpdatePwdReq;
import com.roadjava.market.bean.vo.ManagerVO;
import com.roadjava.market.enums.RoleEnum;
import com.roadjava.market.ex.BizEx;
import com.roadjava.market.mapper.ManagerMapper;
import com.roadjava.market.service.ManagerService;
import com.roadjava.market.util.PageUtil;
import com.roadjava.market.util.ConvertUtil;
import com.roadjava.market.util.JwtUtil;
import java.util.List;
import java.util.Optional;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author 乐之者java
 * @see <a href="http://www.roadjava.com">乐之者java</a>
 */
@Service
@Slf4j
public class ManagerServiceImpl extends ServiceImpl<ManagerMapper, ManagerDO> implements ManagerService {
    @Override
    public String login(LoginReq loginReq) {
        LambdaQueryWrapper<ManagerDO> lqw = new LambdaQueryWrapper<>();
        lqw.eq(ManagerDO::getUsername,loginReq.getUsername())
                .eq(ManagerDO::getPwd,loginReq.getPwd());
        ManagerDO one = getOne(lqw);
        if (one == null) {
            throw new BizEx("用户名或密码不正确");
        }
        TokenDTO tokenDTO = ConvertUtil.convert(one,TokenDTO.class);
        return JwtUtil.getToken(tokenDTO);
    }

    @Override
    public ManagerVO queryById(Long id) {
        ManagerDO existedEntity = getById(id);
        return ConvertUtil.convert(existedEntity,ManagerVO.class);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void modifyById(ManagerUpdateReq updateReq) {
        // 更新字段为null的问题
        LambdaUpdateWrapper<ManagerDO> luw = new LambdaUpdateWrapper<>();
        luw.set(ManagerDO::getRealName,updateReq.getRealName())
                .set(ManagerDO::getSex,updateReq.getSex())
                // 若avatarPath为null,用这种方式也能更新,即null不会被mp忽略
                .set(ManagerDO::getAvatarPath,updateReq.getAvatarPath())
                .eq(ManagerDO::getId,updateReq.getId());
        update(new ManagerDO(),luw);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updatePwd(UpdatePwdReq updatePwdReq) {
        ManagerDO existedEntity = getById(updatePwdReq.getId());
        if (existedEntity == null) {
            throw new BizEx("用户不存在");
        }
        if (!updatePwdReq.getOriginalPwd().equals(existedEntity.getPwd())) {
            throw new BizEx("原始密码不正确");
        }
        existedEntity.setPwd(updatePwdReq.getNewPwd());
        updateById(existedEntity);
    }
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void add(ManagerAddReq addReq) {
        ManagerDO entity = ConvertUtil.convert(addReq,ManagerDO.class);
        save(entity);
    }

    @Override
    public Result<List<ManagerVO>> queryPage(ManagerPageReq pageReq) {
        IPage<ManagerDO> iPage = PageUtil.buildPage(pageReq);
        LambdaQueryWrapper<ManagerDO> lqw = new LambdaQueryWrapper<>();
        // 拼接查询条件
        lqw.like(StrUtil.isNotBlank(pageReq.getUsername()),ManagerDO::getUsername,pageReq.getUsername());
        lqw.like(StrUtil.isNotBlank(pageReq.getRealName()),ManagerDO::getRealName,pageReq.getRealName());
        lqw.eq(StrUtil.isNotBlank(pageReq.getSex()),ManagerDO::getSex,pageReq.getSex());
        lqw.orderByDesc(ManagerDO::getId);
        IPage<ManagerDO> page = page(iPage, lqw);
        IPage<ManagerVO> converted = page.convert(entity -> {
            ManagerVO vo = ConvertUtil.convert(entity, ManagerVO.class);
            // 设置角色名
            Optional.ofNullable(RoleEnum.getEnum(vo.getRoleId()))
                .ifPresent(roleEnum -> vo.setRoleName(roleEnum.getRoleName()));
            return vo;
        });
        return Result.buildSuccess(converted.getRecords(), converted.getTotal());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteById(Long id) {
        removeById(id);
    }
}
