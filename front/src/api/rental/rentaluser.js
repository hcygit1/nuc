import * as API from '../index'


export default {
  findAllUser:params =>{
    return API.GET('/weapp/login/findAllUser', params)
  } ,
  add:params =>{
    return API.POST('/weapp/login/add', params)
  } ,
  remove:params =>{
    return API.GET('/weapp/login/remove', params)
  } ,
  get:params =>{
    return API.GET('/weapp/login/get', params)
  } ,
  update:params =>{
    return API.POST('/weapp/login/update', params)
  },
}
