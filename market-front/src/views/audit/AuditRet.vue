<template>
  <div>
    <section class="title-bar">
      <el-row>
        <el-col :span="22">
          <el-form :model="searchData" :inline="true">
            <el-form-item label-width="120">
              <el-input v-model="searchData.fuzzyRetNo" placeholder="按退货号模糊搜索"></el-input>
            </el-form-item>
            <el-form-item>
              <el-button type="primary" @click="loadTable">查询</el-button>
            </el-form-item>
          </el-form>
        </el-col>
      </el-row>

    </section>
    <section>
      <el-table :data="tableData.rows" stripe :border="true" style="width: 100%">
        <el-table-column type="expand">
          <template v-slot:default="props">
            <el-table :data="props.row.detailList" stripe :border="true" style="width: 100%">
              <el-table-column prop="id" label="系统编号" width="180">
              </el-table-column>
              <el-table-column prop="goodsName" label="商品名">
              </el-table-column>
              <el-table-column prop="unit" label="单位">
              </el-table-column>
              <el-table-column prop="returnPrice" label="退货价">
              </el-table-column>
              <el-table-column prop="num" label="数量">
              </el-table-column>
              <el-table-column prop="supplierName" label="供应商">
              </el-table-column>
            </el-table>
          </template>
        </el-table-column>
        <el-table-column prop="id" label="系统编号" width="180">
        </el-table-column>
        <el-table-column prop="purchaseNo" label="所属进货编号">
        </el-table-column>
        <el-table-column prop="retNo" label="退货编号">
        </el-table-column>
        <el-table-column prop="operatorName" label="经办人">
        </el-table-column>
        <el-table-column label="状态">
          <template v-slot:default="scope">
            <!-- 待审核 -->
            <el-tag type="warning" v-if="scope.row.status===0">{{ scope.row.statusDesc }}</el-tag>
            <!-- 审核通过 -->
            <el-tag type="success" v-if="scope.row.status===1">{{ scope.row.statusDesc }}</el-tag>
            <!-- 审核拒绝 -->
            <el-tag type="danger" v-if="scope.row.status===2">{{ scope.row.statusDesc }}</el-tag>
          </template>
        </el-table-column>
        <el-table-column prop="returnDate" label="退货日期">
        </el-table-column>

        <el-table-column label="操作">
          <template v-slot:default="scope">
            <el-button type="success" icon="Select" @click="handleAgree(scope.row)">通过
            </el-button>
            <el-button type="danger" icon="Close" @click="handleReject(scope.row)">拒绝
            </el-button>
          </template>
        </el-table-column>
      </el-table>
    </section>

    <section class="page">
      <el-pagination @size-change="handlePageSizeChange" @current-change="handlePageNowChange"
        :current-page="searchData.pageNow" :page-sizes="[5, 10, 15, 20]" :page-size="searchData.pageSize"
        layout="total, sizes, prev, pager, next, jumper" :total="tableData.total">
      </el-pagination>
    </section>
  </div>
</template>

<script setup name='AuditReturnGoods' lang='ts'>
import { ref, reactive, onMounted } from 'vue'
import { useTable } from '@/hooks/useTable';
import { loadTableApi, updateStatusApi } from '@/api/returnGoodsApi';
let searchFormData = reactive({
  fuzzyRetNo: '',
  status: 0
})


let {
  searchData,
  tableData,
  loadTable,
  handlePageSizeChange,
  handlePageNowChange,
} = useTable({ searchFormData, loadTableApi })

function handleAgree(rowData: any) {
  updateStatusApi({ "id": rowData["id"], "status": 1 }).then(result => {
    loadTable();
  });
}
function handleReject(rowData: any) {
  updateStatusApi({ "id": rowData["id"], "status": 2 }).then(result => {
    loadTable();
  });
}

onMounted(() => {
  loadTable()
})
</script>

<style scoped></style>
