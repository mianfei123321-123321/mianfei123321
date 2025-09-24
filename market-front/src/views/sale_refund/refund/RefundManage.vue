<template>
  <div>
    <section>
      <el-row>
        <el-col :span="22">
          <el-form :model="searchData" :inline="true">
            <el-form-item>
              <el-input v-model="searchData.fuzzyRefundNo"
                        placeholder="按退款号模糊搜索"></el-input>
            </el-form-item>
            <el-form-item>
              <el-button @click="loadTable" type="primary">查询</el-button>
            </el-form-item>
          </el-form>
        </el-col>
      </el-row>
    </section>

    <!-- 表格   -->
    <section>
      <el-table :data="tableData.rows" stripe :border="true" style="width: 100%">
        <el-table-column type="expand">
          <template v-slot:default="props">
            <el-table
              :data="props.row.detailList"
              stripe
              :border="true"
              style="width: 100%">
              <el-table-column
                prop="id"
                label="系统编号"
                width="180">
              </el-table-column>
              <el-table-column
                prop="goodsName"
                label="商品名">
              </el-table-column>
              <el-table-column
                prop="unit"
                label="单位">
              </el-table-column>
              <el-table-column
                prop="refundPrice"
                label="退款价">
              </el-table-column>
              <el-table-column
                prop="num"
                label="数量">
              </el-table-column>
            </el-table>
          </template>
        </el-table-column>
        <el-table-column
          prop="id"
          label="系统编号"
          width="180">
        </el-table-column>
        <el-table-column
          prop="orderNo"
          label="所属订单号">
        </el-table-column>
        <el-table-column
          prop="refundNo"
          label="退款编号">
        </el-table-column>
        <el-table-column
          prop="totalAmount"
          label="总金额">
        </el-table-column>
        <el-table-column
          prop="createdTime"
          label="退款日期">
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
<script setup name='RefundManage' lang='ts'>
import { reactive, onMounted } from 'vue'
import { useTable } from '@/hooks/useTable';
import { loadTableApi } from '@/api/refundApi';

let searchFormData = reactive({
  fuzzyRefundNo: ''
})

let {
  searchData,
  tableData,
  loadTable,
  handlePageSizeChange,
  handlePageNowChange,
} = useTable({ searchFormData, loadTableApi })


onMounted(() => {
  loadTable()
})
</script>
<style scoped></style>
