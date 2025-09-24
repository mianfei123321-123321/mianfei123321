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
              <el-input type="text" placeholder="按进货号模糊搜索" v-model="searchData.purchaseNo" clearable
                style="width: 150px"></el-input>
            </el-form-item>
            <el-form-item>
              <el-button @click="loadTable" type="primary">查询</el-button>
              <el-button type="primary" @click="doRetGoods">全部退货</el-button>
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
              <el-table-column prop="buyPrice" label="采购价">
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
        <el-table-column prop="purchaseNo" label="进货编号">
        </el-table-column>
        <el-table-column prop="operatorName" label="经办人">
        </el-table-column>
        <el-table-column prop="statusDesc" label="状态">
          <template v-slot:default="scope">
            <!-- 待审核 -->
            <el-tag type="warning" v-if="scope.row.status===0">{{ scope.row.statusDesc }}</el-tag>
            <!-- 审核通过 -->
            <el-tag type="success" v-if="scope.row.status===1">{{ scope.row.statusDesc }}</el-tag>
            <!-- 审核拒绝 -->
            <el-tag type="danger" v-if="scope.row.status===2">{{ scope.row.statusDesc }}</el-tag>
            <!-- 已退货 -->
            <el-tag type="info" v-if="scope.row.status===3">{{ scope.row.statusDesc }}</el-tag>
          </template>
        </el-table-column>
        <el-table-column prop="purchaseDate" label="进货日期">
        </el-table-column>
        <el-table-column label="操作">
          <template v-slot:default="scope">
            <el-button type="primary" icon="Edit" @click="toUpdate(scope.row.id)">编辑
            </el-button>
            <el-button type="danger" icon="Delete" @click="deleteById(scope.row.id)">删除
            </el-button>
          </template>
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
<script setup name='PurchaseManage' lang='ts'>
import { reactive, onMounted } from 'vue'
import { Plus } from '@element-plus/icons-vue';
import { useTable } from '@/hooks/useTable';
import { loadTableApi, deleteByIdApi } from '@/api/purchaseApi';
import { addApi } from '@/api/returnGoodsApi';
import { useRouter } from 'vue-router';
import { ElMessage } from 'element-plus';

let searchFormData = reactive({
  purchaseNo: ''
})

let selectedInfo = reactive({
  selectedRows: [] as any[]
})

let {
  searchData,
  tableData,
  loadTable,
  deleteById,
  handlePageSizeChange,
  handlePageNowChange,
} = useTable({ searchFormData, loadTableApi, deleteByIdApi })
// 获取router
let router = useRouter()
// 打开编辑页面
function toUpdate(id: string) {
  router.replace({
    path: '/warehouse/purchase/toUpdate',
    query: { idToUpdate: id }
  })
}
// 打开新增页面
function toAdd() {
  router.replace('/warehouse/purchase/toAdd')
}

// 表格选中事件处理
function handleSelectionChange(selectedRowsArr: any[]) {
  selectedInfo.selectedRows = selectedRowsArr;
}
// 全部退货
function doRetGoods() {
  let purchaseIdList: string[] = [];
  selectedInfo.selectedRows.forEach((crtValue, idx) => {
    purchaseIdList.push(crtValue["id"]);
  });
  if (purchaseIdList.length === 0) {
    ElMessage({ "message": "请先选择进货单", "type": "error" })
    return;
  }
  addApi({ purchaseIdList }).then(result => {
    ElMessage({ "message": result.data, "type": "success" })
  })
}

onMounted(() => {
  loadTable()
})
</script>
<style scoped></style>
