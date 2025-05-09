import App from './App'

// #ifndef VUE3
import Vue from 'vue'
import './uni.promisify.adaptor'
import uView from 'uview-ui'
Vue.use(uView)

import $ from 'common/fun.js'
Vue.prototype.$ = $
Vue.config.productionTip = false
App.mpType = 'app'
const app = new Vue({
  ...App
})
app.$mount()
// main.js
Vue.prototype.globalData = {
    apiBaseUrl: 'http://192.168.104.251:8082'
};
// #endif

// #ifdef VUE3
import { createSSRApp } from 'vue'
export function createApp() {
  const app = createSSRApp(App)
  return {
    app
  }
}
// #endif