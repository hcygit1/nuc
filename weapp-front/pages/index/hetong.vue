<template>
  <view class="container">
    <swiper class="swiper" :autoplay="false" :interval="5000" :duration="500" circular>
      <swiper-item v-for="(image, index) in images" :key="index">
        <image style="height: 90vh;" :src="image" @click="previewImage(index)" class="slide-image"></image>
      </swiper-item>
    </swiper>
	<u-button @click="qianding" type="primary" text="签订合同"></u-button>
	<u-modal :show="show" :closeOnClickOverlay="true" @close="closeModal">
	<view style="width: 750rpx ;height: 600rpx;">
	    <jp-signature :landscape="false" ref="signatureRef"   ></jp-signature>
	</view>
	<view>
	    <button @click="clear">清空</button>
	    <button @click="">撤消</button>
	    <button @click="save">保存</button>
	</view>
	</u-modal>
	   
  </view>
</template>
 
<script>
	import http from '@/utils/http';
	import SignaturePad from 'signature_pad';
export default {
  data() {
    return {
		baseUrl:'',
		hetongurl:'',
		startTime:'',
		endTime:'',
		sTime:'',
		eTime:'',
		id:null,
		signaturePad: null, // signature_pad 实例
		show:false,
      images: []
    };
  },
  mounted() {
	   this.baseUrl=this.globalData.apiBaseUrl
	   console.log("this.globalData.apiBaseUrl===="+this.globalData.apiBaseUrl); // 输出地址
      // 初始化签名画布
    },
  created() {
  	this.getPdf()
  },
  methods: {
	  closeModal(){
		  this.show=false
	  },
	       save(){
	              this.$refs.signatureRef.canvasToTempFilePath({
	                  success: (res) => {
	                      // 是否为空画板 无签名
	                      console.log(res.isEmpty)
	                      // 生成图片的临时路径
	                      // H5 生成的是base64
						  this.uploadSignature(res.tempFilePath);
	                  }
	              })
	          },
			  uploadSignature(dataUrl) {
			        uni.uploadFile({
			          url: this.baseUrl+'/uploadFile/signpdf', // 例如: 'https://yourserver.com/upload'
			          filePath: dataUrl,
			          name: 'file', // 服务器接收的文件参数名
			          formData: {
			            'user': 'test-user' // 其他要上传的数据
			          },
			          success: (uploadFileRes) => {
						this.hetongurl=JSON.parse(uploadFileRes.data).data.imgUrl
						this.show=false
						uni.navigateTo({
							url:"/pages/index/xiadan?id="+this.id+"&hetongurl="+this.hetongurl+
		"&startTime="+this.startTime+"&endTime="+this.endTime+
		"&sTime="+this.sTime+"&eTime="+this.eTime
						})
			            // 处理上传成功的逻辑，如提示用户或更新UI等
			          },
			          fail: (uploadFileErr) => {
			            console.error('upload failed:', uploadFileErr);
			            // 处理上传失败的逻辑，如提示错误等
			          }
			        });
			      },
	          clear(){
	              this.$refs.signatureRef.clear()
	          },
	          undo(){
	              this.$refs.signatureRef.undo()
	          },
	  qianding(){
		  this.show=true
	  },
    previewImage(index) {
      uni.previewImage({
        current: index, // 当前显示图片的http链接
        urls: this.images // 需要预览的图片http链接列表
      });
    },
	getPdf(){
		uni.showLoading({
			title:"合同加载中"
		})
		http.get("/rentalCars/getPdf",null).then(res => {
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
  	    this.id=options.id
  	    this.startTime=options.startTime
  	    this.endTime=options.endTime
  	    this.eTime=options.eTime
  	    this.sTime=options.sTime
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