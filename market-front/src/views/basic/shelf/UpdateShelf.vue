<template>
  <div>
    <el-dialog title="更新" :model-value="updateDialogVisible" @open="handleOpen" width="30%"
      :before-close="handleCancel">
      <el-form :model="formData" :rules="rules" label-width="120px" ref="updateForm">
        <el-form-item label="主键:" prop="id">
          <el-input v-model="formData.id" disabled></el-input>
        </el-form-item>
        <el-form-item label="货架编号:" prop="shelfNo">
          <el-input v-model="formData.shelfNo"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="handleCancel">取消</el-button>
        <el-button type="primary" @click="onSubmit">确定</el-button>
      </div>
    </el-dialog>
  </div>
</template>
<script setup name='UpdateShelf' lang='ts'>
import { reactive, ref } from "vue"
import { queryByIdApi, modifyByIdApi } from "@/api/shelfApi"
import { ElMessage } from "element-plus";
// data
let formData = reactive({
  id: '',
  shelfNo: '',
})
let rules = reactive({
  shelfNo: [{ required: true, message: '请输入货架编号', trigger: 'blur' }],
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
      emitter("updateSuccess")
    })
  })
}
//取消
function handleCancel() {
  updateForm.value.resetFields()
  emitter("closeDialog")
}
// 打开对话框回调
function handleOpen() {
  queryByIdApi(props.idToUpdate)
    .then(result => {
      Object.assign(formData, result.data)
    })
}
</script>
<style scoped></style>
