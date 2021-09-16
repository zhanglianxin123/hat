<template>
  <div>
<!--导航栏-->
<!--    标题容器-->
    <div class="titleContainer"><img src="../../assets/images/globalImages/智能安全帽.png" alt=""></div>
<!--    导航菜单容器-->
    <div class="navContainer">
      <div class="navImgContainer">
        <img :id="homeId" :class="{imgWidth: homeId===current}" @click="toHome" src="../../assets/images/globalImages/首页.png" alt="">
        <img :class="{imgWidth: electricFenceId===current}" @click="toElectricFence" src="../../assets/images/globalImages/电子围栏.png" alt="">
        <img :class="{imgWidth: personnelPositionId===current}" @click="toPersonnelPosition" src="../../assets/images/globalImages/人员定位.png" alt="">
        <img :class="{imgWidth: personnelTemperatureId===current}" @click="toPersonnelTemperature" src="../../assets/images/globalImages/人员体温.png" alt="">
        <img :class="{imgWidth: alarmId===current}" @click="toAlarm" style="margin-left: 540px" src="../../assets/images/globalImages/报警.png" alt="">
        <img :class="{imgWidth: signId===current}" @click="toSign" src="../../assets/images/globalImages/签到.png" alt="">
        <img :class="{imgWidth: personnelManagementId===current}" @click="toPersonnelManagement" src="../../assets/images/globalImages/人员管理.png" alt="">
        <img :class="{imgWidth: deviceManagement===current}" @click="toDeviceManagement" src="../../assets/images/globalImages/设备管理.png" alt="">
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: 'navigationBar',
  data () {
    return {
      current: 0,
      homeId: 1,
      electricFenceId: 2,
      personnelPositionId: 3,
      personnelTemperatureId: 4,
      alarmId: 5,
      signId: 6,
      personnelManagementId: 7,
      deviceManagement: 8
    }
  },
  created () {
    if (this.$route.path === '/home') {
      this.current = 1
    }
    if (this.$route.path === '/electricFence') {
      this.current = 2
    }
    if (this.$route.path === '/personnelPosition') {
      this.current = 3
    }
    if (this.$route.path === '/personnelTemperature') {
      this.current = 4
    }
    if (this.$route.path === '/alarm') {
      this.current = 5
    }
    if (this.$route.path === '/sign') {
      this.current = 6
    }
    if (this.$route.path === '/personnelManagement') {
      this.current = 7
    }
    if (this.$route.path === '/deviceManagement') {
      this.current = 8
    }
  },
  watch: {
    $route: {
      handler () {
        if (this.$route.path === '/home') {
          this.current = 1
        }
        if (this.$route.path === '/electricFence') {
          this.current = 2
        }
        if (this.$route.path === '/personnelPosition') {
          this.current = 3
        }
        if (this.$route.path === '/personnelTemperature') {
          this.current = 4
        }
        if (this.$route.path === '/alarm') {
          this.current = 5
        }
        if (this.$route.path === '/sign') {
          this.current = 6
        }
        if (this.$route.path === '/personnelManagement') {
          this.current = 7
        }
        if (this.$route.path === '/deviceManagement') {
          this.current = 8
        }
      }
    }
  },
  methods: {
    toHome () {
      this.current = 1
      this.$router.push('/home')
    },
    toElectricFence () {
      this.current = 2
      this.$router.push('/electricFence')
    },
    toPersonnelPosition () {
      this.current = 3
      this.$router.push('/personnelPosition')
    },
    toPersonnelTemperature () {
      this.current = 4
      this.$router.push('/personnelTemperature')
    },
    toAlarm () {
      this.current = 5
      this.$router.push('/alarm')
    },
    toSign () {
      this.current = 6
      this.$router.push('/sign')
    },
    toPersonnelManagement () {
      this.current = 7
      this.$router.push('/personnelManagement')
    },
    toDeviceManagement () {
      this.current = 8
      this.$router.push('/deviceManagement')
    }
  }
}
</script>

<style scoped lang="less">
.titleContainer {
  min-width: 1500px;
  max-width: 1920px;
  img {
    width: 100%;
    height: 70px;
  }
}
.navContainer {
  min-width: 1500px;
  max-width: 1920px;
  display: flex;
  padding: 5px;
  .navImgContainer {
    height: 40px;
    img {
      width: 100px;
      margin-left: 20px;
      cursor: pointer;
    }
    .imgWidth {
      width: 120px;
    }
  }
}
</style>
