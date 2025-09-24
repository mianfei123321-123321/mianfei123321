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
              <el-input type="text" placeholder="商品名" v-model="searchData.name" clearable
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
        <el-table-column prop="shelfNo" label="所在货架">
        </el-table-column>
        <el-table-column prop="name" label="商品名">
        </el-table-column>
        <el-table-column label="产地">
          <template v-slot:default="scope">
            {{ codeList2Text(scope.row.bornPlaceCodeList) }}
          </template>
        </el-table-column>
        <el-table-column prop="salePrice" label="销售价(元)">
        </el-table-column>
        <el-table-column prop="stock" label="库存">
        </el-table-column>
        <el-table-column prop="unit" label="单位">
        </el-table-column>
        <el-table-column prop="specifications" label="规格">
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
    <AddGoods :addDialogVisible="addDialogVisible" @closeDialog="addDialogVisible = false"
      @addSuccess="handleAddSuccess">
    </AddGoods>
    <!-- 更新对话框 -->
    <UpdateGoods :updateDialogVisible="updateDialogVisible" @closeDialog="updateDialogVisible = false"
      :idToUpdate="idToUpdate" @updateSuccess="handleUpdateSuccess"></UpdateGoods>
  </div>
</template>
<script setup name='GoodsManage' lang='ts'>
import { ref, reactive, onMounted } from 'vue'
import { Plus, Edit, Delete } from '@element-plus/icons-vue';
import AddGoods from '@/views/basic/goods/AddGoods.vue';
import UpdateGoods from '@/views/basic/goods/UpdateGoods.vue';
import { useTable } from '@/hooks/useTable';
import { loadTableApi, deleteByIdApi } from '@/api/goodsApi';
import { codeToText } from 'element-china-area-data';

// 添加对话框是否显示
let addDialogVisible = ref(false)
let updateDialogVisible = ref(false)
let idToUpdate = ref('')
let searchFormData = reactive({
  name: '',
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
function codeList2Text(codeList?: string[]) {
  if(!codeList) {
    return
  }
  // code转为文字
  return codeList.map((value, index) => {
      return codeToText[value];
    }).join("/");
}
onMounted(() => {
  loadTable()
})
</script>
<style scoped></style>
