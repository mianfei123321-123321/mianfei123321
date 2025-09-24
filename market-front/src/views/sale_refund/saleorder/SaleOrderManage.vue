<template>
  <div>
    <section>
      <el-row>
        <el-col :span="2">
          <el-button @click="toAdd" type="primary" :icon="Plus">
            新增
          </el-button>
        </el-col>
        <el-col :span="22">
          <el-form :model="searchData" :inline="true">
            <el-form-item>
              <el-input type="text" placeholder="按订单号模糊搜索" v-model="searchData.orderNo" clearable
                style="width: 150px"></el-input>
            </el-form-item>
            <el-form-item>
              <el-button @click="loadTable" type="primary">查询</el-button>
              <el-button type="primary" @click="doRefund">全部退款</el-button>
            </el-form-item>
          </el-form>
        </el-col>
      </el-row>
    </section>

    <!-- 表格   -->
    <section>
      <el-table :data="tableData.rows" stripe :border="true" @selection-change="handleSelectionChange"
        style="width: 100%">
        <el-table-column type="selection" width="55">
        </el-table-column>
        <el-table-column type="expand">
          <template v-slot:default="props">
            <el-table :data="props.row.detailList" stripe :border="true" style="width: 100%">
              <el-table-column prop="id" label="系统编号" width="180">
              </el-table-column>
              <el-table-column prop="goodsName" label="商品名">
              </el-table-column>
              <el-table-column prop="unit" label="单位">
              </el-table-column>
              <el-table-column prop="salePrice" label="销售价格">
              </el-table-column>
              <el-table-column prop="num" label="数量">
              </el-table-column>
            </el-table>
          </template>
        </el-table-column>
        <el-table-column prop="id" label="系统编号" width="180">
        </el-table-column>
        <el-table-column prop="orderNo" label="订单号">
        </el-table-column>
        <el-table-column prop="totalAmount" label="总金额">
        </el-table-column>
        <el-table-column prop="statusDesc" label="状态">
          <template v-slot:default="scope">
            <!-- 已完成 -->
            <el-tag type="success" v-if="scope.row.status===0">{{ scope.row.statusDesc }}</el-tag>
            <!-- 已退款 -->
            <el-tag type="info" v-if="scope.row.status===1">{{ scope.row.statusDesc }}</el-tag>
          </template>
        </el-table-column>
        <el-table-column prop="createdTime" label="订单创建时间">
        </el-table-column>
      </el-table>
    </section>
    <!-- 分页   -->
    <section>
      <el-pagination @size-change="handlePageSizeChange" @current-change="handlePageNowChange"
        :current-page="searchData.pageNow" :page-sizes="[5, 10, 15, 20]" :page-size="searchData.pageSize"
        layout="total, sizes, prev, pager, next, jumper" :total="tableData.total">
      </el-pagination>
    </section>
  </div>
</template>
<script setup name='SaleOrderManage' lang='ts'>
import { reactive, onMounted } from 'vue'
import { Plus } from '@element-plus/icons-vue';
import { useTable } from '@/hooks/useTable';
import { loadTableApi } from '@/api/saleOrderApi';
import { addApi } from '@/api/refundApi';
import { useRouter } from 'vue-router';
import { ElMessage } from 'element-plus';

let searchFormData = reactive({
  orderNo: ''
})
let selectedInfo = reactive({
  selectedRows: [] as any[]
})
let {
  searchData,
  tableData,
  loadTable,
  handlePageSizeChange,
  handlePageNowChange,
} = useTable({ searchFormData, loadTableApi })
// 获取router
let router = useRouter()
// 打开新增页面
function toAdd() {
  router.replace('/saleAndRefund/saleOrder/toAdd')
}
// 表格选中事件处理
function handleSelectionChange(selectedRowsArr: any[]) {
  selectedInfo.selectedRows = selectedRowsArr;
}
// 全部退货
function doRefund() {
  let orderIdList: string[] = [];
  selectedInfo.selectedRows.forEach((crtValue, idx) => {
    orderIdList.push(crtValue["id"]);
  });
  if (orderIdList.length === 0) {
    ElMessage({ "message": "请先选择销售单", "type": "error" })
    return;
  }
  addApi({ orderIdList }).then(result => {
    ElMessage({ "message": result.data, "type": "success" })
    // 因为销售单状态变化为已退货了,所以需要重新加载表格
    loadTable()
  })
}
onMounted(() => {
  loadTable()
})
</script>
<style scoped></style>
