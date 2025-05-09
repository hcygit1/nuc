<template>
  <view class="chat-container" >
    <!-- 聊天内容区域 -->
   <div  class="chat-content" id="box"  ref="scrollBox" style="height: 100vh;">
	   <scroll-view >
	   		<view v-for="(message, index) in userInfoList" :key="index" :id="'msg-' + message.id" class="message-item">
	   		  <!-- 对方消息（左侧） -->
	   		  <view v-if="!message.isMe" class="message-left">
	   		    <image class="avatar" src="/static/logo.png"></image>
	   		    <view class="message-text">{{ message.info }}</view>
	   		  </view>
	   		  <!-- 我的消息（右侧） -->
	   		  <view v-else class="message-right">
	   		    <view class="message-text">{{ message.info }}</view>
	   		    <image class="avatar" src="/static/logo.png"></image>
	   		  </view>
	   		</view>
	   </scroll-view>
   </div>

    <!-- 输入框和发送按钮 -->
    <view class="input-area">
		<view style="width: 80%;">
			  <u-input  v-model="inputMessage"  placeholder="请输入消息" ></u-input>
		</view>
		<view style="padding-left:2%;width: 18%;">
			   <u-button @click="sendMessage" type="primary"  style="margin-right: 10rpx !important;">发送</u-button>
		</view>
    </view>
  </view>
</template>
<script>
	import http from '@/utils/http';
export default {
  data() {
    return {
		scrollTop: 0, // 滚动位置
		  //发送和输入显隐
		  timerId:null,
      inputMessage: '', // 输入框内容
      userInfoList: [],
	  autoScroll:'',
	  isScrolledUp: false,
    };
  },
  methods: {
	  
	   handleScroll(event) {
	       const chatWindow = event.target;
	            const scrollTop = chatWindow.scrollTop;
	            const scrollHeight = chatWindow.scrollHeight;
	            const clientHeight = chatWindow.clientHeight;
	      
	            // 判断用户是否向上滚动
	            if (scrollTop < this.scrollTop) {
	              this.isScrolledUp = true;
	            } else if (scrollTop + clientHeight >= scrollHeight) {
	              this.isScrolledUp = false;
	            }
	      
	            this.scrollTop = scrollTop;
	      },
		sendMessage() {
		  if (this.inputMessage.trim() === '') {
				uni.showToast({
				  title: '消息不能为空',
				  icon: 'none',
				});
				return;
		  }

   http.post("/enterpriseProjectChatRecord/add",{"content":this.inputMessage,"roles":"common"}).then(result => {
        if (result && result.code === 200) {
         // 将消息添加到消息列表
         this.userInfoList.push({
           info: this.inputMessage,
           isMe: true,
         });
		   this.inputMessage = '';
	   this.$nextTick(() => { // 一定要用nextTick
	            this.setPageScrollTo();
	            //页面滚动条距离顶部高度等于这个盒子的高度
	            this.$refs.scrollBox.scrollTop = this.$refs.scrollBox.scrollHeight;
	          })
          // 清空输入框
        
        } else {
        uni.showToast({
           title: result.message,
           icon: 'none',
           duration: 2000
        })
        }
      })
    },
		
	   //定时轮询对方的未读状态信息添加到userInfoList数组中，并将数据置为已读
		startTimer() {
		  const _this=this;
		  _this.timerId = setInterval(() => {
			  http.get("/enterpriseProjectChatRecord/findMyUnReadMessage",null).then(result => {
			        if( result.code==200){
			  		  if(result.data.length>0){
			  		    _this.userInfoList=_this.userInfoList.concat(result.data);
			  		  _this.$nextTick(() => { // 一定要用nextTick
			  		                  _this.setPageScrollTo();
			  		                  //页面滚动条距离顶部高度等于这个盒子的高度
			  		                  _this.$refs.scrollBox.scrollTop = _this.$refs.scrollBox.scrollHeight;
			  		                })
					  }
			        }else{
			          uni.showToast({
			            title: result.message,
			            icon: 'none',
			            duration: 2000
			         })
			       }  
			    })
		  }, 1500);
		},
		    //滚动条到达顶部
		    srTop() {
		      //判断：当滚动条距离顶部为0时代表滚动到顶部了
		      if (this.$refs.scrollBox.scrollTop == 0) {
		      }
		    },
		    //滚动条默认滚动到最底部
		    setPageScrollTo(s, c) {
		      //获取中间内容盒子的可见区域高度
		      this.scrollTop = document.querySelector("#box").offsetHeight;
		      setTimeout((res) => {
		        //加个定时器，防止上面高度没获取到，再获取一遍。
		        if (this.scrollTop != this.$refs.scrollBox.offsetHeight) {
		          this.scrollTop = document.querySelector("#box").offsetHeight;
		        }
		      }, 100);
		      //scrollTop：滚动条距离顶部的距离。
		      //把上面获取到的高度座位距离，把滚动条顶到最底部
		      this.$refs.scrollBox.scrollTop = this.scrollTop;
		      //判断是否有滚动条,有滚动条就创建一个监听滚动事件，滚动到顶部触发srTop方法
		      if (this.$refs.scrollBox.scrollTop > 0) {
		        this.$refs.scrollBox.addEventListener("scroll", this.srTop);
		      }
		    },
		 //初始化历史记录
		findList(){
		  const _this=this;
		  http.get("/enterpriseProjectChatRecord/findList",null).then(res => {
				if( res.code==200){
				  if(res.data.length>0){
					console.log("==res.data=="+JSON.stringify(res.data))
				   _this.userInfoList.push(...res.data);
				 // 点击用户切换数据时先清除监听滚动事件，防止出现没有历史数据的用户，滚动条为0，会触发滚动事件
				     // _this.$refs.scrollBox.removeEventListener("scroll", _this.srTop);
				      //点击变色
				      // 直接调用不生效：因为你历史数据刚给，渲染的时候盒子高度还没有成型，所以直接调用拿不到，用个定时器让他在下一轮循环中调用，盒子就已经生成了
				      _this.$nextTick(() => { // 一定要用nextTick
				        _this.setPageScrollTo();
				        //页面滚动条距离顶部高度等于这个盒子的高度
				        _this.$refs.scrollBox.scrollTop = _this.$refs.scrollBox.scrollHeight;
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
			}
	},
	onUnload() {
	    // 清除定时任务
	    if (this.timerId) {
	      clearInterval(this.timerId);
	      this.timerId = null;
	      console.log("定时任务已清除");
	    }
	  },
	  onShow() {
		   console.log("= 开始定时任务==")
		   this.findList()
	  	this.startTimer()
	  },
	  onHide() {
		  console.log("= 清除定时任务==")
	  	// 清除定时任务
	  	if (this.timerId) {
	  	  clearInterval(this.timerId);
	  	  this.timerId = null;
	  	  console.log("定时任务已清除");
	  	}
	  },
};
</script>

<style scoped>
	
	/* H5 样式 */
	/* #ifdef H5 */
	.chat-container {
	  display: flex;
	  flex-direction: column;
	  height: 85vh;
	}
	
	/* #endif */
	
	/* 小程序样式 */
	/* #ifdef MP-WEIXIN */
	.chat-container {
	  display: flex;
	  flex-direction: column;
	  height: 100vh;
	}
	/* #endif */
	
	

.chat-content {
  flex: 1;
  padding: 20rpx;
  overflow-y: auto;
}

.message-item {
  margin-bottom: 20rpx;
}

/* 左侧消息（对方） */
.message-left {
  display: flex;
  align-items: flex-start;
}

.message-left .avatar {
  width: 80rpx;
  height: 80rpx;
  border-radius: 50%;
}

.message-left .message-text {
  margin-left: 20rpx;
  background-color: #f1f1f1;
  padding: 10rpx 20rpx;
  border-radius: 10rpx;
  max-width: 60%;
}

/* 右侧消息（自己） */
.message-right {
  display: flex;
  align-items: flex-start;
  justify-content: flex-end; /* 靠右对齐 */
  padding-right: 15px;
}

.message-right .avatar {
  width: 80rpx;
  height: 80rpx;
  border-radius: 50%;
}

.message-right .message-text {
  margin-right: 20rpx;
  background-color: #007aff;
  color: #fff;
  padding: 10rpx 20rpx;
  border-radius: 10rpx;
  max-width: 60%;
}

/* 输入框和发送按钮 */
.input-area {
  display: flex;
  align-items: center;
  padding-left: 20rpx;
  padding-right: 20rpx;
  background-color: #f8f8f8;
}

.input-box {
  flex: 1;
  margin-right: 20rpx;
}

.send-btn {
	margin-left: 10rpx;
  width: 120rpx;
}
</style>