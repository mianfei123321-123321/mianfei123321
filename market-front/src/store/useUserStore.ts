import {defineStore} from 'pinia'
import type {UserInfoDefine} from "@/types/common"
import { getItem } from '@/util/storageUtil'
import { STORAGE_USER_INFO_KEY, STORAGE_USER_TOKEN_KEY } from '@/config/constants'
import pinia from '@/store/index'

// 命名一般为hook的方式(useXxx)
export const useUserStore = defineStore("user",{
  // 定义动作的地方
  actions:{
    updateUserRoutesAttached(attached:boolean) {
      this.userRoutesAttached = attached
    },
    storeUserInfo(userInfo:UserInfoDefine){
      this.userInfo = userInfo
    },
    storeToken(token:string){
      this.token = token
    },
    resetAll(){
      // 把state重置为初始状态
      this.$reset()
    }
  },
  // 存储数据的地方
  state(){
    return {
      userInfo: getItem(STORAGE_USER_INFO_KEY) ? JSON.parse(getItem(STORAGE_USER_INFO_KEY) as string) : {
        id: '-1',
        username:'',
        avatarPath:'',
        rodeId:-1,
        userRoutes: []
      } as UserInfoDefine,
      token: getItem(STORAGE_USER_TOKEN_KEY) ? getItem(STORAGE_USER_TOKEN_KEY) : '',
      // 是否已添加动态路由
      userRoutesAttached: false,
    }
  }
})

// 在非.vue组件中使用store,需要传入pinia实例
export function useUserStoreExternal() {
  return useUserStore(pinia)
}
