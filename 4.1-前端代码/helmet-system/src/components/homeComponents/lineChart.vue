<template>
  <div>
    <div :id="lineChart" style="width: 650px;height: 130px"></div>
  </div>
</template>

<script>
import * as echarts from 'echarts'

export default {
  name: 'lineChart',
  data () {
    return {
      charts: null,
      lineChart: 'lineChart'
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
      this.charts = echarts.init(document.getElementById(this.lineChart))
      const dataArr = [12, 13, 10, 34, 90, 30, 20, 82, 11, 34, 90, 33]
      const dateArr = ['7月6号', '7月7号', '7月8号', '7月9号', '7月10号', '7月11号', '7月12号']
      const fontColor = '#30eee9'
      const options = {
        title: {
          text: '七天内\n报警变化',
          textStyle: {
            color: '#4b7bec'
          }
        },
        grid: {
          left: '25%',
          right: '10%',
          top: '25%',
          bottom: '15%'
          // containLabel: true
        },
        tooltip: {
          show: true,
          trigger: 'item'
        },
        xAxis: [{
          type: 'category',
          boundaryGap: false,
          axisLabel: {
            color: fontColor
          },
          axisLine: {
            show: true,
            lineStyle: {
              color: '#397cbc'
            }
          },
          axisTick: {
            show: false
          },
          splitLine: {
            show: false,
            lineStyle: {
              color: '#195384'
            }
          },
          data: dateArr
        }],
        yAxis: [{
          type: 'value',
          name: '',
          min: 0,
          max: 100,
          axisLabel: {
            formatter: '{value}',
            textStyle: {
              color: '#2ad1d2'
            }
          },
          axisLine: {
            lineStyle: {
              color: '#27b4c2'
            }
          },
          axisTick: {
            show: false
          },
          splitLine: {
            show: true,
            lineStyle: {
              color: '#11366e'
            }
          }
        }],
        series: [{
          name: '报警数',
          type: 'line',
          symbol: 'circle',
          symbolSize: 8,
          itemStyle: {
            normal: {
              color: '#0092f6',
              lineStyle: {
                color: '#0092f6',
                width: 1
              }
            }
          },
          markPoint: {
            data: [{
              type: 'max',
              name: '最大值',
              symbolSize: 20
            },
            {
              type: 'min',
              name: '最小值',
              symbolSize: 20
            }],
            itemStyle: {
              normal: {
                color: '#0092f6'
              }
            }
          },
          data: dataArr
        }]
      }
      this.charts.setOption(options)
    }
  }
}
</script>

<style scoped lang="less">

</style>
