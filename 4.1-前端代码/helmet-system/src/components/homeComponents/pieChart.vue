<template>
  <div>
    <div :id="pieChart" style="height: 130px;width: 400px"></div>
  </div>
</template>

<script>
import * as echarts from 'echarts'
export default {
  name: 'pieChart',
  data () {
    return {
      charts: null,
      pieChart: 'pieChart',
      pieData: [
        { value: 1048, name: '在线人数' },
        { value: 735, name: '未在线人数' }
      ]
    }
  },
  beforeDestroy () {
    if (!this.charts) {
      return ''
    }
    this.charts.dispose()
    this.charts = null
  },
  mounted () {
    this.initCharts()
  },
  methods: {
    initCharts () {
      this.charts = echarts.init(document.getElementById(this.pieChart))
      const pieData = this.pieData
      const options = {
        color: ['#4b7bec', '#a5b1c2'],
        title: {
          text: '安全帽在线数',
          left: 'left',
          textStyle: {
            color: '#4b7bec'
          }
        },
        tooltip: {
          trigger: 'item'
        },
        grid: {
          left: 100
        },
        legend: {
          orient: 'vertical',
          left: 300,
          textStyle: {
            color: ['#4b7bec', '#a5b1c2']
          }
        },
        series: [
          {
            center: ['50%', '50%'],
            type: 'pie',
            radius: '50%',
            label: {
              textStyle: {
                color: '#4b7bec'
              }
            },
            data: pieData,
            emphasis: {
              itemStyle: {
                shadowBlur: 10,
                shadowOffsetX: 0,
                shadowColor: 'rgba(0, 0, 0, 0.5)'
              }
            }
          }
        ]
      }
      this.charts.setOption(options)
    }
  }
}
</script>

<style scoped lang="less">

</style>
