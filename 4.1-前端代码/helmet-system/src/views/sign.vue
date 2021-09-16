<template>
  <div class="bodyContainer">
<!--    历史签到记录对话框-->
    <el-dialog title="历史签到记录" :visible.sync="historyRecordDialog" center width="35%" :close-on-click-modal="false">
      <el-table :data="historyRecordDialogData">
        <el-table-column property="sign_name" align="center" label="签到区域" width="200"></el-table-column>
        <el-table-column property="update_time" align="center" label="签到时间"></el-table-column>
      </el-table>
    </el-dialog>
<!--    删除签到区域对话框-->
    <el-dialog
      title="确认删除"
      :visible.sync="deleteSignAreaDialog"
      center
      width="30%">
      <span>删除后工人将无法签到，确认删除？</span>
      <span slot="footer" class="dialog-footer">
        <el-button type="danger" @click="sureDeleteSignArea">确 定</el-button>
        <el-button @click="cancelDeleteSignArea">取 消</el-button>
      </span>
    </el-dialog>
<!--    人员签到表格容器-->
    <div class="signContainer">
      <el-table
        height="550px"
        :data="signPersonnelTableData"
        :header-cell-style="{borderColor: '#30eee9'}"
        empty-text="暂无签到人员数据"
        style="width: 100%">
        <el-table-column
          label="用户id"
          align="center"
          prop="user_id"
          width="80">
        </el-table-column>
        <el-table-column
          label="设备id"
          align="center"
          prop="equipment_id"
          width="80">
        </el-table-column>
        <el-table-column
          label="用户名"
          align="center"
          prop="user_name"
          width="80">
        </el-table-column>
        <el-table-column
          label="签到次数"
          align="center"
          prop="sign_size"
          width="80">
        </el-table-column>
        <el-table-column label="操作" align="center">
          <template slot-scope="scope">
            <el-button
              size="mini"
              type="text"
              style="color: #686de0"
              @click="historyRecord(scope.$index, scope.row)">历史记录</el-button>
          </template>
        </el-table-column>
      </el-table>
    </div>
<!--    签到区域划分容器-->
    <div class="mapContainer" v-if="show">
<!--      显示签到区域地图-->
      <sign-map ref="signMap" :polygonPathData="polygonPathData"></sign-map>
    </div>
<!--    添加签到区域地图-->
    <div class="mapContainer" v-else>
      <div class="btnBox">
        <el-button type="primary" :disabled="newSelect" @click="handleNewSelect">重新选择</el-button>
        <el-button type="primary" :disabled="buildPolygon" @click="handleBuildPolygon">构建多边形</el-button>
        <el-button type="primary" :disabled="startEdit" @click="handleStartEdit">开始编辑</el-button>
        <el-button type="primary" :disabled="cancelEdit" @click="handleEndEdit">取消编辑</el-button>
      </div>
      <add-sign-map ref="addSignMap" @ListenAddSignArea="getAddSignData" @ListenAddSignMarker="getAddSignMarker"></add-sign-map>
    </div>
<!--    签到区域卡片容器-->
    <div class="card" v-if="show">
      <div class="btnContainer"><el-button @click="addSignArea" type="primary">添加签到区域</el-button></div>
      <el-table
        height="500px"
        :data="signAreaTableData"
        empty-text="暂无签到区域"
        :header-cell-style="{borderColor: '#30eee9'}"
        style="width: 100%">
        <el-table-column
          label="编号"
          align="center"
          prop="sign_number"
          width="80">
        </el-table-column>
        <el-table-column
          label="区域名"
          align="center"
          prop="sign_name"
          width="120">
        </el-table-column>
        <el-table-column
          label="备注"
          align="center"
          prop="sign_node"
          width="100">
        </el-table-column>
        <el-table-column label="操作" align="center">
          <template slot-scope="scope">
            <el-button
              size="mini"
              type="text"
              style="color: #686de0"
              @click="lookSignArea(scope.$index, scope.row)">查看</el-button>
            <el-button
              size="mini"
              type="text"
              style="color: #f9ca24"
              @click="deleteSignArea(scope.$index, scope.row)">删除</el-button>
          </template>
        </el-table-column>
      </el-table>
    </div>
<!--    添加签到区域表单-->
    <div class="card" v-else style="text-align: center">
      <el-form ref="addForm" :model="addSignAreaData" label-width="80px">
        <el-form-item label="区域id" prop="sign_id">
          <el-input v-model="addSignAreaData.sign_id"></el-input>
        </el-form-item>
        <el-form-item label="区域编号" prop="sign_number">
          <el-input v-model="addSignAreaData.sign_number"></el-input>
        </el-form-item>
        <el-form-item label="区域名" prop="sign_name">
          <el-input v-model="addSignAreaData.sign_name"></el-input>
        </el-form-item>
        <el-form-item label="备注"  prop="sign_node">
          <el-input v-model="addSignAreaData.sign_node"></el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="sureAddSignArea('addForm')" :disabled="sureAddSign">立即创建</el-button>
          <el-button @click="cancelAddSignArea('addForm')">取消</el-button>
        </el-form-item>
      </el-form>
    </div>
  </div>
</template>

<script>
import SignMap from '@/components/signComponents/signMap'
import http from '@/utils/http'
import AddSignMap from '@/components/signComponents/addSignMap'
export default {
  name: 'sign',
  components: { AddSignMap, SignMap },
  data () {
    return {
      currentSignId: '',
      newSelect: true,
      buildPolygon: true,
      startEdit: true,
      cancelEdit: true,
      sureAddSign: true,
      //  是否显示
      show: true,
      //  历史签到数据对话框
      historyRecordDialog: false,
      //  删除签到区域对话框
      deleteSignAreaDialog: false,
      //  签到人员表格数据
      signPersonnelTableData: [],
      //  历史签到数据
      historyRecordDialogData: [],
      //  签到区域表格数据
      signAreaTableData: [],
      //  添加签到区域数据
      addSignAreaData: {
        sign_id: '',
        sign_number: '',
        sign_name: '',
        sign_node: '',
        point: []
      },
      polygonPathData: {}
    }
  },
  created () {
    //  获取签到人员数据
    http.get('/sign/getUserList').then(resp => {
      this.signPersonnelTableData = resp.data
      this.polygonPathData = resp.data
    })
    //  获取签到区域信息
    http.get('/sign/list').then(resp => {
      this.signAreaTableData = resp.data
    })
  },
  methods: {
    //  产看历史签到数据
    historyRecord (index, row) {
      this.historyRecordDialog = true
      http.post('/sign/getListById', row.equipment_id).then(resp => {
        this.historyRecordDialogData = resp.data
      })
    },
    //  查看签到区域
    lookSignArea (index, row) {
      this.polygonPathData = row
      this.$refs.signMap.getCurrentCenter(row.centerPoint)
    },
    //  删除签到区域
    deleteSignArea (index, row) {
      this.deleteSignAreaDialog = true
      this.currentSignId = row.sign_id
    },
    sureDeleteSignArea () {
      http.post('/sign/delete', this.currentSignId).then(resp => {
        this.$message.success(resp.message)
        this.refreshTable()
      })
      this.deleteSignAreaDialog = false
    },
    cancelDeleteSignArea () {
      this.deleteSignAreaDialog = false
    },
    //  添加签到区域
    addSignArea () {
      this.show = false
    },
    //  确认添加
    sureAddSignArea (formName) {
      http.post('/sign/add', this.addSignAreaData).then(resp => {
        this.$message.success(resp.message)
        this.$refs[formName].resetFields()
        this.refreshTable()
      })
      this.show = true
      this.buildPolygon = true
      this.newSelect = true
      this.startEdit = true
      this.cancelEdit = true
    },
    //  取消添加
    cancelAddSignArea (formName) {
      this.$refs[formName].resetFields()
      this.show = true
      this.buildPolygon = true
      this.newSelect = true
      this.startEdit = true
      this.cancelEdit = true
    },
    //  重新选择
    handleNewSelect () {
      this.$refs.addSignMap.clearMarker()
      this.buildPolygon = true
      this.newSelect = true
      this.startEdit = true
      this.cancelEdit = true
    },
    //  构建多边形
    handleBuildPolygon () {
      this.$refs.addSignMap.polygon()
      this.sureAddSign = false
      this.buildPolygon = true
      this.newSelect = false
      this.startEdit = false
      this.cancelEdit = false
    },
    //  开始编辑
    handleStartEdit () {
      this.$refs.addSignMap.startEditPolygon()
    },
    //  结束编辑
    handleEndEdit () {
      this.$refs.addSignMap.endEditPolygon()
    },
    //  是否添加标注
    getAddSignMarker (val) {
      this.newSelect = val
      this.buildPolygon = val
    },
    //  获取签到区域的数据
    getAddSignData (data) {
      this.addSignAreaData.point = data
    },
    refreshTable () {
      http.get('/sign/list').then(resp => {
        this.signAreaTableData = resp.data
        this.polygonPathData = resp.data
      })
    }
  }
}
</script>

<style scoped lang="less">
.bodyContainer {
  width: 98%;
  height: 550px;
  position: absolute;
  left: 1%;
  margin-top: 20px;
  display: flex;
  .signContainer {
    width: 450px;
    height: 550px;
    margin-right: 20px;
  }
  .mapContainer {
    width: 600px;
    height: 550px;
    margin-right: 20px;
    .btnBox {
      height: 50px;
    }
  }
  .card {
    width: 440px;
    height: 550px;
    .btnContainer {
      height: 50px;
    }
  }
}
.el-button--primary {
  background-color: #00132c;
  color: #706fd3;
  border-color: #30eee9;
}
.el-button--default {
  background-color: #00132c;
  color: #D980FA;
  border-color: #30eee9;
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
// 对话框样式
/deep/.el-dialog, /deep/.el-pager li {
  background-color: #001831;
  .el-dialog__title {
    color: #30eee9;
  }
}
//  输入框样式
/deep/.el-input__inner, /deep/.el-textarea__inner{
  background-color: transparent;
  border-color: #30eee9;
  color: #30eee9;
  &::placeholder {
    color: #d0d09e;
  }
}
/deep/.el-form-item__label {
  color: #34ace0;
}
</style>
