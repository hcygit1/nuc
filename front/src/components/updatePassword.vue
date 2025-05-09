<template>

  <el-form ref="form" :model="form" label-width="120px">
    <el-form-item label="原始密码:">
      <el-input v-model="form.originalpass"  type="password" show-password>
      </el-input>
    </el-form-item>
    <el-form-item label="输入新密码:">
        <el-input v-model="form.newpass" type="password" show-password></el-input>
    </el-form-item>
    <el-form-item label="再次输入新密码:">
        <el-input v-model="form.confirmnewpass" type="password" show-password></el-input>
    </el-form-item>
    <el-form-item class="update_pass_sub_btn">
      <el-button  type="primary" @click="onSubmit">确定</el-button>
    </el-form-item>
  </el-form>

</template>

<script>

  import user from '../api/user/user_api';
  import {encryptPassword} from '../utils/encrypt_p';
  import { mapState, mapGetters, mapMutations, mapActions } from 'vuex'
  export default {
    data() {
      return {
        form:{}
      }
    },
    methods: {
      onSubmit(){
        const _this=this;
        if(!_this.form.originalpass){
          _this.$message.warning("请输入原始密码");
          return false;
        }
        if(!_this.form.newpass){
          _this.$message.warning("请输入新密码");
          return false;
        }
        if(!_this.form.confirmnewpass){
          _this.$message.warning("请再次输入新密码");
          return false;
        }
        _this.form.mobile=_this.getUserInfo.mobile;
        _this.form.password=encryptPassword(_this.form.originalpass)
        _this.form.newPassword=encryptPassword(_this.form.newpass)
        _this.form.confirmNewPassword=encryptPassword(_this.form.confirmnewpass)
        user.updatePassWord(_this.form).then(function (result) {
          if (result && result.code === 200) {
            _this.$message.warning("修改成功");
            _this.$router.replace({path: '/login'});
          }else {
            _this.$message.error(result.message)
          }
        }, function (err) {
          _this.$message.error(err.toString())
        })
      },
      clearData(){
        const _this=this;
        let emptyDta={
          token: '',
          userInfo:{
            mobile:'',
            loginName:''
          },
          permissionList:[],
          per:[],
          loginState:false
        }
        _this.setToken(emptyDta);
        _this.setUserInfo(emptyDta);
        _this.setPermissionList(emptyDta);
        _this.setPer(emptyDta);
        window.localStorage.removeItem("payload");
      }
    },
    computed: {
      ...mapMutations(['setToken','setUserInfo','setPermissionList','setPer',"setLoginState"]),
      ...mapGetters(['getToken','getUserInfo']),
    },
  };

</script>

<style>
.update_pass_sub_btn{
  display: flex;
  justify-content: center;

}

.update_pass_sub_btn .el-form-item__content{
  margin-left:0 !important;
  width: 20%;
}
.update_pass_sub_btn .el-form-item__content .el-button{
  width: 100%;
}
</style>
