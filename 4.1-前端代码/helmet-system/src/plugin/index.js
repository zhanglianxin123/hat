// 引入的插件

//  注册ElementUI组件库
import Vue from 'vue'
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
import BaiduMap from 'vue-baidu-map'
Vue.use(ElementUI)
Vue.use(BaiduMap, {
  ak: 'HM49mzUmv5LgpvqQtCdRNUZTdoEp1z79'
})
