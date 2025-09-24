package com.roadjava.market.service;

import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;
import com.roadjava.market.bean.entity.ManagerDO;
import com.roadjava.market.bean.res.Result;
import com.roadjava.market.bean.req.manager.LoginReq;
import com.roadjava.market.bean.req.manager.ManagerUpdateReq;
import com.roadjava.market.bean.req.manager.UpdatePwdReq;
import com.roadjava.market.bean.req.manager.ManagerAddReq;
import com.roadjava.market.bean.req.manager.ManagerPageReq;
import com.roadjava.market.bean.vo.ManagerVO;

/**
 * @author 乐之者java
 * @see <a href="http://www.roadjava.com">乐之者java</a>
 */
public interface ManagerService extends IService<ManagerDO> {
    /**
     * 登录
     */
    String login(LoginReq loginReq);

    /**
     * 根据主键查询
     */
    ManagerVO queryById(Long id);

    /**
     * 根据id修改信息
     */
    void modifyById(ManagerUpdateReq updateReq);
    /**
     * 根据id修改密码
     */
    void updatePwd(UpdatePwdReq updatePwdReq);
    /**
    * 新增
    */
    void add(ManagerAddReq addReq);
    /**
    * 分页查询
    */
    Result<List<ManagerVO>> queryPage(ManagerPageReq pageReq);
    /**
    * 按主键删除
    */
    void deleteById(Long id);
}
