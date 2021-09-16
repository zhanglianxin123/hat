<template>
  <div class="bodyContainer">
<!--    删除对话框-->
    <el-dialog
      :close-on-click-modal="false"
      title="删除设备"
      center
      :visible.sync="deleteDialog"
      width="30%">
      <span>删除后将无法查看该设备的信息,确认删除?</span>
      <span slot="footer" class="dialog-footer">
        <el-button type="danger" @click="sureDeleteDevice">确 定</el-button>
        <el-button @click="cancelDeleteDevice">取 消</el-button>
      </span>
    </el-dialog>
<!--    修改设备信息设备对话框-->
    <el-dialog title="修改信息" :visible.sync="modifyDialog" center :close-on-click-modal="false">
      <el-form :model="deviceData" label-width="80px">
        <el-row>
          <el-col :span="8">
            <el-form-item label="设备id" prop="equipment_id">
              <el-input disabled v-model="deviceData.equipment_id"></el-input>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="8">
            <el-form-item label="设备编号" prop="equipment_number">
              <el-input v-model="deviceData.equipment_number"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="设备名" prop="equipment_name">
              <el-input v-model="deviceData.equipment_name"></el-input>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="8">
            <el-form-item label="用户工号" prop="user_id">
              <el-input v-model="deviceData.user_id"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="用户名" prop="user_name">
              <el-input v-model="deviceData.user_name"></el-input>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="8">
            <el-form-item label="生产日期" prop="equipment_data">
              <el-date-picker type="date" value-format="yyyy-MM-dd" placeholder="选择日期" v-model="deviceData.equipment_data"></el-date-picker>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="生产地址" prop="equipment_place">
              <el-input v-model="deviceData.equipment_place"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="使用状态" prop="using">
              <el-select v-model="deviceData.employ" placeholder="请选择使用状态">
                <el-option label="正常使用" :value="1"></el-option>
                <el-option label="停用" :value="0"></el-option>
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="sureModifyDevice">确 定</el-button>
        <el-button @click="cancelModifyDevice">取 消</el-button>
      </div>
    </el-dialog>
<!--    添加设备信息对话框-->
    <el-dialog title="添加设备" :visible.sync="addDialog" center :close-on-click-modal="false">
      <el-form :model="addDeviceData" ref="addDeviceData" label-width="80px">
        <el-row>
          <el-col :span="8">
            <el-form-item label="设备id" prop="equipment_id">
              <el-input v-model="addDeviceData.equipment_id"></el-input>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="8">
            <el-form-item label="设备编号" prop="equipment_number">
              <el-input v-model="addDeviceData.equipment_number"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="设备名" prop="equipment_name">
              <el-input v-model="addDeviceData.equipment_name"></el-input>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="8">
            <el-form-item label="用户工号" prop="user_id">
              <el-input v-model="addDeviceData.user_id"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="用户名" prop="user_name">
              <el-input v-model="addDeviceData.user_name"></el-input>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="8">
            <el-form-item label="生产日期" prop="equipment_data">
              <el-date-picker type="date" value-format="yyyy-MM-dd" placeholder="选择日期" v-model="addDeviceData.equipment_data"></el-date-picker>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="生产地址" prop="equipment_place">
              <el-input v-model="addDeviceData.equipment_place"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="使用状态" prop="using">
              <el-select v-model="addDeviceData.employ" placeholder="请选择使用状态">
                <el-option label="正常使用" :value="1"></el-option>
                <el-option label="停用" :value="0"></el-option>
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="sureAddDevice('addDeviceData')">确 定</el-button>
        <el-button @click="cancelAddDevice('addDeviceData')">取 消</el-button>
      </div>
    </el-dialog>
    <!--    查询输入框容器-->
    <div class="queryContainer">
      <el-form :inline="true" :model="deviceFormData" class="demo-form-inline">
        <el-form-item>
          <el-input @input="clearInput" v-model="deviceFormData.ch" placeholder="请输入id"></el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="queryDevice">查询</el-button>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="addDevice">添加</el-button>
        </el-form-item>
      </el-form>
    </div>
    <!--    表格容器-->
    <div class="tableContainer">
      <el-table
        :data="deviceTableData"
        empty-text="暂无设备"
        :header-cell-style="{borderColor: '#30eee9'}"
        style="width: 100%">
        <el-table-column
          label="设备ID"
          align="center"
          prop="equipment_id"
          width="120">
        </el-table-column>
        <el-table-column
          label="设备编号"
          align="center"
          prop="equipment_number"
          width="120">
        </el-table-column>
        <el-table-column
          label="设备名"
          align="center"
          prop="equipment_name"
          width="120">
        </el-table-column>
        <el-table-column
          label="用户工号"
          align="center"
          prop="user_id"
          width="100">
        </el-table-column>
        <el-table-column
          label="用户名"
          align="center"
          prop="user_name"
          width="100">
        </el-table-column>
        <el-table-column
          label="生产日期"
          align="center"
          prop="equipment_data"
          width="200">
        </el-table-column>
        <el-table-column
          label="生产地址"
          align="center"
          prop="equipment_place"
          width="300">
        </el-table-column>
        <el-table-column
          label="使用状态"
          align="center"
          prop="employ"
          width="120">
          <template slot-scope="scope">
            <el-tag
              :type="scope.row.employ === '正常使用' ? 'success' : 'info'"
              disable-transitions>{{scope.row.employ}}</el-tag>
          </template>
        </el-table-column>
        <el-table-column label="操作" align="center">
          <template slot-scope="scope">
            <el-button
              size="mini"
              type="text"
              style="color: #686de0"
              @click="modifyDevice(scope.$index, scope.row)">修改</el-button>
            <el-button
              size="mini"
              type="text"
              style="color: #f9ca24"
              @click="deleteDevice(scope.$index, scope.row)">删除</el-button>
          </template>
        </el-table-column>
      </el-table>
    </div>
    <!--    分页容器-->
    <div class="paginationContainer">
      <el-pagination
        background
        @current-change="getCurrentPage"
        :page-size="7"
        :hide-on-single-page = 'isHide'
        layout="prev, pager, next"
        :total="deviceNumber">
      </el-pagination>
    </div>
  </div>
</template>

<script>

import http from '@/utils/http'

export default {
  name: 'deviceManagement',
  data () {
    return {
      deviceNumber: 0, //  显示总设备
      isHide: false, //  是否隐藏分页器
      deleteDialog: false, //  删除设备对话框
      addDialog: false, //  添加设备对话框
      modifyDialog: false, //  修改设备信息对话框
      currentDeviceID: '', // 当前设备id
      //  查询设备表单数据
      deviceFormData: {
        ch: ''
      },
      //  设备表格数据
      deviceTableData: [],
      //  设备数据
      deviceData: {},
      //  添加设备数据
      addDeviceData: {
        equipment_id: '',
        equipment_number: '',
        equipment_name: '',
        user_id: '',
        user_name: '',
        equipment_data: '',
        equipment_place: '',
        employ: ''
      }
    }
  },
  created () {
    http.post('/equipment/getList', { current: 1, size: 7 }).then(resp => {
      resp.data.forEach(item => {
        if (item.employ === 0) {
          item.employ = '停用'
        } else {
          item.employ = '正常使用'
        }
      })
      this.deviceTableData = resp.data
    })
  },
  methods: {
    //  查询设备
    queryDevice () {
      http.post('/equipment', this.deviceFormData.ch).then(resp => {
        resp.data.forEach(item => {
          if (item.employ === 0) {
            item.employ = '停用'
          } else {
            item.employ = '正常使用'
          }
        })
        this.deviceTableData = resp.data
      })
    },
    //  添加设备
    addDevice () {
      this.addDialog = true
    },
    //  确认添加
    sureAddDevice (formName) {
      http.post('/equipment/add', this.addDeviceData).then(resp => {
        if (resp.code === 200) {
          this.$message.success(resp.message)
          this.refreshTableData()
          this.$refs[formName].resetFields()
        } else {
          this.$message.error(resp.message)
        }
      })
      this.addDialog = false
    },
    //  取消添加
    cancelAddDevice (formName) {
      this.addDialog = false
      this.$refs[formName].resetFields()
    },
    //  修改设备信息
    modifyDevice (index, row) {
      this.modifyDialog = true
      this.deviceData = row
    },
    // 确认修改设备信息
    sureModifyDevice () {
      http.post('/equipment/update', this.deviceData).then(resp => {
        this.$message.success(resp.message)
        this.refreshTableData()
      })
      this.modifyDialog = false
    },
    //  取消修改设备信息
    cancelModifyDevice () {
      this.modifyDialog = false
      this.refreshTableData()
    },
    //  删除设备信息
    deleteDevice (index, row) {
      this.currentDeviceID = row.equipment_id
      this.deleteDialog = true
    },
    // 确认删除设备信息
    sureDeleteDevice () {
      http.post('/equipment/delete', this.currentDeviceID).then(resp => {
        this.$message.success(resp.message)
        this.refreshTableData()
      })
      this.deleteDialog = false
    },
    //  取消删除设备信息
    cancelDeleteDevice () {
      this.deleteDialog = false
    },
    getCurrentPage (page) {
    },
    //  表单清空刷新表格
    clearInput (val) {
      if (val === '') {
        this.refreshTableData()
      }
    },
    //  重新渲染表格数据
    refreshTableData () {
      http.post('/equipment/getList', { current: 1, size: 7 }).then(resp => {
        resp.data.forEach(item => {
          if (item.employ === 0) {
            item.employ = '停用'
          } else {
            item.employ = '正常使用'
          }
        })
        this.deviceTableData = resp.data
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
    /deep/.el-input__inner {
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
    width: 100%;
    height: 450px;
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
/deep/.el-tag.el-tag--success {
  background-color: #3867d6;
  border-color: #30eee9;
  color: #ff9f43;
}
/deep/.el-tag.el-tag--info {
  background-color: #c8d6e5;
  border-color: #c8d6e5;
  color: #909399;
}
</style>
