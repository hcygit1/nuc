import * as API from '../index'


export default {
  findList:params =>{
    return API.GET('/nationalPolicy/findList', params)
  } ,
  findMyList:params =>{
    return API.GET('/nationalPolicy/findMyList', params)
  } ,
  add:params =>{
    return API.POST('/nationalPolicy/add', params)
  } ,
  remove:params =>{
    return API.GET('/nationalPolicy/remove', params)
  } ,
  get:params =>{
    return API.GET('/nationalPolicy/get', params)
  } ,
  update:params =>{
    return API.POST('/nationalPolicy/update', params)
  },
}
