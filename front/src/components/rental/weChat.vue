<template>
  <div class="chat-container">
    <!-- 左侧聊天列表 -->
    <div class="chat-list">
      <el-scrollbar>
        <div
            v-for="user in chatList"
            :key="user.id"
            class="chat-item"
            :class="{ 'selected': selectedUserId === user.userId }"
            @click="selectUser(user)"
        >
          <!-- 头像 -->
          <el-avatar :src="user.imageUrl" :size="70" class="avatar"></el-avatar>
          <!-- 用户信息 -->
          <div class="user-info">
            <div class="username">{{ user.username }} </div>
            <div class="phone">{{ user.mobile }}</div>
            <div class="email">{{ user.mail }}</div>
          </div>
        </div>
      </el-scrollbar>
    </div>

    <!-- 右侧聊天区域 -->
    <div class="chat-area">
      <!-- 聊天记录 -->
      <div class="chat-history" id="box"  ref="scrollBox">
        <el-scrollbar>
          <div
              v-for="(message, index) in currentChat"
              :key="index"
              :class="['message', message.sender === 'user' ? 'admin-message' : 'user-message']"
          >
            <div class="message-time">{{ message.time|global_time_filter }}</div>
            <div class="message-content">{{ message.content }}</div>

          </div>
        </el-scrollbar>
      </div>

      <!-- 输入框 -->
      <div class="chat-input">
        <el-input
            v-model="inputMessage"
            type="textarea"
            :rows="4"
            placeholder="请输入消息"
            @keyup.enter.native="sendMessage">

        </el-input>
        <el-button
            type="primary"
            class="send-button"
            @click="sendMessage"
        >发送</el-button>
      </div>
    </div>
  </div>
</template>

<script>
import wechat from '../../api/rental/wechat'
export default {
  data() {
    return {
      // 聊天列表数据
      chatList: [],
      selectedUserId:null,
      // 当前选中的用户
      selectedUser: null,
      // 输入框内容
      inputMessage: '',

      //滚动条距离顶部距离
      scrollTop: 0,
      //发送和输入显隐
      timerId:null
    };
  },
  computed: {
    // 当前聊天记录
    currentChat() {
      return this.selectedUser ? this.selectedUser.messages : [];
    },
  },
  mounted() {
    this.startTimer()
  },
  created() {
  this.findList()
    },
  methods: {
    findList(){
      const _this=this;
      wechat.findWechatRcordAndHis().then(function (result) {
        if (result && result.code === 200) {
          _this.chatList=result.data
          if(_this.chatList.length>0){
            _this.selectUser(_this.chatList[0])
          }
        }else {
          _this.$message.error(result.message)
        }
      }, function (err) {
        _this.$message.error(err.toString())
      })
    },
    // 选择用户
    selectUser(user) {
      this.selectedUserId = user.userId;
      this.selectedUser = user;
      // 点击用户切换数据时先清除监听滚动事件，防止出现没有历史数据的用户，滚动条为0，会触发滚动事件
      this.$refs.scrollBox.removeEventListener("scroll", this.srTop);
      //点击变色
      // 直接调用不生效：因为你历史数据刚给，渲染的时候盒子高度还没有成型，所以直接调用拿不到，用个定时器让他在下一轮循环中调用，盒子就已经生成了
      this.$nextTick(() => { // 一定要用nextTick
        this.setPageScrollTo();
        //页面滚动条距离顶部高度等于这个盒子的高度
        this.$refs.scrollBox.scrollTop = this.$refs.scrollBox.scrollHeight;
      })
    },
    // 发送消息
    sendMessage() {
      const _this=this;
      if (_this.inputMessage.trim() === '') return;
      const newMessage = {
        sender: 'admin',
        content: this.inputMessage,
        time: new Date().toLocaleString(),
      };
      const parapAdd={
          "toUserId":this.selectedUser.userId,
          "content":_this.inputMessage.trim(),
          "roles":'manage'
      }
      wechat.add(parapAdd).then(function (result) {
        if (result && result.code === 200) {
          _this.selectedUser.messages.push(newMessage);
          _this.inputMessage = '';
          _this.$nextTick(() => { // 一定要用nextTick
            _this.setPageScrollTo();
            //页面滚动条距离顶部高度等于这个盒子的高度
            _this.$refs.scrollBox.scrollTop = _this.$refs.scrollBox.scrollHeight;
          })
        }else {
          _this.$message.error(result.message)
        }
      }, function (err) {
        _this.$message.error(err.toString())
      })
    },
    //定时轮询对方的未读状态信息添加到userInfoList数组中，并将数据置为已读
    startTimer() {
      const _this=this;
      _this.timerId = setInterval(() => {
        if(_this.selectedUser!=null){
          wechat.findSysUnReadMessage({"userId":_this.selectedUser.userId}).then(function (result) {
            if (result && result.code === 200) {
              if(result.data.length>0){
                _this.selectedUser.messages=_this.selectedUser.messages.concat(result.data);
                _this.$nextTick(() => { // 一定要用nextTick
                  _this.setPageScrollTo();
                  //页面滚动条距离顶部高度等于这个盒子的高度
                  _this.$refs.scrollBox.scrollTop = _this.$refs.scrollBox.scrollHeight;
                })
              }

            } else {
              _this.$message.error(result.message)
            }
          }, function (err) {
            _this.$message.error(err.toString())
          })
        }
      }, 1500);
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
    //滚动条到达顶部
    srTop() {
      //判断：当滚动条距离顶部为0时代表滚动到顶部了
      if (this.$refs.scrollBox.scrollTop == 0) {
      }
    },
    beforeDestroy() {
      if (this.timerId) {
        clearInterval(this.timerId);
        this.timerId = null;
      }
    },
  },
};
</script>

<style scoped>
.chat-container {
  display: flex;
  height: 90vh;
  overflow: hidden; /* 禁止整体页面上下滑动 */
}

.chat-list {
  width:13%;
  border-right: 1px solid #e4e7ed;
  overflow: auto;
}

.chat-item {
  display: flex;
  align-items: center;
  padding: 10px;
  cursor: pointer;
  border-bottom: 1px solid #e4e7ed;
}

.chat-item:hover {
  background-color: #f5f7fa;
}

.avatar {
  margin-right: 10px;
}

.user-info {
  flex: 1;
}

.username {
  font-weight: bold;
}

.phone,
.email {
  font-size: 12px;
  color: #909399;
}

.chat-area {
  flex: 1;
  display: flex;
  flex-direction: column;
  overflow: hidden; /* 禁止聊天区域上下滑动 */
}

.chat-history {
  flex: 7; /* 占70% */
  padding: 10px;
  overflow-y: auto; /* 允许聊天记录上下滑动 */
  border-bottom: 1px solid #e4e7ed;
}

.message {
  margin-bottom: 10px;
}

.message-content {
  padding: 10px;
  border-radius: 5px;
  max-width: 40%;
}

.user-message .message-content {
  background-color: #f0f0f0;
  margin-left: auto;
}

.admin-message .message-content {
  background-color: #409eff;
  color: white;
  margin-right: auto;
}

.message-time {
  font-size: 12px;
  color: #909399;
  margin-top: 5px;
}

.chat-input {
  display: flex;
  padding: 10px;
  background-color: #f5f7fa;
}

.send-button {
  margin-left: 10px;
}
.chat-item.selected {
  background-color: #e0f7fa; /* 选中时的背景颜色 */
}
.el-scrollbar__wrap {
  overflow-y: auto !important;
  height: 100% !important;
}
</style>