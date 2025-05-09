<template>

  <div class="resource-content">

    <el-row :gutter="20">

      <el-col :span="8">
        <el-card class="box-card">
          <el-table
            :data="userData" highlight-current-row
            border
            @row-click="clickUserRow"
            :header-cell-style="{'text-align':'center'}"
            style="width: 100%">
            <el-table-column type="index"  width="50" label="序号" align="center"></el-table-column>
            <el-table-column prop="mobile" label="手机号" align="center"> </el-table-column>
            <el-table-column prop="loginName" label="名称" align="center"> </el-table-column>
          </el-table>
        </el-card>
      </el-col>


      <el-col :span="8">
        <el-card class="box-card">
          <div style="float:right;margin-right:8px; margin-bottom: 10px">
            <el-button type="primary" @click="saveRole">保存</el-button>
          </div>
          <el-table  :data="roleTableData" highlight-current-row  border   @row-click="clickRoleRow" style="width: 100%">
            <el-table-column label="单选" width="50" align="center">
              <template slot-scope="scope">
                <el-radio v-model="currentSelectId" :label="scope.row.id" >&nbsp;</el-radio>
              </template>
            </el-table-column>
            <el-table-column type="index"  width="50" label="序号"></el-table-column>
            <el-table-column prop="name" label="角色名" > </el-table-column>
            <el-table-column prop="description" label="角色描述"> </el-table-column>
          </el-table>
        </el-card>
      </el-col>


      <el-col :span="8">
        <el-card class="box-card">
          <div class="custom-tree-container" style="overflow: auto;max-height:700px ">
            <div class="block">
              <el-tree ref="tree" :data="rbacData"  node-key="id" show-checkbox
                        default-expand-all   highlight-current
                        :expand-on-click-node="false">
      <span class="custom-tree-node" slot-scope="{ node, data }">
        <span>{{ node.label }} </span>
      </span>
              </el-tree>
            </div>
          </div>
        </el-card>
      </el-col>

    </el-row>



  </div>

</template>

<script>

  import rbac from '../api/rbac/rbac_api';
  export default {
    data() {
      return {
        rbacData:[],
        userData:[],
        roleTableData:[],
        currentSelectId:null,
        userItem:null
      }
    },
    mounted: function () {
      const _this = this;
      _this.findAllUser();
      _this.rbacRoles();
      _this.rbacResoure();
    },
    methods: {

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

      clickRoleRow(row){
        const _this=this;
        //查询当前角色拥有的权限
        _this.currentSelectId=row.id;
        _this.getResourceIdsByRoleId(row.id)
      },


      clickUserRow(row){
        const _this=this;
        //查询当前角色拥有的权限
        _this.getRoleByUserId(row.id)
        _this.userItem=row;
      },





      rbacResoure () {
        const _this = this;
        rbac.rbacResoure().then(function (result) {
          if (result && result.code === 200) {
            _this.rbacData=result.data;
            for(var i = 0; i < _this.rbacData.length; i++){
              _this.rbacData[i].disabled=true
              if(_this.rbacData[i].hasOwnProperty("children")){
                for(var j = 0; j <  _this.rbacData[i].children.length; j++){
                  _this.rbacData[i].children[j].disabled=true
                  if(_this.rbacData[i].children[j].hasOwnProperty("children")){
                    for(var k = 0; k <  _this.rbacData[i].children[j].children.length; k++){
                      _this.rbacData[i].children[j].children[k].disabled=true
                    }
                  }
                }
              }
            }
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
            _this.$refs.tree.setCheckedKeys(result.data);
          }else {
            _this.$message.error(result.message)
          }
        }, function (err) {
          _this.$message.error(err.toString())
        })
      },
      findAllUser(){
        const _this = this;
        rbac.findAllUser().then(function (result) {
          if (result && result.code === 200) {
            _this.userData=result.data;
          }else {
            _this.$message.error(result.message)
          }
        }, function (err) {
          _this.$message.error(err.toString())
        })
      },

      getRoleByUserId(userId){
        const _this = this;
        rbac.getRoleByUserId({"userId":userId}).then(function (result) {
          if (result && result.code === 200) {
            if(result.data){
              _this.currentSelectId=result.data.roleId;
              _this.getResourceIdsByRoleId(result.data.roleId)
            }else {
              _this.currentSelectId=null;
              _this.$refs.tree.setCheckedKeys([]);
            }
          }else {
            _this.$message.error(result.message)
          }
        }, function (err) {
          _this.$message.error(err.toString())
        })
      },
      saveRole(){
        const _this=this;
        if(!_this.userItem){
          _this.$message.warning("请先选择用户");
          return false;
        }
        if(!_this.currentSelectId){
          _this.$message.warning("请先选择角色");
          return false;
        }
        rbac.addRoleOfUser({"userId":_this.userItem.id,"roleId":_this.currentSelectId}).then(function (result) {
          if (result && result.code === 200) {
            _this.$message.success("修改成功");
            _this.getRoleByUserId(_this.userItem.id)
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
