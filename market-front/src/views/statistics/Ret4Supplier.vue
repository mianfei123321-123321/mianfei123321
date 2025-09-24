<template>
  <div>
    <el-row>
      <el-col :span="24">
        <div style="height:500px;" ref="chartsRef"></div>
      </el-col>
    </el-row>
  </div>
</template>

<script setup name='Ret4Supplier' lang='ts'>
import * as echarts from 'echarts';
import { ref, reactive, onMounted } from 'vue'
import { loadRet4SupplierApi } from '@/api/statisticsApi'
let trendChartCfg = reactive({
  pieChart: {} as any,
  pieOption: {
    title: {
      text: '供应商退货占比统计',
      subtext: '统计口径:该供应商被退货次数/供应商退货总次数',
      left: 'center'
    },
    tooltip: {
      trigger: 'item',
      formatter: '{b}占比:{c}%'
    },
    legend: {
      orient: 'vertical',
      left: 'left',
    },
    series: [
      {
        name: '供应商退货占比统计',
        type: 'pie',
        radius: '50%',
        data: [
          { value: 1048, name: '搜索引擎' },
          { value: 735, name: '直接访问' },
          { value: 580, name: '邮件营销' },
          { value: 484, name: '联盟广告' },
          { value: 300, name: '视频广告' }
        ],
        emphasis: {
          itemStyle: {
            shadowBlur: 10,
            shadowOffsetX: 0,
            shadowColor: 'rgba(0, 0, 0, 0.5)'
          }
        }
      }
    ]
  } as any
})
let chartsRef = ref()
function loadChart() {
  trendChartCfg.pieChart = echarts.init(chartsRef.value);
  // 使用配置项和数据显示图表
  trendChartCfg.pieChart.setOption(trendChartCfg.pieOption);
  updateChart();
}
function updateChart() {
  loadRet4SupplierApi().then(result => {
    trendChartCfg.pieOption.series = {
      name: '供应商退货占比统计',
      data: result.data.seriesData
    }
    // 设置真实数据
    trendChartCfg.pieChart.setOption(trendChartCfg.pieOption);
  })
}

onMounted(() => {
  loadChart()
})
</script>

<style scoped></style>
