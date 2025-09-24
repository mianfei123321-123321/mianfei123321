import {doGet} from '@/network/request'

/**
 * 查询全部
 */
export function listRolesApi() {
  return doGet({
      url: "/role/listRoles"
  })
}
