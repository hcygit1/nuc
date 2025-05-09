<template>
	<view >
		<view class="pay_success"> <u--image
		    src="/static/pay/paysuccess.png"
		    width="120px"
		    height="120px"
		    @click="click"
			class="success_img"
		  ></u--image></view>
		  <view class="pay_success_text">
			 支付成功
		  </view>
		<u-button style="width: 80%;position: absolute;bottom: 100px;left: 10%;" type="primary" @click="backIndex">完成支付</u-button>

	</view>
	
</template>
<!-- 支付成功 -->
<script>
	export default {
	  data() {
	    return {
	    };
	  },
	  methods:{
		backIndex(){
			// 自定义返回逻辑
			uni.switchTab({
			  url: '/pages/index/index', // 指定返回的页面
			});
				  
		}  
	  },
	  onBackPress(options) {
	      // 自定义返回逻辑
	      uni.switchTab({
	        url: '/pages/index/index', // 指定返回的页面
	      });
	  
	      // 阻止默认返回行为
	      return true;
	    },
	};
</script>

<style>
	.pay_success{
		height: 100%;
		width: 100%;
		display: flex;
		align-items: center; /* 垂直居中 */
		justify-content: center;
		padding-top: 100px;
	}
	.pay_success_text{
		height: 100%;
		width: 100%;
		text-align: center;
		padding-top: 30px;
	}
</style>