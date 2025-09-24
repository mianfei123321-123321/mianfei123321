<template>
  <div>
    <el-dialog title="选择商品" :model-value="checkGoodsVisible" :before-close="handleCancel">
      <section class="title-bar">
        <el-row>
          <el-col :span="22">
            <el-form :model="searchData" :inline="true" ref="searchFormRef">
              <el-form-item label="按商品名搜索" label-width="120">
                <el-input v-model="searchData.name"></el-input>
              </el-form-item>
              <el-form-item>
                <el-button type="primary" @click="loadTable">查询</el-button>
                <el-button type="primary" @click="confirmCheck">确认选择</el-button>
              </el-form-item>
            </el-form>
          </el-col>
        </el-row>
      </section>

      <section>
        <el-table :data="tableData.rows" stripe :border="true" ref="checkGoodsTableRef"
          @selection-change="handleSelectionChange" :highlight-current-row="true" style="width: 100%">
          <el-table-column type="selection" width="55">
          </el-table-column>
          <el-table-column prop="id" label="系统编号" width="180">
          </el-table-column>
          <el-table-column prop="name" label="商品名">
          </el-table-column>
          <el-table-column prop="bornPlace" label="产地">
          </el-table-column>
          <el-table-column prop="salePrice" label="销售价">
          </el-table-column>
          <el-table-column prop="stock" label="库存">
          </el-table-column>
          <el-table-column prop="unit" label="单位">
          </el-table-column>
          <el-table-column prop="specifications" label="规格">
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
    </el-dialog>
  </div>
</template>

<script setup name='CheckGoods' lang='ts'>
import { ref, reactive, onMounted } from 'vue'
import { useTable } from '@/hooks/useTable';
import { loadTableApi } from '@/api/goodsApi';
import { ElMessage } from 'element-plus'
let searchFormData = reactive({
  name: '',
})
let searchFormRef = ref()
let checkGoodsTableRef = ref()
let selectedInfo = reactive({
  selectedRow: null
})
// props
const props = defineProps<{
  checkGoodsVisible: boolean,
  forRowIndex: number
}>()
// 事件
let emitter = defineEmits(["confirmCheck", "closeDialog"])
let {
  searchData,
  tableData,
  loadTable,
  handlePageSizeChange,
  handlePageNowChange,
} = useTable({ searchFormData, loadTableApi })

function confirmCheck() {
  if (!selectedInfo.selectedRow) {
    ElMessage({ message: "请选择商品后再确认", type: "error" })
    return;
  }
  let selectedGoods = selectedInfo.selectedRow;
  emitter("confirmCheck", {
    rowIndex: props.forRowIndex,
    goodsId: selectedGoods["id"],
    goodsName: selectedGoods["name"],
    unit: selectedGoods["unit"],
    salePrice: selectedGoods["salePrice"],
    stock: selectedGoods["stock"],
  })
}
function handleSelectionChange(selectedRowsArr:any[]) {
  if (selectedRowsArr.length === 1) {
    selectedInfo.selectedRow = selectedRowsArr[0];
  } else if (selectedRowsArr.length > 1) {
    // 清空所有已选中的
    checkGoodsTableRef.value.clearSelection();
    // 只选择最后一行
    let lastSelectedRow = selectedRowsArr.pop();
    checkGoodsTableRef.value.toggleRowSelection(lastSelectedRow);
    selectedInfo.selectedRow = lastSelectedRow;
  }
}
function handleCancel() {
  searchFormRef.value.resetFields()
  emitter("closeDialog")
}
onMounted(() => {
  loadTable()
})
</script>

<style scoped></style>
