import * as API from '../index'


export default {
  findList:params =>{
    return API.GET('/enterpriseAppealDealRecord/findList', params)
  } ,
  add:params =>{
    return API.POST('/enterpriseAppealDealRecord/add', params)
  } ,
  update:params =>{
    return API.POST('/enterpriseAppealDealRecord/update', params)
  },
}
