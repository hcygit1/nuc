import * as API from '../index'


export default {
  findList:params =>{
    return API.GET('/enterpriseProjectChatRecord/findList', params)
  } ,
  findMyUnReadMessage:params =>{
    return API.GET('/enterpriseProjectChatRecord/findMyUnReadMessage', params)
  } ,
  add:params =>{
    return API.POST('/enterpriseProjectChatRecord/add', params)
  } ,
  update:params =>{
    return API.POST('/enterpriseProjectChatRecord/update', params)
  },
}
