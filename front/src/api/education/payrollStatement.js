import * as API from '../index'


export default {
  findList:params =>{
    return API.GET('/payrollStatement/findList', params)
  } ,
  findMyList:params =>{
    return API.GET('/payrollStatement/findMyList', params)
  },
  getMyAccouny:params =>{
    return API.GET('/payrollStatement/getMyAccouny', params)
  }
}
