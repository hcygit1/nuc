import * as API from '../index'


export default {
  findList:params =>{
    return API.GET('/maintenanceRecord/findList', params)
  } ,
  add:params =>{
    return API.POST('/maintenanceRecord/add', params)
  } ,
  remove:params =>{
    return API.GET('/maintenanceRecord/remove', params)
  } ,
  get:params =>{
    return API.GET('/maintenanceRecord/get', params)
  } ,
  update:params =>{
    return API.POST('/maintenanceRecord/update', params)
  },
}
