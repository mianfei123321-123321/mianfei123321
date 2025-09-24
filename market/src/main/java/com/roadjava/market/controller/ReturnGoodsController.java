package com.roadjava.market.controller;
import com.roadjava.market.bean.req.returngoods.RetUpdateStatusReq;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import lombok.RequiredArgsConstructor;
import com.roadjava.market.bean.req.returngoods.ReturnGoodsPageReq;
import com.roadjava.market.bean.res.Result;
import com.roadjava.market.bean.req.returngoods.ReturnGoodsAddReq;
import com.roadjava.market.bean.vo.ReturnGoodsVO;
import com.roadjava.market.service.ReturnGoodsService;
/**
  * 退货表Controller层
  * @author 乐之者java
  * @see <a href="http://www.roadjava.com">乐之者java</a>
  */
@RestController
@RequestMapping("/returnGoods")
@RequiredArgsConstructor
@Slf4j
@Validated
public class ReturnGoodsController {

    private final ReturnGoodsService returnGoodsService;

    @PostMapping("/add")
    public Result<String> add(@RequestBody @Validated ReturnGoodsAddReq addReq) {
        returnGoodsService.add(addReq);
        return Result.buildSuccess("全部退货成功");
    }

    @PostMapping("/queryPage")
    public Result<List<ReturnGoodsVO>> queryPage(@RequestBody ReturnGoodsPageReq pageReq) {
        return  returnGoodsService.queryPage(pageReq);
    }

    @GetMapping("/deleteById")
    public Result<String> deleteById(@RequestParam("id") Long id) {
        returnGoodsService.deleteById(id);
        return Result.buildSuccess("删除成功");
    }

    /**
     * 更新
     */
    @PostMapping("/updateStatus")
    public Result<String> updateStatus(@RequestBody @Validated RetUpdateStatusReq req) {
        returnGoodsService.updateStatus(req);
        return Result.buildSuccess("更新成功");
    }
}
