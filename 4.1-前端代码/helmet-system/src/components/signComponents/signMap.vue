<template>
  <baidu-map class="map" :center="center" :scroll-wheel-zoom="true" :zoom="zoom" @ready="handler">
    <bm-polygon
      :path="polygonPathData.point"
      fill-color="#74b9ff"
      stroke-color="#fd79a8"
      :stroke-opacity="0"
      :stroke-weight="2">
    </bm-polygon>
  </baidu-map>
</template>

<script>
export default {
  name: 'signMap',
  props: ['polygonPathData'],
  data () {
    return {
      center: {},
      zoom: 0
    }
  },
  methods: {
    handler () {
      this.center = '赣南科技学院'
      this.zoom = 17
    },
    getCurrentCenter (val) {
      this.center = val
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
