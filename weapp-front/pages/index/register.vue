<template>
  <view class="container">
    <u-form :model="form" ref="registerForm" labelWidth="60">
      <!-- 用户名 -->
      <u-form-item prop="mobile">
        <u-input v-model="form.mobile" placeholder="请输入用户名" />
      </u-form-item>
	<!-- 邮箱 -->
      <u-form-item prop="mail">
        <u-input v-model="form.mail" placeholder="请输入邮箱 找回密码使用" />
      </u-form-item>
      <!-- 密码 -->
      <u-form-item  prop="password">
        <u-input v-model="form.password" placeholder="请输入密码" password />
      </u-form-item>

      <!-- 确认密码 -->
      <u-form-item>
        <u-input v-model="confirmPassword" placeholder="请确认密码" password />
      </u-form-item>

      <!-- 上传身份证 -->
      <u-form-item label="身份证:" prop="idCard">
        <u-upload
          :fileList="idCard"
          @afterRead="handleIdCardUpload"
          @delete="handleIdCardDelete"
          name="6"
          multiple
          :maxCount="1"
          width="250"
          height="150"
        >
          <image
            :src="form.idCardUrl==''?idCardUrlModel:form.idCardUrl"
            mode="widthFix"
            style="width: 235px;height: 150px;"
          ></image>
        </u-upload>
      </u-form-item>

      <!-- 上传行驶证 -->
      <u-form-item label="行驶证:" prop="drivingLicense">
     <u-upload
       :fileList="drivingLicense"
       @afterRead="handleDrivingLicenseUpload"
       @delete="handleDrivingLicenseDelete"
       name="6"
       multiple
       :maxCount="1"
       width="250"
       height="150"
     >
       <image
	   :src="form.dlCardUrl==''?dlCardUrlModel:form.dlCardUrl"
         mode="widthFix"
         style="width: 235px;height: 150px;"
       ></image>
     </u-upload>
      </u-form-item>

      <!-- 提交按钮 -->
      <u-button style="margin-top: 20px;" type="primary" @click="handleRegister">注册</u-button>
    </u-form>
  </view>
</template>

<script>
	import http from '@/utils/http';
export default {
  data() {
    return {
		baseUrl:'',
		confirmPassword:'',
	   drivingLicense: [], // 行驶证图片
	   idCard: [], // 身份证图片
		dlCardUrlModel:'https://5b0988e595225.cdn.sohucs.com/images/20181207/2cb6c658f56849caa5ebe5c0f2b07ad5.jpeg',
		idCardUrlModel:'https://q3.itc.cn/images01/20240507/0dfee0cd5e304702b3e63732ea9aa8ef.jpeg',
      form: {
        mobile: "", // 用户名
        password: "", // 密码
		dlCardUrl:'',
		idCardUrl:'',
		mail:'',
      },
    };
  },
  mounted() {
  	   this.baseUrl=this.globalData.apiBaseUrl
  	   console.log("this.globalData.apiBaseUrl===="+this.globalData.apiBaseUrl); // 输出地址
      // 初始化签名画布
    },
  methods: {
    // 处理身份证上传
    handleIdCardUpload(event) {
		const { file } = event;
		      // 如果是多选，file为数组；如果是单选，file为对象
		      const files = Array.isArray(file) ? file : [file];
			files.forEach((item) => {
	         uni.uploadFile({
	         	          url: this.baseUrl+'/uploadFile/upload', // 例如: 'https://yourserver.com/upload'
	         	          filePath: item.url,
	         	          name: 'file', // 服务器接收的文件参数名
	         	          formData: {
	         	            'user': 'test-user' // 其他要上传的数据
	         	          },
	         	          success: (uploadFileRes) => {
							  this.form.idCardUrl=JSON.parse(uploadFileRes.data).data.imgUrl
							    this.idCard = [{ url: JSON.parse(uploadFileRes.data).data.imgUrl, name: file.name }];
	         				  console.log("====="+JSON.parse(uploadFileRes.data).data.imgUrl)
	         	          },
	         	          fail: (uploadFileErr) => {
	         	            console.error('upload failed:', uploadFileErr);
	         	            // 处理上传失败的逻辑，如提示错误等
	         	          }
	         	        });
	        });
	
    
    },

    // 删除身份证图片
    handleIdCardDelete() {
	  this.form.idCardUrl=''
      this.idCard = [];
    },

    // 处理行驶证上传
    handleDrivingLicenseUpload(event) {
    const { file } = event;
          // 如果是多选，file为数组；如果是单选，file为对象
          const files = Array.isArray(file) ? file : [file];
    	files.forEach((item) => {
         uni.uploadFile({
         	          url: this.baseUrl+'/uploadFile/upload', // 例如: 'https://yourserver.com/upload'
         	          filePath: item.url,
         	          name: 'file', // 服务器接收的文件参数名
         	          formData: {
         	            'user': 'test-user' // 其他要上传的数据
         	          },
         	          success: (uploadFileRes) => {
    					  this.form.dlCardUrl=JSON.parse(uploadFileRes.data).data.imgUrl
    					    this.drivingLicense = [{ url: JSON.parse(uploadFileRes.data).data.imgUrl, name: file.name }];
         				  console.log("====="+JSON.parse(uploadFileRes.data).data.imgUrl)
         	          },
         	          fail: (uploadFileErr) => {
         	            console.error('upload failed:', uploadFileErr);
         	            // 处理上传失败的逻辑，如提示错误等
         	          }
         	        });
        });
    	
        
    },

    // 删除行驶证图片
    handleDrivingLicenseDelete() {
		this.form.dlCardUrl=''
      this.drivingLicense = [];
    },

    // 处理注册
    handleRegister() {
      if (!this.form.mobile || !this.form.password || !this.confirmPassword||!this.form.mail) {
        uni.showToast({
          title: "请填写完整信息",
          icon: "none",
        });
        return;
      }

      if (this.form.password !== this.confirmPassword) {
        uni.showToast({
          title: "两次输入的密码不一致",
          icon: "none",
        });
        return;
      }

      if (this.form.dlCardUrl.length === 0 || this.form.idCardUrl.length === 0) {
        uni.showToast({
          title: "请上传身份证和行驶证",
          icon: "none",
        });
        return;
      }
	 http.post("/weapp/login/add",this.form).then(res => {
				      if( res.code==200){
						 uni.showToast({
						   title: "注册成功",
						   icon: "success",
						 });
						 // 跳转到登录页面
						 uni.navigateTo({
						   url: "/pages/index/login",
						 });
				      }else{
				        uni.showToast({
				          title: res.message,
				          icon: 'none',
				          duration: 2000
				       })
				     }  
				  })      // 模拟注册成功
    },
  },
};
</script>


<style>
.container {
   padding:20px 40px ;
}

</style>