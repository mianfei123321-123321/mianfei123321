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
              <el-input type="text" placeholder="用户名" v-model="searchData.username" clearable
                style="width: 150px"></el-input>
            </el-form-item>
            <el-form-item>
              <el-input type="text" placeholder="姓名" v-model="searchData.realName" clearable
                style="width: 150px"></el-input>
            </el-form-item>
            <el-form-item label="性别:">
              <el-select v-model="searchData.sex" style="width: 150px">
                <el-option v-for="one in formCfg.sexList" :key="one.sex" :label="one.sexName"
                  :value="one.sex"></el-option>
              </el-select>
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
        <el-table-column prop="id" label="系统编号">
        </el-table-column>
        <el-table-column prop="username" label="用户名">
        </el-table-column>
        <el-table-column prop="realName" label="姓名">
        </el-table-column>
        <el-table-column prop="sex" label="性别">
          <template v-slot:default="scope">
            <span v-if="scope.row.sex === 'M'">男</span>
            <span v-if="scope.row.sex === 'F'">女</span>
          </template>
        </el-table-column>
        <el-table-column
          prop="roleName"
          label="角色">
        </el-table-column>
        <el-table-column prop="avatarPath" label="头像">
          <!-- preview-teleported避免预览的图片被表格遮挡 -->
          <template v-slot:default="scope">
            <el-image v-if="scope.row.avatarPath" style="width: 50px;height: 50px;"
              :src="toAddressable(scope.row.avatarPath)" :preview-src-list="[toAddressable(scope.row.avatarPath)]"
              :preview-teleported="true" fit="fill"></el-image>
          </template>
        </el-table-column>
        <el-table-column label="操作" width="450px">
          <template v-slot:default="scope">
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
    <AddManager :addDialogVisible="addDialogVisible" @closeDialog="addDialogVisible = false"
      @addSuccess="handleAddSuccess">
    </AddManager>
  </div>
</template>
<script setup name='ManagerManage' lang='ts'>
import { ref, reactive, onMounted } from 'vue'
import { Plus, Delete } from '@element-plus/icons-vue';
import AddManager from '@/views/basic/manager/AddManager.vue';
import { useTable } from '@/hooks/useTable';
import { loadTableApi, deleteByIdApi } from '@/api/managerApi';
import { useResource } from '@/hooks/useResource';

// 添加对话框是否显示
let addDialogVisible = ref(false)
let searchFormData = reactive({
  username: '',
  realName: '',
  sex: '',
})

let formCfg = reactive({
  sexList: [
    { sex: '', sexName: "全部" },
    { sex: 'M', sexName: "男" },
    { sex: 'F', sexName: "女" },
  ]
})

let {
  searchData,
  tableData,
  loadTable,
  deleteById,
  handlePageSizeChange,
  handlePageNowChange,
} = useTable({ searchFormData, loadTableApi, deleteByIdApi })
let { toAddressable } = useResource()

function handleAddSuccess() {
  addDialogVisible.value = false
  loadTable()
}

onMounted(() => { loadTable() })
</script>
<style scoped></style>
