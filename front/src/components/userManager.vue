<template>

  <div class="resource-content">
    <div>
      <el-form :label-position="labelPosition" size="small">
        <el-form-item >
          <el-row :gutter="20">
            <el-col :span="4">
              <el-form-item label="手机号:"  label-width="60px">
                <el-input v-model="queryItems.mobile"></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="4">
                <el-form-item label="名称:"  label-width="60px">
                  <el-input v-model="queryItems.loginName"></el-input>
                </el-form-item>
            </el-col>
            <el-col :span="4">
              <el-form-item label="年龄:"  label-width="60px">
                <el-input v-model="queryItems.age"></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="4">
              <el-form-item label="性别:"  label-width="60px">
                <el-select v-model="queryItems.sex" >
                  <el-option
                    v-for="item in querySexOptions"
                    :key="item.value"
                    :label="item.label"
                    :value="item.value">
                  </el-option>
                </el-select>
              </el-form-item>
            </el-col>
            <el-col :span="4">
              <el-form-item label="地址:"  label-width="60px">
                <el-input v-model="queryItems.address"></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="2">
              <el-button type="primary" @click="findAllUserIncludeUnEable(1)">查询</el-button>
            </el-col>
            <el-col :span="2">
              <el-button type="primary" @click="addUser()">添加</el-button>
            </el-col>
          </el-row>
        </el-form-item>
      </el-form>
    </div>
    <el-row :gutter="20">

      <el-col :span="24">
        <el-card class="box-card">
          <el-table
            :data="itemList.items" highlight-current-row
            border
            :header-cell-style="{'text-align':'center'}"
            style="width: 100%">
            <el-table-column type="index"  width="50" label="序号" align="center"></el-table-column>
            <el-table-column prop="mobile" label="手机号" align="center"> </el-table-column>
            <el-table-column prop="loginName" label="名称" align="center"> </el-table-column>
            <el-table-column prop="age" label="年龄" align="center"> </el-table-column>
            <el-table-column prop="address" label="地址" align="center"> </el-table-column>
            <el-table-column prop="sex" label="性别" align="center"> </el-table-column>
            <el-table-column fixed="right"  label="状态"  align="center">
              <template slot-scope="scope">
                <el-button @click="handleClick(scope.row)" type="text" size="small">查看</el-button>
                <el-button  @click="editItem(scope.row)"  type="text" size="small">编辑</el-button>
                <el-button  @click="deleteUser(scope.row)"  type="text" size="small">删除</el-button>
              </template>
            </el-table-column>
          </el-table>
          <div> <!--分页组件-->
            <pagination :data-list="itemList" @change-page="findAllUserIncludeUnEable"></pagination>
          </div>
        </el-card>

      </el-col>

    </el-row>

    <el-dialog title="用户信息" :visible.sync="dialogFormVisible">
      <el-form>
        <el-form-item label="手机号:" label-width="120px">
          <el-input v-model="item.mobile" autocomplete="off" :disabled="true"></el-input>
        </el-form-item>
        <el-form-item label="名称:" label-width="120px">
          <el-input v-model="item.loginName" autocomplete="off"  :disabled="true"></el-input>
        </el-form-item>
        <el-form-item label="年龄:" label-width="120px">
          <el-input v-model="item.age" autocomplete="off"  :disabled="true"></el-input>
        </el-form-item>
        <el-form-item label="地址:" label-width="120px">
          <el-input v-model="item.address" autocomplete="off"  :disabled="true"></el-input>
        </el-form-item>
        <el-form-item label="性别:" label-width="120px">
          <el-input v-model="item.sex" autocomplete="off"  :disabled="true"></el-input>
        </el-form-item>
      </el-form>
    </el-dialog>
    <el-dialog :title="editTitle" :visible.sync="editDialogFormVisible">
      <el-form>
        <el-form-item label="手机号:" label-width="120px">
          <el-input v-model="item.mobile" autocomplete="off" ></el-input>
        </el-form-item>
        <el-form-item label="名称:" label-width="120px">
          <el-input v-model="item.loginName" autocomplete="off" ></el-input>
        </el-form-item>
        <el-form-item label="年龄:" label-width="120px" >
          <el-input v-model="item.age" autocomplete="off" type="number" ></el-input>
        </el-form-item>
        <el-form-item label="地址:" label-width="120px">
          <el-input v-model="item.address" autocomplete="off"  ></el-input>
        </el-form-item>
        <el-form-item label="性别:" label-width="120px">
          <el-select v-model="item.sex" >
            <el-option
              v-for="item in sexOptions"
              :key="item.value"
              :label="item.label"
              :value="item.value">
            </el-option>
          </el-select>

        </el-form-item>
      </el-form>
            <div slot="footer" class="dialog-footer-update-info">
              <el-button @click="editDialogFormVisible = false">取 消</el-button>
              <el-button @click="updateUserInfo" type="primary">确 定</el-button>
            </div>
    </el-dialog>
  </div>

</template>

<script>
import {encryptPassword} from '../utils/encrypt_p';
import pagination from '../components/pagination.vue'
  import rbac from '../api/rbac/rbac_api';
  import userApi from '../api/user/user_api';

import {transformDate} from '../utils/dateUtil';
  export default {
    data() {
      return {
        userData:[],
        itemList: {items: []},  //查询结果
        queryItems:{             //查询条件
          page: 1,//页数
          limit: 10,//每页展示个数
          mobile: "",
          loginName:"",
          age:"",
          address:"",
          sex:"",
        },
        item:{},
        queryItem:{},
        editTitle:"",
        dialogFormVisible:false,
        editDialogFormVisible:false,
        sexOptions:[
          {
          value: '男',
          label: '男'
        },
          {
            value: '女',
            label: '女'
          }
        ],
        querySexOptions:[
          {
          value: '男',
          label: '男'
        },
          {
            value: '女',
            label: '女'
          }
        ],
        labelPosition: 'left',
      }
    },
    mounted: function () {
      const _this = this;
      _this.findAllUserIncludeUnEable();
    },
    components: {
      'pagination': pagination,
    },

    methods: {
      findAllUserIncludeUnEable(page, limit){
        const _this = this;
        if(page){
          _this.queryItems.page = page;
        }
        if(limit){
          _this.queryItems.limit = limit;
        }
        rbac.findAllUserIncludeUnEable(_this.queryItems).then(function (result) {
          if (result && result.code === 200) {
            console.log("result.data=="+JSON.stringify(result.data))
            _this.itemList = result.data;
          }else {
            _this.$message.error(result.message)
          }
        }, function (err) {
          _this.$message.error(err.toString())
        })
      },
      handleClick(row){
        const _this=this;
       _this.item=row;
       _this.dialogFormVisible=true;
      },
      editItem(row){
        const _this=this;
        _this.item=row;
        _this.editTitle="编辑用户信息";
        _this.editDialogFormVisible=true;
        console.log(JSON.stringify(row))
      },
      updateUserInfo(){
        const _this = this;
        var isPhone = /^1[3456789]\d{9}$/;
        if (!isPhone.test(_this.item.mobile)) {
          _this.$message.warning("手机号格式不正确");
          return false;
        }
        if (!_this.item.address) {
          _this.$message.warning("地址不能为空");
          return false;
        } if (!_this.item.loginName) {
          _this.$message.warning("名称不能为空");
          return false;
        }
        console.log("_this.item.id==="+_this.item.id)
        if(!_this.item.id){
          _this.item.password=encryptPassword("123456789");
          userApi.addUser(_this.item).then(function (result) {
            if (result && result.code === 200) {
              console.log("result.data=="+JSON.stringify(result.data))
              _this.editDialogFormVisible=false;
            }else {
              _this.$message.error(result.message)
            }
          }, function (err) {
            _this.$message.error(err.toString())
          })
        }else {
          userApi.update(_this.item).then(function (result) {
            if (result && result.code === 200) {
              console.log("result.data=="+JSON.stringify(result.data))
              _this.editDialogFormVisible=false;
            }else {
              _this.$message.error(result.message)
            }
          }, function (err) {
            _this.$message.error(err.toString())
          })
        }

      },
      deleteUser(row){
        const _this = this;
        userApi.deleted(row).then(function (result) {
          if (result && result.code === 200) {
            _this.$message.success("删除成功");
            _this.findAllUserIncludeUnEable();
          }else {
            _this.$message.error(result.message)
          }
        }, function (err) {
          _this.$message.error(err.toString())
        })
      },
      changeSwitch(data){
        console.log("SWITCH"+JSON.stringify(data))
      },
      addUser(){
        const _this=this;
        _this.editTitle="添加用户信息";
        _this.item={};
        _this.editDialogFormVisible=true;
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
    text-align: center;
  }
  .box-card{
    height:700px;
  }
  .dialog-footer-update-info{

  }

</style>
