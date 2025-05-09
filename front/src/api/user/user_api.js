import * as API from './../index'


export default {
  updatePassWord:params =>{
    return API.POST('/user/updatePassWord', params)
  },

  update:params =>{
    return API.POST('/user/update', params)
  },
  deleted:params =>{
    return API.POST('/user/deleted', params)
  }  ,

  addUser:params =>{
    return API.POST('/user/addUser', params)
  }
}
