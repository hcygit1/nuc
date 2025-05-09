import * as API from './../index'


export default {
  rbacResoure:params =>{
    return API.GET('/rbac/rbacResoure', params)
  } ,

  addResoure:params =>{
    return API.POST('/rbac/addResoure', params)
  } ,
  removeResoure:params =>{
    return API.POST('/rbac/removeResoure', params)
  } ,
  updateResource:params =>{
    return API.POST('/rbac/updateResource', params)
  } ,

  rbacRoles:params =>{
    return API.GET('/rbac/rbacRoles', params)
  } ,

  addRole:params =>{
    return API.POST('/rbac/addRole', params)
  } ,



  updateRole:params =>{
    return API.POST('/rbac/updateRole', params)
  } ,


  removeRole:params =>{
    return API.POST('/rbac/removeRole', params)
  } ,



  getResourceIdsByRoleId:params =>{
    return API.GET('/rbac/getResourceIdsByRoleId', params)
  } ,



  addroleResouce:params =>{
    return API.POST('/rbac/addroleResouce', params)
  } ,




  findAllUser:params =>{
    return API.GET('/user/findAllUser', params)
  } ,


  findAllUserIncludeUnEable:params =>{
    return API.GET('/user/findAllUserIncludeUnEable', params)
  } ,



  getRoleByUserId:params =>{
    return API.GET('/rbac/getRoleByUserId', params)
  } ,



  addRoleOfUser:params =>{
    return API.POST('/rbac/addRoleOfUser', params)
  } ,



}
