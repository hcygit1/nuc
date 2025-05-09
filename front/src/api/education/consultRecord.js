import * as API from '../index'


export default {
  findList:params =>{
    return API.GET('/consultRecord/findList', params)
  } ,
  add:params =>{
    return API.GET('/consultRecord/add', params)
  } ,
  remove:params =>{
    return API.GET('/consultRecord/remove', params)
  } ,
  update:params =>{
    return API.POST('/consultRecord/update', params)
  },
}
