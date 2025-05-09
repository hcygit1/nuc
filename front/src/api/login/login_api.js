import * as API from './../index'


export default {
  login:params =>{
    return API.POST('/login', params)
  } ,

  loginOut:params =>{
    return API.POST('/loginOut', params)
  } ,
  currentUserName:params =>{
    return API.POST('/currentUserName', params)
  }
}
