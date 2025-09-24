package com.roadjava.market.controller;
import com.roadjava.market.bean.req.goods.GoodsUpdateReq;
import com.roadjava.market.bean.req.purchase.PurchaseUpdateStatusReq;
import com.roadjava.market.constants.Constants;
import com.roadjava.market.util.NoUtil;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import lombok.RequiredArgsConstructor;
import com.roadjava.market.bean.req.purchase.PurchasePageReq;
import com.roadjava.market.bean.res.Result;
import com.roadjava.market.bean.req.purchase.PurchaseAddReq;
import com.roadjava.market.bean.req.purchase.PurchaseUpdateReq;
import com.roadjava.market.bean.vo.PurchaseVO;
import com.roadjava.market.service.PurchaseService;
/**
  * 进货表Controller层
  * @author 乐之者java
  * @see <a href="http://www.roadjava.com">乐之者java</a>
  */
@RestController
@RequestMapping("/purchase")
@RequiredArgsConstructor
@Slf4j
@Validated
public class PurchaseController {

    private final PurchaseService purchaseService;

    @PostMapping("/add")
    public Result<String> add(@RequestBody @Validated PurchaseAddReq addReq) {
        purchaseService.add(addReq);
        return Result.buildSuccess("添加成功");
    }

    @PostMapping("/queryPage")
    public Result<List<PurchaseVO>> queryPage(@RequestBody PurchasePageReq pageReq) {
        return  purchaseService.queryPage(pageReq);
    }

    @GetMapping("/deleteById")
    public Result<String> deleteById(@RequestParam("id") Long id) {
        purchaseService.deleteById(id);
        return Result.buildSuccess("删除成功");
    }

    @GetMapping("/queryById")
    public Result<PurchaseVO> queryById(@RequestParam("id") Long id) {
        return Result.buildSuccess(purchaseService.queryById(id));
    }

    @PostMapping("/modifyById")
    public Result<String> modifyById(@RequestBody @Validated PurchaseUpdateReq updateReq) {
        purchaseService.modifyById(updateReq);
        return Result.buildSuccess("更新成功");
    }

    /**
     * 更新
     */
    @PostMapping("/updateStatus")
    public Result<String> updateStatus(@RequestBody @Validated PurchaseUpdateStatusReq req) {
        purchaseService.updateStatus(req);
        return Result.buildSuccess("更新成功");
    }

    /**
     * 得到单号
     */
    @GetMapping("/getNo")
    public Result<String> getNo() {
        return Result.buildSuccess(NoUtil.getNo(Constants.PRE_PURCHASE));
    }
}
