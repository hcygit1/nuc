<template>
  <el-container class="home_container">



    <el-header>
      <div class="home_title">租赁管理平台</div>

      <div class="home_userinfoContainer">
        <el-image style="height: 30px;width:30px;margin-right:20px; border-radius:50%;" :src="photoUrl"></el-image>
        <el-dropdown @command="handleCommand">
  <span class="el-dropdown-link home_userinfo">
    {{getUserInfo.loginName}}<i class="el-icon-arrow-down el-icon--right home_userinfo"></i>
  </span>
          <el-dropdown-menu slot="dropdown">
            <el-dropdown-item command="updatePassword">修改密码</el-dropdown-item>
            <el-dropdown-item command="updateImage">修改头像</el-dropdown-item>
            <el-dropdown-item command="logout" divided>退出登录</el-dropdown-item>
          </el-dropdown-menu>
        </el-dropdown>
      </div>
    </el-header>




    <el-container>

      <el-aside width="200px" style="text-align: left">
        <el-menu :default-active="this.$route.path"  :default-openeds=defaultOpens  class="el-menu-vertical-demo"   background-color="#545c64"  text-color="#fff" active-text-color="#ffd04b" router>
          <template v-for="(item,index) in permissionLists">

            <el-submenu :index="index+''" v-if="item.subMenu.length>1" :key="index">
              <template slot="title">
                <i :class="item.iconStyle"></i>
                <span>{{item.name}}</span>
              </template>
              <el-menu-item v-for="child in item.subMenu" v-if="child.jumpPath!='/rentalRecord'&&child.jumpPath!='/weChatWindow'" :index="child.jumpPath" :key="child.jumpPath">
                <template slot="title">
                  <i :class="child.iconStyle"></i>
                  {{child.name}}
                </template>
              </el-menu-item>
            </el-submenu>

            <template v-else>
              <el-menu-item :index="item.subMenu[0].jumpPath">
                <i :class="item.subMenu[0].iconStyle"></i>
                <span slot="title">{{item.subMenu[0].name}}</span>
              </el-menu-item>
            </template>

          </template>
        </el-menu>
      </el-aside>

      <el-main>
        <el-breadcrumb separator-class="el-icon-arrow-right">
        </el-breadcrumb>
        <router-view ></router-view>
      </el-main>


    </el-container>


    <el-dialog title="修改密码" :visible.sync="showUpdatePasswordDialog" class="dialog_content">
    <update-password></update-password>
    </el-dialog>

    <!--新增编辑-->
    <el-dialog
        title="修改头像"
        center
        :visible.sync="dialogupdateImage"
        width="20%"
        :before-close="handleClose">
      <div class="upload-container">
        <el-upload
            class="avatar-uploader"
            action="http://localhost:8082/uploadFile/upload"
            :show-file-list="false"
            :on-success="onUpLoadSuccess"
            :before-upload="beforeAvatarUpload">
          <img v-if="photoUrl" :src="photoUrl" class="avatar">
          <i v-else class="el-icon-plus avatar-uploader-icon"></i>
        </el-upload>
      </div>
    </el-dialog>
  </el-container>
</template>
<script>
  import user_api from '../api/user/user_api';
  import login_api from '../api/login/login_api';
  import updatePassword from '../components/updatePassword';
  import { mapState, mapGetters, mapMutations, mapActions } from 'vuex'
  export default{
    data(){
      return {
        dialogupdateImage:false,
        photoUrl:'',
        loginName: '',
        permissionLists:[],
        showUpdatePasswordDialog:false,
        defaultOpens:[],
        defaultActive:'0',
      }
    },
    components: {
      updatePassword,
    },
    methods: {
      ...mapGetters(['getPermissionList']),
      ...mapMutations(['setToken','setUserInfo','setPermissionList','setPer',"setLoginState"]),
      handleCommand(command){
        const _this = this;
        if (command == 'updatePassword') {
          _this.showUpdatePasswordDialog=true;
        }else if(command == 'logout'){
          _this.loginOut();
        }else if(command == 'updateImage'){
          _this.dialogupdateImage=true;
        }
      },
      handleClose(){
        const _this = this;
        _this.dialogupdateImage=false;
      },
      loginOut(){
        const _this=this;
        login_api.loginOut().then(function (result) {
          if (result && result.code === 200) {
            _this.$message.success("退出成功")
            _this.clearData();
            _this.$router.replace({path: '/login'});
          }else {
            _this.$message.error(result.message)
          }
        }, function (err) {
          _this.$message.error(err.toString())
        })
      },

      getMenuList: function () {
        const _this=this;
        _this.permissionLists=_this.getPermissionList();
      },
      refreshState: function () {
        const _this=this;
        if (window.localStorage.getItem("payload")) {
          let payload=JSON.parse(window.localStorage.getItem('payload'));
          _this.setToken(payload);
          _this.setUserInfo(payload);
          _this.setPermissionList(payload);
          _this.setPer(payload);
          _this.setLoginState(payload);
        }
      },
      uploadPhotoUrl(photoUrl){
        const _this=this;
        user_api.update({"id":_this.getUserInfo.id,"photoUrl":photoUrl}).then(function (result) {
          if (result && result.code === 200) {
            _this.photoUrl =photoUrl;
            const payload=JSON.parse(window.localStorage.getItem("payload"));
            payload.userInfo.photoUrl=_this.photoUrl
            window.localStorage.setItem("payload",JSON.stringify(payload));
            _this.handleClose()
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
      },
      onUpLoadSuccess: function(result){
        const _this=this;
        if (result && result.code === 200) {
          _this.uploadPhotoUrl(result.data.imgUrl);
        }else{
          _this.$message.error(result.message);
        }
      },
      beforeAvatarUpload(file) {
        const _this=this;
        const isJPG = file.type ===  'image/jpeg';
        const isLt2M = file.size / 1024 / 1024 < 10;

        if (!isJPG) {
          this.$message.error('上传视频只能是 jpeg 格式!');
        }
        if (!isLt2M) {
          this.$message.error('上传视频大小不能超过 10MB!');
        }
        _this.uploading=true
        return isJPG && isLt2M;
      },
    },
    mounted: function () {
      const _this = this;
      _this.refreshState();
      _this.getMenuList();
    },
    created() {
      const _this = this;
      _this.photoUrl=JSON.parse(window.localStorage.getItem("payload")).userInfo.photoUrl;
    },
    computed: {
      ...mapGetters(['getToken','getUserInfo']),
    },
  }
</script>
<style>


  .home_container {
    height: 100vh;
    position: absolute;
    top: 0px;
    left: 0px;
    width: 100%;
  }

  .el-header {
    background-color: #add3f8;
    color: #333;
    text-align: center;
    display: flex;
    align-items: center;
    justify-content: space-between;
    top: 0px;
  }

  .el-aside {
    background-color: #545c64;
  }

  .el-main {
    background-color: #fff;
    color: #000;
    text-align: center;
    padding: 5px;
    padding-top: 20px;
    overflow-y: auto;
    height: calc(100vh - 60px);
  }
  .home_title {
    color: #fff;
    font-size: 22px;
    display: inline;
  }

  .home_userinfo {
    color: #fff;
    cursor: pointer;
  }

  .home_userinfoContainer {
    /*display: inline;*/
    margin-right: 20px;
    display: flex;
    justify-content: center;
    align-items: center;

  }
  .dialog_content .el-dialog__body{
    padding-bottom: 50px;
  }
  .avatar-uploader .el-upload {
    border: 1px dashed #d9d9d9;
    border-radius: 6px;
    cursor: pointer;
    position: relative;
    overflow: hidden;
    justify-items: center;
    justify-content: center;
    text-align: center
  }
  .avatar-uploader .el-upload:hover {
    border-color: #409EFF;
  }
  .avatar-uploader-icon {
    font-size: 28px;
    color: #8c939d;
    width: 178px;
    height: 178px;
    line-height: 178px;
    text-align: center;
  }
  .avatar {
    width: 178px;
    height: 178px;
    display: block;
  }
  .upload-container {
    display: flex;
    justify-content: center; /* 水平居中 */
    align-items: center; /* 垂直居中 */
    height: 100%; /* 根据需要调整 */
  }
</style>
