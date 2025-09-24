<template>
  <div>
    <section>
      <el-row>
        <el-col :span="2">
          <el-button @click="addDialogVisible = true" type="primary" :icon="Plus">
            新增
          </el-button>
        </el-col>
        <el-col :span="22">
          <el-form :model="searchData" :inline="true">
            <el-form-item>
              <el-input type="text" placeholder="货架编号" v-model="searchData.shelfNo" clearable
                style="width: 150px"></el-input>
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
        <el-table-column prop="id" label="主键">
        </el-table-column>
        <el-table-column prop="shelfNo" label="货架编号">
        </el-table-column>
        <el-table-column label="操作">
          <template v-slot:default="scope">
            <el-button type="primary" :icon="Edit" @click="openUpdateDialog(scope.row.id)">编辑
            </el-button>
            <el-button type="danger" :icon="Delete" @click="deleteById(scope.row.id)">删除
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

    <!-- 新增对话框 -->
    <AddShelf :addDialogVisible="addDialogVisible" @closeDialog="addDialogVisible = false"
      @addSuccess="handleAddSuccess">
    </AddShelf>
    <!-- 更新对话框 -->
    <UpdateShelf :updateDialogVisible="updateDialogVisible" @closeDialog="updateDialogVisible = false"
      :idToUpdate="idToUpdate" @updateSuccess="handleUpdateSuccess"></UpdateShelf>
  </div>
</template>
<script setup name='ShelfManage' lang='ts'>
import { ref, reactive, onMounted } from 'vue'
import { Plus, Edit, Delete } from '@element-plus/icons-vue';
import AddShelf from '@/views/basic/shelf/AddShelf.vue';
import UpdateShelf from '@/views/basic/shelf/UpdateShelf.vue';
import { useTable } from '@/hooks/useTable';
import { loadTableApi, deleteByIdApi } from '@/api/shelfApi';

// 添加对话框是否显示
let addDialogVisible = ref(false)
let updateDialogVisible = ref(false)
let idToUpdate = ref('')
let searchFormData = reactive({
  shelfNo: '',
})

let {
  searchData,
  tableData,
  loadTable,
  deleteById,
  handlePageSizeChange,
  handlePageNowChange,
} = useTable({ searchFormData, loadTableApi, deleteByIdApi })

// 打开更新对话框
function openUpdateDialog(id: string) {
  idToUpdate.value = id
  updateDialogVisible.value = true
}

function handleAddSuccess() {
  addDialogVisible.value = false
  loadTable()
}

function handleUpdateSuccess() {
  updateDialogVisible.value = false
  loadTable()
}

onMounted(() => {
  loadTable()
})
</script>
<style scoped></style>
