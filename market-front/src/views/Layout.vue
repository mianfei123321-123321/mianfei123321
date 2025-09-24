<template>
  <div class="layout">
    <el-container class="outer-container">
      <el-header>
        <nav-header></nav-header>
      </el-header>
      <el-container>
        <el-aside :width="asideWidth">
          <side-bar @update:asideWidth="updateAsideWidth"></side-bar>
        </el-aside>
        <el-main>
          <tabs></tabs>
          <!-- empty-values指定空值是什么,默认['', null, undefined],空值无法选中 -->
          <el-config-provider :value-on-clear="null" :empty-values="[undefined, null]">
            <router-view :key="currentRoute.fullPath"></router-view>
          </el-config-provider>
        </el-main>
      </el-container>
    </el-container>
    
  </div>
</template>
<script setup name='Layout' lang='ts'>
   import NavHeader from '@/components/NavHeader.vue';
   import SideBar from '@/components/SideBar.vue';
   import Tabs from '@/components/Tabs.vue';
   import {ref} from 'vue'
   import { useRoute } from 'vue-router';
   let currentRoute = useRoute()
   let asideWidth = ref('200px')

   // 更新aside的宽度
   function updateAsideWidth(width:string) {
    asideWidth.value = width
   }
</script>
<style scoped>
  .el-header {
    background-color: #0a366b;
    color: #fff;
    padding-left: 0;
  }

  /*让aside高度充满屏幕+App.vue的高度也要设置成100%*/
  .layout,.outer-container {
    height: 100%;
  }
  .el-aside{
    background-color: #003c7f;
  }

  .el-main {
    padding: 10px;
  }
</style>