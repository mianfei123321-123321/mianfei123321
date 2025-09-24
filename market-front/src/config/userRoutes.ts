import type { UserRoutesDefine } from "@/types/common.d.ts"
/**
 * 用户拥有的路由信息格式
 * 获取时机:登录成功后加载,前端存入到pinia中
 * 用于:
 * 1.动态路由--tabs
 * 2.菜单的计算
 * roleId:
 * 1:超级管理员，有所有权限
 * 2: 仓库管理员
 * 3: 销售人员
 * 4: 财务人员
 */
const userRoutes: UserRoutesDefine = [
  {
    path: "basic",
    componentPath: "basic/BasicHome",
    children: [
      {
        path: "manager",
        componentPath: "basic/manager/ManagerHome",
        children: [
          {
            path: "manage",
            componentPath: "basic/manager/ManagerManage",
            meta: {
              authorityName: "人员管理",
              showInMenu: true,
              icon: "User"
            }
          }
        ]
      },
      {
        path: "supplier",
        componentPath: "basic/supplier/SupplierHome",
        children: [
          {
            path: "manage",
            componentPath: "basic/supplier/SupplierManage",
            meta: {
              authorityName: "供应商管理",
              showInMenu: true,
              icon: "FolderOpened"
            }
          }
        ]
      },
      {
        path: "shelf",
        componentPath: "shelf/ShelfHome",
        children: [
          {
            path: "manage",
            componentPath: "basic/shelf/ShelfManage",
            meta:{
              authorityName:"货架管理",
              showInMenu:true,
              icon:"ShoppingCart"
            }
          }
        ]
      },
      {
        path: "goods",
        componentPath: "basic/goods/GoodsHome",
        children: [
          {
            path: "manage",
            componentPath: "basic/goods/GoodsManage",
            meta: {
              authorityName: "商品管理",
              showInMenu: true,
              icon: "Goods"
            }
          }
        ]
      },
    ],
    meta: {
      authorityName: "基础信息",
      showInMenu: true,
      icon: "InfoFilled",
      roleIds: [1]
    }
  },
  {
    path: "warehouse",
    componentPath: "warehouse/WarehouseHome",
    children: [
      {
        path: "purchase",
        componentPath: "warehouse/purchase/PurchaseHome",
        children: [
          {
            path: "manage",
            componentPath: "warehouse/purchase/PurchaseManage",
            meta: {
              authorityName: "进货管理",
              showInMenu: true,
              icon: "DArrowRight"
            }
          },
          {
            path: "toAdd",
            componentPath: "warehouse/purchase/AddPurchase",
            meta: {
              authorityName: "新增进货",
              showInMenu: false
            }
          },
          {
            path: "toUpdate",
            componentPath: "warehouse/purchase/UpdatePurchase",
            meta: {
              authorityName: "编辑进货信息",
              showInMenu: false
            }
          }
        ]
      },
      {
        path: "returnGoods",
        componentPath: "warehouse/returngoods/ReturnGoodsHome",
        children: [
          {
            path: "manage",
            componentPath: "warehouse/returngoods/ReturnGoodsManage",
            meta: {
              authorityName: "退货管理",
              showInMenu: true,
              icon: "DArrowLeft"
            }
          }
        ]
      },
    ],
    meta: {
      authorityName: "仓储管理",
      showInMenu: true,
      icon: "Operation",
      roleIds: [1, 2]
    }
  },
  {
    path: "saleAndRefund",
    componentPath: "sale_refund/SaleAndRefundHome",
    children: [
      {
        path: "saleOrder",
        componentPath: "sale_refund/saleorder/SaleOrderHome",
        children: [
          {
            path: "manage",
            componentPath: "sale_refund/saleorder/SaleOrderManage",
            meta: {
              authorityName: "销售订单管理",
              showInMenu: true,
              icon: "ShoppingCartFull"
            }
          },
          {
            path: "toAdd",
            componentPath: "sale_refund/saleorder/AddSaleOrder",
            meta: {
              authorityName: "新增销售单",
              showInMenu: false
            }
          }
        ]
      },
      {
        path: "refund",
        componentPath: "sale_refund/refund/RefundHome",
        children: [
          {
            path: "manage",
            componentPath: "sale_refund/refund/RefundManage",
            meta: {
              authorityName: "退款管理",
              showInMenu: true,
              icon: "ChatDotRound"
            }
          }
        ]
      },
    ],
    meta: {
      authorityName: "销售与退款",
      showInMenu: true,
      icon: "Monitor",
      roleIds: [1, 3]
    }
  },
  {
    path: "audit",
    componentPath: "audit/AuditHome",
    children: [
      {
        path: "purchase",
        componentPath: "audit/AuditPurchase",
        meta: {
          authorityName: "进货审核",
          showInMenu: true,
          icon: "FolderChecked"
        }
      },
      {
        path: "returnGoods",
        componentPath: "audit/AuditRet",
        meta: {
          authorityName: "退货审核",
          showInMenu: true,
          icon: "FolderChecked"
        }
      },
    ],
    meta: {
      authorityName: "财务审核",
      showInMenu: true,
      icon: "Coordinate",
      roleIds: [1, 4]
    }
  },
  {
    path: "statistics",
    componentPath: "statistics/StatisticsHome",
    children: [
      {
        path: "purchasePrice4Goods",
        componentPath: "statistics/PurchasePrice4Goods",
        meta: {
          authorityName: "商品进价波动",
          showInMenu: true,
          icon: "DataLine"
        }
      },
      {
        path: "saleNum4Goods",
        componentPath: "statistics/SaleNum4Goods",
        meta: {
          authorityName: "商品销量",
          showInMenu: true,
          icon: "ShoppingCartFull"
        }
      },
      {
        path: "ret4Supplier",
        componentPath: "statistics/Ret4Supplier",
        meta: {
          authorityName: "供应商退货占比",
          showInMenu: true,
          icon: "PieChart"
        }
      },
      {
        path: "saleAmount4Goods",
        componentPath: "statistics/SaleAmount4Goods",
        meta: {
          authorityName: "单品销售额排行榜",
          showInMenu: true,
          icon: "Postcard"
        }
      },
    ],
    meta: {
      authorityName: "经营分析",
      showInMenu: true,
      icon: "DataAnalysis",
      roleIds: [1]
    }
  },
]

export default userRoutes
