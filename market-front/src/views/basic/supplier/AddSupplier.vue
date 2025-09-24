<template>
    <div>
        <el-dialog title="新增" :model-value="addDialogVisible" :before-close="handleCancel" width="30%">
            <el-form :model="formData" :rules="rules" label-width="120px" ref="addForm">
                                    <el-form-item label="供应商名:" prop="name">
                        <el-input v-model="formData.name"></el-input>
                    </el-form-item>
            </el-form>
            <div slot="footer" class="dialog-footer">
                <el-button @click="handleCancel()">取消</el-button>
                <el-button type="primary" @click="onSubmit()">确定</el-button>
            </div>
        </el-dialog>
    </div>
</template>
<script setup name='AddSupplier' lang='ts'>
    import {ref, reactive} from 'vue'
    import {addApi} from '@/api/supplierApi';
    import {ElMessage} from 'element-plus';

    let formData = reactive({
        name: '',
    })
    let rules = reactive({
        name:[{required: true, message: '请输入供应商名', trigger: 'blur'}],
    })
    defineProps < {addDialogVisible: boolean} > ()

    let emitter = defineEmits(["closeDialog", "addSuccess"])

    let addForm = ref()

    function onSubmit() {
        addForm.value.validate((valid: boolean) => {
            if (!valid) return
            addApi(formData).then(result => {
                ElMessage({"message": result.data, "type": "success"})
                addForm.value.resetFields()
                emitter("addSuccess")
            })
        })
    }

    // 取消对话框
    function handleCancel() {
        addForm.value.resetFields()
        emitter("closeDialog")
    }
</script>
<style scoped></style>