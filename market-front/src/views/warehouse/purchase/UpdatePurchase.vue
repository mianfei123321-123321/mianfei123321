<template>
  <div>
    <el-form :model="formData" :rules="rules" label-width="120px" ref="updateForm">
      <el-form-item label="主键:">
        <el-input v-model="formData.id" disabled></el-input>
      </el-form-item>
      <el-form-item label="进货单号:">
        <el-input v-model="formData.purchaseNo" disabled></el-input>
      </el-form-item>
      <el-form-item label="经办人:">
        <el-input v-model="formData.operatorName" disabled></el-input>
      </el-form-item>
      <el-form-item label="采购日期:">
        <el-input v-model="formData.purchaseDate" disabled></el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="addRow">添加一行</el-button>
        <el-button type="primary" @click="onSubmit()">提交采购单</el-button>
      </el-form-item>
      <el-table :data="formData.detailList" stripe :border="true" style="width: 100%">
        <el-table-column label="商品名" width="180">
          <template v-slot:default="scope">
            <el-input v-model="scope.row.goodsName" @click.native="openCheckGoods(scope.$index)"
              :readonly="true"></el-input>
          </template>
        </el-table-column>
        <el-table-column label="售价(元)" width="180">
          <template v-slot:default="scope">
            <el-input v-model="scope.row.salePrice" disabled></el-input>
          </template>
        </el-table-column>
        <el-table-column label="单位" width="180">
          <template v-slot:default="scope">
            <el-input v-model="scope.row.unit" disabled></el-input>
          </template>
        </el-table-column>
        <el-table-column label="采购价(元)">
          <template v-slot:default="scope">
            <el-input-number v-model="scope.row.buyPrice" :precision="2" :step="1" :min="0"></el-input-number>
          </template>
        </el-table-column>
        <el-table-column label="采购数量">
          <template v-slot:default="scope">
            <el-input-number v-model="scope.row.num" :precision="0" :step="1" :min="1"></el-input-number>
          </template>
        </el-table-column>
        <el-table-column label="供应商">
          <template v-slot:default="scope">
            <el-select v-model="scope.row.supplierId">
              <el-option v-for="(one) in formCfg.supplierList" :label="one.name" :value="one.id" :key="one.id">
              </el-option>
            </el-select>
          </template>
        </el-table-column>
      </el-table>
    </el-form>

        <!-- 选择商品对话框 -->
        <check-goods :checkGoodsVisible="checkGoodsVisible" :forRowIndex="forRowIndex"
      @closeDialog="checkGoodsVisible = false" @confirmCheck="handleConfirmCheck">
    </check-goods>
  </div>
</template>
<script setup name='UpdatePurchase' lang='ts'>
import { ref, reactive, onMounted,toRefs } from 'vue'
import { queryByIdApi, modifyByIdApi, getPurchaseNoApi } from '@/api/purchaseApi';
import { queryAllApi } from '@/api/supplierApi';
import { ElMessage } from 'element-plus';
import CheckGoods from '@/components/CheckGoods.vue'
import { useRoute } from 'vue-router';

let checkGoodsVisible = ref(false)
// 通过点击表格的第几行打开的选择商品对话框
let forRowIndex = ref(-1)
// data
let formData = reactive({
  id: '',
  purchaseNo: '',
  operatorId: '',
  operatorName: '',
  purchaseDate: null,
  detailList: [] as any[]
})

let formCfg = reactive({
  supplierList: [] as any[]
})

let rules = reactive({})

let route = useRoute()
const {query} = toRefs(route)
let updateForm = ref()
// 添加一行
function addRow() {
  formData.detailList.push({
    goodsId: '',
    goodsName: '',
    salePrice: '',
    unit: '',
    // el-input-number需要指定数字
    buyPrice: 0.00,
    num: 1,
    supplierId: '',
    supplierName: ''
  })
}

function openCheckGoods(index: number) {
  checkGoodsVisible.value = true;
  forRowIndex.value = index;
}

function handleConfirmCheck(selectedGoods: any) {
  let idx = selectedGoods["rowIndex"];
  formData.detailList[idx].goodsId = selectedGoods["goodsId"];
  formData.detailList[idx].goodsName = selectedGoods["goodsName"];
  formData.detailList[idx].salePrice = selectedGoods["salePrice"];
  formData.detailList[idx].unit = selectedGoods["unit"];
  // 关闭对话框
  checkGoodsVisible.value = false;
}

function initSupplierList() {
  queryAllApi().then(result => {
    formCfg.supplierList = result.data;
  });
}

// 方法
function onSubmit() {
  updateForm.value.validate((valid: boolean) => {
    if (!valid) return
    modifyByIdApi(formData).then(result => {
      ElMessage({ message: result.data, type: "success" })
      updateForm.value.resetFields()
    })
  })
}

onMounted(() => {
  initSupplierList()
  queryByIdApi(query.value.idToUpdate as string)
    .then(result => {
      Object.assign(formData, result.data)
    })
})
</script>
<style scoped></style>
