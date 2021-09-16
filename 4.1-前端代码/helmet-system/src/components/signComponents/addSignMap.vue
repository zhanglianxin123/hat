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
    <bm-marker v-for="(item,index) of positionData" :position="item" :key="index"></bm-marker>
  </baidu-map>
</template>

<script>
export default {
  name: 'addSignMap',
  data () {
    return {
      current: 0,
      polygonPath: [],
      positionData: [],
      center: {},
      zoom: 0,
      isEditing: false,
      newSelect: true,
      removeClick: true
    }
  },
  methods: {
    //  添加标注
    addMarker (e) {
      this.newSelect = false
      this.$emit('ListenAddSignMarker', this.newSelect)
      //  最多添加五个
      if (this.current < 5 && this.removeClick === true) {
        this.positionData.push(e.point)
        this.current += 1
      }
      if (this.current === 5) {
        this.$message.error('只能添加五个点')
      }
    },
    handler () {
      this.center = '赣南科技学院'
      this.zoom = 17
    },
    //  生成多边形
    polygon () {
      this.polygonPath = this.positionData
      this.$emit('ListenAddSignArea', this.polygonPath)
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
      this.$emit('ListenAddSignArea', this.polygonPath)
    },
    //  编辑多边形时更新数据
    updatePolygonPath (e) {
      this.polygonPath = e.target.getPath()
    },
    //  结束编辑
    endEditPolygon () {
      this.isEditing = false
      //  最终提交的数据
      this.$emit('ListenAddSignArea', this.polygonPath)
    }
  }
}
</script>

<style scoped lang="less">
.map {
  height: 500px;
}
/deep/.BMap_cpyCtrl {
  display:none;
}
/deep/.anchorBL {
  display:none;
}
</style>
