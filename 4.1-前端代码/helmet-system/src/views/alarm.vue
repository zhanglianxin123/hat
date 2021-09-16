<template>
  <div class="bodyContainer">
    <div class="leftContainer" v-if="alarmSwitchData[0]">
      <div class="box">
        <alarm-switch @listenClose = "closeSwitch"  :alarmSwitchData="alarmSwitchData[0]"></alarm-switch>
        <alarm-switch @listenClose = "closeSwitch"  :alarmSwitchData="alarmSwitchData[1]" style="margin-top: 20px"></alarm-switch>
      </div>
      <div class="box">
        <alarm-switch @listenClose = "closeSwitch" :alarmSwitchData="alarmSwitchData[2]"></alarm-switch>
        <alarm-switch @listenClose = "closeSwitch" :alarmSwitchData="alarmSwitchData[3]" style="margin-top: 20px"></alarm-switch>
      </div>
      <div class="box" style="margin: 0">
        <alarm-switch @listenClose = "closeSwitch" :alarmSwitchData="alarmSwitchData[4]"></alarm-switch>
      </div>
    </div>
    <div class="rightContainer">
      <el-table
        :data="alarmTableData"
        empty-text="暂无报警信息"
        :header-cell-style="{borderColor: '#30eee9'}"
        height="550px"
        style="width: 100%">
        <el-table-column
          prop="user_name"
          align="center"
          label="姓名"
          width="100">
        </el-table-column>
        <el-table-column
          prop="warning_time"
          align="center"
          :show-overflow-tooltip="true"
          label="时间"
          width="140">
        </el-table-column>
        <el-table-column
          prop="warning_name"
          align="center"
          :show-overflow-tooltip="true"
          label="报警类型">
        </el-table-column>
        <el-table-column
          prop="shutdown_time"
          align="center"
          :show-overflow-tooltip="true"
          label="报警关闭时间"
          width="140">
        </el-table-column>
      </el-table>
    </div>
  </div>
</template>

<script>
import AlarmSwitch from '@/components/alarmComponents/alarmSwitch'
import http from '@/utils/http'
export default {
  name: 'alarm',
  components: { AlarmSwitch },
  data () {
    return {
      // 报警信息表格数据
      alarmTableData: [],
      alarmSwitchData: []
    }
  },
  created () {
    http.get('/warning-switch/getListWarning').then(resp => {
      this.alarmTableData = resp.data
    })
    http.get('/warning-switch/getList').then(resp => {
      this.alarmSwitchData = resp.data
    })
  },
  methods: {
    closeSwitch (val) {
      if (val) {
        http.get('/warning-switch/getListWarning').then(resp => {
          this.alarmTableData = resp.data
        })
      }
    }
  }
}
</script>

<style scoped lang="less">
.bodyContainer {
  width: 92%;
  height: 550px;
  position: absolute;
  left: 4%;
  margin-top: 20px;
  display: flex;
  .leftContainer {
    width: 900px;
    height: 100%;
    display: flex;
    .box {
      width: 270px;
      height: 550px;
      margin-right: 20px;
    }
  }
  .rightContainer {
    width: 500px;
    height: 100%;
    background-color: aqua;
  }
}
//  表格样式
/deep/.el-table{
  color: #d0d09e;
  .el-button {
    color: #05899e;
  }
  thead {
    color: #0652DD;
  }
  th {
    background-color: #001831;
  }
  tr{
    background-color: #001831;
  }
}
/deep/.el-table, .el-table__expanded-cell {
  background-color: #001831;
}
// 修改表格最底部边框颜色和高度
.el-table::before{
  height: 1px;
  background-color: #30eee9;
}
//  修改行内线的颜色
/deep/.el-table td,.building-top .el-table th.is-leaf {
  border-bottom:  1px solid #30eee9;
}
//  修改表格鼠标悬浮hover背景色
/deep/.el-table--enable-row-hover .el-table__body tr:hover>td {
  background-color: rgb(4, 25, 63);
}
//  修改表格无数据的时候背景，字体颜色
/deep/.el-table__empty-block{
  background-color: rgb(4, 25, 63);
}
/deep/.el-table__empty-text{
  color: #02a6b5;
  font-size: 22px;
}
</style>
