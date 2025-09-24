<template>
  <div class="side-bar">
    <div class="collapse">
      <span @click="toggleCollapse" v-show="isCollapsed">
        <el-icon>
          <Expand />
        </el-icon>
      </span>
      <span @click="toggleCollapse" v-show="!isCollapsed">
        <el-icon>
          <Fold />
        </el-icon>
      </span>
    </div>
    <!-- 菜单列表 -->
    <el-scrollbar>
      <!-- collapse:是否水平折叠收起菜单 -->
      <el-menu @select="handleSelect" :default-active="defaultActivePath" :collapse="isCollapsed"
        :collapse-transition="false" text-color="#fff" active-text-color="#ffd04b">
        <template v-for="menu in menuList" :key="menu.path">
          <MenuItem :item="menu">
          </MenuItem>
        </template>
      </el-menu>
    </el-scrollbar>
  </div>
</template>
<script setup name='SideBar' lang='ts'>
import { reactive, ref, onMounted, watch } from 'vue'
import { useRouter, useRoute } from 'vue-router';
import MenuItem from '@/components/MenuItem.vue'
import type { UserInfoDefine, MenuItemDefine, MenuItemsDefine, UserRoutesDefine } from '@/types/common.d.ts';
import { useUserStore } from '@/store/useUserStore';

let router = useRouter()
let currentRoute = useRoute()
let userStore = useUserStore()
// isCollapsed=true:已折叠 isCollapsed=false:已展开
let isCollapsed = ref(false)
// 刷新页面/路由变更时应该激活哪个菜单项
let defaultActivePath = ref('')
// 菜单列表
let menuList = reactive<MenuItemsDefine>([])

// 监听
watch(() => currentRoute.path, (newValue, oldValue) => {
  defaultActivePath.value = newValue
}, {
  immediate: true
})

let emitter = defineEmits(["update:asideWidth"])
function toggleCollapse() {
  // 修改el-aside的宽度
  if (isCollapsed.value) { // 折叠的状态
    emitter("update:asideWidth", "200px")
  } else {
    emitter("update:asideWidth", "65px")
  }
  isCollapsed.value = !isCollapsed.value
}
// 菜单项点击,index: 选中菜单项的 index,我们配置成菜单对应的路由路径
function handleSelect(index: string) {
  router.replace(index)
}

// 计算菜单列表
function calcMenuList() {
  doCalcMenuList(userStore.userInfo.userRoutes, '/')
}
function doCalcMenuList(userRoutes: UserRoutesDefine, ancestorsPath: string, parentMenu?: MenuItemDefine) {
  for (let originalRoute of userRoutes) {
    let includeRole = false;
    if(!originalRoute.meta || !originalRoute.meta.roleIds) {//默认认为包含
      includeRole = true
    }else{
      includeRole = originalRoute.meta.roleIds.includes(userStore.userInfo.roleId)
    }
    if (!includeRole) {
      continue;
    }
    let isMenu = originalRoute.meta && originalRoute.meta.showInMenu
    let hasChildren = originalRoute.children && originalRoute.children.length > 0
    if (!ancestorsPath.endsWith("/")) {
      // 连接多级路由
      ancestorsPath += '/'
    }
    // 拼接路由的全路径
    let routePath = `${ancestorsPath}${originalRoute.path}`
    if (isMenu) {
      let menu: MenuItemDefine = {
        path: routePath,
        menuName: originalRoute.meta?.authorityName || "",
        icon: originalRoute.meta?.icon || ""
      }
      if (hasChildren) {
        doCalcMenuList(originalRoute.children as UserRoutesDefine, routePath, menu)
      }
      if (parentMenu) {
        // 有父级就放到父级的children中
        if (parentMenu.children) {
          parentMenu.children.push(menu)
        } else {
          parentMenu.children = [menu]
        }
      } else {
        menuList.push(menu)
      }
    } else {
      if (hasChildren) {
        doCalcMenuList(originalRoute.children as UserRoutesDefine, routePath, parentMenu)
      }
    }
  }
}
// 声明周期钩子
onMounted(() => {
  calcMenuList()
})
</script>
<style scoped>
.collapse {
  background-color: rgb(110, 170, 220);
  padding: 8px;
  color: #ffffff;
  text-align: center;
  cursor: pointer;
  line-height: 1.5;
}

/*避免 el-menu-item 选中时多1px*/
.el-menu {
  border-right-width: 0;
}

/*侧边栏背景*/
.side-bar ul,
.side-bar li,
:deep(.el-menu) {
  background-color: #003c7f;
}

/* 多级菜单悬浮/激活时的背景 */
:deep(.el-sub-menu__title:hover) {
  background-color: rgb(0, 48, 102) !important;
}

:deep(.el-menu-item:focus),
:deep(.el-menu-item:hover),
:deep(.el-menu-item.is-active) {
  background-color: rgb(0, 48, 102) !important;
}
</style>
