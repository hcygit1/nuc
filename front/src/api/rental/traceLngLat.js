import * as API from '../index'


export default {
  findList:params =>{
    return API.GET('/traceLngLat/findList', params)
  } ,
  add:params =>{
    return API.POST('/traceLngLat/add', params)
  } ,
  remove:params =>{
    return API.GET('/traceLngLat/remove', params)
  } ,
  get:params =>{
    return API.GET('/traceLngLat/get', params)
  } ,
  update:params =>{
    return API.POST('/traceLngLat/update', params)
  },
}
