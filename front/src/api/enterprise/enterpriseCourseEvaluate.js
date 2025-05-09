import * as API from '../index'


export default {
  findList:params =>{
    return API.GET('/enterpriseCourseEvaluate/findList', params)
  } ,
  findMyList:params =>{
    return API.GET('/enterpriseCourseEvaluate/findMyList', params)
  } ,
  add:params =>{
    return API.POST('/enterpriseCourseEvaluate/add', params)
  } ,
  remove:params =>{
    return API.GET('/enterpriseCourseEvaluate/remove', params)
  } ,
  get:params =>{
    return API.GET('/enterpriseCourseEvaluate/get', params)
  } ,
  update:params =>{
    return API.POST('/enterpriseCourseEvaluate/update', params)
  },
}
