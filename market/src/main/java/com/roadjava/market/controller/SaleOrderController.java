package com.roadjava.market.controller;

import com.roadjava.market.constants.Constants;
import com.roadjava.market.util.NoUtil;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import lombok.extern.slf4j.Slf4j;
import lombok.RequiredArgsConstructor;
import com.roadjava.market.bean.req.saleorder.SaleOrderPageReq;
import com.roadjava.market.bean.res.Result;
import com.roadjava.market.bean.req.saleorder.SaleOrderAddReq;
import com.roadjava.market.bean.vo.SaleOrderVO;
import com.roadjava.market.service.SaleOrderService;

/**
 * 销售订单表Controller层
 *
 * @author 乐之者java
 * @see <a href="http://www.roadjava.com">乐之者java</a>
 */
@RestController
@RequestMapping("/saleOrder")
@RequiredArgsConstructor
@Slf4j
@Validated
public class SaleOrderController {

    private final SaleOrderService saleOrderService;

    @PostMapping("/add")
    public Result<String> add(@RequestBody @Validated SaleOrderAddReq addReq) {
        saleOrderService.add(addReq);
        return Result.buildSuccess("添加成功");
    }

    @PostMapping("/queryPage")
    public Result<List<SaleOrderVO>> queryPage(@RequestBody SaleOrderPageReq pageReq) {
        return saleOrderService.queryPage(pageReq);
    }
    /**
     * 得到单号
     */
    @GetMapping("/getNo")
    public Result<String> getNo() {
        return Result.buildSuccess(NoUtil.getNo(Constants.PRE_SALE));
    }
}
