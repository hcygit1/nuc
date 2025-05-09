<template>
    <view class="container">
		<view class="selectItem">
			<view v-if="carModelshow">
				<u-picker :show="carModelshow" :columns="carModelArray" closeOnClickOverlay 
				 @confirm="confirmModel"   @cancel="cancelCarModel"
				  @close="closeCarModel" keyName="vehicleModel"></u-picker>
			</view>
			<u-popup :show="priceshow" mode="bottom" :round="10" @close="closePrice">
					<view  class="pop_check_address">
					  <view class="popup-content">
					        <view class="input-container">
					          <u-input v-model="minPrice" placeholder="最低价(元)" type="digit" />
					        </view>
					        <view class="separator">至</view>
					        <view class="input-container">
					          <u-input v-model="maxPrice" placeholder="最高价(元)" type="digit" />
					        </view>
					        <u-button @click="confirmPriceRange" type="primary">确定</u-button>
					      </view>
					</view>
			</u-popup>
			<u-popup :show="xuHangshow" mode="bottom" :round="10" @close="closexuHang">
					<view  class="pop_check_address">
						<view class="popup-content">
						      <view class="input-container">
						        <u-input v-model="minEndurance" placeholder="最低续航(KM)" type="digit" />
						      </view>
						      <view class="separator">至</view>
						      <view class="input-container">
						        <u-input v-model="maxEndurance" placeholder="最高续航(KM)" type="digit" />
						      </view>
						      <u-button @click="confirmPriceRange" type="primary">确定</u-button>
						    </view>
					</view>
			</u-popup>
			<view class="demo-layout bg-purple-light" @click="selectCarModel">车型</view>
			 <view class="demo-layout bg-purple" @click="selectPrice">价格</view>
			 <view class="demo-layout bg-purple-light" @click="selectXuHang">续航里程</view>
		</view>
			
		
       
		<view class="scroll-panel" id="scroll-panel">
            <view class="list-box">
                <view class="left">
                    <scroll-view scroll-y="true" :style="{ height: scrollHeight + 'px' }">
                        <view
                            class="item"
                            v-for="(item, index) in leftArray"
                            :key="index"
                            :class="{ active: index == leftIndex }"
                            :id="'left-' + index"
                            :data-index="index"
                            @tap="leftTap"
                        >
                        <view class="activelink"></view>
                         <text class="item-name">{{ item.classify }}</text> 
                        </view>
                    </scroll-view>
                </view>
                <view class="main">
                    <scroll-view scroll-y="true" :style="{ height: scrollHeight + 'px' }" :scroll-into-view="scrollInto" scroll-with-animation="true">
                        <view class="item main-item" v-for="(item, index) in mainArray" :key="index" :id="'item-' + index">
                           <view class="orderlist-list">
                               <view class="list-left">
                                   <image :src="gettitlePhotourl(item, index)" mode=""></image>
                               </view>
                               <view class="list-right">
                                   <view class="list-name">型号:{{item.model}}</view>
                                   <view class="list-name">品牌:{{item.brand}}</view>
                                   <view class="list-name">价格:{{item.price/100}}元/天</view>
                               </view>
							   <view class="fill-last" ><u-button :disabled="item.status==='1'" class="yuding_btn" @click="gotorental(item)" type="primary">
							   <span v-if="item.status==='1'">已出租</span>
							   <span v-else>去租赁</span>
							   </u-button>
						</view>
                           </view>
						   
                        </view>
                        
                    </scroll-view>
                </view>
            </view>
        </view>
		
    </view>
    
</template>
<!-- 首页 -->
<script>
	import http from '@/utils/http';
export default{
    data() {
            return {
				addressshow:false,
				carModelshow:false,
				priceshow:false,
				xuHangshow:false,
                scrollHeight: 400,
                scrollTopSize: 0,
                fillHeight: 0, // 填充高度，用于最后一项低于滚动区域时使用
                leftArray: [],
                carModelArray: [],
                mainArray: [],
                topArr: [],
                leftIndex: 0,
                scrollInto: '',
                count: 5,
                value: 4,
				maxPrice:'',
				minPrice:'',
				minEndurance:'',
				maxEndurance:'',
				classify:'',
				vehicleModel:''
            };
        },
        computed: {
        },
        mounted() {
			 
        },
        methods: {
            /* 左侧导航点击 */
            leftTap(e) {
                let index = e.currentTarget.dataset.index;
				this.leftIndex=index
				this.scrollInto = `item-${index}`;
				this.getRightData(this.leftArray[index].classify)
            },
			// 获取数据
				getListData() {
				  http.get("/rentalCars/findList",null).then(res => {
				        if( res.code==200){
							 this.leftArray=res.data.leftArray
							 if(this.leftArray.length>0){
								 this.leftIndex=0
								  this.getRightData(this.leftArray[0].classify)
							 }
							
				        }else{
				          uni.showToast({
				            title: res.message,
				            icon: 'none',
				            duration: 2000
				         })
				       }  
				    })
				},
				gotorental(item){
					
					http.get("/weapp/login/getLoginUserInfo",null).then(res => {
					      if( res.code==200){
							  if(res.data.userInfo.audit==='0'){
								  uni.showToast({
								     title: '信息正在审核中',
								     icon: 'none',
								     duration: 2000
								  })
							  }else if(res.data.userInfo.audit==='2'){
								  uni.showToast({
								     title: '信息审核失败',
								     icon: 'none',
								     duration: 2000
								  })
							  }else if(res.data.userInfo.audit==='1'){
								  uni.navigateTo({
								  	url:"/pages/index/gotorental?id="+item.id
								  })
							  }
					      }else{
					        uni.showToast({
					          title: res.message,
					          icon: 'none',
					          duration: 2000
					       })
					     }  
					  })
					
				},
				getvehicleModelArray(){
					http.get("/rentalCars/getvehicleModelArray",null).then(res => {
					      if( res.code==200){
							 this.carModelArray.push(res.data)
					      }else{
					        uni.showToast({
					          title: res.message,
					          icon: 'none',
					          duration: 2000
					       })
					     }  
					  })
				},
			// 获取右侧内容minEndurance:'',
			getRightData(classify) {
			  http.get("/rentalCars/getRightData",{"classify":classify,"minPrice":this.minPrice,
			  "maxPrice":this.maxPrice,"minEndurance":this.minEndurance,
			  "maxEndurance":this.maxEndurance,"vehicleModel":this.vehicleModel}).then(res => {
			        if( res.code==200){
						 this.classify=classify
						 this.mainArray=res.data
			        }else{
			          uni.showToast({
			            title: res.message,
			            icon: 'none',
			            duration: 2000
			         })
			       }  
			    })
			},
			gettitlePhotourl(item){
				return item.photosUrl.split(",")[0];
			},
			selectAddress(){
				this.addressshow=true
			},
			selectCarModel(){
				this.carModelshow=true
			},
			selectPrice() {
			  this.priceshow = true
			},
			selectXuHang() {
			  this.xuHangshow = true
			},
			closexuHang(){
			 this.xuHangshow = false
			},
			 closeAddress() {
			  this.addressshow = false
			}, 
			closeCarModel() {
			  this.carModelshow = false
			},
			cancelCarModel() {
			  this.carModelshow = false,
			  this.vehicleModel = '',
			  this.getListData();
			  this.getvehicleModelArray();
			},
			confirmModel(e) {
			  this.carModelshow = false
			  console.log(JSON.stringify(e))
			  this.vehicleModel=e.value[0].vehicleModel
			  this.getRightData(this.classify)
			},
			closePrice() {
			  this.priceshow = false
			},
			confirmPriceRange(){
				 this.priceshow = false
				 this.xuHangshow = false
				 this.getRightData(this.classify)
			},
        },
		onShow() {
			console.log("==leftIndex=="+this.leftIndex)
			this.getListData();
			this.getvehicleModelArray();
		}
    };
</script>
<style lang="scss">
    
    page,
    .container {
        width: 100vw;
        height: 100%;
    }
    /* 容器 */
    .container {
        display: flex;
        flex-direction: column;
        flex-wrap: nowrap;
        justify-content: flex-start;
        align-items: flex-start;
        align-content: flex-start;
		padding-top: 15px;
        // & > view {
        //     width: 100%;
        // }
    
        .scroll-panel {
            flex-grow: 1;
            height: 0;
            overflow: hidden;
        }
    
        
    }
    .fication-search{
        width: 686rpx;
        height: 64rpx;
        margin: 12rpx auto;
        background: #F5F6F7;
        border-radius: 32rpx 32rpx 32rpx 32rpx;
        display: flex;
        flex-direction: row;
        align-items: center;
        justify-content: space-between;
    }
    .fication-search input{
        padding-left: 24rpx;
        font-size: 12px;
        font-family: PingFang SC-Regular, PingFang SC;
        font-weight: 400;
        color: #B5B5B5;
    }
    .fication-search input::-webkit-input-placeholder{
                color:red!important;
    }
    
    .search-icon{
        width: 24rpx;
        height: 24rpx;
        margin-right: 30rpx;
    }
    .list-box {
        display: flex;
        flex-direction: row;
        flex-wrap: nowrap;
        justify-content: flex-start;
        align-items: flex-start;
        align-content: flex-start;
        font-size: 28rpx;
		height: 100vh;
		width: 100vw;
		.main{
			  width:70vw;
		}
        .left {
            width: 27vw;
			height: 92vh;
            background: #F5F6F7;
            line-height: 112rpx;
            box-sizing: border-box;
            font-size: 28rpx;
            font-family: PingFang SC-Medium, PingFang SC;
			
            .item {
                
                position: relative;
                display: flex;
                
                &:not(:first-child) {
                    margin-top: 1px;
    
                    &::after {
                        content: '';
                        display: block;
                        height: 0;
                        // border-top: #d6d6d6 solid 1px;
                        width: 620upx;
                        position: absolute;
                        top: 0px;
                        right: 0;
                        transform: scaleY(0.5); /* 1px像素 */
                    }
                }
                .item-name{
                    padding-left: 32rpx;
                }
                &.active {
                    color: #F7433D;
                    background-color: #fff;
                }
                &.active .activelink{
                    width: 8rpx;
                    height: 48rpx;
                    margin-top: 32rpx;
                    padding-left: 0;
                    background-color: #F7433D;
                    border-radius: 2px 2px 2px 2px;
                }
            }
        }

            .title {
                line-height: 64rpx;
                font-size: 16px;
                font-family: PingFang SC-Bold, PingFang SC;
                font-weight: bold;
                color: #000000;
                padding: 8rpx 0;
                background-color: #fff;
                position: sticky;
                top: 0;
                z-index: 19;
            }
    
    }
    .orderlist-list{
        height: 200rpx;
        background: #FFFFFF;
        margin: 0 auto;
        display: flex;
        border-bottom: 2rpx solid #F5F6F7;
        // border-radius: 8px 8px 8px 8px;
    }
    .list-left{
        margin: 24rpx;
        width: 130rpx;
        height: 130rpx;
        border-radius: 4px 4px 4px 4px;
        overflow: hidden;
    }
    .list-left image{
        width: 130rpx;
        height: 130rpx;
    }
    .list-right{
		
        flex: 1;
        display: flex;
        flex-direction: column;
    }
    .list-name{
        margin-top: 30rpx;
        font-size: 14px;
        font-family: PingFang SC-Medium, PingFang SC;
        font-weight: 500;
        color: #000000;
        line-height:20rpx;
    }
    .list-ping{
        margin: 20rpx 0;
        display: flex;
        font-size: 12px;
        font-family: PingFang SC-Regular, PingFang SC;
        font-weight: 400;
        color: #FFB800;
        
    }
    .list-ping text{
        margin-left: 6rpx;
    }
    .list-meuns{
        margin-bottom: 28rpx;
        font-size: 12px;
        font-family: PingFang SC-Medium, PingFang SC;
        font-weight: 500;
        color: #999999;
    }
	.wrap {
	        padding: 12px;
	    }
	
	 
	
	    .bg-purple {
	        background: #F5F6F7;
	    }
	
	    .bg-purple-light {
	        background: #F5F6F7;
	    }
	
	    .bg-purple-dark {
	        background: #99a9bf;
	    }
		.u-demo-block__content{
			padding-bottom: 10px;
		}
		.search-content {
		 font-size: 14px;
		 padding-bottom: 10px;
		 width: 100%;
		 padding-left: 2px;
		}
		.fill-last{
			display: flex;
		  justify-content: center; /* 水平居中 */
		  align-items: center; /* 垂直居中 */
		}
		.yuding_btn{
			height:20%;
			border-radius: 5px;
			justify-content: center; /* 水平居中 */
			align-items: center; /* 垂直居中 */
		}
		.pop_check_address{
			height: 250px;
		}
		.popup-content {
		  padding: 20px;
		}
		
		.input-container {
		  margin-bottom: 20px;
		}
		
		.separator {
		  text-align: center;
		  margin: 10px 0;
		  font-size: 16px;
		  color: #666;
		}
		.demo-layout {
			margin-right: 3%;
			width: 30%;
		    height: 25px;
		    border-radius: 4px;
			display: flex;
			align-items: center; /* 垂直居中 */
			justify-content: center;
		}
		.selectItem{
			padding-bottom: 20px;
			display: flex;
			width: 100%;
		}
</style>