import * as API from '../index'


export default {
  findList:params =>{
    return API.GET('/evaluateFeekBack/findList', params)
  } ,
  findMyList:params =>{
    return API.GET('/evaluateFeekBack/findMyList', params)
  } ,
  add:params =>{
    return API.POST('/evaluateFeekBack/add', params)
  } ,
  remove:params =>{
    return API.GET('/evaluateFeekBack/remove', params)
  } ,
  update:params =>{
    return API.POST('/evaluateFeekBack/update', params)
  },
}
