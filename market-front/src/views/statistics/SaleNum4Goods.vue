<template>
  <div>
    <el-row>
      <el-col :span="24">
        <div style="height:500px;" ref="chartsRef"></div>
      </el-col>
    </el-row>
  </div>
</template>

<script setup name='SaleNum4Goods' lang='ts'>
import * as echarts from 'echarts';
import { ref, reactive, onMounted } from 'vue'
import { loadSaleNum4GoodsApi } from '@/api/statisticsApi'
let trendChartCfg = reactive({
  rankChart: {} as any,
  rankOption: {
    title: {
      text: '商品近30天内销量Top10排行榜',
      subtext: "统计口径:每个商品在每天的销量累加后排序",
      padding: [10, 10, 10, 20],
      textStyle: {
        color: '#5FB878'
      },
      subtextStyle: {
        color: '#888'
      }
    },
    tooltip: {
      // {a}:系列名 {b}:x轴的值  {c}:y轴的值
      formatter: '{b}的销量:{c}'
    },
    grid: {
      show: true,
      top: 90 //坐标系距离整个chart顶部90px
    },
    legend: {
      show: false
    },
    xAxis: {
      name: '商品名',
      data: ['a', 'b']
    },
    yAxis: {
      type: 'value',
      name: '销量',
      axisLine: {
        show: true // 显示y轴坐标轴线
      }
    },
    series: [{
      name: 'series-rank',
      type: 'bar',
      data: [1, 2],
      // 设置柱条宽度,避免过少时很宽;过多时重叠
      barMaxWidth: 40
    }]
  } as any
})
let chartsRef = ref()
function loadRankChart() {
  trendChartCfg.rankChart = echarts.init(chartsRef.value);
  // 使用配置项和数据显示图表
  trendChartCfg.rankChart.setOption(trendChartCfg.rankOption);
  updateRank();
}
function updateRank() {
  loadSaleNum4GoodsApi().then(result => {
    trendChartCfg.rankOption.xAxis.data = result.data.xAxisData;
    trendChartCfg.rankOption.series = [{
      name: 'series-rank',
      data: result.data.seriesData
    }];
    // 设置真实数据
    trendChartCfg.rankChart.setOption(trendChartCfg.rankOption);
  })
}

onMounted(() => loadRankChart())
</script>

<style scoped></style>
