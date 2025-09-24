import {doGet,appJsonPost} from '@/network/request'
/**
 * 新增
 */
export function addApi(data:object) {
  return appJsonPost({
      url: "/purchase/add",
      data: data
  })
}
/**
 * 分页查询
 */
export function loadTableApi(searchData:object) {
  return appJsonPost({
      url: "/purchase/queryPage",
      data: searchData
  })
}
/**
 * 按id删除
 */
export function deleteByIdApi(id:string) {
  return doGet({
      url: "/purchase/deleteById",
      params: {id}
  })
}

/**
 * 通过id进行查询
 */
export function queryByIdApi(id:string) {
  return doGet({
      url: "/purchase/queryById",
      params: {id}
  })
}
/**
 * 通过id进行修改
 */
export function modifyByIdApi(data:object) {
  return appJsonPost({
      url: "/purchase/modifyById",
      data: data
  })
}

/**
 * 获取编号
 */
export function getPurchaseNoApi() {
  return doGet({
      url: "/purchase/getNo"
  })
}

/**
 * 更新状态
 */
export function updateStatusApi(data:object) {
  return appJsonPost({
      url: "/purchase/updateStatus",
      data: data
  })
}
