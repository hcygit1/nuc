import * as API from '../index'


export default {
  findList:params =>{
    return API.GET('/evaluateFeekBack/findList', params)
  } ,
  add:params =>{
    return API.POST('/evaluateFeekBack/add', params)
  } ,
  remove:params =>{
    return API.GET('/evaluateFeekBack/remove', params)
  } ,
  get:params =>{
    return API.GET('/evaluateFeekBack/get', params)
  } ,
  update:params =>{
    return API.POST('/evaluateFeekBack/update', params)
  },
}
