//h5 要调用的js文件
// #ifdef H5
import amap from '@/utils/maps.js';
// #endif
//微信小程序要调用的js文件
// #ifdef MP  
import amap from '@/utils/amap-wx.js';
// #endif
//获取位置信息
const getlocation = (opt) => {
	return new Promise((resolve, reject) => {
		//h5开始
		// #ifdef H5
		// 检查并请求地理位置权限
		if (navigator.permissions) {
		  navigator.permissions.query({ name: 'geolocation' }).then((result) => {
			      console.log('====result.stat==='+result.state);
		    if (result.state === 'granted') {
		      console.log('已授权地理位置权限');
		    } else if (result.state === 'prompt') {
		      console.log('等待用户授权地理位置权限');
		    } else if (result.state === 'denied') {
		      console.error('用户拒绝了地理位置权限');
		      uni.showToast({
		        title: '请允许浏览器获取地理位置权限',
		        icon: 'none',
		      });
		    }
		  });
		}
		AMap.plugin('AMap.IpLocation', function() {
		    AMap.IpLocation({
		      success: function(result) {
		        console.log("==IpLocation=="+result);
		      },
		      fail: function(error) {
				    console.log("==IpLocation=="+error);
		      }
		    });
		  });
		AMap.plugin('AMap.Geolocation', function() {
			uni.showLoading({
				title: '系统正在定位'
			});
			console.log("============H5===111=================")
			var geolocation = new AMap.Geolocation({
				enableHighAccuracy: true, //是否使用高精度定位，默认:true
				timeout: 10000, //超过10秒后停止定位，默认：5s
				buttonPosition: 'RB', //定位按钮的停靠位置
				buttonOffset: new AMap.Pixel(10, 20), //定位按钮与设置的停靠位置的偏移量，默认：Pixel(10, 20)
				zoomToAccuracy: true, //定位成功后是否自动调整地图视野到定位点
 
			});
			console.log("============H5==222==================")
			geolocation.getCurrentPosition(function(status, result) {
				console.log("============H5status222=================="+status)
				if (status == 'complete') {
					//这个地方的result，可能会出现报错：获得地理定位时间。得到ipLocation成功。获取地址失败，请检查您的密钥或网络。
					//可能是密匙申请错了，重新申请密匙，生成maps.js文件。
					console.log(result)
					uni.hideLoading();
					resolve(result)
				} else {
					uni.hideLoading();
					uni.showToast({
						title: '定位失败',
					});
					reject(result)
				}
			});
		});
		// #endif
		//h5结束
		
		//app开始
		// #ifdef APP-PLUS
		uni.showLoading({
			title: '获取信息中'
		});
		uni.getLocation({
			// map组件默认为国测局坐标gcj02，调用 uni.getLocation返回结果传递给组件时，需指定 type 为 gcj02 
			type: 'gcj02',
			geocode: true,
			success: function(data) {
				resolve(data)
				console.log(data)
			},
			fail: function(err) {
				reject(err)
			},
			complete() {
				uni.hideLoading();
			}
		})
		// #endif
		//app结束
		
		///小程序开始
		// #ifdef MP
		var amapPlugin = new amap.AMapWX({
			key: 'a7344a82b30cfc9066b352e12c9dfe06'  //此处为高德平台申请的微信小程序的key
		});
		uni.showLoading({
			title: '获取信息中'
		});
		amapPlugin.getRegeo({
			success: function(data) {
			uni.hideLoading();
				resolve(data)
			},
			fail: function(err) {
				reject(err)
			},
			complete: function() {
				uni.hideLoading();
			}
		});
		// #endif
		//小程序结束
	})
};
export default {
	getlocation: getlocation
}