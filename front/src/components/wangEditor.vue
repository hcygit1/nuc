<template>
  <div>
    <div id="websiteEditorElem" ></div>
  </div>
</template>

<script>
import E from 'wangeditor'
export default {
  name: "wangEditor",
  props: {
    content: null, //获取从父组件中传过来的值，主要用于修改的时候获取值，并加入到富文本框中
  },
  data() {
    return {
      phoneEditor: '',
      name: '',
    }
  },
  methods: {

    initEditor(content){
      this.phoneEditor = new E('#websiteEditorElem')
      // 上传图片到服务器，base64形式
      this.phoneEditor.config.uploadImgShowBase64 = true
      // // 隐藏网络图片
      this.phoneEditor.config.showLinkImg = false;

      this.phoneEditor.config.debug = true;
      //图片上传接口
      this.phoneEditor.config.uploadImgServer = 'http://localhost:8082/bookStock/upload' // 上传图片的接口地址
      this.phoneEditor.config.uploadFileName = 'file' // formdata中的name属性，比如现在是将图片image加入到formdate，后台从image中接收到图片数据
      this.phoneEditor.config.uploadImgHooks = {
        customInsert: function (insertImg, result, editor) {
          // before: function (xhr, editor, files) {
          //     // 图片上传之前触发
          //     // xhr 是 XMLHttpRequst 对象，editor 是编辑器对象，files 是选择的图片文件

          //     // 如果返回的结果是 {prevent: true, msg: 'xxxx'} 则表示用户放弃上传
          //     // return {
          //     //     prevent: true,
          //     //     msg: '放弃上传'
          //     // }
          // },
          // success: function (xhr, editor, result) {
          //     // 图片上传并返回结果，图片插入成功之后触发
          //     // xhr 是 XMLHttpRequst 对象，editor 是编辑器对象，result 是服务器端返回的结果
          // },
          // fail: function (xhr, editor, result) {
          //     // 图片上传并返回结果，但图片插入错误时触发
          //     // xhr 是 XMLHttpRequst 对象，editor 是编辑器对象，result 是服务器端返回的结果
          // },
          // error: function (xhr, editor) {
          //     // 图片上传出错时触发
          //     // xhr 是 XMLHttpRequst 对象，editor 是编辑器对象
          // },
          // timeout: function (xhr, editor) {
          //     // 图片上传超时时触发
          //     // xhr 是 XMLHttpRequst 对象，editor 是编辑器对象
          // },

          // // 如果服务器端返回的不是 {errno:0, data: [...]} 这种格式，可使用该配置
          // // （但是，服务器端返回的必须是一个 JSON 格式字符串！！！否则会报错）
          // customInsert: function (insertImg, result, editor) {
          //     // 图片上传并返回结果，自定义插入图片的事件（而不是编辑器自动插入图片！！！）
          //     // insertImg 是插入图片的函数，editor 是编辑器对象，result 是服务器端返回的结果

          //     // 举例：假如上传图片成功后，服务器端返回的是 {url:'....'} 这种格式，即可这样插入图片：
          var url = result.data.imgUrl;
          insertImg(url)

          //     // result 必须是一个 JSON 格式字符串！！！否则报错
          // }
        }
      }
      // 创建一个富文本编辑器
      this.phoneEditor.create()
      // 修改的时候，需要富文本内容回显，则需要加入以下代码
      this.phoneEditor.txt.html(content)
      // 讲内容同步到父组件中
      this.phoneEditor.config.onchange = (html) => {
        this.info_ = html // 绑定当前逐渐地值
        this.$emit('change', this.info_) // 将内容同步到父组件中
      }
    },
    destroyedit(msg,enableE){
      this.phoneEditor.destroy();
      this.initEditor(msg);
      if(enableE==='1'){
        this.phoneEditor.$textElem.attr('contenteditable', false)
      }else {
        this.phoneEditor.$textElem.attr('contenteditable', true)
      }
    }
  },
  mounted() {
    this.initEditor();

  },

}
</script>

<style>
.w-e-menu {
  z-index: 2 !important;
}
.w-e-text-container {
  z-index: 1 !important;
}
.w-e-toolbar {
  z-index: 2 !important;
}
.w-e-text-container{
  height: 550px !important;
}

</style>

