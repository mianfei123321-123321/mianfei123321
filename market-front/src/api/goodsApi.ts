import {doGet,appJsonPost} from '@/network/request'
/**
 * 新增
 */
export function addApi(data:object) {
  return appJsonPost({
      url: "/goods/add",
      data: data
  })
}
/**
 * 分页查询
 */
export function loadTableApi(searchData:object) {
  return appJsonPost({
      url: "/goods/queryPage",
      data: searchData
  })
}
/**
 * 按id删除
 */
export function deleteByIdApi(id:string) {
  return doGet({
      url: "/goods/deleteById",
      params: {id}
  })
}

/**
 * 通过id进行查询
 */
export function queryByIdApi(id:string) {
  return doGet({
      url: "/goods/queryById",
      params: {id}
  })
}
/**
 * 通过id进行修改
 */
export function modifyByIdApi(data:object) {
  return appJsonPost({
      url: "/goods/modifyById",
      data: data
  })
}

/**
 * 查询全部
 */
export function queryAllApi() {
  return doGet({
      url: "/goods/queryAll"
  })
}