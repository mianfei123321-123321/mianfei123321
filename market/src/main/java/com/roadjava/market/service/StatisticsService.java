package com.roadjava.market.service;


import com.roadjava.market.bean.req.statistics.ChartReq;
import com.roadjava.market.bean.res.Result;
import com.roadjava.market.bean.vo.statistics.BarVO;
import com.roadjava.market.bean.vo.statistics.LineVO;
import com.roadjava.market.bean.vo.statistics.PieVO;

/**
 * @author zhaodaowen
 * @see <a href="http://www.roadjava.com">乐之者java</a>
 */
public interface StatisticsService {

    Result<BarVO> loadSaleNum4Goods();

    Result<LineVO> loadTrend(ChartReq req);

    Result<PieVO> loadRet4Supplier();

    Result<BarVO> loadSaleAmount4Goods();
}
