import Vue from 'vue'
import Vuex from 'vuex'
Vue.use(Vuex)

const store = new Vuex.Store({
  state: {
    token: '',
    userInfo:{
      mobile:'',
      loginName:''
    },
    permissionList:[],
    per:[],
    loginState:null
  },
  getters: {
    getToken(state) {
      return  state.token
    },
    getUserInfo(state){
      return  state.userInfo
    },
    getPermissionList(state){
      return  state.permissionList
    },
    getPer(state){
      return  state.per
    },
    getLoginState(state){
      return  state.loginState
    },

  },
  mutations: {
    setToken(state, payload) {
      state.token = payload.token
    },
    setUserInfo(state, payload) {
      state.userInfo = payload.userInfo
    },
    setPermissionList(state, payload) {
      state.permissionList = payload.permissionList
    },
    setPer(state, payload) {
      state.per = payload.per
    },
    setLoginState(state, payload) {
      state.loginState = payload.loginState
    }
  },
  actions: {
    setName(content, payload) {
      return new Promise(resolve => {
        setTimeout(() => {
          content.commit('setToken', {token: payload.token})
          resolve()
        }, 1000)
      })
    }
  },
})

export default store
