<template>
  <view class="container">
    <!-- 商品信息 -->
    <view class="product-info">
      <text class="product-price">单价：￥{{ itemInfo.price/100 }}元</text>
    </view>

    <!-- 选择开始时间 -->
    <view class="time-picker">
      <text class="label">开始时间：</text>
	  <u-datetime-picker
	  			@confirm="handleStartTimeConfirm" 
	  			@cancel="showstart = false"
	  			:show="showstart" 
	  			v-model="startTime"
	  			ref="datetimePicker"
	  			mode="date"
	  			closeOnClickOverlay>
	  		</u-datetime-picker>
      </u-picker>
      <text class="value" v-if="sTime!=''" @click="selectStartTime">{{sTime}}</text>
      <text class="value" v-else @click="selectStartTime">{{ '请选择开始时间' }}</text>
    </view>

    <!-- 选择结束时间 -->
    <view class="time-picker">
      <text class="label">结束时间：</text>
	  <u-datetime-picker
	  			@confirm="handleEndTimeConfirm" 
	  			@cancel="showend = false"
	  			:show="showend" 
	  			v-model="endTime"
	  			ref="datetimePicker"
	  			mode="date"
	  			closeOnClickOverlay>
	  		</u-datetime-picker>
			<text class="value" v-if="eTime!=''" @click="selectEndTime">{{eTime}}</text>
			<text class="value" v-else @click="selectEndTime">{{ '请选择结束时间' }}</text>
    </view>
	
    <!-- 底部按钮 -->
    <view class="footer-buttons">
      <u-button type="primary" v-if="hetongurl!=''">已签订合同</u-button>
      <u-button type="primary" v-else  @click="viewContract">查看合同</u-button>
      <u-button type="primary" @click="handlePayment">支付</u-button>
    </view>
  </view>
</template>

<script>
	import uSwiper from '../../node_modules/uview-ui/components/u-swiper/u-swiper.vue';
		import uModal from '../../node_modules/uview-ui/components/u-modal/u-modal.vue';
		import http from '@/utils/http';
export default {
  data() {
    return {
      // 商品信息
      itemInfo: {},
	  id:null,
      // 开始时间
      startTime: '',
      // 结束时间
      endTime: '',
      sTime: '',
      eTime: '',
	  showstart:false,
	  showend:false,
	  showPdf:false,
	  swiperList:[],
	  hetongurl:''
    };
  },
  components: {
	  uSwiper,
     uModal
    },
  methods: {
	  selectStartTime(){
		  this.showstart=true;
	  },
	  selectEndTime(){
		  this.showend=true;
	  },
    // 选择开始时间
    handleStartTimeConfirm(value) {
		console.log("==handleStartTimeConfirm=="+value.value)
	  this.showstart=false;
	  this.startTime =value.value
	  this.sTime=this.timestampToDateFormat(value.value)
    },
    // 选择结束时间
    handleEndTimeConfirm(value) {
		console.log("==handleEndTimeConfirm=="+value.value)
		this.showend=false
        this.endTime = value.value;
		 this.eTime=this.timestampToDateFormat(value.value)
    },
    // 查看合同
    viewContract() {
		uni.navigateTo({
			url:"/pages/index/hetong?id="+this.id+"&hetongurl="+this.hetongurl+
		"&startTime="+this.startTime+"&endTime="+this.endTime+
		"&sTime="+this.sTime+"&eTime="+this.eTime
		})
	
    },
    // 支付
    handlePayment() {
      if (!this.sTime || !this.eTime) {
        uni.showToast({
          title: '请选择开始和结束时间',
          icon: 'none',
        });
        return;
      } 
	  if (!this.hetongurl) {
        uni.showToast({
          title: '请先查看合同生成签名',
          icon: 'none',
        });
        return;
      }
	  if(this.endTime-this.startTime<0){
		  uni.showToast({
		    title: '结束日期要早于晚于开始日期',
		    icon: 'none',
		  });
		  return;
	  }
     uni.navigateTo({
     	url:"/pages/index/pay?id="+this.id+"&hetongurl="+this.hetongurl+
		"&startTime="+this.startTime+"&endTime="+this.endTime+
		"&sTime="+this.sTime+"&eTime="+this.eTime
     })
      // 这里可以调用支付接口
    },
	calculateDays(timestamp1, timestamp2) {
	    const date1 = new Date(timestamp1);
	    const date2 = new Date(timestamp2);
	    const diffInMs = Math.abs(date2 - date1); // 毫秒差
	    const diffInDays = diffInMs / (24 * 60 * 60 * 1000); // 天数
	    return diffInDays;
	},
	getInfo(){
		http.get("/rentalCars/get",{"id":this.id}).then(res => {
		      if( res.code==200){
				 this.itemInfo=res.data
		      }else{
		        uni.showToast({
		          title: res.message,
		          icon: 'none',
		          duration: 2000
		       })
		     }  
		  })
	},

	timestampToDateFormat(timestamp) {
	  const dateObj = new Date(timestamp); // 创建Date对象
	  const year = String(dateObj.getFullYear()); // 获取年份
	  const month = String(dateObj.getMonth() + 1).padStart(2, "0"); // 获取月份，并补零
	  const day = String(dateObj.getDate()).padStart(2, "0"); // 获取日期，并补零
	  return year + "-" + month + "-" + day; // 返回转换后的日期格式
	},
  },
  onLoad(options) {
	    this.id=options.id
		console.log("==onLoad=="+JSON.stringify(options))
		if(options.hetongurl!=undefined){
			  this.hetongurl=options.hetongurl
			  this.sTime=options.sTime
			  this.eTime=options.eTime
			  this.endTime=options.endTime
			  this.startTime=options.startTime
			  console.log("this.sTime="+this.sTime)
			  console.log("this.startTime="+this.startTime)
		}
		 this.getInfo()
  }
};
</script>

<style>
.container {
  padding: 20px;
}

/* 商品信息样式 */
.product-info {
  margin-bottom: 20px;
}

.product-name {
  font-size: 18px;
  font-weight: bold;
}

.product-price {
  font-size: 16px;
  color: #e4393c;
  margin-top: 10px;
}

/* 时间选择器样式 */
.time-picker {
  display: flex;
  align-items: center;
  margin-bottom: 20px;
}

.label {
  font-size: 14px;
  color: #666;
  margin-right: 10px;
}

.value {
  font-size: 14px;
  color: #333;
}

/* 底部按钮样式 */
.footer-buttons {
  position: fixed;
  bottom: 0;
  left: 0;
  right: 0;
  display: flex;
  justify-content: space-between;
  padding: 10px;
  background-color: #fff;
  box-shadow: 0 -2px 10px rgba(0, 0, 0, 0.1);
}

.footer-buttons .u-button {
  flex: 1;
  margin: 0 5px;
}
</style>