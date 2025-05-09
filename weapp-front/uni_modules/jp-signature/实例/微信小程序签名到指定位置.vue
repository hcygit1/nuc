<template>
	<view class="h100">
		<view style="text-align: center;padding-bottom: 150px;">
			<image :src="image" v-if="image" style="width: 200px;height: 200px;border: 1px solid #ccc;"></image>
			<image :src="imgs" v-else style="width: 200px;height: 200px;border: 1px solid #ccc;"></image>
			<view class="but" style="margin: 0 25px;" @click="toPop">我要在上面签字</view>
			<jp-signature-popup ref="signature2" @change="setImg" popup  />
			<canvas canvas-id="shareCanvas" class="canvas" bindlongpress="saveImg" catchtouchmove="true"
				style="position:fixed;left:500%"
				:style="{height: canvasHeight+'px',width:canvasWidth+'px'}">
			</canvas>
		</view>
	</view>
</template>
<!-- 有项目需要开发的请联系 扣 - 371524845 -->
<!-- 
 注意：需要采用线上图片且需要配置白名单，未配置手机无法签名，采用真机调试2.0不配置白名单也可以签名，正式版本需要线上图片且需要配置白名单
 -->
<script>
	export default {
		data() {
			return {
				canvasHeight: 400,
				canvasWidth: 400,
				width:80,
				height: 50,
				left: 20,
				top: 20,
				ctx:null,
				image: '',
				imgs: 'http://mmbiz.qpic.cn/sz_mmbiz_jpg/GEWVeJPFkSGTfkSpSbg9cHUqcibBv38r8GXDIVy4W6FN7a1TMWf6RSNQLemKBwG8VqjlxUhicIzz3NTONVrD96ibg/0?wx_fmt=jpeg'
			}
		},
		mounted() {
			//初始化画布
			this.ctx = wx.createCanvasContext('shareCanvas', this)
		},
		methods: {
			setImg(val) {
				if (val) {
					this.exportPost(val).then(res => {
						this.image = res
					})
				}
			},
			toPop() {
				this.$refs.signature2.toPop()
			},
			getImageInfo(src) {
				return new Promise((resolve, reject) => {
					wx.getImageInfo({
						src,
						success: (res) => resolve(res),
						fail: (res) => reject(res)
					})
				});
			},
			exportPost(image2){
				let that  =  this
			   return new Promise(function (resolve, reject) {
				let image =  that.imgs
				//获取系统的基本信息，为后期的画布和底图适配宽高
				 uni.getSystemInfo({
					success: function (res) {
					Promise.all([that.getImageInfo(image),that.getImageInfo(image2)]).then(res=>{
			        //获取底图和二维码图片的基本信息,通常前端导出的二维码是base64格式的，所以要转成图片格式的才可以获取图片的基本信息			
					that.ctx.drawImage(res[0].path,0 , 0,that.canvasWidth,that.canvasHeight);
					that.ctx.drawImage(res[1].path,that.left,that.top,that.width, that.height);
						  that.ctx.draw(false,function(){
							  wx.canvasToTempFilePath({
								  x: 0,
								  y: 0,
								  width:that.canvasWidth,
								  height:that.canvasHeight,
								  destWidth:that.canvasWidth*2,//这里乘以2是为了保证合成图片的清晰度
								  destHeight:that.canvasHeight*2,
								  canvasId: 'shareCanvas',
								  fileType:'jpg',//设置导出图片的后缀名
								  success: function (res) {
									  resolve(res.tempFilePath)
								  },
								  fail: function (res) {
									  reject(res)
								  },
							  })   
						  });
						})     
					}
				})
			   })
			},
		}
	}
</script>

<style lang="scss">
	.but {
		margin: 25px;
		line-height: 40px;
		text-align: center;
		background-color: #55aaff;
		color: #fff;
	}

	.sssv {
		height: 1150px;
	}
</style>