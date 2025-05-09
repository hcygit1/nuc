import * as API from '../index'


export default {
  findList:params =>{
    return API.GET('/enterpriseCourse/findList', params)
  } ,
  findMyList:params =>{
    return API.GET('/enterpriseCourse/findMyList', params)
  } ,
  add:params =>{
    return API.POST('/enterpriseCourse/add', params)
  } ,
  remove:params =>{
    return API.GET('/enterpriseCourse/remove', params)
  } ,
  enterpriseProgressList:params =>{
    return API.GET('/enterpriseCourse/enterpriseProgressList', params)
  } ,
  get:params =>{
    return API.GET('/enterpriseCourse/get', params)
  } ,
  update:params =>{
    return API.POST('/enterpriseCourse/update', params)
  },
}
