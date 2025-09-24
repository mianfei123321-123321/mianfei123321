<template>
  <div>
    <el-dialog title="更新" :model-value="updateDialogVisible" @open="handleOpen" width="30%"
      :before-close="handleCancel">
      <el-form :model="formData" :rules="rules" label-width="120px" ref="updateForm">
        <el-form-item label="主键:" prop="id">
          <el-input v-model="formData.id" disabled></el-input>
        </el-form-item>
        <el-form-item label="所在货架:" prop="shelfId">
          <el-select v-model="formData.shelfId">
            <el-option v-for="(one) in formCfg.shelfList" :label="one.shelfNo" :value="one.id" :key="one.id">
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="商品名:" prop="name">
          <el-input v-model="formData.name"></el-input>
        </el-form-item>
        <el-form-item label="产地:" prop="bornPlace">
          <el-cascader :options="regionData" v-model="formData.bornPlaceCodeList">
          </el-cascader>
        </el-form-item>
        <el-form-item label="销售价(元):" prop="salePrice">
          <el-input-number v-model="formData.salePrice" :precision="2" :step="1" :min="1"></el-input-number>
        </el-form-item>
        <el-form-item label="单位:" prop="unit">
          <el-input v-model="formData.unit"></el-input>
        </el-form-item>
        <el-form-item label="规格:" prop="specifications">
          <el-input v-model="formData.specifications"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="handleCancel">取消</el-button>
        <el-button type="primary" @click="onSubmit">确定</el-button>
      </div>
    </el-dialog>
  </div>
</template>
<script setup name='UpdateGoods' lang='ts'>
import { reactive, ref } from "vue"
import { queryByIdApi, modifyByIdApi } from "@/api/goodsApi"
import { queryAllApi } from '@/api/shelfApi';
import { ElMessage } from "element-plus";
import { regionData } from 'element-china-area-data';
// data
let formData = reactive({
  id: '',
  shelfId:'',
  name: '',
  bornPlaceCodeList: [] as string[],
  bornPlace: '',
  salePrice: null,
  unit: '',
  specifications: '',
})
// 表单配置
let formCfg = reactive({
  shelfList: [] as any[]
})
let rules = reactive({
  name: [{ required: true, message: '请输入商品名', trigger: 'blur' }],
  bornPlace: [{ required: false, trigger: 'blur' }],
  salePrice: [{ required: true, message: '请输入销售价,单位:元', trigger: 'blur' }],
  stock: [{ required: true, message: '请输入库存', trigger: 'blur' }],
  unit: [{ required: true, message: '请输入单位', trigger: 'blur' }],
  specifications: [{ required: true, message: '请输入规格', trigger: 'blur' }],
})
let updateForm = ref()
// props
const props = defineProps<{
  updateDialogVisible: boolean,
  idToUpdate: string
}>()
let emitter = defineEmits(["updateSuccess", "closeDialog"])

// 方法
function onSubmit() {
  updateForm.value.validate((valid: boolean) => {
    if (!valid) return
    modifyByIdApi(formData).then(result => {
      ElMessage({ message: result.data, type: "success" })
      updateForm.value.resetFields()
      // 上面reset没法情况数组
      formData.bornPlaceCodeList = []
      emitter("updateSuccess")
    })
  })
}
//取消
function handleCancel() {
  updateForm.value.resetFields()
  emitter("closeDialog")
}
function initShelfList() {
  queryAllApi().then(result => {
    if(result.data.length > 0) {
      formCfg.shelfList = result.data
    }
  })
}
// 打开对话框回调
function handleOpen() {
  initShelfList()
  queryByIdApi(props.idToUpdate)
    .then(result => {
      Object.assign(formData, result.data)
    })
}
</script>
<style scoped></style>
