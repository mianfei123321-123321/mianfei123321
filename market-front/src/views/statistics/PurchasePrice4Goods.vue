<template>
  <div>
    <el-row>
      <el-col :span="24">
        <el-form :model="searchFormData" :inline="true">
          <el-form-item label="商品" label-width="120">
            <el-select v-model="searchFormData.selectedGoodsIds" multiple filterable @change="updateTrend">
              <el-option v-for="(one) in formCfg.goods" :label="one.name" :value="one.id" :key="one.id">
              </el-option>
            </el-select>
          </el-form-item>
        </el-form>
      </el-col>
    </el-row>
    <el-row>
      <el-col :span="24">
        <div style="height:500px;" ref="trendRef"></div>
      </el-col>
    </el-row>
  </div>
</template>

<script setup name='PurchasePrice4Goods' lang='ts'>
import * as echarts from 'echarts';
import { ref, reactive, onMounted } from 'vue'
import { loadTrendApi } from '@/api/statisticsApi'
import { queryAllApi } from '@/api/goodsApi'

let searchFormData = reactive({
  selectedGoodsIds: [] as string[],
})
let formCfg = reactive({
  goods: [{ id: -1, name: '' }]
})

let trendChartCfg = reactive({
  trendChart: {} as any,
  trendOption: {
    title: {
      text: '商品近30天平均进价走势图',
      subtext: "统计口径:已审核通过的商品在每天的平均进价",
      padding: [10, 10, 10, 20],
      textStyle: {
        color: '#5FB878'
      },
      subtextStyle: {
        color: '#888'
      }
    },
    tooltip: {
      formatter: '{b}号{a}的进价:{c}'
    },
    grid: {
      show: true,
      top: 90 //坐标系距离整个chart顶部90px
    },
    legend: {
      data: ['x商品']
    },
    xAxis: {
      name: '日期',
      type: 'category',
      data: ['2021-12-01', '2021-12-02', '2021-12-03', '2021-12-04']
    },
    yAxis: {
      type: 'value',
      name: '进价',
      axisLine: {
        show: true // 显示y轴坐标轴线
      }
    },
    series: [{
      name: 'x商品',
      data: [820, 932, 901, 934, 1290, 1330, 1320],
      type: 'line',
      smooth: true
    }]
  }
})

let trendRef = ref()

function loadTrend() {
  trendChartCfg.trendChart = echarts.init(trendRef.value);
  // true:表示不进行合并,全部更新，老的删除
  trendChartCfg.trendChart.setOption(trendChartCfg.trendOption, true);
  updateTrend();
}
function updateTrend() {
  loadTrendApi(
    { "goodsIds": searchFormData.selectedGoodsIds }
  ).then(result => {
    trendChartCfg.trendOption.legend.data = result.data.legendData;
    trendChartCfg.trendOption.xAxis.data = result.data.xAxisData;
    trendChartCfg.trendOption.series = result.data.series;
    // 设置真实数据
    trendChartCfg.trendChart.setOption(trendChartCfg.trendOption, true);
  })
}
function initGoods() {
  queryAllApi().then(result => {
    if (!result.data) {
      return;
    }
    if (result.data.length === 0) {
      result.data = [];
    }
    formCfg.goods = result.data;
    if (result.data.length > 0) {
      // 默认选中第一个
      searchFormData.selectedGoodsIds.push(result.data[0]["id"]);
      loadTrend();
    }
  })
}

onMounted(() => {
  initGoods()
})
</script>

<style scoped></style>
