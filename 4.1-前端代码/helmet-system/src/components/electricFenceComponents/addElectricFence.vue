<template>
  <baidu-map class="map" :center="center" :scroll-wheel-zoom="true" :zoom="zoom" @click="addMarker" @ready="handler">
    <bm-polygon
      :path="polygonPath"
      fillColor="#a0cff6"
      :editing="isEditing"
      stroke-color="#fdd1f5"
      :stroke-opacity="0"
      :stroke-weight="8"
      @lineupdate="updatePolygonPath">
    </bm-polygon>
    <bm-marker v-for="(item,index) of positionData" :mass-clear="true" :position="item" :key="index"></bm-marker>
  </baidu-map>
</template>

<script>
export default {
  name: 'addElectricFence',
  data () {
    return {
      current: 0,
      polygonPath: [],
      positionData: [],
      center: '',
      zoom: 0,
      isEditing: false,
      removeClick: true,
      isSelect: true
    }
  },
  methods: {
    //  添加标注
    addMarker (e) {
      this.isSelect = false
      this.$emit('ListenAddMarker', this.isSelect)
      //  最多添加五个
      if (this.current < 5 && this.removeClick === true) {
        this.positionData.push(e.point)
        this.current += 1
      }
      if (this.current === 5) {
        this.$message.error('只能添加五个点')
      }
    },
    //  异步加载地图
    handler () {
      this.center = '赣南科技学院'
      this.zoom = 17
    },
    //  生成多边形
    polygon () {
      this.polygonPath = this.positionData
      this.$emit('ListenAddFence', this.polygonPath)
      this.removeClick = false
      this.positionData = []
    },
    //  重新选择生成多边形
    clearMarker () {
      this.positionData = []
      this.polygonPath = []
      this.current = 0
      this.removeClick = true
      this.isEditing = false
    },
    //  编辑多边形
    startEditPolygon () {
      this.isEditing = true
      this.$emit('ListenAddFence', this.polygonPath)
    },
    //  编辑多边形时更新数据
    updatePolygonPath (e) {
      this.polygonPath = e.target.getPath()
    },
    //  结束编辑
    endEditPolygon () {
      this.isEditing = false
      //  最终提交的数据
      this.$emit('ListenAddFence', this.polygonPath)
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
