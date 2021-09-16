import axios from 'axios'
import { Message } from 'element-ui'

// 封装axios 配置拦截器

const instance = axios.create({
  baseURL: 'http://10.147.20.74:2002',
  timeout: 3000
})

//  请求拦截器
instance.interceptors.request.use(config => {
  config.data = JSON.stringify(config.data)
  config.headers = {
    'Content-Type': 'application/json'
  }
  if (localStorage.token) {
    //  携带token
    config.headers.token = localStorage.token
  }
  return config
}, error => {
  return Promise.reject(error)
})

//  响应拦截器
instance.interceptors.response.use(res => {
  return res.data
}, error => {
  if (error && error.response) {
    switch (error.response.status) {
      case 400:
        error.message = '错误请求'
        break
      case 401:
        error.message = '未授权，请重新登录'
        break
      case 403:
        error.message = '拒绝访问'
        break
      case 404:
        error.message = '请求错误,未找到该资源'
        window.location.href = '/NotFound'
        break
      case 405:
        error.message = '请求方法未允许'
        break
      case 408:
        error.message = '请求超时'
        break
      case 500:
        error.message = '服务器端出错'
        break
      case 501:
        error.message = '网络未实现'
        break
      case 502:
        error.message = '网络错误'
        break
      case 503:
        error.message = '服务不可用'
        break
      case 504:
        error.message = '网络超时'
        break
      case 505:
        error.message = 'http版本不支持该请求'
        break
      default:
        error.message = `连接错误${error.response.status}`
    }
  } else {
    // 超时处理
    if (JSON.stringify(error).includes('timeout')) {
      Message.error('服务器响应超时，请刷新当前页')
    }
    error.message = '连接服务器失败'
  }

  Message.error(error.message)
  return Promise.resolve(error.response)
})

export default instance
