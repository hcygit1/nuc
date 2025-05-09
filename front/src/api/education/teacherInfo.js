import * as API from '../index'


export default {
  findList:params =>{
    return API.GET('/teacherInfo/findList', params)
  } ,
  findAuditPassList:params =>{
    return API.GET('/teacherInfo/findAuditPassList', params)
  } ,
  remove:params =>{
    return API.GET('/teacherInfo/remove', params)
  } ,
  add:params =>{
    return API.POST('/teacherInfo/add', params)
  } ,
  update:params =>{
    return API.POST('/teacherInfo/update', params)
  },
  getByUserId:params =>{
    return API.POST('/teacherInfo/getByUserId', params)
  },
  audit:params =>{
    return API.GET('/teacherInfo/audit', params)
  },
}
