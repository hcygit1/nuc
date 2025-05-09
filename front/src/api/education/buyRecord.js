import * as API from '../index'


export default {
  findList:params =>{
    return API.GET('/buyRecord/findList', params)
  } ,
  add:params =>{
    return API.GET('/buyRecord/add', params)
  } ,
  update:params =>{
    return API.POST('/buyRecord/update', params)
  },
  applyRefund:params =>{
    return API.GET('/buyRecord/applyRefund', params)
  },
  getRefundInfo:params =>{
    return API.GET('/buyRecord/getRefundInfo', params)
  },
  confirmPayFinal:params =>{
    return API.GET('/buyRecord/confirmPayFinal', params)
  },
  findAllList:params =>{
    return API.GET('/buyRecord/findAllList', params)
  } ,
  dealRefund:params =>{
    return API.GET('/buyRecord/dealRefund', params)
  } ,
}
