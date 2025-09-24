package com.roadjava.market.controller;
import com.roadjava.market.constants.Constants;
import com.roadjava.market.util.NoUtil;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import lombok.RequiredArgsConstructor;
import com.roadjava.market.bean.req.refund.RefundPageReq;
import com.roadjava.market.bean.res.Result;
import com.roadjava.market.bean.req.refund.RefundAddReq;
import com.roadjava.market.bean.vo.RefundVO;
import com.roadjava.market.service.RefundService;
/**
  * 退款表Controller层
  * @author 乐之者java
  * @see <a href="http://www.roadjava.com">乐之者java</a>
  */
@RestController
@RequestMapping("/refund")
@RequiredArgsConstructor
@Slf4j
@Validated
public class RefundController {

    private final RefundService refundService;

    @PostMapping("/add")
    public Result<String> add(@RequestBody @Validated RefundAddReq addReq) {
        refundService.add(addReq);
        return Result.buildSuccess("全部退款成功");
    }

    @PostMapping("/queryPage")
    public Result<List<RefundVO>> queryPage(@RequestBody RefundPageReq pageReq) {
        return  refundService.queryPage(pageReq);
    }
}
