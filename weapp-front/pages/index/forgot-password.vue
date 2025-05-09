<template>
  <view class="container">
    <u-form :model="form" ref="forgotPasswordForm">
		<u-form-item  prop="mobile">
		  <u-input v-model="form.mobile" placeholder="请输入登录手机号" />
		</u-form-item>
      <u-form-item  prop="email">
        <u-input v-model="form.email" placeholder="请输入邮箱" />
      </u-form-item>
      <u-button type="primary" @click="handleForgotPassword">提交</u-button>
      <u-button type="default" @click="goToLogin">返回登录</u-button>
    </u-form>
  </view>
</template>

<script>
	import http from '@/utils/http';
export default {
  data() {
    return {
      form: {
        email: "",
      },
    };
  },
  methods: {
    // 处理找回密码
    handleForgotPassword() {
      if (!this.form.email) {
        uni.showToast({
          title: "请输入邮箱",
          icon: "none",
        });
        return;
      } 
	  if (!this.form.mobile) {
        uni.showToast({
          title: "请输入登录手机号",
          icon: "none",
        });
        return;
      }
	  
	  http.get("/weapp/login/sendEmail",{"mobile":this.form.mobile,"email":this.form.email}).then(res => {
	        if( res.code==200){
	  				// 模拟提交成功
	  				uni.showToast({
	  				  title: "重置密码邮件已发送",
	  				  icon: "success",
	  				});
	  				this.goToLogin()
	        }else{
	          uni.showToast({
	            title: res.message,
	            icon: 'none',
	            duration: 2000
	         })
	       }  
	    })
    
    },
    // 跳转到登录页面
    goToLogin() {
      uni.navigateTo({
        url: "/pages/index/login",
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