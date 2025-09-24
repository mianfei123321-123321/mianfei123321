import {appJsonPost} from '@/network/request'
/**
 * 新增
 */
export function addApi(data:object) {
  return appJsonPost({
      url: "/refund/add",
      data: data
  })
}
/**
 * 分页查询
 */
export function loadTableApi(searchData:object) {
  return appJsonPost({
      url: "/refund/queryPage",
      data: searchData
  })
}
