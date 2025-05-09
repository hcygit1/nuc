
const ApiRootUrl ="http://192.168.104.251:8082"; 
/**
 * GET请求封装
 */
function get(url, data = {}) {
  var contentType = 'application/x-www-form-urlencoded';
  return request(ApiRootUrl+url, data, 'GET', contentType)
}
 
/**
 * POST请求封装
 */
function post(url, data = {}) {
  var contentType = 'application/json';
  return request(ApiRootUrl+url, data, 'POST', contentType)
}
 
/**
 * PUT请求封装
 */
function put(url, data = {}) {
  var contentType = 'application/json';
  return request(ApiRootUrl+url, data, 'PUT', contentType)
}
 
/**
 * DELETE请求封装
 */
function del(url, data = {}) {
  var contentType = 'application/json';
  return request(ApiRootUrl+url, data, 'DELETE', contentType)
}
 

function request(url, data = {}, method, contentType) {
  return new Promise(function(resolve, reject) {
    uni.request({
      url: url,
      data: data,
      method: method,
      header: {
        'Content-Type': contentType,
        'Authorization': uni.getStorageSync("Authorization"),
      },
      success: function(res) {
        console.log("==    接口状态：" + res.statusCode);
        // console.log('===========================================================')
        if (res.statusCode == 200) {
          resolve(res.data);
        } else if (res.statusCode == 401) {
          uni.navigateTo({
            url: '/pages/index/login'
          })        
        } else {
          wx.showToast({
            title: '系统异常',
            icon: 'none',
            duration: 400
        })
          reject("请求失败：" + res.statusCode)
        }
      },
      fail: function(err) {
        //服务器连接异常
        console.log('=============================================================')
        console.log('==    接口地址：' + url)
        console.log('==    接口参数：' + JSON.stringify(data))
        console.log('==    请求类型：' + method)
        console.log("==    服务器连接异常")
        console.log('=============================================================')
        uni.showToast({
          title: '服务器连接异常，请检查网络再试',
          icon: 'none',
          duration: 400
      })
        reject("服务器连接异常，请检查网络再试")
      }
    })
  });
}

module.exports = {
  request,
  get,
  post, 
  put,
  del
}