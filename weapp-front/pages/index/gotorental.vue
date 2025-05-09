<template>
  <view class="container">
    <!-- 轮播图 -->
    <u-swiper  :list="swiperList" height="300"  :autoplay="true" :interval="3000" indicator-pos="bottomCenter"></u-swiper>
	
	<view style="display: flex;">
		<!-- 信息简介 -->
		<view class="card-content" style="width: 70%;">
		  <view class="description">型号:{{ itemInfo.model }}</view>
			<view class="description">品牌：{{ itemInfo.brand }}</view>
			<view class="description">车型：{{ itemInfo.vehicleModel }}</view>
			<view class="description">续航：{{ itemInfo.endurance }}(km)</view>
		  <view class="discount-price">价格：￥{{itemInfo.price/100}}元/天</view>
		</view>
		<view style="display: flex;justify-content: center;align-items: center;">
			<text style="background-color: green;width: 100%;" @click="view3d()">查看3D</text>
		</view>
	</view>


    <!-- 去下单按钮 -->
    <view class="order-button">
      <u-button type="primary" @click="handleOrder">去下单</u-button>
    </view>
  </view>
</template>

<script>
	import uSwiper from '../../node_modules/uview-ui/components/u-swiper/u-swiper.vue';
	import uVutton from '../../node_modules/uview-ui/components/u-button/u-button.vue';
	import http from '@/utils/http';
export default {
  data() {
    return {
		  // 商品信息
		itemInfo:{},
		id:null,
      // 轮播图数据
      swiperList: [],
    
    };
  },
  components: {
      uSwiper,
      uVutton
    },
  methods: {
    // 去下单按钮点击事件
    handleOrder() {
     uni.navigateTo({
     	url:"/pages/index/xiadan?id="+this.id
     })
    },
	getInfo(){
		http.get("/rentalCars/get",{"id":this.id}).then(res => {
		      if( res.code==200){
				 this.itemInfo=res.data
				 this.swiperList=res.data.photosUrl.split(",");
				 console.log("==this.itemInfo=="+JSON.stringify(this.itemInfo))
		      }else{
		        uni.showToast({
		          title: res.message,
		          icon: 'none',
		          duration: 2000
		       })
		     }  
		  })
	},
	view3d(){
		uni.navigateTo({
			url:"/pages/index/detail?src="+ this.itemInfo.glbFileUrl
		})
	}
  },
	onLoad(options) {
	   console.log("== options.id=="+JSON.stringify(options.id))
         this.id=options.id
		 this.getInfo()
   }
};
</script>

<style>
.container {
  padding: 20px;
}

/* 轮播图样式 */
.u-swiper {
  margin-bottom: 20px;
}

/* 卡片内容样式 */
.card-content {
  padding: 10px;
}

.description {
  font-size: 14px;
  color: #666;
  line-height: 1.5;
}

.price {
  margin-top: 10px;
}

.original-price {
  font-size: 12px;
  color: #999;
  text-decoration: line-through;
  margin-right: 10px;
}

.discount-price {
  font-size: 16px;
  color: #e4393c;
  font-weight: bold;
}

/* 下单按钮样式 */
.order-button {
  position: fixed;
  bottom: 0;
  left: 0;
  right: 0;
  padding: 10px;
  background-color: #fff;
  box-shadow: 0 -2px 10px rgba(0, 0, 0, 0.1);
}
</style>