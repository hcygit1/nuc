<template>

  <div class="resource-content">
    <el-row :gutter="20">


      <el-col :span="10">
        <el-card class="box-card">
          <div class="custom-tree-container">
            <div class="block">
              <div style="height: 70px;font-size: larger">系统资源</div>
              <el-tree  :data="rbacData"  node-key="id"
                        default-expand-all   highlight-current :current-node-key="1" :expand-on-click-node="false"
                        @node-click="handleNodeClick">
      <span class="custom-tree-node" slot-scope="{ node, data }">
        <span>{{ node.label }}
        </span>
        <span>
             <el-button  :disabled="node.level=='3'" type="text"  size="mini"  @click="() => append(data)">
            添加节点
          </el-button>
            <el-button :disabled="node.level=='1'"  type="text"    size="mini" @click="() => remove(node, data)">
            删除节点
          </el-button>
        </span>
      </span>
          </el-tree>
            </div>
          </div>
        </el-card>
      </el-col>


      <el-col :span="14">
        <el-card class="box-card">
          <el-table
            :data="rbacTableData"
            border
            max-height="700"
            style="width: 100%">
            <el-table-column prop="label" label="功能名" width="150"> </el-table-column>
            <el-table-column prop="description" label="功能描述" width="150"> </el-table-column>
            <el-table-column prop="iconStyle" label="功能icon图标" width="150"> </el-table-column>
            <el-table-column prop="jumpPath" label="跳转路径" width="150"> </el-table-column>
            <el-table-column prop="sort" label="排序" width="100"> </el-table-column>
            <el-table-column fixed="right"  label="操作" width="100">
              <template slot-scope="scope">
                <el-button type="text" size="small" @click="editResource(scope.row)">编辑</el-button>
              </template>
            </el-table-column>
          </el-table>
        </el-card>
      </el-col>



    </el-row>

    <el-dialog title="添加资源节点" :visible.sync="dialogFormVisible" class="dialog_content"  :before-close="cancelSubmit">
      <el-form :model="rbacResourceForm" label-position="left" label-width="100px">
        <el-row :gutter="40">
          <el-col :span="10">
            <el-form-item label="资源名称:" >
              <el-input v-model="rbacResourceForm.name" autocomplete="off"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="10">
            <el-form-item label="资源描述:">
              <el-input v-model="rbacResourceForm.description" autocomplete="off"></el-input>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row :gutter="40">
          <el-col :span="10">
            <el-form-item label="功能图标:">
              <el-input v-model="rbacResourceForm.iconStyle" autocomplete="off"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="10">
            <el-form-item label="跳转路径:">
              <el-input v-model="rbacResourceForm.jumpPath" autocomplete="off"></el-input>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row :gutter="40">
          <el-col :span="10">
            <el-form-item label="排序:" >
              <el-input type="number" v-model="rbacResourceForm.sort" autocomplete="off"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="10">
            <el-form-item label="父节点资源:">
              <el-input  :disabled="true" v-model="rbacResourceForm.parentLable" autocomplete="off"></el-input>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row v-show="false">
          <el-form-item label="父节点ID:">
            <el-input  v-model="rbacResourceForm.parentId" autocomplete="off"></el-input>
          </el-form-item>
        </el-row>
      </el-form>
      <div slot="footer" class="dialog-footer" style="text-align: center">
        <el-button @click="cancelSubmit">取 消</el-button>
        <el-button type="primary"  @click="onSubmit">确 定</el-button>
      </div>
    </el-dialog>


    <el-dialog title="修改资源" :visible.sync="editDialogFormVisible" class="dialog_content"  :before-close="cancelEditSubmit">
      <el-form :model="editForm" label-position="left" label-width="100px">
        <el-row :gutter="40">
          <el-col :span="10">
            <el-form-item label="资源名称:" >
              <el-input v-model="editForm.label" autocomplete="off"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="10">
            <el-form-item label="资源描述:">
              <el-input v-model="editForm.description" autocomplete="off"></el-input>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row :gutter="40">
          <el-col :span="10">
            <el-form-item label="功能图标:">
              <el-input v-model="editForm.iconStyle" autocomplete="off"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="10">
            <el-form-item label="跳转路径:">
              <el-input v-model="editForm.jumpPath" autocomplete="off"></el-input>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row :gutter="40">
          <el-col :span="10">
            <el-form-item label="排序:" >
              <el-input type="number" v-model="editForm.sort" autocomplete="off"></el-input>
            </el-form-item>
          </el-col>
        </el-row>
      </el-form>
      <div slot="footer" class="dialog-footer" style="text-align: center">
        <el-button @click="cancelEditSubmit">取 消</el-button>
        <el-button type="primary"  @click="onEditSubmit">确 定</el-button>
      </div>
    </el-dialog>

  </div>

</template>

<script>

  import rbac from '../api/rbac/rbac_api';
  export default {
    data() {
      return {
        rbacData:[],
        dialogFormVisible:false,
        editDialogFormVisible:false,
        rbacResourceForm:{},
        editForm:{},
        rbacTableData:[],
        parentLable:'',
        parentId:''
      }
    },
    mounted: function () {
      const _this = this;
      _this.rbacResoure();
    },
    methods: {
      append(data) {
        const _this=this;
        _this.rbacResourceForm.parentLable=data.﻿label;
        _this.rbacResourceForm.parentId=data.﻿id;
        _this.dialogFormVisible=true;
      },

      remove(node, data) {
        const _this = this;
        _this.$confirm('是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          rbac.removeResoure(data).then(function (result) {
            if (result && result.code === 200) {
              _this.rbacResoure();
              _this.$message.success("删除成功")
            }else {
              _this.$message.error(result.message)
            }
          }, function (err) {
            _this.$message.error(err.toString())
          })
        }).catch(() => {
          _this.$message({
            type: 'info',
            message: '已取消删除'
          });
        });

      },

      handleNodeClick(data) {
        const _this=this;
        _this.rbacTableData=data.children;
      },

      onSubmit(){
        const _this = this;
        rbac.addResoure(_this.rbacResourceForm).then(function (result) {
          if (result && result.code === 200) {
            _this.cancelSubmit();
            _this.rbacResoure();
            _this.$message.success("添加成功")
          }else {
            _this.$message.error(result.message)
          }
        }, function (err) {
          _this.$message.error(err.toString())
        })
      },

      onEditSubmit(){
        const _this = this;
        rbac.updateResource(_this.editForm).then(function (result) {
          if (result && result.code === 200) {
            _this.cancelEditSubmit();
            _this.rbacResoure();
            _this.$message.success("修改成功")
          }else {
            _this.$message.error(resmult.message)
          }
        }, function (err) {
          _this.$message.error(err.toString())
        })
      },

      cancelSubmit(){
        const _this=this;
        _this.dialogFormVisible=false,
        _this.rbacResourceForm={}
      },
      cancelEditSubmit(){
        const _this=this;
        _this.editDialogFormVisible=false,
        _this.editForm={}
      },
      rbacResoure () {
        const _this = this;
        rbac.rbacResoure().then(function (result) {
          if (result && result.code === 200) {
            _this.rbacData=result.data;
            if(_this.rbacData.length>0){
              _this.handleNodeClick(_this.rbacData[0]);
            }
          }else {
            _this.$message.error(result.message)
          }
        }, function (err) {
          _this.$message.error(err.toString())
        })
      },

      editResource(row){
        const _this=this;
        _this.editDialogFormVisible=true;
        _this.editForm=row;
      }
    },

  };

</script>

<style>
  .custom-tree-node {
    flex: 1;
    display: flex;
    align-items: center;
    justify-content: space-between;
    font-size: 14px;
    padding-right: 8px;
  }
  .resource-content{
    margin-top: 50px;
    margin-left: 50px;
    margin-right: 50px;
  }
  .box-card{
    height:700px;
  }
</style>
