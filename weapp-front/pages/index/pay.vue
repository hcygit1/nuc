<template>
	<view style="overflow: hidden;">
		<view class="card option-pay">
			<view class="title">支付方式</view>
			<radio-group @change="checkPayType">
				<label class="pay-item center space-between">
					<view class="left center">
						<image src="/static/pay/zhifubao.png"></image>
						<text>支付宝</text>
					</view>
					<radio value="alipay" :checked="provider==='alipay'" class="icon_pay" style="transform:scale(0.8)" />
				</label>
				<label class="pay-item center space-between">
					<view class="left center">
						<image src="/static/pay/weixinpay.png"></image>
						<text>微信支付</text>
					</view>
					<radio value="wxpay" :checked="provider==='wxpay'" class="icon_pay" style="transform:scale(0.8)" />
				</label>
				<label class="pay-item center space-between">
					<view class="left center">
						<image src="/static/pay/yinlian.png"></image>
						<text>微信支付</text>
					</view>
					<radio value="unionpay" :checked="provider==='unionpay'" class="icon_pay" style="transform:scale(0.8)" />
				</label>
			</radio-group>
		</view>
		<view class="card price space-between">
			<text>实付金额:</text>
			<text style="position: absolute;right: 30px;">￥{{itemInfo.price*days/100}}</text>
		</view>
		<u-button  style="width: 80%;position: absolute;bottom: 50px;left: 10%;" type="primary" @click="confirm">立即支付</u-button>
	</view>
</template>
<!-- 支付 -->
<script>
	import http from '@/utils/http';
	export default {
			
			data() {
			return {
				loading:false,
				provider: 'alipay', // 支付方式,即提供商
				data: {},
				itemInfo: {},
				hetongurl:'',
				startTime:'',
				endTime:'',
				id:null,
				eTime:'',
				sTime:'',
				days:0
			};
		},
		onLoad(option) {
			//获取支付数据
			console.log(JSON.stringify(option.params))
			this.hetongurl=option.hetongurl
			this.startTime=option.startTime
			this.endTime=option.endTime
			this.id=option.id
			this.eTime=option.eTime
			this.sTime=option.sTime
			this.getInfo()
		},
		methods: {
			checkPayType(event){
				this.provider=event.detail.value
				console.log("event=="+JSON.stringify(event.detail))
			},
			confirm(){
				const param={
					"carId":this.id,
					"payType":this.provider,
					"rentalStartTime":this.sTime,
					"rentalEndTime":this.eTime,
					"pdfUrl":this.hetongurl,
					"totalMoney":this.itemInfo.price*this.days
				}
				http.post("/rentalOrders/add",param).then(res => {
							console.log("==res=="+JSON.stringify(res))
				      if( res.code==200){
						uni.navigateTo({
							url:"/pages/index/success"
						})
				      }else{
				        uni.showToast({
				          title: res.message,
				          icon: 'none',
				          duration: 2000
				       })
				     }  
				  })
				
			},
			getInfo(){
				http.get("/rentalCars/get",{"id":this.id}).then(res => {
				      if( res.code==200){
						 this.itemInfo=res.data
						this.days=(parseInt(this.endTime-this.startTime)/(1000*3600*24))+1
						 console.log("==this.itemInfo=="+this.days)
				      }else{
				        uni.showToast({
				          title: res.message,
				          icon: 'none',
				          duration: 2000
				       })
				     }  
				  })
			},
		}
	}
</script>
<style lang="scss">
	.card {
		margin: 0 30rpx;
		padding: 0 20rpx;
		background-color: #FFF;
		margin-top: 30rpx;
		border-radius: 20rpx;
		box-shadow: 1px 0 5px 0 rgba(0, 0, 0, 0.08);
	}

	.option-pay {
		.title {
			font-size: 35rpx;
			line-height: 90rpx;
		}

		.pay-item {
			
			line-height:120rpx;
			display: flex;
			align-items: center; /* 垂直居中 */
			.left {
				display: flex;
				align-items: center; /* 垂直居中 */
				image {
					width: 60rpx;
					height: 60rpx;
				}

				text {
					font-size: 33rpx;
					padding-left: 20rpx;
				}
			}
			.icon_pay{
				position: absolute;
				right:20px;
			}
		}
	}

	.price {
		font-size: 30rpx;
		line-height: 90rpx;
		border-bottom: 1px solid #F8F9FB;

		text:last-child {
		}
	}

	.btn {
		margin: 60rpx 30rpx;
		color: #fff;
		line-height: 80rpx;
		font-size: 30rpx;

		&::after {
			// 加载中时，隐藏边框
			border: none;
		}
	}
</style>
