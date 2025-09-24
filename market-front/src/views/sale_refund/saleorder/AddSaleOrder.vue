<template>
  <div>
    <el-form :model="formData" :rules="rules" label-width="120px" ref="addForm">
      <el-form-item label="销售单号:">
        <el-input v-model="formData.orderNo" disabled></el-input>
      </el-form-item>
      <el-form-item label="订单创建时间:">
        <el-input v-model="formData.createdTime" disabled></el-input>
      </el-form-item>
      <el-form-item label="总金额:">
        <el-input v-model="totalAmount" disabled></el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="addRow">添加一行</el-button>
        <el-button type="primary" @click="onSubmit()">提交销售单</el-button>
      </el-form-item>
      <el-table :data="formData.detailList" stripe :border="true" style="width: 100%">
        <el-table-column label="商品名" width="180">
          <template v-slot:default="scope">
            <el-input v-model="scope.row.goodsName" @click.native="openCheckGoods(scope.$index)"
              :readonly="true"></el-input>
          </template>
        </el-table-column>
        <el-table-column label="库存" width="180">
          <template v-slot:default="scope">
            <el-input v-model="scope.row.stock" disabled></el-input>
          </template>
        </el-table-column>
        <el-table-column label="单位" width="180">
          <template v-slot:default="scope">
            <el-input v-model="scope.row.unit" disabled></el-input>
          </template>
        </el-table-column>
        <el-table-column label="销售价">
          <template v-slot:default="scope">
            <el-input v-model="scope.row.salePrice" disabled></el-input>
          </template>
        </el-table-column>
        <el-table-column label="销售数量">
          <template v-slot:default="scope">
            <el-input-number v-model="scope.row.num" :precision="0" :step="1" :min="1"></el-input-number>
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
<script setup name='AddSaleOrder' lang='ts'>
import { ref, reactive, onMounted, computed } from 'vue'
import { addApi, getSaleOrderNoApi } from '@/api/saleOrderApi';
import { ElMessage } from 'element-plus';
import CheckGoods from '@/components/CheckGoods.vue'
import { now } from '@/util/dateUtil'

let checkGoodsVisible = ref(false)
// 通过点击表格的第几行打开的选择商品对话框
let forRowIndex = ref(-1)

let formData = reactive({
  orderNo: '',
  createdTime: '',
  totalAmount: '0' as string,
  detailList: [] as any[]
})

let rules = reactive({})
// 计算总金额
let totalAmount = computed<string>(() => {
  let total = 0;
  if (formData.detailList) {
    formData.detailList.forEach((crtValue, index) => {
      total += crtValue["salePrice"] * crtValue["num"];
    })
  }
  // 保留2位小数
  return total.toFixed(2);
})
let addForm = ref()
// 添加一行
function addRow() {
  formData.detailList.push({
    goodsId: '',
    goodsName: '',
    stock: '',
    unit: '',
    salePrice: '',
    num: 1
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
  formData.detailList[idx].stock = selectedGoods["stock"];
  formData.detailList[idx].unit = selectedGoods["unit"];
  formData.detailList[idx].salePrice = selectedGoods["salePrice"];
  // 关闭对话框
  checkGoodsVisible.value = false;
}

function initOrderNo() {
  getSaleOrderNoApi().then(result => {
    formData.orderNo = result.data;
  });
}

function initCreatedTime() {
  formData.createdTime = now();
}

function onSubmit() {
  addForm.value.validate((valid: boolean) => {
    if (!valid) return
    formData.totalAmount = totalAmount.value;
    addApi(formData).then(result => {
      ElMessage({ "message": result.data, "type": "success" })
      addForm.value.resetFields()
      // 重置
      resetFormData()
    })
  })
}

function resetFormData() {
  initOrderNo()
  initCreatedTime()
  formData.detailList = []
}

onMounted(() => {
  initOrderNo()
  initCreatedTime()
})
</script>
<style scoped></style>
