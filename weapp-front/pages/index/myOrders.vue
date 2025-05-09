<template>
  <view class="order-page">
    <u-list>
      <u-list-item
        v-for="(order, index) in orderList"
        :key="index"
        class="order-item"
      >
        <view class="order-content">
          <view class="order-info">
            <text class="price">订单价格：￥{{ order.totalMoney/100 }}</text>
            <text class="date">租赁日期：{{ timestampToDateFormat(order.rentalStartTime) }}至{{ timestampToDateFormat(order.rentalEndTime) }}</text>
            <text class="date">型号：{{ order.model }}</text>
            <text class="date">品牌：{{ order.brand }}</text>
          </view>
          <view class="status">
            <u-tag
              :text="getText(order.rentalStatus)"
              :type="getStatusType(order.rentalStatus)"
              size="mini"/>
			  <u-tag style="margin-top: 20rpx;" @click="checkHeTong(order.id)" text="查看合同" size="mini"/>
          </view>
        </view>
      </u-list-item>
    </u-list>
  </view>
</template>

<script>
	import uniList from '../../node_modules/uview-ui/components/u-list/u-list.vue';
	import uniListItem from '../../node_modules/uview-ui/components/u-list-item/u-list-item.vue';
		import http from '@/utils/http';
export default {
  data() {
    return {
      orderList: [],
    };
  },
  created() {
  	this.findList()
  },
  methods: {
	  checkHeTong(id){
		  uni.navigateTo({
		  	url:"/pages/index/checkhetong?id="+id
		  })
	  },
	  //初始化历史记录
	  findList(){
	    const _this=this;
	    http.get("/rentalOrders/findList",null).then(res => {
	  		if( res.code==200){
	  		 _this.orderList=res.data
	  		}else{
	  		  uni.showToast({
	  			title: res.message,
	  			icon: 'none',
	  			duration: 2000
	  		 })
	  	   }  
	  	})
	  	},
    getStatusType(status) {
      // 根据状态返回不同的tag类型
      switch (status) {
        case '1':
          return 'success';
        case '2':
          return 'warning';
        default:
          return 'info';
      }
    },
	getText(status) {
	  // 根据状态返回不同的tag类型
	  switch (status) {
	    case '0':
	      return '租赁中';
	    case '1':
	      return '已还车';
	    default:
	      return 'info';
	  }
	},
	timestampToDateFormat(timestamp) {
	  const dateObj = new Date(timestamp); // 创建Date对象
	  const year = String(dateObj.getFullYear()); // 获取年份
	  const month = String(dateObj.getMonth() + 1).padStart(2, "0"); // 获取月份，并补零
	  const day = String(dateObj.getDate()).padStart(2, "0"); // 获取日期，并补零
	  return year + "-" + month + "-" + day; // 返回转换后的日期格式
	},
  },
};
</script>

<style lang="scss" scoped>
.order-page {
  padding: 20rpx;
}

.order-item {
  margin-bottom: 20rpx;
  background-color: #fff;
  border-radius: 10rpx;
  padding: 20rpx;
  box-shadow: 0 2rpx 10rpx rgba(0, 0, 0, 0.1);
}

.order-content {
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.order-info {
  display: flex;
  flex-direction: column;
}

.price {
  font-size: 32rpx;
  font-weight: bold;
  color: #333;
}

.date {
  font-size: 24rpx;
  color: #999;
}

.status {
  margin-left: 20rpx;
}
</style>