import * as API from '../index'


export default {
  get:params =>{
    return API.GET('/enterpriseCourseStudyProgress/get', params)
  } ,
  add:params =>{
    return API.POST('/enterpriseCourseStudyProgress/add', params)
  },
}
