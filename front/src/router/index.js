import Vue from 'vue'
import Router from 'vue-router'
import Home from '@/components/Home'
import Login from '@/components/Login'
import rentalInfoAudit from '@/components/rental/rentalInfoAudit'
import carsManage from '@/components/rental/carsManage'
import ordersManage from '@/components/rental/ordersManage'
import securityManage from '@/components/rental/securityManage'
import weChat from '@/components/rental/weChat'
import gongdan from '@/components/rental/gongdan'
import rentalRecord from '@/components/rental/rentalRecord'



import resource from '@/components/Resource'
import roles from '@/components/roles'
import userRole from '@/components/userRole'
import userManager from '@/components/userManager'
import index from '@/components/index'
import router from '../router'
Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/login',
      name: '登录',
      hidden: true,
      component: Login
    },
    {
      path: '/index',
      name: '',
      component: index,
      hidden: true,
      redirect:'/home',
      children: [
        {
          path: '/home',
          name: '',
          component: Home,
          hidden: true,
        },
      ]
    },
    {
      path: '/',
      name: '',
      hidden: true,
      redirect:'/login'
    },
    {
      path: '/index',
      name: '权限管理',
      component: index,
      children: [
        {
          path: '/resource',
          iconCls: 'fa fa-bar-chart',
          name: '资源管理',
          component: resource
        }
        ,
        {
          path: '/role',
          iconCls: 'fa fa-bar-chart',
          name: '角色管理',
          component: roles
        },
        {
          path: '/userRole',
          iconCls: 'fa fa-bar-chart',
          name: '角色授权',
          component: userRole
        },
        {
          path: '/userManager',
          iconCls: 'fa fa-bar-chart',
          name: '用户管理',
          component: userManager
        }
      ]
    },
    {
      path: '/enterprise',
      name: '租车管理系统',
      component: index,
      children:[
       {
          path: '/rentalInfoAudit',
          iconCls: 'fa fa-bar-chart',
          name: '租车人信息审核',
          component: rentalInfoAudit,
        },{
          path: '/carsManage',
          iconCls: 'fa fa-bar-chart',
          name: '车辆管理',
          component: carsManage,
        },{
          path: '/ordersManage',
          iconCls: 'fa fa-bar-chart',
          name: '订单管理',
          component: ordersManage,
        },{
          path: '/securityManage',
          iconCls: 'fa fa-bar-chart',
          name: '安全管理',
          component: securityManage,
        },{
          path: '/weChat',
          iconCls: 'fa fa-bar-chart',
          name: '在线客服',
          component: weChat,
        },{
          path: '/gongdan',
          iconCls: 'fa fa-bar-chart',
          name: '工单管理',
          component: gongdan,
        },{
          path: '/rentalRecord',
          iconCls: 'fa fa-bar-chart',
          name: '租赁记录',
          component: rentalRecord,
        },
      ]
    }
  ],
})

//全局前置路由，配合浏览器localStorage进行鉴权操作
router.beforeEach((to, from, next) =>{
  if(to.path==='/home'){
    if(JSON.parse(window.localStorage.getItem('payload'))==null||!JSON.parse(window.localStorage.getItem('payload')).hasOwnProperty("loginState")||!JSON.parse(window.localStorage.getItem('payload')).loginState){
      next({ path: '/login'})
      return false;
    }
    next();
  }
  //首先，我们先看一看to和from参数，next就是执行的意思，不写页面是不会跳转的
  if(to.path!='/login'&&to.path!='/home'){
    if(JSON.parse(window.localStorage.getItem('payload'))==null||!JSON.parse(window.localStorage.getItem('payload')).hasOwnProperty("loginState")||!JSON.parse(window.localStorage.getItem('payload')).loginState){
      next({ path: '/login'})
      return false;
    }
    let perList=JSON.parse(window.localStorage.getItem('payload')).per;
    var exist=false;
    for (let i = 0; i < perList.length; i++) {
      if(to.path===perList[i]){
        exist=true;
        break;
      }
    }
    if(!exist){
      next({ path: '/login'})
    }else {
      next();
    }
  }else {
    next();
  }
})
