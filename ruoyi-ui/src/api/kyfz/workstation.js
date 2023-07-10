import request from '@/utils/request'

// 查询工作站列表
export function listWorkstation(query) {
  return request({
    url: '/kyfz/workstation/list',
    method: 'get',
    params: query
  })
}

// 查询工作站详细
export function getWorkstation(taskId) {
  return request({
    url: '/kyfz/workstation/' + taskId,
    method: 'get'
  })
}

// 新增工作站
export function addWorkstation(data) {
  return request({
    url: '/kyfz/workstation',
    method: 'post',
    data: data
  })
}

// 修改工作站
export function updateWorkstation(data) {
  return request({
    url: '/kyfz/workstation',
    method: 'put',
    data: data
  })
}

// 删除工作站
export function delWorkstation(taskId) {
  return request({
    url: '/kyfz/workstation/' + taskId,
    method: 'delete'
  })
}
