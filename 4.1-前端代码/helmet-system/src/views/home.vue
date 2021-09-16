<template>
  <div class="bodyContainer">
    <el-dialog
      title="报警信息"
      :visible.sync="alarmDialog"
      center
      width="30%">
      <span>{{warningInfo.warning_why}}</span>
    </el-dialog>
    <div class="leftContainer">
      <div class="mapContainer">
        <location-map></location-map>
      </div>
      <div class="hatInfoContainer">
<!--        七天报警数量-->
        <div class="lineContainer">
          <line-chart></line-chart>
        </div>
<!--        帽子在线率-->
        <div class="onlineRateContainer">
          <pie-chart></pie-chart>
        </div>
      </div>
    </div>
    <div class="rightContainer">
      <div class="titleContainer">报警信息</div>
      <div class="contentContainer">
        <alarm-info ref="alarmInfo"></alarm-info>
      </div>
    </div>
  </div>
</template>

<script>
import LocationMap from '@/components/homeComponents/locationMap'
import AlarmInfo from '@/components/homeComponents/alarmInfo'
import PieChart from '@/components/homeComponents/pieChart'
import LineChart from '@/components/homeComponents/lineChart'
import { closeWebsocket, sendWebsocket } from '@/utils/socket'
export default {
  name: 'home',
  components: { LineChart, PieChart, AlarmInfo, LocationMap },
  data () {
    return {
      alarmDialog: false,
      warningInfo: {
      }
    }
  },
  created () {
    closeWebsocket()
    // 跟后端协商，需要什么参数数据给后台
    const obj = 'hello'
    // 发起ws请求
    sendWebsocket('ws://10.147.20.74:2002/websocket/home', obj, this.wsMessage, this.wsError)
  },
  beforeDestroy () {
    closeWebsocket()
  },
  methods: {
    wsMessage (data) {
      console.log('data', data)
      // 这里写拿到数据后的业务代码
      if (data.warning !== undefined) {
        this.alarmDialog = true
        this.warningInfo = data.warning
        this.$refs.alarmInfo.refreshTable(this.alarmDialog)
      }
    },
    // ws连接失败，组件要执行的代码
    wsError () {
      // 比如取消页面的loading
    }
  },
  watch: {}
}
</script>

<style scoped lang="less">
.bodyContainer {
  width: 95%;
  height: 550px;
  position: absolute;
  left: 2.5%;
  margin-top: 20px;
  display: flex;
  .leftContainer {
    width: 1100px;
    .mapContainer {
      width: 100%;
      height: 400px;
      margin-bottom: 20px;
    }
    .hatInfoContainer {
      width: 100%;
      height: 130px;
      display: flex;
      .lineContainer {
        width: 60%;
        height: 100%;
      }
      .onlineRateContainer {
        width: 40%;
        height: 100%;
      }
    }
  }
  .rightContainer {
    width: 400px;
    margin-left: 30px;
    .titleContainer {
      height: 30px;
      font-size: 24px;
      line-height: 30px;
      color: aqua;
      font-family: "KaiTi",serif;
    }
    .contentContainer {
      height: 400px;
    }
  }
}
/deep/.el-dialog, .el-pager li {
  background-image: radial-gradient(rgba(241, 37, 37, 0.75),rgba(255,63,45,0.75));
}
/deep/.el-dialog__title {
  color: #30eee9;
}
/deep/.el-dialog__body {
  color: #30eee9;
}
</style>
