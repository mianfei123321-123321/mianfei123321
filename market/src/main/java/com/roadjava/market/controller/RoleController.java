package com.roadjava.market.controller;

import com.roadjava.market.bean.res.Result;
import com.roadjava.market.bean.vo.RoleVO;
import com.roadjava.market.enums.RoleEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhaodaowen
 * @see <a href="https://www.roadjava.com">乐之者java</a>
 */
@Slf4j
@RestController
@RequestMapping("/role")
public class RoleController {

    /**
     * 查询角色列表
     */
    @GetMapping("/listRoles")
    public Result<List<RoleVO>> listRoles() {
        List<RoleVO> list = new ArrayList<>();
        for (RoleEnum value : RoleEnum.values()) {
            if (value.equals(RoleEnum.ADMIN)) {
                // 超级管理员不能新增
                continue;
            }
            RoleVO roleVO = new RoleVO().setRoleId(value.getRoleId()).setRoleName(value.getRoleName());
            list.add(roleVO);
        }
        return Result.buildSuccess(list);
    }
}
