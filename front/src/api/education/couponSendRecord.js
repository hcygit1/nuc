import * as API from '../index'


export default {
  getLastUnCancelCoupon:params =>{
    return API.GET('/couponSendRecord/getLastUnCancelCoupon', params)
  } ,
  findList:params =>{
    return API.GET('/couponSendRecord/findList', params)
  } ,
  findMyList:params =>{
    return API.GET('/couponSendRecord/findMyList', params)
  } ,
  add:params =>{
    return API.POST('/couponSendRecord/add', params)
  } ,
  remove:params =>{
    return API.GET('/couponSendRecord/remove', params)
  } ,
  update:params =>{
    return API.POST('/couponSendRecord/update', params)
  },
}
