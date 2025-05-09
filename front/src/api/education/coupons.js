import * as API from '../index'


export default {
  findList:params =>{
    return API.GET('/coupons/findList', params)
  } ,
  add:params =>{
    return API.POST('/coupons/add', params)
  } ,
  update:params =>{
    return API.POST('/coupons/update', params)
  },
  remove:params =>{
    return API.GET('/coupons/remove', params)
  },
  getCouponIds:params =>{
    return API.GET('/coupons/getCouponIds', params)
  },
}
