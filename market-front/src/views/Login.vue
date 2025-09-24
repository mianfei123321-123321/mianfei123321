<template>
  <div class="login">
    <el-row justify="center" align="middle" style="height: 100vh;">
      <el-col :span="5">
        <el-form :model="formData" :rules="rules" size="large" label-position="top" ref="loginForm">
            <el-form-item label="用户名:" label-width="80px" prop="username">
              <el-input v-model="formData.username" type="text" placeholder="请输入用户名" clearable>
                <template v-slot:prepend><el-button :icon="UserFilled"></el-button></template>
              </el-input>
            </el-form-item>
            <el-form-item label="密码:" label-width="80px" prop="pwd">
              <el-input v-model="formData.pwd" type="password" placeholder="请输入密码" show-password clearable>
                <template v-slot:prepend><el-button :icon="Lock"></el-button></template>
              </el-input>
            </el-form-item>
            <el-form-item>
              <div class="btn-group">
                  <el-button type="success" @click="submitForm">登录</el-button>
                  <el-button type="primary" @click="resetForm">重置</el-button>
              </div>
            </el-form-item>
        </el-form>
      </el-col>
    </el-row>
  </div>
</template>
<script setup name='Login' lang='ts'>
import { ref,reactive } from "vue";
import {UserFilled,Lock} from "@element-plus/icons-vue"
import {loginApi} from '@/api/managerApi'
import {useUserStore} from '@/store/useUserStore'
import {useRouter} from "vue-router"
import userRoutes from '@/config/userRoutes'
let formData = reactive({
  username:'',
  pwd:''
})
let loginForm = ref()
let userStore = useUserStore()
let router = useRouter()
// 校验规则
let rules = reactive({
  username: [
    {
      required: true,
      message: '请输入用户名',
      trigger: 'blur'
    }
  ],
  pwd: [
    {
      required: true,
      message: '请输入密码',
      trigger: 'blur'
    }
  ],
})

// 提交表单
function submitForm() {
  loginForm.value.validate((valid:boolean) => {
    if(!valid) return
    loginApi(formData).then(result => {
      // 获取后端返回值
      let token = result.data
      let userInfo = extractUserInfoFromToken(token)
      // 设置用户的动态路由信息
      userInfo.userRoutes = userRoutes
      // 调用store进行存储
      userStore.storeUserInfo(userInfo)
      userStore.storeToken(token)
      // 跳转到首页
      router.replace("/")
    })
  })
}

function extractUserInfoFromToken(token:string) {
  // header.payload.sign
  let payload = atob(token.split(".")[1])
  // {"TokenDTO":"{\"avatarPath\":\"resources/20240420/7e9ca0250e424df9b57f3bd7f6cb977d.jpg\",\"id\":1,\"username\":\"user1\"}","exp":1716194334}
  let payloadObj = JSON.parse(payload)
  return JSON.parse(payloadObj["TokenDTO"])
}
//重置表单
function resetForm() {
  loginForm.value.resetFields()
}
</script>
<style scoped>
.login {
  background: url("../assets/images/login_bg.png") no-repeat;
  background-size: 100% 100%;
}

:deep(.el-form-item__label) {
  color: #ffffff !important;
}

.btn-group {
  flex: 1;
  display: flex;
  justify-content: space-around;
}
</style>