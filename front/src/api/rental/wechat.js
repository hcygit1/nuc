import * as API from '../index'


export default {
  findWechatRcordAndHis:params =>{
    return API.GET('/enterpriseProjectChatRecord/findWechatRcordAndHis', params)
  } ,
  findSysUnReadMessage:params =>{
    return API.GET('/enterpriseProjectChatRecord/findSysUnReadMessage', params)
  } ,
  add:params =>{
    return API.POST('/enterpriseProjectChatRecord/add', params)
  } ,
  remove:params =>{
    return API.GET('/enterpriseProjectChatRecord/remove', params)
  } ,
  get:params =>{
    return API.GET('/enterpriseProjectChatRecord/get', params)
  } ,
  update:params =>{
    return API.POST('/enterpriseProjectChatRecord/update', params)
  },
}
