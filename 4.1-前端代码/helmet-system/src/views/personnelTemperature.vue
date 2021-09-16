<template>
  <div class="bodyContainer">
<!--    历史记录对话框-->
    <el-dialog title="历史体温记录" center :visible.sync="lookRecordDialog" width="35%" :close-on-click-modal="false">
      <el-table
        :data="recordTemperature"
        empty-text="暂无历史体温"
        :header-cell-style="{borderColor: '#30eee9'}"
        width="420">
        <el-table-column
          align="center"
          property="equipment_id"
          label="设备id"
          width="120">
        </el-table-column>
        <el-table-column
          align="center"
          property="update_time"
          label="时间">
        </el-table-column>
        <el-table-column
          align="center"
          property="temperature"
          label="体温"
          width="120">
        </el-table-column>
      </el-table>
    </el-dialog>
    <div class="queryContainer">
      <el-form :inline="true" :model="queryTemperatureFromData" class="demo-form-inline">
        <el-form-item>
          <el-input @input="clearInput" v-model="queryTemperatureFromData.ch" placeholder="请输入用户名"></el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="queryTemperature">查询</el-button>
        </el-form-item>
      </el-form>
    </div>
    <div class="tableContainer">
      <el-table
        :data="temperatureTableData"
        empty-text="暂无用户体温数据"
        :header-cell-style="{borderColor: '#30eee9'}"
        style="width: 100%">
        <el-table-column
          label="工号"
          align="center"
          prop="user_id"
          width="120">
        </el-table-column>
        <el-table-column
          label="姓名"
          prop="user_name"
          align="center"
          width="180">
        </el-table-column>
        <el-table-column
          label="设备id"
          prop="equipment_id"
          align="center"
          width="120">
        </el-table-column>
        <el-table-column
          label="部门"
          prop="department_name"
          align="center"
          width="180">
        </el-table-column>
        <el-table-column
          label="体温"
          prop="temperature"
          align="center"
          width="180">
        </el-table-column>
        <el-table-column label="操作" align="center">
          <template slot-scope="scope">
            <el-button
              size="mini"
              type="text"
              style="color: #686de0"
              @click="lookRecord(scope.$index, scope.row)">历史体温</el-button>
          </template>
        </el-table-column>
      </el-table>
    </div>
    <div class="paginationContainer">
      <el-pagination
        background
        @current-change="getCurrentPage"
        :page-size="7"
        :hide-on-single-page = 'isHide'
        layout="prev, pager, next"
        :total="personnelNumber">
      </el-pagination>
    </div>
  </div>
</template>

<script>
import http from '@/utils/http'

export default {
  name: 'personnelTemperature',
  data () {
    return {
      lookRecordDialog: false,
      personnelNumber: 0,
      isHide: false,
      currentVal: '',
      //  温度表格数据
      temperatureTableData: [],
      //  查询用户温度数据
      queryTemperatureFromData: {
        current: 1,
        size: 7,
        ch: ''
      },
      //  用户历史体温数据
      recordTemperature: []
    }
  },
  created () {
    //  获取所有用户的体温数据
    http.post('/temperature/list', { current: 1, size: 7 }).then(resp => {
      if (resp.data.size <= 7) {
        this.isHide = true
      }
      this.personnelNumber = resp.data.size
      this.temperatureTableData = resp.data.list
    })
  },
  methods: {
    //  查看历史记录事件
    lookRecord (index, row) {
      this.lookRecordDialog = true
      http.post('/temperature/getListByID', row.equipment_id).then(resp => {
        this.recordTemperature = resp.data
      })
    },
    //  查询用户体温事件
    queryTemperature () {
      http.post('/temperature/getListByName', this.queryTemperatureFromData).then(resp => {
        if (resp.data.list.length === 0) {
          this.$message.error('没有此人')
        } else {
          if (resp.data.size <= 7) {
            this.isHide = true
          }
          this.personnelNumber = resp.data.size
          this.temperatureTableData = resp.data.list
        }
      })
    },
    //  分页事件
    getCurrentPage (page) {
      if (this.currentVal === '') {
        http.post('/temperature/list', { current: page, size: 7 }).then(resp => {
          if (resp.data.size <= 7) {
            this.isHide = true
          }
          this.personnelNumber = resp.data.size
          this.temperatureTableData = resp.data.list
        })
      } else {
        this.queryTemperatureFromData.current = page
        http.post('/temperature/getListByName', this.queryTemperatureFromData).then(resp => {
          this.temperatureTableData = resp.data.list
        })
      }
    },
    //  监听查询输入框事件
    clearInput (val) {
      this.currentVal = val
      if (val === '') {
        this.refreshTable()
      }
    },
    //  重新渲染表格数据
    refreshTable () {
      http.post('/temperature/list', { current: 1, size: 7 }).then(resp => {
        if (resp.data.size <= 7) {
          this.isHide = true
        }
        this.personnelNumber = resp.data.size
        this.temperatureTableData = resp.data.list
      })
    }
  }
}
</script>

<style scoped lang="less">
.bodyContainer {
  width: 95%;
  height: 550px;
  position: absolute;
  left: 2.5%;
  margin-top: 30px;
  .queryContainer {
    width: 500px;
    height: 40px;
    position: relative;
    left: 50%;
    transform: translateX(-50%);
    text-align: center;
    //  输入框样式
    /deep/.el-input__inner{
      width: 160px;
      background-color: transparent;
      border-color: #30eee9;
      color: #30eee9;
      &::placeholder {
        color: #d0d09e;
      }
    }
  }
  .tableContainer {
    margin: 10px 0;
    width: 70%;
    height: 450px;
    position: relative;
    left: 50%;
    transform: translateX(-50%);
  }
  .paginationContainer {
    width: 500px;
    height: 40px;
    position: relative;
    left: 50%;
    transform: translateX(-50%);
    text-align: center;
  }
}
.el-button--primary {
  background-color: #0fbcf9;
  color: #0652DD;
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
//  分页器样式
/deep/.paginationContainer button,
/deep/.paginationContainer .el-pager li{
  background-color: transparent !important;
  color: #B4C2DC !important;
  border: 1px solid #0672C4;
}
/deep/.paginationContainer .el-pagination.is-background .el-pager li:not(.disabled).active {
  background-color: #0672C4 !important;
}
/deep/.el-dialog, /deep/.el-pager li {
  background-color: #001831;
  .el-dialog__title {
    color: #30eee9;
  }
}
</style>
