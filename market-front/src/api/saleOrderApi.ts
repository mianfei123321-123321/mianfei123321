import {doGet,appJsonPost} from '@/network/request'
/**
 * 新增
 */
export function addApi(data:object) {
  return appJsonPost({
      url: "/saleOrder/add",
      data: data
  })
}
/**
 * 分页查询
 */
export function loadTableApi(searchData:object) {
  return appJsonPost({
      url: "/saleOrder/queryPage",
      data: searchData
  })
}

/**
 * 获取编号
 */
export function getSaleOrderNoApi() {
  return doGet({
      url: "/saleOrder/getNo"
  })
}
