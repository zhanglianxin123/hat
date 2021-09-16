<template>
  <baidu-map class="map" :center="center" :zoom="zoom" :scroll-wheel-zoom="true" @ready="handler">
    <marker-map
      v-for="item of markerPoint"
      :markerPoint="item"
      :key="item.user_id">
    </marker-map>
  </baidu-map>
</template>

<script>

import MarkerMap from '@/components/homeComponents/markerMap'
import http from '@/utils/http'
export default {
  name: 'locationMap',
  components: { MarkerMap },
  data () {
    return {
      markerPoint: [],
      center: '',
      zoom: 0
    }
  },
  created () {
    http.get('/index/getPoint')
      .then(resp => {
        this.markerPoint = resp.data
      })
  },
  methods: {
    handler () {
      this.center = '赣南科技学院'
      this.zoom = 15
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
