import * as API from '../index'


export default {
  findList:params =>{
    return API.GET('/enterpriseAppeal/findList', params)
  } ,
  findMyList:params =>{
    return API.GET('/enterpriseAppeal/findMyList', params)
  } ,
  add:params =>{
    return API.POST('/enterpriseAppeal/add', params)
  } ,
  remove:params =>{
    return API.GET('/enterpriseAppeal/remove', params)
  } ,
  get:params =>{
    return API.GET('/enterpriseAppeal/get', params)
  } ,
  update:params =>{
    return API.POST('/enterpriseAppeal/update', params)
  },
}
