// const fs = require('fs');
// const path = require('path');

// module.exports = {
//   devServer: {
//     host: 'localhost', // 服务器主机
//     port: 8080,       // 服务器端口
//     https: {          // 启用 HTTPS
//       key: fs.readFileSync(path.resolve(__dirname, 'ssl/rootCA-key.pem')), // 私钥文件路径
//       cert: fs.readFileSync(path.resolve(__dirname, 'ssl/rootCA.pem')),     // 证书文件路径
//     },
//     hot: true,        // 启用热更新
//     open: true,       // 启动后自动打开浏览器
//   },
// };