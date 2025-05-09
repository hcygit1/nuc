import * as API from '../index'


export default {
  findList:params =>{
    return API.GET('/course/findList', params)
  } ,
  remove:params =>{
    return API.GET('/course/remove', params)
  } ,
  add:params =>{
    return API.POST('/course/add', params)
  } ,
  update:params =>{
    return API.POST('/course/update', params)
  },
  getByUserId:params =>{
    return API.POST('/course/getByUserId', params)
  },
  shangjia:params =>{
    return API.GET('/course/audit', params)
  },
  bohui:params =>{
    return API.GET('/course/bohui', params)
  },
  findClassifys:params =>{
    return API.GET('/course/findClassifys', params)
  },
  get:params =>{
    return API.GET('/course/get', params)
  } ,
  getSectionsByCourseId:params =>{
    return API.GET('/course/getSectionsByCourseId', params)
  } ,
  jieshou:params =>{
    return API.GET('/course/jieshou', params)
  } ,
  findAllCoursesList:params =>{
    return API.GET('/course/findAllCoursesList', params)
  } ,
  getDingjin:params =>{
    return API.GET('/course/getDingjin', params)
  } ,
  findMyCourseId:params =>{
    return API.GET('/course/findMyCourseId', params)
  } ,
  applyQingJia:params =>{
    return API.GET('/course/applyQingJia', params)
  } ,
  findAllCoursesIds:params =>{
    return API.GET('/course/findAllCoursesIds', params)
  } ,
  findAllCoursesIdsIgnoreaccept:params =>{
    return API.GET('/course/findAllCoursesIdsIgnoreaccept', params)
  } ,
  getSectionsByCourseIdByPage:params =>{
    return API.GET('/course/getSectionsByCourseIdByPage', params)
  } ,
}
