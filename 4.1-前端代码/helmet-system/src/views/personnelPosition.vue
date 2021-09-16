<template>
  <div class="bodyContainer">
    <div class="tableContainer">
<!--      查看当前定位数据-->
      <div class="currentPositionTable" v-show="isViewHistoryData">
        <el-table
          :data="personnelPositionData"
          height="550px"
          empty-text="暂无人员定位数据"
          :header-cell-style="{borderColor: '#30eee9'}"
          style="width: 100%">
          <el-table-column
            label="工号"
            prop="user_id"
            align="center"
            width="100">
          </el-table-column>
          <el-table-column
            label="设备号"
            prop="user_name"
            align="center"
            width="100">
          </el-table-column>
          <el-table-column
            label="姓名"
            prop="user_name"
            align="center"
            width="100">
          </el-table-column>
          <el-table-column label="操作" align="center">
            <template slot-scope="scope">
              <el-button
                size="mini"
                type="text"
                style="color: #686de0"
                @click="lookMotionTrajectory(scope.$index, scope.row)">查看运动轨迹</el-button>
              <el-button
                size="mini"
                type="text"
                style="color: #f9ca24"
                @click="historicalTrajectory(scope.$index, scope.row)">历史轨迹</el-button>
            </template>
          </el-table-column>
        </el-table>
      </div>
<!--      查看历史定位数据-->
      <div class="historyPositionTable" v-show="isViewHistoryData===false">
        <div class="backContainer fontStyle" @click="goBack
"><i class="el-icon-arrow-left"></i><span>返回</span></div>
        <el-table
          :data="personnelHistoryPositionData"
          height="520px"
          :header-cell-style="{borderColor: '#30eee9'}"
          empty-text="暂无历史定位数据"
          style="width: 100%">
          <el-table-column
            label="时间"
            prop="times"
            align="center"
            width="200">
          </el-table-column>
          <el-table-column label="操作" align="center">
            <template slot-scope="scope">
              <el-button
                size="mini"
                type="text"
                style="color: #686de0"
                @click="lookHistoryMotionTrajectory(scope.$index, scope.row)">查看运动轨迹</el-button>
            </template>
          </el-table-column>
        </el-table>
      </div>
    </div>
    <div class="mapContainer">
      <position-map ref="positionMap"></position-map>
    </div>
  </div>
</template>

<script>
import PositionMap from '@/components/positionComponents/positionMap'
import http from '@/utils/http'
export default {
  name: 'personnelPosition',
  components: { PositionMap },
  data () {
    return {
      //  当天轨迹
      path: [],
      //  当天日期
      nowDate: '',
      //  获取历史轨迹ID
      currentID: 0,
      //  是否展示历史轨迹列表
      isViewHistoryData: true,
      //  人员定位表格数据
      personnelPositionData: [],
      //  人员历史定位表格数据
      personnelHistoryPositionData: []
    }
  },
  created () {
    http.get('/positioning/getUserList')
      .then(resp => {
        this.personnelPositionData = resp.data
      })
    this.formatDate()
  },
  methods: {
    //  查看当天运动轨迹
    lookMotionTrajectory (index, row) {
      const date = this.formatDate()
      http.post('/positioning/getPoint', { equipment_id: row.equipment_id, time: date })
        .then(resp => {
          this.path = resp.data
          this.$refs.positionMap.getPath(this.path)
        })
    },
    //  查看历史轨迹
    historicalTrajectory (index, row) {
      this.isViewHistoryData = false
      this.currentID = row.equipment_id
      http.post('/positioning/historicalPositioning', row.equipment_id)
        .then(resp => {
          this.personnelHistoryPositionData = resp.data
        })
    },
    lookHistoryMotionTrajectory (index, row) {
      http.post('/positioning/getPoint', { equipment_id: this.currentID, time: row.times })
        .then(resp => {
          this.path = resp.data
          this.$refs.positionMap.getPath(this.path)
        })
    },
    goBack () {
      this.isViewHistoryData = true
    },
    //  获取当天日期
    formatDate () {
      const date = new Date()
      const year = date.getFullYear() // 年
      const month = date.getMonth() + 1 // 月
      const day = date.getDate() // 日
      if (month < 10) {
        this.nowDate = `${year}-0${month}-${day}`
      } else {
        this.nowDate = `${year}-${month}-${day}`
      }
      return this.nowDate
    }
  }
}
</script>

<style scoped lang="less">
.bodyContainer {
  width: 90%;
  height: 550px;
  position: absolute;
  left: 5%;
  margin-top: 20px;
  display: flex;
  .tableContainer {
    width: 500px;
    height: 550px;
    margin-right: 60px;
    .backContainer {
      height: 30px;
      width: 80px;
      cursor: pointer;
    }
  }
  .mapContainer {
    width: 800px;
    height: 550px;
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
