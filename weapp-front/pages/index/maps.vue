<template>
 <view>
	<view  class="map-con">
		<map style="width: 100%; height: 100vh;"
		 :latitude="latitude" 
		 :longitude="longitude" 
		 :markers="markers"
		 :scale="12"
		 @markertap="handleMarkerTap"
		 >
		</map>
	</view>
 </view>
</template>

<script>
		import http from '@/utils/http';
 export default {
	   data() {
	     return {
			latitude: 39.90469, // 地图中心纬度
			longitude: 116.40717, // 地图中心经度
	 		tabbarHeight:0,
	       scale: 14, // 地图缩放级别
	       markers: [],
	       showPopup: false, // 控制弹窗显示
	       selectedMarker: {}, // 当前选中的标记点
	     };
	   },
	   created() {
	   },
 	onShow(e) {
		  this.getPoints();
 		},
 		methods: {
 			 init() {
 				this.$.getlocation().then(res => {
					const platform = uni.getSystemInfoSync().platform;
									console.log("===platform=="+platform)
									console.log("===定位结果=="+JSON.stringify(res))
									// #ifdef MP-WEIXIN
									 this.latitude=res[0].latitude
									 this.longitude=res[0].longitude
									  this.markers.push({"latitude":this.latitude,"longitude":this.longitude,
									  "iconPath":'/static/mark/mine_mark.png',"name":res[0].name,"id":0,"width":30,"height":30})
									// #endif
									// #ifdef APP
									this.latitude=res.latitude
									this.longitude=res.longitude
									 this.markers.push({"latitude":this.latitude,"longitude":this.longitude,
									 "iconPath":'/static/mark/mine_mark.png',"name":res.address.poiName,"id":0})
									// #endif
									// #ifdef H5
									this.latitude=res[0].latitude
									this.longitude=res[0].longitude
									 this.markers.push({"latitude":this.latitude,"longitude":this.longitude,
									 "iconPath":'/static/mark/mine_mark.png',"name":res[0].name,"id":0,"width":30,"height":30})
									// #endif
 				}).catch(err => {
 					console.log(err)
 				})
 			},
			//获取所有租车地点
			 getPoints(){
			  http.get("/rentalCars/getpoints",null).then(res => {
					if( res.code==200){
					 this.markers=res.data
					 this.init();
					}else{
					  uni.showToast({
						title: res.message,
						icon: 'none',
						duration: 2000
					 })
				   }  
				})
			 },
			 handleMarkerTap(e) {
			  const markerId = e.detail.markerId;
			  const marker = this.markers.find((item) => item.id === markerId);
			   if (marker) {
				 this.selectedMarker = marker;
			     uni.showModal({
			       title: '导航提示',
			       content: `是否导航到 ${marker.name}？`,
			       showCancel: true,
			       confirmText: '导航过去',
			       success: (res) => {
			         if (res.confirm) {
			           this.goto(marker);
			         } else if (res.cancel) {
			           this.closeMapMark();
			         }
			       },
			     });
			   }
			 },
			 closeMapMark(){
			 	  this.showPopup = false;
			 },
			 goto(){
				 console.log("：开始导航")
				this.navigateToAmap(this.selectedMarker.latitude,this.selectedMarker.longitude,this.selectedMarker.name)
			 },
			 // 示例调用
			navigateToAmap(latitude, longitude, name) {
				// #ifdef MP-WEIXIN
				 console.log("：开始导航==MP-WEIXIN")
				  this.openAmapInMiniProgram(latitude, longitude, name);
				// #endif
				// #ifdef APP
				 console.log("：开始导航==APP")
			     this.openAmapInApp(latitude, longitude, name);
				// #endif
				// #ifdef H5
				 console.log("：开始导航==H5")
				 this.openAmapInWeb(latitude, longitude, name);
				// #endif
			},
			
			// APP 端
			 openAmapInApp(latitude, longitude, name) {
			  uni.openLocation({
			    latitude: latitude,
			    longitude: longitude,
			    name: name || '目的地',
			    success: () => {
			      console.log('成功打开地图');
			    },
			    fail: (err) => {
			      console.log('打开地图失败', err);
			      this.openAmapByUrlScheme(latitude, longitude, name);
			    }
			  });
			},
			
			// APP 端使用 URL Scheme
			 openAmapByUrlScheme(latitude, longitude, name) {
			  const url = `androidamap://navi?sourceApplication=appname&poiname=${name}&lat=${latitude}&lon=${longitude}&dev=0&style=2`;
			  plus.runtime.openURL(url, (err) => {
			    if (err) {
			      console.log('调起高德地图失败', err);
			    }
			  });
			},
			
			// H5 和 Web 端
			 openAmapInWeb(latitude, longitude, name) {
			  const url = `https://uri.amap.com/navigation?to=${longitude},${latitude},${name}&mode=car&src=yourAppName`;
			  window.location.href = url;
			},
			
			// 微信小程序
			 openAmapInMiniProgram(latitude, longitude, name) {
			    wx.openLocation({
			      latitude:  Number(latitude), // 目标纬度
			      longitude: Number(longitude), // 目标经度
			      name: name, // 地点名称
			      address: '', // 地址详情
			      scale: 18, // 地图缩放级别
			      success: (res) => {
			        console.log('打开地图成功', res);
			      },
			      fail: (err) => {
			        console.log('打开地图失败', err);
			      },
			    });
			}
	
 }
}
</script>
<style>
</style>