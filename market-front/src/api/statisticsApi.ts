import {doGet,appJsonPost} from '@/network/request'
/**
 * 商品近30天内销量Top10排行榜
 */
export function loadSaleNum4GoodsApi() {
  return appJsonPost({
      url: "/statistics/loadSaleNum4Goods"
  })
}

/**
 * 商品近30天内销售总金额Top10排行榜
 */
export function loadSaleAmount4GoodsApi() {
  return appJsonPost({
      url: "/statistics/loadSaleAmount4Goods"
  })
}

/**
 * 商品近30天平均进价走势图
 */
export function loadTrendApi(data:object) {
  return appJsonPost({
      url: "/statistics/loadTrend",
      data: data
  })
}

/**
 * 供应商退货占比
 */
export function loadRet4SupplierApi() {
  return appJsonPost({
      url: "/statistics/loadRet4Supplier"
  })
}
