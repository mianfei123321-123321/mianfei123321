package com.roadjava.market.controller;

import com.roadjava.market.bean.req.manager.LoginReq;
import com.roadjava.market.bean.req.manager.ManagerUpdateReq;
import com.roadjava.market.bean.req.manager.ManagerAddReq;
import com.roadjava.market.bean.req.manager.ManagerPageReq;
import com.roadjava.market.bean.req.manager.UpdatePwdReq;
import com.roadjava.market.bean.res.Result;
import com.roadjava.market.bean.vo.ManagerVO;
import com.roadjava.market.service.ManagerService;
import lombok.extern.slf4j.Slf4j;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;

/**
 * @author 乐之者java
 * @see <a href="http://www.roadjava.com">乐之者java</a>
 */
@RestController
@RequestMapping("/manager")
@Validated
@Slf4j
public class ManagerController {
    @Resource
    private ManagerService managerService;

    @PostMapping("/login")
    public Result<String> login(@RequestBody @Validated LoginReq loginReq) {
        return Result.buildSuccess(managerService.login(loginReq));
    }

    @GetMapping("/queryById")
    public Result<ManagerVO> queryById(@NotNull Long id) {
        return Result.buildSuccess(managerService.queryById(id));
    }

    @PostMapping("/modifyById")
    public Result<String> modifyById(@RequestBody @Validated ManagerUpdateReq updateReq) {
        managerService.modifyById(updateReq);
        return Result.buildSuccess("修改成功");
    }

    @PostMapping("/updatePwd")
    public Result<String> updatePwd(@RequestBody @Validated UpdatePwdReq updatePwdReq) {
        managerService.updatePwd(updatePwdReq);
        return Result.buildSuccess("密码修改成功");
    }

    @PostMapping("/add")
    public Result<String> add(@RequestBody @Validated ManagerAddReq addReq) {
        managerService.add(addReq);
        return Result.buildSuccess("新增成功");
    }

    @PostMapping("/queryPage")
    public Result<List<ManagerVO>> queryPage(@RequestBody ManagerPageReq pageReq) {
        return managerService.queryPage(pageReq);
    }

    @GetMapping("/deleteById")
    public Result<String> deleteById(@NotNull Long id) {
        managerService.deleteById(id);
        return Result.buildSuccess("删除成功");
    }
}
