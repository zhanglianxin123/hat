<template>
  <baidu-map class="map" :center="center" :zoom="zoom" :scroll-wheel-zoom="true" @ready="handler">
    <bml-lushu
      :path="path"
      :play="true"
      :speed="500"
      :icon="icon"
      @stop="reset"
      :autoView="true">
    </bml-lushu>
  </baidu-map>
</template>

<script>
import { BmlLushu } from 'vue-baidu-map'
export default {
  name: 'positionMap',
  components: { BmlLushu },
  data () {
    return {
      content: '安全帽',
      center: '',
      path: [],
      icon: {
        url: 'http://10.168.200.65:8082/hys/icon/anquanmao.png',
        size: { width: 52, height: 26 },
        opts: { anchor: { width: 27, height: 13 } }
      },
      zoom: 0
    }
  },
  created () {
  },
  methods: {
    handler () {
      this.center = '赣南科技学院'
      this.zoom = 17
    },
    reset () {
    },
    getPath (path) {
      this.center = path[0]
      path.forEach(item => {
        this.path.push(item)
      })
      this.reset()
    }
  }
}
</script>

<style scoped lang="less">
/deep/.BMap_cpyCtrl {
  display:none;
}
/deep/.anchorBL {
  display:none;
}
</style>
