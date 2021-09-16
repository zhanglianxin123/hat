<template>
  <div class="bodyContainer">
<!--    删除对话框-->
    <el-dialog
      :close-on-click-modal="false"
      title="删除人员"
      center
      :visible.sync="deleteDialog"
      width="30%">
      <span>删除后将无法查看该人员的信息,确认删除?</span>
      <span slot="footer" class="dialog-footer">
        <el-button type="danger" @click="sureDeletePersonnel">确 定</el-button>
        <el-button @click="cancelDeletePersonnel">取 消</el-button>
      </span>
    </el-dialog>
<!--    修改人员信息对话框-->
    <el-dialog title="修改信息" :visible.sync="modifyDialog" center :close-on-click-modal="false">
      <el-form :model="personnelData" label-width="80px">
        <el-row>
          <el-col :span="8">
            <el-form-item label="工号" prop="user_id">
              <el-input disabled v-model="personnelData.user_id"></el-input>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="8">
            <el-form-item label="姓名" prop="user_name">
              <el-input v-model="personnelData.user_name"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="性别" prop="sex">
              <el-radio-group v-model="personnelData.sex">
                <el-radio label="男"></el-radio>
                <el-radio label="女"></el-radio>
              </el-radio-group>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="身份证号" prop="id_card">
              <el-input v-model="personnelData.id_card"></el-input>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="家庭住址" prop="address">
              <el-input v-model="personnelData.address"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="联系电话" prop="phone">
              <el-input v-model="personnelData.phone"></el-input>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="8">
            <el-form-item label="部门" prop="department_id">
              <el-select v-model="personnelData.department_id">
                <el-option
                  v-for="item of departmentData"
                  :label="item.department_name"
                  :value="item.department_id"
                  :key="item.department_id"
                ></el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="职务" prop="role">
              <el-select v-model="personnelData.role" placeholder="请选择部门">
                <el-option label="超级管理员" value="超级管理员"></el-option>
                <el-option label="管理员" value="管理员"></el-option>
                <el-option label="普通工人" value="普通工人"></el-option>
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="密码" prop="user_password">
              <el-input show-password v-model="personnelData.user_password"></el-input>
            </el-form-item>
          </el-col>
        </el-row>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="sureModifyPersonnel">确 定</el-button>
        <el-button @click="cancelModifyPersonnel">取 消</el-button>
      </div>
    </el-dialog>
<!--    添加人员对话框-->
    <el-dialog title="添加人员" :visible.sync="addDialog" center :close-on-click-modal="false">
      <el-form :model="addPersonnelFormData" ref="addPersonnelFormData" label-width="80px">
        <el-row>
          <el-col :span="8">
            <el-form-item label="工号" prop="user_id">
              <el-input v-model="addPersonnelFormData.user_id"></el-input>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="8">
            <el-form-item label="姓名" prop="user_name">
              <el-input v-model="addPersonnelFormData.user_name"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="性别" prop="sex">
              <el-radio-group v-model="addPersonnelFormData.sex">
                <el-radio label="男"></el-radio>
                <el-radio label="女"></el-radio>
              </el-radio-group>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="身份证号" prop="id_card">
              <el-input v-model="addPersonnelFormData.id_card"></el-input>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="家庭住址" prop="address">
              <el-input v-model="addPersonnelFormData.address"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="联系电话" prop="phone">
              <el-input v-model="addPersonnelFormData.phone"></el-input>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="8">
            <el-form-item label="部门" prop="department_id">
              <el-select v-model="addPersonnelFormData.department_id" placeholder="请选择部门">
                <el-option
                  v-for="item of departmentData"
                  :label="item.department_name"
                  :value="item.department_id"
                  :key="item.department_id"
                ></el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="职务" prop="role">
              <el-select v-model="addPersonnelFormData.role" placeholder="请选择部门">
                <el-option label="超级管理员" value="超级管理员"></el-option>
                <el-option label="管理员" value="管理员"></el-option>
                <el-option label="普通工人" value="普通工人"></el-option>
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="sureAddPersonnel('addPersonnelFormData')">确 定</el-button>
        <el-button @click="cancelAddPersonnel('addPersonnelFormData')">取 消</el-button>
      </div>
    </el-dialog>
<!--    查询输入框容器-->
    <div class="queryContainer">
      <el-form :inline="true" :model="personnelFormData" class="demo-form-inline">
        <el-form-item>
          <el-input @input="clearInput" v-model="personnelFormData.ch" placeholder="请输入工号或姓名"></el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="queryPersonnel">查询</el-button>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="addPersonnel">添加</el-button>
        </el-form-item>
      </el-form>
    </div>
<!--    表格容器-->
    <div class="tableContainer">
      <el-table
        :data="personnelTableData"
        empty-text="暂无人员"
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
          align="center"
          prop="user_name"
          width="120">
        </el-table-column>
        <el-table-column
          label="性别"
          align="center"
          prop="sex"
          width="80">
        </el-table-column>
        <el-table-column
          label="身份证号"
          align="center"
          prop="id_card"
          width="240">
        </el-table-column>
        <el-table-column
          label="家庭住址"
          align="center"
          prop="address"
          width="300">
        </el-table-column>
        <el-table-column
          label="联系电话"
          align="center"
          prop="phone"
          width="120">
        </el-table-column>
        <el-table-column
          label="部门"
          align="center"
          prop="department_name"
          width="120">
        </el-table-column>
        <el-table-column
          label="职务"
          align="center"
          prop="role"
          width="120">
        </el-table-column>
        <el-table-column label="操作" align="center">
          <template slot-scope="scope">
            <el-button
              size="mini"
              type="text"
              style="color: #686de0"
              @click="modifyPersonnel(scope.$index, scope.row)">修改</el-button>
            <el-button
              size="mini"
              type="text"
              style="color: #f9ca24"
              @click="deletePersonnel(scope.$index, scope.row)">删除</el-button>
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
        :total="personnelNumber">
      </el-pagination>
    </div>
  </div>
</template>

<script>
import http from '@/utils/http'

export default {
  name: 'personnelManagement',
  data () {
    return {
      currentPage: 1, //  当前页数
      currentSize: 7, //  当前页数
      currentVal: '', //  当前输入框内容
      personnelNumber: 0, //  显示总人数
      isHide: false, //  是否隐藏分页器
      deleteDialog: false, //  删除人员对话框
      addDialog: false, //  添加人员对话框
      modifyDialog: false, //  修改人员信息对话框
      currentPersonnelID: '', // 当前用户id
      deleteData: {
        user_id: 0
      },
      departmentData: [], //  部门数据
      //  查询用户表单数据
      personnelFormData: {
        ch: '',
        current: 1,
        size: 7
      },
      //  添加用户表单数据
      addPersonnelFormData: {
        user_id: '',
        user_name: '',
        sex: '',
        department_id: '',
        role: '',
        id_card: '',
        address: '',
        phone: ''
      },
      //  用户表格数据
      personnelTableData: [],
      //  编辑用户数据
      personnelData: {}
    }
  },
  created () {
    //  获取用户列表
    http.post('/user/getUser', { current: this.currentPage, size: 7 }).then(resp => {
      this.personnelTableData = resp.data
    })
    //  获取用户总人数
    http.get('/user/getSize').then(resp => {
      this.personnelNumber = resp.data
      if (this.personnelNumber <= 6) {
        this.isHide = true
      }
    })
  },
  methods: {
    //  获取当前点击页数
    getCurrentPage (page) {
      this.currentPage = page
      if (this.currentVal === '') {
        http.post('/user/getUser', { current: page, size: this.currentSize }).then(resp => {
          this.personnelTableData = resp.data
        })
      } else {
        //  当输入框不为空时，采用此方法分页
        this.personnelFormData.current = page
        http.post('/user/getListById', this.personnelFormData).then(resp => {
          this.personnelTableData = resp.data.list
        })
      }
    },
    //  添加用户
    addPersonnel () {
      this.addDialog = true
      //  获取部门数据
      http.get('/department/list').then(resp => {
        this.departmentData = resp.data
      })
    },
    //  确认添加
    sureAddPersonnel (formName) {
      this.addDialog = false
      http.post('/user/addUser', this.addPersonnelFormData).then(resp => {
        this.$message.success(resp.message)
        this.$refs[formName].resetFields()
        this.refreshTableData()
      })
    },
    //  取消添加
    cancelAddPersonnel (formName) {
      this.addDialog = false
      this.$refs[formName].resetFields()
    },
    //  查询用户信息
    queryPersonnel () {
      http.post('/user/getListById', this.personnelFormData).then(resp => {
        if (resp.data.list.length === 0) {
          this.$message.error('没有此人,请重新输入')
        } else {
          if (resp.data.size <= 7) {
            this.isHide = true
          }
          this.personnelNumber = resp.data.size
          this.personnelTableData = resp.data.list
        }
      })
    },
    clearInput (val) {
      this.currentVal = val
      if (val === '') {
        this.refreshTableData()
      }
    },
    //  修改用户信息
    modifyPersonnel (index, row) {
      this.modifyDialog = true
      //  获取部门数据
      http.get('/department/list').then(resp => {
        this.departmentData = resp.data
      })
      this.personnelData = row
    },
    //  确认修改人员信息
    sureModifyPersonnel () {
      this.modifyDialog = false
      http.post('/user/update', this.personnelData).then(resp => {
        this.$message.success(resp.message)
      })
    },
    //  取消修改人员信息
    cancelModifyPersonnel () {
      this.modifyDialog = false
      this.refreshTableData()
    },
    //  删除用户信息
    deletePersonnel (index, row) {
      this.deleteDialog = true
      this.currentPersonnelID = row.user_id
    },
    //  确认删除用户
    sureDeletePersonnel () {
      this.deleteDialog = false
      this.deleteData.user_id = this.currentPersonnelID
      http.post('/user/delete', this.deleteData.user_id).then(resp => {
        this.$message.success(resp.message)
        this.refreshTableData()
      })
    },
    cancelDeletePersonnel () {
      this.deleteDialog = false
    },
    //  重新渲染表格数据
    refreshTableData () {
      http.post('/user/getUser', { current: 1, size: 7 }).then(resp => {
        this.personnelTableData = resp.data
      })
      http.get('/user/getSize').then(resp => {
        this.personnelNumber = resp.data
        if (this.personnelNumber <= 6) {
          this.isHide = true
        }
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
</style>
