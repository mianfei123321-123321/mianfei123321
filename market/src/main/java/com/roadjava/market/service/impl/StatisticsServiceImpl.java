package com.roadjava.market.service.impl;

import com.roadjava.market.bean.dto.BarDTO;
import com.roadjava.market.bean.req.statistics.ChartReq;
import com.roadjava.market.bean.res.Result;
import com.roadjava.market.bean.vo.statistics.BarVO;
import com.roadjava.market.bean.vo.statistics.LineVO;
import com.roadjava.market.bean.vo.statistics.PieVO;
import com.roadjava.market.mapper.StatisticsMapper;
import com.roadjava.market.service.StatisticsService;
import com.roadjava.market.util.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.MapUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 学生业务类
 *
 * @author zhaodaowen
 * @see <a href="http://www.roadjava.com">乐之者java</a>
 */
@Service
@Slf4j
public class StatisticsServiceImpl implements StatisticsService {
    @Resource
    private StatisticsMapper statisticsMapper;

    @Override
    public Result<BarVO> loadSaleNum4Goods() {
        // 近30天
        List<String> dateList = DateUtil.getDateList(30);
        ChartReq req = new ChartReq();
        req.setStartDate(dateList.get(0));
        List<BarDTO> list = statisticsMapper.loadSaleNum4Goods(req);
        BarVO vo = new BarVO();
        list.forEach(co -> {
            vo.getXAxisData().add(co.getXAxisName());
            vo.getSeriesData().add(co.getYAxisValueInt());
        });
        return Result.buildSuccess(vo);
    }

    @Override
    public Result<LineVO> loadTrend(ChartReq req) {
        if (CollectionUtils.isEmpty(req.getGoodsIds())) {
            return Result.buildSuccess(new LineVO());
        }
        // 近30天
        List<String> dateList = DateUtil.getDateList(30);
        req.setStartDate(dateList.get(0));
        List<Map<String, Object>> maps = statisticsMapper.loadTrend(req);
        List<String> goodsNames = maps.stream()
                .map(map -> map.get("goodsName").toString())
                .distinct()
                .collect(Collectors.toList());

        LineVO lineVO = new LineVO();
        lineVO.setLegendData(goodsNames);
        lineVO.setXAxisData(dateList);
        List<Map<String, Object>> series = new ArrayList<>();
        Map<String, List<Map<String, Object>>> goodsNameMap = maps.stream()
                .collect(Collectors.groupingBy(map -> map.get("goodsName").toString()));

        for (String goodsName : goodsNames) {
            Map<String, Object> oneSeries = new HashMap<>();
            oneSeries.put("name", goodsName);
            oneSeries.put("type", "line");
            oneSeries.put("smooth", true);
            // 每个商品近30天平均进价
            List<Map<String, Object>> goodsDatePriceList = goodsNameMap.get(goodsName);
            // 按日期转为map结构
            Map<String, Double> datePriceMap = goodsDatePriceList.stream()
                    .collect(Collectors.toMap(m -> m.get("purchaseDate").toString(),
                            m -> Double.valueOf(m.get("buyPrice").toString()),
                            (a, b) -> b)
                    );
            // 近30天进价
            List<Double> oneSeriesData = new ArrayList<>();
            for (String day : dateList) {
                // 如果该商品在该天没有值,则补0,不然数据就错位了
                oneSeriesData.add(datePriceMap.getOrDefault(day, 0.0d));
            }
            oneSeries.put("data", oneSeriesData);
            series.add(oneSeries);
        }
        lineVO.setSeries(series);
        return Result.buildSuccess(lineVO);
    }

    @Override
    public Result<PieVO> loadRet4Supplier() {
        // [{"供应商1":1},{"供应商2":2}]
        List<Map<String, Object>> supplierRetCount = statisticsMapper.supplierRetCount();

        int totalRetCount = supplierRetCount.stream()
                .mapToInt(map -> MapUtils.getInteger(map,"cnt"))
                .sum();

        for (Map<String, Object> map : supplierRetCount) {
            // 退货占比=该供应商退货次数/退货总数
            BigDecimal ratio = new BigDecimal(MapUtils.getString(map,"cnt"))
                    .divide(new BigDecimal(totalRetCount),
                            2, RoundingMode.HALF_UP);
            map.put("value", ratio.multiply(new BigDecimal(100)));
        }
        PieVO vo = new PieVO();
        vo.setSeriesData(supplierRetCount);
        return Result.buildSuccess(vo);
    }

    @Override
    public Result<BarVO> loadSaleAmount4Goods() {
        // 近30天
        List<String> dateList = DateUtil.getDateList(30);
        ChartReq req = new ChartReq();
        req.setStartDate(dateList.get(0));
        List<BarDTO> list = statisticsMapper.loadSaleAmount4Goods(req);
        BarVO vo = new BarVO();
        list.forEach(co -> {
            vo.getXAxisData().add(co.getXAxisName());
            vo.getSeriesData().add(co.getYAxisValueDouble());
        });
        return Result.buildSuccess(vo);
    }
}
