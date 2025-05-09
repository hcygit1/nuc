<template>
  <view class="container">
    <u-form class="userInfo" :model="form" ref="loginForm">
      <u-form-item  prop="mobile">
        <u-input v-model="form.mobile" placeholder="请输入用户名" />
      </u-form-item>
      <u-form-item  prop="password">
        <u-input v-model="form.password" placeholder="请输入密码" password />
      </u-form-item>
      <u-button style="text-align: ;" type="primary" @click="handleLogin">登录</u-button>
      <u-button type="default" @click="goToRegister">去注册</u-button>
      <u-button type="text" @click="goToForgotPassword">忘记密码</u-button>
    </u-form>
  </view>
</template>

<script>
		import http from '@/utils/http';
export default {
	
  data() {
    return {
      form: {
        mobile: "",
        password: "",
      },
    };
  },
  methods: {
    // 处理登录
    handleLogin() {
      if (!this.form.mobile || !this.form.password) {
        uni.showToast({
          title: "用户名和密码不能为空",
          icon: "none",
        });
        return;
      }
	  http.get("/weapp/login/login",this.form).then(res => {
	        if( res.code==200){
				  wx.setStorageSync("Authorization",res.data.token);
				  uni.switchTab({
					url: "/pages/index/index",
				  });
	        }else{
	          uni.showToast({
	            title: res.message,
	            icon: 'none',
	            duration: 2000
	         })
	       }  
	    })
    },
    // 跳转到注册页面
    goToRegister() {
      uni.navigateTo({
        url: "/pages/index/register",
      });
    },
    // 跳转到找回密码页面
    goToForgotPassword() {
      uni.navigateTo({
        url: "/pages/index/forgot-password",
      });
    },
  },
};
</script>

<style>
.container {
  padding:70px 40px ;
}

</style>