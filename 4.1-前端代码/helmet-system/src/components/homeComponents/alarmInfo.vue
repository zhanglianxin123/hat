<template>
  <div>
    <el-table
      :data="waringTableData"
      height="520px"
      empty-text="暂无报警信息"
      :show-header="false"
      style="width: 100%">
      <el-table-column
        prop="user_name"
        label="姓名"
        align="center"
        width="100">
      </el-table-column>
      <el-table-column
        prop="warning_time"
        label="时间"
        :show-overflow-tooltip="true"
        align="center"
        width="130">
      </el-table-column>
      <el-table-column
        prop="warning_name"
        align="center"
        :show-overflow-tooltip="true"
        label="类型">
      </el-table-column>
    </el-table>
  </div>
</template>

<script>
import http from '@/utils/http'

export default {
  name: 'alarmInfo',
  data () {
    return {
      waringTableData: []
    }
  },
  created () {
    http.get('/index/getWaring').then(resp => {
      this.waringTableData = resp.data
    })
  },
  methods: {
    refreshTable (val) {
      if (val) {
        http.get('/index/getWaring').then(resp => {
          this.waringTableData = resp.data
        })
      }
    }
  }
}
</script>

<style scoped lang="less">
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
