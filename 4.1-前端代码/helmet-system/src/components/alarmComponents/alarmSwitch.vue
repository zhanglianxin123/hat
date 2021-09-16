<template>
  <div>
    <div class="switchBox boxBorder">
      <div class="title fontStyle">{{title}}报警</div>
      <div class="switch">
        <span class="fontStyle">报警开关</span>
        <el-switch
          v-model="alarmSwitchData.switch_state"
          @change="getState"
          :active-value = 1
          :inactive-value = 0
          inactive-color="#6261ba"
          active-color="#EFB139FF">
        </el-switch>
      </div>
      <div class="footerBorder"></div>
    </div>
  </div>
</template>

<script>
import http from '@/utils/http'

export default {
  name: 'alarmSwitch',
  props: ['alarmSwitchData'],
  data () {
    return {
      title: this.alarmSwitchData.switch_name
    }
  },
  methods: {
    getState (state) {
      http.post('/warning-switch/setBYid', { switch_id: this.alarmSwitchData.switch_id, switch_state: state })
        .then(resp => {
          if (state === 1) {
            this.$message.success('开启' + resp.message)
          }
          if (state === 0) {
            this.$message.success('关闭' + resp.message)
            this.$emit('listenClose', true)
          }
        })
    }
  }
}
</script>

<style scoped lang="less">
.switchBox {
  width: 270px;
  height: 160px;
  .title {
    font-size: 24px;
    text-align: center;
    top: 30px;
    position: relative
  }
  .switch {
    position: relative;
    top: 40px;
    text-align: center;
    span {
      margin-right: 20px;
      font-size: 20px;
    }
  }
}
/deep/.el-switch__core:after {
  background-color: #9880fd;
}
</style>
