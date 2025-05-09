import * as API from '../index'


export default {
  getMyAccouny:params =>{
    return API.GET('/teacherAccount/getMyAccouny', params)
  }
}
