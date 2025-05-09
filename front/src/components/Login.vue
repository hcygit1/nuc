<template>
  <div class="app_content" >
  <el-form  class="login-container" label-position="left"
           label-width="0px" v-loading="loading">
    <h3 class="login_title">系统登录</h3>
    <el-form-item >
      <el-input type="text" v-model="loginForm.mobile" auto-complete="off" placeholder="账号"></el-input>
    </el-form-item>
    <el-form-item >
      <el-input type="password" v-model="loginForm.password" auto-complete="off" placeholder="密码"></el-input>
    </el-form-item>
    <el-form-item style="width: 100%">
      <el-button type="primary" @click.native.prevent="submitClick" style="width: 100%">登录</el-button>
    </el-form-item>
    <el-form-item style="width: 100%">
      <el-button type="primary" @click.native.prevent="clickRegister" style="width: 100%">注册</el-button>
    </el-form-item>
  </el-form>

    <el-dialog title="注册用户" :visible.sync="dialogFormVisible">
      <el-form>
        <el-form-item label="手机号:" label-width="120px">
          <el-input v-model="item.mobile" autocomplete="off" ></el-input>
        </el-form-item>
        <el-form-item label="企业名称:" label-width="120px">
          <el-input v-model="item.loginName" autocomplete="off" ></el-input>
        </el-form-item>
        <el-form-item label="密码:" label-width="120px">
          <el-input v-model="item.password" autocomplete="off" type="password" ></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer-update-info">
        <el-button @click="dialogFormVisible = false">取 消</el-button>
        <el-button @click="updateUserInfo" type="primary">确 定</el-button>
      </div>
    </el-dialog>
  </div>
</template>
<script>
  import login_api from '../api/login/login_api';
  import {encryptPassword} from '../utils/encrypt_p';
  import { mapState, mapGetters, mapMutations, mapActions } from 'vuex'
  import userApi from "../api/user/user_api";

  export default{
    data(){
      return {
        loginForm: {
          mobile: '',
          password: ''
        },
        item:{},
        loading: false,
        dialogFormVisible:false
      }
    },
    methods: {
      ...mapMutations(['setToken','setUserInfo','setPermissionList','setPer',"setLoginState"]),
      ...mapGetters(['getToken','getUserInfo']),
      submitClick: function () {
        const _this = this;
        if(!_this.loginForm.mobile){
          _this.$message.error("请输入用户名");
          return false;
        }
        if(!_this.loginForm.password){
          _this.$message.error("请输入密码");
          return false;
        }
        _this.loading=true;
        login_api.login({
          mobile: _this.loginForm.mobile,
          password:encryptPassword(_this.loginForm.password)
        }).then(function (result) {

          if (result && result.code === 200) {
            window.localStorage.setItem("payload",JSON.stringify(result.data));
            _this.setToken(result.data);
            _this.setUserInfo(result.data);
            _this.setPermissionList(result.data);
            _this.setPer(result.data);
            _this.setLoginState(result.data);
           _this.$router.replace({path: '/index'});
          }else {
            _this.$message.error(result.message)
          }
          _this.loading=false
        }, function (err) {
          _this.loading=false
          _this.$message.error(err.toString())
        })
      },
      clickRegister(){
        const _this=this;
        _this.item={};
        _this.dialogFormVisible=true;
      },
      updateUserInfo(){
        const _this = this;
        var isPhone = /^1[3456789]\d{9}$/;
        if (!isPhone.test(_this.item.mobile)) {
          _this.$message.warning("手机号格式不正确");
          return false;
        }
        if (!_this.item.loginName) {
          _this.$message.warning("名称不能为空");
          return false;
        }
        if (!_this.item.password) {
          _this.$message.warning("密码不能为空");
          return false;
        }
         if (_this.item.password.length<6) {
          _this.$message.warning("密码长度不能小于6位");
          return false;
        }
        // if (!_this.item.roles) {
        //   _this.$message.warning("角色不能为空");
        //   return false;
        // }
        _this.item.password=encryptPassword(_this.item.password);
        userApi.addUser(_this.item).then(function (result) {
          console.log("result.data=="+JSON.stringify(result))
          if (result && result.code === 200) {
            console.log("result.data=="+JSON.stringify(result))
            _this.$message.success("注册成功");
            _this.dialogFormVisible=false;
          }else {
            _this.$message.error(result.message)
          }
        }, function (err) {
          _this.$message.error(err.toString())
        })
      }
    },
  }
</script>
<style>
  .login-container {
    border-radius: 15px;
    background-clip: padding-box;
    margin: 180px auto;
    width: 350px;
    padding: 35px 35px 15px 35px;
    background: #fff;
    border: 1px solid #eaeaea;
    box-shadow: 0 0 25px #cac6c6;
  }

  .login_title {
    margin: 0px auto 40px auto;
    text-align: center;
    color: #505458;
  }
  html{
    background: url("../imgs/six.jpg");
    width: 100% ;
    height: 100% ;
    background-size: 100% 100%;
  }
  .app_content{
    position: relative;
    background: url("../imgs/six.jpg");
    width: 100% ;
    height: 100% ;
    background-size: 100% 100%;
  }
  .login-container{
    position: relative;
    left: 550px;
    top: 100px;
  }
</style>
