package com.roadjava.market.controller;


import com.roadjava.market.bean.req.statistics.ChartReq;
import com.roadjava.market.bean.res.Result;
import com.roadjava.market.bean.vo.statistics.BarVO;
import com.roadjava.market.bean.vo.statistics.LineVO;
import com.roadjava.market.bean.vo.statistics.PieVO;
import com.roadjava.market.service.StatisticsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 统计控制器
 * @author zhaodaowen
 * @see <a href="http://www.roadjava.com">乐之者java</a>
 */
@RestController
@RequestMapping("/statistics")
@Slf4j
public class StatisticsController {
    @Resource
    private StatisticsService statisticsService;

    /**
     * 商品近30天内销量Top10排行榜
     */
    @PostMapping("/loadSaleNum4Goods")
    public Result<BarVO> loadSaleNum4Goods(){
        return statisticsService.loadSaleNum4Goods();
    }

    /**
     * 商品近30天内销售总金额Top10排行榜
     */
    @PostMapping("/loadSaleAmount4Goods")
    public Result<BarVO> loadSaleAmount4Goods(){
        return statisticsService.loadSaleAmount4Goods();
    }

    /**
     * 商品近30天平均进价走势图
     */
    @PostMapping("/loadTrend")
    public Result<LineVO> loadTrend(@RequestBody ChartReq req){
        return statisticsService.loadTrend(req);
    }

    /**
     * 供应商退货占比
     */
    @PostMapping("/loadRet4Supplier")
    public Result<PieVO> loadRet4Supplier(){
        return statisticsService.loadRet4Supplier();
    }
}
