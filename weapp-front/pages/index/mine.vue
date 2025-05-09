<template>
	<view class="my">
	<u--image
	class="img"
	    :showLoading="true"
	    src="https://img0.baidu.com/it/u=3901339399,2930772333&fm=253&fmt=auto&app=138&f=JPEG?w=750&h=500"
	    width="100%"
	    height="200px"
	    @click="click"
	  ></u--image>
		<!-- <image class="img" src="https://img0.baidu.com/it/u=3901339399,2930772333&fm=253&fmt=auto&app=138&f=JPEG?w=750&h=500" mode="right" @click="up"></image> -->
		<view class="personal">
			<view class="name-profile">
				<image class="head" :src="myInfo.imageUrl" mode="cover" @click="uppicture"></image>
				<view class="info">
					<view class="name">{{myInfo.mobile}}</view>
					<view class="name">{{myInfo.mail}}</view>
				</view>
			</view>
			
		</view>
	<view class="items">
				<view   class="mine-items" @click="gotoMyorder()"> 我的订单</view>
				<u-line  class="item_line"   color="grey"></u-line>
				<view   class="mine-items" @click="gotoGongdan()"> 我的工单</view>
				<u-line  class="item_line"   color="grey"></u-line>
				<view class="mine-items" @click="loginOut()"> 退出登录</view>
				<u-line   class="item_line"  color="grey"></u-line>
	</view>
	</view>
</template>
 <!-- 我的 -->
<script>
	import uniList from '../../node_modules/uview-ui/components/u-list/u-list.vue';
	import uniListItem from '../../node_modules/uview-ui/components/u-list-item/u-list-item.vue';
		import http from '@/utils/http';
	export default {
		data() {
			return {
				baseUrl: '',
				token: '',
				myInfo: {}
			}
		},
		onShow() {
			this.getMyInfo()
			 this.baseUrl=this.globalData.apiBaseUrl
		},
		mounted() {
			   this.baseUrl=this.globalData.apiBaseUrl
			   console.log("this.globalData.apiBaseUrl===="+this.globalData.apiBaseUrl); // 输出地址
		    // 初始化签名画布
		  },
		components: {
		    uniList,
		    uniListItem
		  },
		methods: {
			uppicture() {
				uni.chooseImage({
					//定义 选择的 数量
					count: 1,
					success: (res) => {
						//获取 图片 链接
						let imgUrl = res.tempFilePaths[0]
								console.log("==uppicture===")
					uni.uploadFile({
						          url: this.baseUrl+'/uploadFile/upload', // 例如: 'https://yourserver.com/upload'
						          filePath: imgUrl,
						          name: 'file', // 服务器接收的文件参数名
						          formData: {
						            'user': 'test-user' // 其他要上传的数据
						          },
						          success: (uploadFileRes) => {
									  this.updateImage(JSON.parse(uploadFileRes.data).data.imgUrl)
									  console.log("====="+JSON.parse(uploadFileRes.data).data.imgUrl)
						          },
						          fail: (uploadFileErr) => {
						            console.error('upload failed:', uploadFileErr);
						            // 处理上传失败的逻辑，如提示错误等
						          }
						        });
					}
				})
			},
			up() {
				uni.chooseImage({
					//定义 选择的 数量
					count: 1,
					success: (res) => {
						//获取 图片 链接
						let imgUrl = res.tempFilePaths[0]
						uni.request({
							url: imgUrl,
							//设置arraybuffer对象 类型
							responseType: "arraybuffer",
							success: (res) => {
								if (res.statusCode === 200) {
									let data = res.data
									//转换成 base64编码
									const base64 = uni.arrayBufferToBase64(data)
									uni.request({
										url: "替换为自己的url地址值",
										method: "post",
										header: {
											'content-type': 'application/x-www-form-urlencoded;charset=utf-8'
										},
										data: {
											appkey: '替换为自己的appkey值',
											tokenString: this.token,
											imgType: 'jpg',
											serverBase64Img: base64
										},
										success(res) {
											console.log('res', res);
										},
										fail(err) {
											console.log('err', err)
										}
									})
									this.back();
								}
							}
 
 
						})
					}
				})
			},
			back() {
				uni.navigateBack({
					delta: 1
				})
			},
			getMyInfo(){
				http.get("/weapp/login/getLoginUserInfo",null).then(res => {
				      if( res.code==200){
						  this.myInfo=res.data.userInfo
				      }else{
				        uni.showToast({
				          title: res.message,
				          icon: 'none',
				          duration: 2000
				       })
				     }  
				  })
			},
			updateImage(imageUrl){
				http.get("/weapp/login/updateInfo",{"imageUrl":imageUrl}).then(res => {
				      if( res.code==200){
						  this.getMyInfo()
				      }else{
				        uni.showToast({
				          title: res.message,
				          icon: 'none',
				          duration: 2000
				       })
				     }  
				  })
			},
			loginOut(){
				uni.showModal({
				    title: '提示',
				    content: '确定退出登录吗？',
				    success(res) {
				      if (res.confirm) {
				        http.get("/weapp/login/loginout", null).then(res => {
				          if( res.code==200){
				            wx.clearStorage()
				            wx.reLaunch({
				              url: '/pages/index/login'
				            })        
				          }else{
				            wx.showToast({
				              title: res.message,
				              icon: 'none',
				              duration: 1000
				          })
				          } 
				      })
				      }
				    }
				  })
			},
			gotoMyorder(){
				uni.navigateTo({
					url:"/pages/index/myOrders"
				})
			},
			gotoGongdan(){
				uni.navigateTo({
					url:"/pages/index/gongdan"
				})
			}
		},
	}
</script>
 
<style lang="scss">
	.img {
		height:30%;
	}
	.my{
		height:100%;
	}
	
	.personal {
		height:240rpx;
		background: linear-gradient(to bottom, rgba(255, 255, 255, 0.8), rgba(255, 255, 255, 0.5));
		/* 渐变透明白色背景 */
		margin: 20rpx;
		border-radius: 20rpx 20rpx 0 0;
		margin-top: -17%;
		position: relative;
		box-shadow: 0 10rpx 20rpx rgba(0, 0, 0, 0.1);
		z-index: 9999;
		/* 确保高于其他元素 */
		.name-profile {
			padding: 35rpx;
			display: flex;
			align-items: flex-start;
			position: relative;
			height: 150rpx;
			.head {
				margin-top: -10rpx;
				/* 确保头像左边界紧贴容器左边 */
				border-radius: 50%;
				width: 150rpx;
				height: 150rpx;
			}
 
			.info {
				display: flex;
				flex-direction: column;
				justify-content: center;
				margin-left: 50rpx;
			}
 
			.name {
				font-size: 30rpx;
				margin-top:10rpx;
				margin-bottom: 10rpx;
				color: #0c34ba;
				font-weight: 600;
			}
 
			.profile {
				margin-top: 30rpx;
				color: #646566;
				font-size: 24rpx;
				font-weight: 400;
			}
		}
	}
	.mine-items{
		line-height: 50px;
		padding-left: 20px;
	}
	.items{
		margin-top:30px;
	}
	.item_line{
		width: 89% !important;
		margin-left:20px !important;
	}
</style>