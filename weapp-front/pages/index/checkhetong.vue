<template>
  <view class="container">
    <swiper class="swiper" :autoplay="false" :interval="5000" :duration="500" circular>
      <swiper-item v-for="(image, index) in images" :key="index">
        <image style="height: 90vh;" :src="image" @click="previewImage(index)" class="slide-image"></image>
      </swiper-item>
    </swiper>
  </view>
</template>
 
<script>
	import http from '@/utils/http';
	import SignaturePad from 'signature_pad';
export default {
  data() {
    return {
		hetongurl:'',
       images: []
    };
  },
  methods: {
    previewImage(index) {
      uni.previewImage({
        current: index, // 当前显示图片的http链接
        urls: this.images // 需要预览的图片http链接列表
      });
    },
	getPdf(id){
		uni.showLoading({
			title:"合同加载中"
		})
		http.get("/rentalOrders/getRentalOrderPdfImages",{"id":id}).then(res => {
		      if( res.code==200){
				   console.log("=res.data="+JSON.stringify(res.data))
				 this.images=res.data
				 console.log("==this.swiperList=="+JSON.stringify(this.swiperList))
				 this.showPdf=true
				 uni.hideLoading()
		      }else{
		        uni.showToast({
		          title: res.message,
		          icon: 'none',
		          duration: 2000
		       })
		     }  
		  })
	},
  },
  onLoad(options) {
	   console.log("==options=="+JSON.stringify(options))
	  	this.getPdf(options.id)
	 
  }
}
</script>
 
<style>
.container {
  width: 100%;
  height: 90vh; /* 根据需要调整 */
}
.swiper {
  height: 100%;
}
.slide-image {
  width: 100%;
  height: 100%;
}

.signature-canvas {
  width: 100%;
  height: 600px;
  border: 1px solid #ccc;
}

.button-group {
  margin-top: 20px;
  display: flex;
  justify-content: space-between;
}
.lime-signature{
	height:550rpx !important;
}
.u-modal__button-group__wrapper{
	visibility: hidden !important;
}
</style>