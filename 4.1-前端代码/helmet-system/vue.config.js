module.exports = {
  devServer: {
    // host: 'localhost',
    port: 1002,
    hotOnly: true,
    //  代理服务器
    proxy: {
      '/': {
        target: 'http://10.147.20.74:2002',
        ws: false,
        changeOrigin: true,
        pathReWrite: {
          '^/': '/'
        }
      }
    }
  }
}
