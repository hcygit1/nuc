import * as API from '../index'


export default {
  findList:params =>{
    return API.GET('/enterpriseFinancingProject/findList', params)
  } ,
  findOrgList:params =>{
    return API.GET('/enterpriseFinancingProject/findOrgList', params)
  } ,
  findMyList:params =>{
    return API.GET('/enterpriseFinancingProject/findMyList', params)
  } ,
  add:params =>{
    return API.POST('/enterpriseFinancingProject/add', params)
  } ,
  remove:params =>{
    return API.GET('/enterpriseFinancingProject/remove', params)
  } ,
  get:params =>{
    return API.GET('/enterpriseFinancingProject/get', params)
  } ,
  update:params =>{
    return API.POST('/enterpriseFinancingProject/update', params)
  },
  getOtherUserInfoByProjectId:params =>{
    return API.GET('/enterpriseFinancingProject/getOtherUserInfoByProjectId', params)
  },
}
