
import axios from 'axios'
import router from '../router/index'
import store from '../store'

//跨域
axios.defaults.withCredentials = true;

//添加一个请求拦截器
axios.interceptors.request.use( config => {
 config.headers.Authorization =store.state.token;
 config.headers.SOURCE ="pm";
    return config
}, error => {
  return Promise.reject(error)
});
// http response 拦截器
axios.interceptors.response.use(response => {
    return response;
  },error => {
  if (error.response) {
    if (error.response.status == 401) {
      error.message = '请重新登录';
      router.push('/login')
    }
    switch (error.response.status) {
      case 400: error.message = '请求错误'; break;
      case 401: error.message = '请重新登录'; break;
      case 403: error.message = '拒绝访问'; break;
      case 404: error.message = '请求地址出错';  break;
      case 500: error.message = '服务器内部错误'; break;
      default:
     }
  }
  return Promise.reject(error)
  }
);
//基地址
let base = process.env.API_ROOT;
// let base = "http://localhost:8601";

//通用方法
export const POST = (url, params) => {
  return axios.post(`${base}${url}`, params).then(res => res.data)
};

export const GET = (url, params) => {
  return axios.get(`${base}${url}`, {
    params: params
  }).then(res => res.data)
};

export const PUT = (url, params) => {
  return axios.put(`${base}${url}`, params).then(res => res.data)
};

export const DELETE = (url, params) => {
  return axios.delete(`${base}${url}`, {
    params: params
  }).then(res => res.data)
};

export const PATCH = (url, params) => {
  return axios.patch(`${base}${url}`, params).then(res => res.data)
};
