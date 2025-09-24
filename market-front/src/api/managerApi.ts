import {doGet,appJsonPost} from '@/network/request'

/**
 * 登录
 * @param data 
 */
export function loginApi(data:object) {
  return appJsonPost({
    url:'/manager/login',
    data
  })
}


/**
 * 修改密码
 */
export function modifyPwdApi(data:object) {
  return appJsonPost({
      url: "/manager/updatePwd",
      data: data
  })
}

/**
 * 通过id进行查询
 */
export function queryByIdApi(id:string) {
  return doGet({
      url: "/manager/queryById",
      params: {id}
  })
}
/**
 * 通过id进行修改
 */
export function modifyByIdApi(data:object) {
  return appJsonPost({
      url: "/manager/modifyById",
      data: data
  })
}
/**
 * 新增
 */
export function addApi(data:object) {
    return appJsonPost({
        url: "/manager/add",
        data: data
    })
}
/**
 * 分页查询
 */
export function loadTableApi(data:object) {
    return appJsonPost({
        url: "/manager/queryPage",
        data: data
    })
}
/**
 * 按id删除
 */
export function deleteByIdApi(id:string) {
    return doGet({
        url: "/manager/deleteById",
        params: {id}
    })
}