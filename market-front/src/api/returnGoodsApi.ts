import {doGet,appJsonPost} from '@/network/request'
/**
 * 新增
 */
export function addApi(data:object) {
  return appJsonPost({
      url: "/returnGoods/add",
      data: data
  })
}
/**
 * 分页查询
 */
export function loadTableApi(searchData:object) {
  return appJsonPost({
      url: "/returnGoods/queryPage",
      data: searchData
  })
}
/**
 * 按id删除
 */
export function deleteByIdApi(id:string) {
  return doGet({
      url: "/returnGoods/deleteById",
      params: {id}
  })
}

/**
 * 更新状态
 */
export function updateStatusApi(data:object) {
  return appJsonPost({
      url: "/returnGoods/updateStatus",
      data: data
  })
}
