<template>
  <bm-marker
    @click="infoWindowOpen"
    :position="markerPoint.point"
    :icon="{url: 'http://10.168.200.65:8082/hys/icon/anquanmao.png', size: {width: 32, height: 26}}">
    <bm-info-window :show="show" @close="infoWindowClose" @open="infoWindowOpen">
      <p>工号:{{userInfo.user_id}}</p>
      <p>设备编号:{{userInfo.equipment_id}}</p>
      <p>姓名:{{userInfo.user_name}}</p>
    </bm-info-window>
  </bm-marker>
</template>

<script>
import http from '@/utils/http'

export default {
  name: 'markerMap',
  props: ['markerPoint'],
  data () {
    return {
      show: false,
      userInfo: {
        user_id: '',
        user_name: '',
        equipment_id: 0
      }
    }
  },
  created () {
  },
  methods: {
    infoWindowClose () {
      this.show = false
    },
    infoWindowOpen () {
      this.show = true
      http.post('/index/getById', this.markerPoint.user_id).then(resp => {
        this.userInfo = resp.data
      })
    }
  }
}
</script>

<style scoped lang="less">

</style>
