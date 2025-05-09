<template>
  <div class="user-list-container">
    <!-- 搜索框 -->
    <div class="search-box">
      <el-input
          v-model="mobile"
          placeholder="请输入手机号筛选"
          clearable
          @clear="findList"
          @keyup.enter.native="findList"
          style="width: 300px;"
      >
        <el-button slot="append" icon="el-icon-search" @click="findList"></el-button>
      </el-input>
    </div>

    <!-- 用户列表 -->
    <el-table :data="userList" border style="width: 80%;height: 100%; margin: 0 auto;">
      <el-table-column prop="mobile" label="手机号" width="150"  align="center"></el-table-column>
      <el-table-column prop="mail" label="邮箱" width="200"  align="center"></el-table-column>
      <el-table-column label="头像" width="120"  align="center">
        <template slot-scope="scope">
          <el-image
              :preview-src-list='[scope.row.imageUrl]'
              style="width: 80px; height: 80px; border-radius: 50%;"
              :src="scope.row.imageUrl"
              fit="cover"
          ></el-image>
        </template>
      </el-table-column>
      <el-table-column label="身份证正面照片" width="200"  align="center">
        <template slot-scope="scope">
          <el-image
              :preview-src-list='[scope.row.idCardUrl]'
              style="width: 100px; height: 60px;"
              :src="scope.row.idCardUrl"
              fit="cover"
          ></el-image>
        </template>
      </el-table-column>
      <el-table-column label="行驶证正面照片" width="200"  align="center">
        <template slot-scope="scope">
          <el-image
              :preview-src-list='[scope.row.dlCardUrl]'
              style="width: 100px; height: 60px;"
              :src="scope.row.dlCardUrl"
              fit="cover"
          ></el-image>
        </template>
      </el-table-column>
      <el-table-column label="审核状态" width="120"  align="center">
        <template slot-scope="scope">
          <el-tag :type="scope.row.audit === '1' ? 'success' : scope.row.audit === '2'?'danger':'warning'">
            {{ scope.row.audit === '1' ? '审核成功': scope.row.audit === '2'?'审核失败': '待审核' }}
          </el-tag>
        </template>
      </el-table-column>
      <el-table-column fixed="right" width="300" label="状态"  align="center">
        <template slot-scope="scope">
          <el-button v-show="scope.row.audit === '0'||scope.row.audit === '2'" @click="handleApprove(scope.row, '1')" type="text" size="small">审核成功</el-button>
          <el-button  v-show="scope.row.audit === '0'||scope.row.audit === '2'" @click="handleApprove(scope.row, '2')" type="text" size="small">审核失败</el-button>
          <el-tag  v-show="scope.row.audit === '1'" type="success">审核成功</el-tag>
        </template>
      </el-table-column>
    </el-table>
  </div>
</template>

<script>
import rentaluser from '../../api/rental/rentaluser'
export default {
  data() {
    return {
      mobile: '', // 搜索的手机号
      userList: [],
    };
  },
 created() {
   this.findList()
    },
  methods: {
    findList(){
      const _this=this;
      rentaluser.findAllUser({"mobile":_this.mobile}).then(function (result) {
        if (result && result.code === 200) {
          _this.userList=result.data
        }else {
          _this.$message.error(result.message)
        }
      }, function (err) {
        _this.$message.error(err.toString())
      })
    },
    // 处理审核
    handleApprove(user, audit) {
      const _this=this;
      const action = audit === '1' ? '审核成功' : '审核失败';
      _this.$confirm(`确定要${action}吗？`, '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning',
      })
          .then(() => {
            user.audit=audit
            rentaluser.update(user).then(function (result) {
              if (result && result.code === 200) {
                _this.findList()
                _this.$message({
                  message: `${action}成功`,
                  type: 'success',
                });
              }else {
                _this.$message.error(result.message)
              }
            }, function (err) {
              _this.$message.error(err.toString())
            })
          })
          .catch(() => {
            _this.$message({
              message: '已取消',
              type: 'info',
            });
          });
    },
  },
};
</script>

<style scoped>
.user-list-container {
  padding: 20px;
  text-align: left; /* 搜索框靠左 */
}

.search-box {
  margin-bottom: 20px;
  margin-left: 10%;
}

.el-table {
  width: 80%;
  margin: 0 auto; /* 列表居中 */
}
.user-list-container{
  height: 80vh;
  overflow-y: auto;
}
</style>