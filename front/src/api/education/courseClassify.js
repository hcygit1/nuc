import * as API from '../index'


export default {
  findList:params =>{
    return API.GET('/courseClassify/findList', params)
  } ,
  add:params =>{
    return API.POST('/courseClassify/add', params)
  } ,
  update:params =>{
    return API.POST('/courseClassify/update', params)
  },
  remove:params =>{
    return API.GET('/courseClassify/remove', params)
  },
}
