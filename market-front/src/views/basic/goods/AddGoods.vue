<template>
  <div>
    <el-dialog title="新增" :model-value="addDialogVisible" :before-close="handleCancel" width="30%">
      <el-form :model="formData" :rules="rules" label-width="120px" ref="addForm">
        <el-form-item label="所在货架:" prop="shelfId">
          <el-select v-model="formData.shelfId">
            <el-option v-for="(one) in formCfg.shelfList" :label="one.shelfNo" :value="one.id" :key="one.id">
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="商品名:" prop="name">
          <el-input v-model="formData.name"></el-input>
        </el-form-item>
        <el-form-item label="产地:">
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
        <el-button @click="handleCancel()">取消</el-button>
        <el-button type="primary" @click="onSubmit()">确定</el-button>
      </div>
    </el-dialog>
  </div>
</template>
<script setup name='AddGoods' lang='ts'>
import { ref, reactive, onMounted } from 'vue'
import { addApi } from '@/api/goodsApi';
import { queryAllApi } from '@/api/shelfApi';
import { ElMessage } from 'element-plus';
import { regionData } from 'element-china-area-data';

let formData = reactive({
  shelfId:'',
  name: '',
  bornPlaceCodeList: [] as string[],
  salePrice: null,
  stock: null,
  unit: '',
  specifications: '',
})
// 表单配置
let formCfg = reactive({
  shelfList: [] as any[]
})
let rules = reactive({
  shelfId: [{ required: true, message: '请选择所在货架', trigger: 'blur' }],
  name: [{ required: true, message: '请输入商品名', trigger: 'blur' }],
  bornPlaceCodeList: [{ required: false, trigger: 'blur' }],
  salePrice: [{ required: true, message: '请输入销售价,单位:元', trigger: 'blur' }],
  unit: [{ required: true, message: '请输入单位', trigger: 'blur' }],
  specifications: [{ required: true, message: '请输入规格', trigger: 'blur' }],
})
defineProps<{ addDialogVisible: boolean }>()

let emitter = defineEmits(["closeDialog", "addSuccess"])

let addForm = ref()

function onSubmit() {
  addForm.value.validate((valid: boolean) => {
    if (!valid) return
    addApi(formData).then(result => {
      ElMessage({ "message": result.data, "type": "success" })
      addForm.value.resetFields()
      // 上面reset没法情况数组
      formData.bornPlaceCodeList = []
      emitter("addSuccess")
    })
  })
}

// 取消对话框
function handleCancel() {
  addForm.value.resetFields()
  emitter("closeDialog")
}
function initShelfList() {
  queryAllApi().then(result => {
    if(result.data.length > 0) {
      formCfg.shelfList = result.data
      // 选中第1个
      formData.shelfId = formCfg.shelfList[0].id
    }
  })
}
onMounted(() => {
  initShelfList()
})
</script>
<style scoped></style>
