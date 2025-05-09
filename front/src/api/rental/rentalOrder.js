import * as API from '../index'


export default {
  findListOrders:params =>{
    return API.GET('/rentalOrders/findListOrders', params)
  } ,
  findListByCarId:params =>{
    return API.GET('/rentalOrders/findListByCarId', params)
  } ,
  add:params =>{
    return API.POST('/rentalOrders/add', params)
  } ,
  remove:params =>{
    return API.GET('/rentalOrders/remove', params)
  } ,
  get:params =>{
    return API.GET('/rentalOrders/get', params)
  } ,
  update:params =>{
    return API.POST('/rentalOrders/update', params)
  },
}
