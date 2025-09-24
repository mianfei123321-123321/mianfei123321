package com.roadjava.market.controller;

import com.roadjava.market.bean.res.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 返回应用的健康状态
 * @author zhaodaowen
 * @see <a href="http://www.roadjava.com">乐之者java</a>
 */
@RestController
public class HealthController {

    @GetMapping("/")
    public Result<String> health(){
        return Result.buildSuccess("UP");
    }
}
