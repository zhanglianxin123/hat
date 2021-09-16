import Vue from 'vue'
import VueRouter from 'vue-router'
import Login from '@/views/Login'

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'Login',
    component: Login
  },
  {
    path: '/homePage',
    name: 'homePage',
    meta: {
      requireAuth: true // 该路由项需要权限校验
    },
    component: () => import('../views/homePage'),
    children: [
      {
        path: '/home',
        name: 'home',
        meta: {
          requireAuth: true // 该路由项需要权限校验
        },
        component: () => import('../views/home')
      },
      {
        path: '/electricFence',
        name: 'electricFence',
        meta: {
          requireAuth: true // 该路由项需要权限校验
        },
        component: () => import('../views/electricFence')
      },
      {
        path: '/personnelPosition',
        name: 'personnelPosition',
        meta: {
          requireAuth: true // 该路由项需要权限校验
        },
        component: () => import('../views/personnelPosition')
      },
      {
        path: '/personnelTemperature',
        name: 'personnelTemperature',
        meta: {
          requireAuth: true // 该路由项需要权限校验
        },
        component: () => import('../views/personnelTemperature')
      },
      {
        path: '/alarm',
        name: 'alarm',
        meta: {
          requireAuth: true // 该路由项需要权限校验
        },
        component: () => import('../views/alarm')
      },
      {
        path: '/sign',
        name: 'sign',
        meta: {
          requireAuth: true // 该路由项需要权限校验
        },
        component: () => import('../views/sign')
      },
      {
        path: '/personnelManagement',
        name: 'personnelManagement',
        meta: {
          requireAuth: true // 该路由项需要权限校验
        },
        component: () => import('../views/personnelManagement')
      },
      {
        path: '/deviceManagement',
        name: 'deviceManagement',
        meta: {
          requireAuth: true // 该路由项需要权限校验
        },
        component: () => import('../views/deviceManagement')
      }
    ]
  }
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})
router.beforeEach((to, from, next) => {
  if (to.meta.requireAuth) { // 判断该路由是否需要登录权限
    if (localStorage.token) { // 获取当前的token是否存在
      next()
    } else {
      next({
        path: '/', // 将跳转的路由path作为参数，登录成功后跳转到该路由
        query: { redirect: to.fullPath }
      })
    }
  } else { // 如果不需要权限校验，直接进入路由界面
    next()
  }
})
const originalPush = VueRouter.prototype.push
VueRouter.prototype.push = function push (location) {
  return originalPush.call(this, location).catch(err => err)
}
export default router
