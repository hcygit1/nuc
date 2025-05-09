<template>
  <view class="container">
    <!-- 工单列表 -->
    <u-list>
      <u-list-item v-for="(item, index) in orderList" :key="index" class="order-item">
        <view class="left-section">
		
		<!-- 上半部分：工单内容和提交时间 -->
        <view class="top-section" style="display: flex;">
         <div> <text class="content">{{ item.content }}</text>
          <text class="create-time">{{ formateDate(item.createTime) }}</text></div>
			<view  class="deal_tag"><u-tag
         :text="item.status === '0' ? '未处理' : '已处理'"
         :type="item.status === '0' ? 'warning' : 'success'"
         size="mini"
       /></view>
		<view>
         
        </view>
		</view>

        <!-- 中间的淡灰色横线 -->
        <u-line color="#f0f0f0" />

        <!-- 下半部分：处理结果 -->
        <view class="bottom-section">
          <text class="deal-result">{{ item.dealResult }}</text>
        </view>
		
		 </view>
		 
      </u-list-item>
    </u-list>

    <!-- 底部创建工单按钮 -->
    <view class="footer">
      <u-button type="primary" @click="showCreateModal">创建工单</u-button>
    </view>

    <!-- 创建工单弹窗 -->
    <u-modal :show="showModal" title="创建工单" :show-cancel-button="true" 
	 @cancel="cancleModal" @confirm="submitOrder">
		<u-textarea
	          v-model="newOrderContent"
	          placeholder="请输入工单内容"
	          :auto-height="true"
	          :maxlength="300"
			  :minlength="200"
	        />	  
    </u-modal>
  </view>
</template>

<script>
	import uModal from '../../node_modules/uview-ui/components/u-modal/u-modal.vue';
		import http from '@/utils/http';
export default {
  data() {
    return {
      // 工单列表数据
      orderList: [],
      // 控制弹窗显示
      showModal: false,
      // 新工单内容
      newOrderContent: "",
    };
  },
  created() {
  	this.findList()
  },
  components: {
      uModal
    },
  methods: {
		formateDate(value){
			let date = new Date(value);//时间戳为10位需*1000，如果为13位的话不需乘1000。
			let Y = date.getFullYear() + '-';
			let M = date.getMonth() + 1 < 10 ? '0' + (date.getMonth() + 1) + '-' : date.getMonth() + 1 + '-';
			let D = date.getDate() < 10 ? '0' + date.getDate() + ' ' : date.getDate() + ' ';
			let h = date.getHours() < 10 ? '0' + date.getHours() + ':' : date.getHours() + ':';
			let m = date.getMinutes() < 10 ? '0' + date.getMinutes() + ':' : date.getMinutes() + ':';
			let s = date.getSeconds() < 10 ? '0' + date.getSeconds() : date.getSeconds();
			return Y + M + D + h + m + s;
		},
    // 显示创建工单弹窗
    showCreateModal() {
      this.showModal = true;
    },
	cancleModal(){
		console.log("==cancleModal=")
		 this.showModal = false;
	},
    // 提交工单
    submitOrder() {
      if (this.newOrderContent.trim() === "") {
        uni.showToast({
          title: "工单内容不能为空",
          icon: "none",
        });
        return;
      }
	  const param={"content":this.newOrderContent.trim()}
	  http.post("/evaluateFeekBack/add", param).then(res => {
	      if( res.code==200){
	  		this.findList()
			this.newOrderContent = ""; // 清空输入框
			this.showModal = false; // 关闭弹窗
	      } else{
	  		wx.showToast({
	  		    title: res.message,
	  		    icon: 'none',
	  		    duration: 1000
	  		})
	  	}
	  })
    },
	findList(){
	http.get("/evaluateFeekBack/findMyList", null).then(res => {
	    if( res.code==200){
			this.orderList=res.data
	     
	    } else{
			wx.showToast({
			    title: res.message,
			    icon: 'none',
			    duration: 1000
			})
		}
	})
	},
  },
};
</script>

<style lang="scss" scoped>
.container {
  padding: 20rpx;
  padding-bottom: 100rpx; // 避免底部按钮遮挡内容
}

.order-item {
  padding: 20rpx;
  margin-bottom: 20rpx;
  background-color: #fff;
  border-radius: 10rpx;
  box-shadow: 0 2rpx 10rpx rgba(0, 0, 0, 0.1);
  position: relative;
}

.top-section {
  margin-bottom: 20rpx;

  .content {
    font-size: 28rpx;
    color: #333;
    display: block;
    margin-bottom: 10rpx;
  }

  .create-time {
    font-size: 24rpx;
    color: #999;
  }
}

.bottom-section {
  margin-top: 20rpx;

  .deal-result {
    font-size: 26rpx;
    color: #666;
  }
}

.status-section {
  
  
  transform: translateY(-50%);
}

.footer {
  position: fixed;
  bottom: 0;
  left: 0;
  right: 0;
  padding: 20rpx;
  background-color: #fff;
  box-shadow: 0 -2rpx 10rpx rgba(0, 0, 0, 0.1);
  z-index: 100;
}
.left-section{
	position: relative;
  padding-right: 120rpx; // 与右侧按钮的间距
}
 .bottom-section{
  padding-right: 120rpx; // 与右侧按钮的间距
 }

.right-section {
  flex: 2; // 右侧占 2 份
  display: flex;
  justify-content: flex-end; // 按钮靠右对齐
}
.deal_tag{
	position: absolute;
	right: 10px;
	display: flex;
	justify-content: center;
	text-align: center;
}
</style>