import * as API from '../index'


export default {
  findList:params =>{
    return API.GET('/enterpriseInvestmentOrganization/findList', params)
  } ,
  findSuggestOrg:params =>{
    return API.GET('/enterpriseInvestmentOrganization/findSuggestOrg', params)
  } ,
  findMyList:params =>{
    return API.GET('/enterpriseInvestmentOrganization/findMyList', params)
  } ,
  add:params =>{
    return API.POST('/enterpriseInvestmentOrganization/add', params)
  } ,
  remove:params =>{
    return API.GET('/enterpriseInvestmentOrganization/remove', params)
  } ,
  get:params =>{
    return API.GET('/enterpriseInvestmentOrganization/get', params)
  } ,
  update:params =>{
    return API.POST('/enterpriseInvestmentOrganization/update', params)
  },
}
