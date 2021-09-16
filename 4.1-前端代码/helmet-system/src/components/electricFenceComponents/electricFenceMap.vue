<template>
  <baidu-map class="map" :center="center" :scroll-wheel-zoom="true" :zoom="zoom" @ready="handler">
    <bm-polygon
      :path="polygonPathData.point"
      fill-color="#ff7675"
      stroke-color="blue"
      :stroke-opacity="0"
      :stroke-weight="2">
    </bm-polygon>
  </baidu-map>
</template>

<script>
export default {
  name: 'electricFenceMap',
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
.map {
  width: 100%;
  height: 100%;
}
/deep/.BMap_cpyCtrl {
  display:none;
}
/deep/.anchorBL {
  display:none;
}
</style>
