package com.roadjava.market.controller;

import com.roadjava.market.bean.req.goods.GoodsUpdateReq;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import lombok.extern.slf4j.Slf4j;
import lombok.RequiredArgsConstructor;
import com.roadjava.market.bean.req.goods.GoodsPageReq;
import com.roadjava.market.bean.res.Result;
import com.roadjava.market.bean.req.goods.GoodsAddReq;
import com.roadjava.market.bean.vo.GoodsVO;
import com.roadjava.market.service.GoodsService;

/**
 * 商品表Controller层
 *
 * @author 乐之者java
 * @see <a href="http://www.roadjava.com">乐之者java</a>
 */
@RestController
@RequestMapping("/goods")
@RequiredArgsConstructor
@Slf4j
@Validated
public class GoodsController {

    private final GoodsService goodsService;

    @PostMapping("/add")
    public Result<String> add(@RequestBody @Validated GoodsAddReq addReq) {
        goodsService.add(addReq);
        return Result.buildSuccess("添加成功");
    }

    @PostMapping("/queryPage")
    public Result<List<GoodsVO>> queryPage(@RequestBody GoodsPageReq pageReq) {
        return goodsService.queryPage(pageReq);
    }

    @GetMapping("/deleteById")
    public Result<String> deleteById(@RequestParam("id") Long id) {
        goodsService.deleteById(id);
        return Result.buildSuccess("删除成功");
    }
    /**
     * 通过主键查询一个实体
     */
    @GetMapping("/queryById")
    public Result<GoodsVO> queryById(@RequestParam("id") Long id) {
        return Result.buildSuccess(goodsService.queryById(id));
    }

    @PostMapping("/modifyById")
    public Result<String> modifyById(@RequestBody @Validated GoodsUpdateReq updateReq) {
        goodsService.modifyById(updateReq);
        return Result.buildSuccess("更新成功");
    }
    @GetMapping("/queryAll")
    public Result<List<GoodsVO>> queryAll() {
        return Result.buildSuccess(goodsService.queryAll());
    }
}
