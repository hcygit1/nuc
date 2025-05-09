<template>

  <div class="resource-content">
    <el-row :gutter="20">


      <el-col :span="12">
        <el-card class="box-card">
          <div style="float:right;margin-right:8px; margin-bottom: 10px">
            <el-button type="primary" @click="addRole">添加</el-button>
          </div>
          <el-table :data="roleTableData" highlight-current-row  border  @row-click="clickRow" style="width: 100%">
            <el-table-column type="index"  width="50" label="序号"></el-table-column>
            <el-table-column prop="name" label="角色名" > </el-table-column>
            <el-table-column prop="description" label="角色描述"> </el-table-column>
            <el-table-column fixed="right"  label="操作" >
              <template slot-scope="scope">
                <el-button type="text" size="small"  @click="editRole(scope.row)">编辑</el-button>
                <el-button type="text" size="small"  @click="remove(scope.row)">删除</el-button>
              </template>
            </el-table-column>
          </el-table>
        </el-card>
      </el-col>


      <el-col :span="12">
        <el-card class="box-card">
          <div style="float:right;margin-right:8px; margin-bottom: 10px">
            <el-button type="primary" @click="addroleResouce">保存</el-button>
          </div>
          <div class="custom-tree-container" style="overflow: auto;max-height:700px ">
            <div class="block" >
              <div style="height: 70px;font-size: larger">系统资源</div>
              <el-tree   ref="tree" :data="rbacData"  node-key="id" show-checkbox
                       default-expand-all   highlight-current
                       :expand-on-click-node="false"
                        @node-click="handleNodeClick">
      <span class="custom-tree-node" slot-scope="{ node, data }">
        <span>{{ node.label }}
        </span>
      </span>
              </el-tree>
            </div>
          </div>
        </el-card>
      </el-col>

    </el-row>

    <el-dialog title="添加资源节点" :visible.sync="dialogFormVisible" class="dialog_content"  :before-close="cancelSubmit">
      <el-form :model="rbacResourceForm" label-position="left" label-width="100px">
        <el-row :gutter="40">
          <el-col :span="10">
            <el-form-item label="角色名:" >
              <el-input v-model="addForm.name" autocomplete="off"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="10">
            <el-form-item label="角色名描述:">
              <el-input v-model="addForm.description" autocomplete="off"></el-input>
            </el-form-item>
          </el-col>
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
              <el-input v-model="editForm.name" autocomplete="off"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="10">
            <el-form-item label="资源描述:">
              <el-input v-model="editForm.description" autocomplete="off"></el-input>
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
        addForm:{},
        roleTableData:[],
        selectTableData:{},
      }
    },
    mounted: function () {
      const _this = this;
      _this.rbacRoles();
      _this.rbacResoure();
    },
    methods: {

      remove(data) {
        const _this = this;
        _this.$confirm('是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          rbac.removeRole(data).then(function (result) {
            if (result && result.code === 200) {
              _this.rbacRoles();
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
        rbac.addRole(_this.addForm).then(function (result) {
          if (result && result.code === 200) {
            _this.cancelSubmit();
            _this.rbacRoles();
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
        rbac.updateRole(_this.editForm).then(function (result) {
          if (result && result.code === 200) {
            _this.cancelEditSubmit();
            _this.rbacRoles();
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
        _this.addForm={}
      },
      cancelEditSubmit(){
        const _this=this;
        _this.editDialogFormVisible=false,
        _this.editForm={}
      },
      rbacRoles () {
        const _this = this;
        rbac.rbacRoles().then(function (result) {
          if (result && result.code === 200) {
            _this.roleTableData=result.data;
          }else {
            _this.$message.error(result.message)
          }
        }, function (err) {
          _this.$message.error(err.toString())
        })
      },

      editRole(row){
        const _this=this;
        _this.editDialogFormVisible=true;
        _this.editForm=row;
      },
      addRole(){
        const _this=this;
        _this.dialogFormVisible=true;
      },
      clickRow(row){
        const _this=this;
        //查询当前角色拥有的权限
        _this.getResourceIdsByRoleId(row.id)
        _this.selectTableData=row;
      },
      rbacResoure () {
        const _this = this;
        rbac.rbacResoure().then(function (result) {
          if (result && result.code === 200) {
            _this.rbacData=result.data;
          }else {
            _this.$message.error(result.message)
          }
        }, function (err) {
          _this.$message.error(err.toString())
        })
      },
      getResourceIdsByRoleId (id) {
        const _this = this;
        rbac.getResourceIdsByRoleId({"roleId":id}).then(function (result) {
          if (result && result.code === 200) {
            console.log("拥有权限ID=="+JSON.stringify(result.data))
            _this.$refs.tree.setCheckedKeys(result.data);
          }else {
            _this.$message.error(result.message)
          }
        }, function (err) {
          _this.$message.error(err.toString())
        })
      },
      addroleResouce(){
          const _this=this;
          if(!_this.selectTableData.id){
            _this.$message.error("先选择角色")
            return false;
          }
         let param={
            "listIds":_this.$refs.tree.getCheckedKeys(false,true),
             "roleId":_this.selectTableData.id
         }
        rbac.addroleResouce(param).then(function (result) {
          if (result && result.code === 200) {
            _this.$message.success("保存成功")
          _this.rbacRoles();
          }else {
            _this.$message.error(result.message)
          }
        }, function (err) {
          _this.$message.error(err.toString())
        })
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
