<template>
  <div class="bodyContainer">
<!--    删除电子围栏数组-->
    <el-dialog
      title="确认删除"
      :visible.sync="deleteDialog"
      :close-on-click-modal="false"
      center
      width="30%">
      <span>确认删除该电子围栏吗？</span>
      <span slot="footer" class="dialog-footer">
        <el-button type="danger" @click="sureDelete">确 定</el-button>
        <el-button @click="cancelDelete">取 消</el-button>
      </span>
    </el-dialog>
<!--    展示所有围栏表格-->
    <div class="fenceTableContainer" v-if="showTable">
      <div class="addFenceBtn">
        <el-button type="primary" @click="addFence">添加围栏</el-button>
      </div>
      <el-table
        :data="fenceTableData"
        :header-cell-style="{borderColor: '#30eee9'}"
        empty-text="暂无电子围栏数据"
        style="width: 100%">
        <el-table-column
          label="围栏id"
          align="center"
          prop="fence_id"
          width="80">
        </el-table-column>
        <el-table-column
          label="围栏编号"
          align="center"
          prop="fence_number"
          width="80">
        </el-table-column>
        <el-table-column
          label="围栏名称"
          align="center"
          prop="fence_name"
          width="100">
        </el-table-column>
        <el-table-column
          align="center"
          prop="fence_node"
          label="备注">
        </el-table-column>
        <el-table-column label="操作" align="center">
          <template slot-scope="scope">
            <el-button
              size="mini"
              type="text"
              style="color: #686de0"
              @click="lookFence(scope.$index, scope.row)">查看</el-button>
            <el-button
              size="mini"
              type="text"
              style="color: #f9ca24"
              @click="deleteFence(scope.$index, scope.row)">删除</el-button>
          </template>
        </el-table-column>
      </el-table>
    </div>
<!--    添加围栏表单-->
    <div class="fenceTableContainer addFenceContainer" v-else>
      <el-form ref="form" :model="addFenceData" label-width="80px">
        <el-row>
          <el-col :span="16">
            <el-form-item label="围栏id">
              <el-input v-model="addFenceData.fence_id"></el-input>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="16">
            <el-form-item label="围栏编号">
              <el-input v-model="addFenceData.fence_number"></el-input>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="16">
            <el-form-item label="围栏名称">
              <el-input v-model="addFenceData.fence_name"></el-input>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="16">
            <el-form-item label="备注">
              <el-input type="textarea" v-model="addFenceData.fence_node"></el-input>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="16">
            <el-form-item>
              <el-button
                type="primary"
                @click="sureAdd"
                :disabled="isSureAdd">确认</el-button>
              <el-button
                @click="cancelAdd">取消</el-button>
            </el-form-item>
          </el-col>
        </el-row>
      </el-form>
    </div>
<!--    显示围栏地图-->
    <div class="mapContainer" v-if="showTable">
      <electric-fence-map ref="electricFenceMap" :polygonPathData="polygonPathData"></electric-fence-map>
    </div>
<!--    添加围栏地图-->
    <div class="mapContainer" v-else>
      <div style="margin-bottom: 10px">
        <el-button type="primary" @click="reSelect" :disabled="isSelect">重新选择</el-button>
        <el-button type="primary" @click="generatePolygon" :disabled="isGeneratePolygon">生成多边形</el-button>
        <el-button type="primary" @click="startEdit" :disabled="isStartEdit">开始编辑</el-button>
        <el-button type="primary" @click="endEdit" :disabled="isEndEdit">结束编辑</el-button>
      </div>
      <add-electric-fence ref="addElectricFence" @ListenAddFence="fenceData" @ListenAddMarker="isAddMarker"></add-electric-fence>
    </div>
  </div>
</template>

<script>
import ElectricFenceMap from '@/components/electricFenceComponents/electricFenceMap'
import AddElectricFence from '@/components/electricFenceComponents/addElectricFence'
import http from '@/utils/http'
export default {
  name: 'electricFence',
  components: { AddElectricFence, ElectricFenceMap },
  data () {
    return {
      currentFence_id: 0,
      deleteDialog: false,
      isSureAdd: true,
      isEndEdit: true,
      isStartEdit: true,
      isSelect: true,
      isGeneratePolygon: true,
      showTable: true, // 显示添加页面
      fenceTableData: [], //  电子围栏数据
      //  添加电子围栏数据
      addFenceData: {
        fence_id: '', //  围栏id
        fence_number: '', //  围栏编号
        fence_name: '', //  围栏名称
        fence_node: '', //  备注
        point: []
      },
      polygonPathData: {}
    }
  },
  created () {
    http.get('/fence/get').then(resp => {
      this.fenceTableData = resp.data
    })
  },
  methods: {
    lookFence (index, row) {
      this.polygonPathData = row
      this.$refs.electricFenceMap.getCurrentCenter(this.polygonPathData.centerPoint)
    },
    //  删除电子围栏
    deleteFence (index, row) {
      this.deleteDialog = true
      this.currentFence_id = row.fence_id
    },
    sureDelete () {
      http.post('/fence/delete', this.currentFence_id).then(resp => {
        this.$message.success(resp.message)
        this.refreshTable()
        this.deleteDialog = false
      })
    },
    cancelDelete () {
      this.deleteDialog = false
    },
    //  添加电子围栏
    addFence () {
      this.showTable = false
    },
    //  确认添加电子围栏
    sureAdd () {
      http.post('/fence/add', this.addFenceData).then(resp => {
        this.$message.success(resp.message)
        this.refreshTable()
      })
      this.showTable = true
      this.isSelect = true
      this.isGeneratePolygon = true
      this.isStartEdit = true
      this.isEndEdit = true
    },
    //  取消添加电子围栏
    cancelAdd () {
      this.showTable = true
      this.isSelect = true
      this.isGeneratePolygon = true
      this.isStartEdit = true
      this.isEndEdit = true
    },
    //  重新选择
    reSelect () {
      this.$refs.addElectricFence.clearMarker()
      this.isSelect = true
      this.isGeneratePolygon = true
      this.isStartEdit = true
      this.isEndEdit = true
    },
    //  生成多边形
    generatePolygon () {
      this.$refs.addElectricFence.polygon()
      this.isSureAdd = false
      this.isSelect = false
      this.isGeneratePolygon = true
      this.isStartEdit = false
      this.isEndEdit = false
    },
    //  开始编辑
    startEdit () {
      this.$refs.addElectricFence.startEditPolygon()
    },
    //  结束编辑
    endEdit () {
      this.$refs.addElectricFence.endEditPolygon()
    },
    //  是否添加标注
    isAddMarker (val) {
      this.isSelect = val
      this.isGeneratePolygon = val
    },
    //  电子围栏数据
    fenceData (data) {
      this.addFenceData.point = data
    },
    refreshTable () {
      http.get('/fence/get').then(resp => {
        this.fenceTableData = resp.data
        this.polygonPathData = resp.data
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
  margin-top: 20px;
  display: flex;
  .fenceTableContainer {
    flex: 4;
    height: 550px;
    margin-right: 30px;
    .addFenceBtn {
      margin-bottom: 15px;
    }
  }
  .mapContainer {
    height: 400px;
    flex: 6;
    width: 100%;
    padding: 30px;
  }
  .addFenceContainer {
    text-align: center;
    .el-form {
      margin-top: 50px;
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
    color: #02a6b5;
  }
  thead {
    color: #0652DD;
  }
  th {
    background-color: rgb(4, 25, 63);
  }
  tr{
    background-color: rgb(4, 25, 63);
  }
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
  font-size: 16px;
}
</style>
