import * as API from '../index'


export default {
  findList:params =>{
    return API.GET('/rentalCars/findList', params)
  } ,
  add:params =>{
    return API.POST('/rentalCars/add', params)
  } ,
  remove:params =>{
    return API.GET('/rentalCars/remove', params)
  } ,
  get:params =>{
    return API.GET('/rentalCars/get', params)
  } ,
  update:params =>{
    return API.POST('/rentalCars/update', params)
  },
}
