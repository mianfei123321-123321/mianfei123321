package com.roadjava.market.mapper;

import com.roadjava.market.bean.dto.BarDTO;
import com.roadjava.market.bean.req.statistics.ChartReq;

import java.util.List;
import java.util.Map;

public interface StatisticsMapper {
    List<Map<String, Object>> loadTrend(ChartReq req);

    List<BarDTO> loadSaleNum4Goods(ChartReq req);

    List<BarDTO> loadSaleAmount4Goods(ChartReq req);

    List<Map<String, Object>> supplierRetCount();
}
