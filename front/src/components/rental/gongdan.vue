<template>
  <div class="ticket-management-container">
    <!-- 添加按钮 -->
    <div style="width: 100%;display: flex;justify-content: left;padding-left: 10%;padding-bottom: 20px">
      <el-button type="primary" @click="findList" style="margin-bottom: 30px;">刷新</el-button></div>

    <!-- 工单列表表格 -->
    <el-table :data="ticketList" style="width: 80%; margin: 0 auto;" border>
      <el-table-column prop="id" label="工单编号" align="center"></el-table-column>
      <el-table-column prop="mobile" label="用户手机号" width="150"></el-table-column>
      <el-table-column prop="mail" label="用户邮箱" width="200"></el-table-column>
      <el-table-column prop="content" label="问题描述" align="center"></el-table-column>
      <el-table-column prop="dealResult" label="处理结果" align="center"></el-table-column>
      <el-table-column label="提交时间" align="center">
        <template slot-scope="scope">
          {{scope.row.createTime|global_time_filter}}
        </template>
      </el-table-column>
      <el-table-column prop="status" label="状态" align="center">
        <template slot-scope="scope">
          <el-tag :type="scope.row.status === '1' ? 'success' : 'danger'">
           <span v-if="scope.row.status === '0'"> 未处理</span>
           <span v-else> 已处理</span>
          </el-tag>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center">
        <template slot-scope="scope">
          <el-button
              v-if="scope.row.status === '1'"
              type="text">
            已处理
          </el-button>
          <el-button
              v-if="scope.row.status === '0'"
              type="text"
              @click="handleProcess(scope.row)">
            去处理
          </el-button>
        </template>
      </el-table-column>
    </el-table>

    <!-- 处理工单弹窗 -->
    <el-dialog :visible.sync="processDialogVisible" title="处理工单" width="30%">
      <el-form :model="processForm">
        <el-form-item label="处理内容">
          <el-input
              type="textarea"
              v-model="processForm.content"
              :rows="4"
              placeholder="请输入处理内容"
          ></el-input>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button @click="processDialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="submitProcess">确 定</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<script>
import feedBack from '../../api/rental/feedBack'
export default {
  data() {
    return {
      // 工单列表数据
      ticketList: [],
      // 处理工单弹窗是否显示
      processDialogVisible: false,
      // 当前处理的工单
      currentTicket: null,
      // 处理表单数据
      processForm: {
        content: '',
      },
    };
  },
  created(){
    this.findList()
  },
  methods: {
    // 点击“去处理”按钮
    handleProcess(ticket) {
      this.currentTicket = ticket;
      this.processForm.content = ''; // 清空表单内容
      this.processDialogVisible = true;
    },
    findList(){
      const _this=this;
      feedBack.findList().then(function (result) {
        if (result && result.code === 200) {
          _this.ticketList=result.data
        }else {
          _this.$message.error(result.message)
        }
      }, function (err) {
        _this.$message.error(err.toString())
      })
    },
    // 提交处理内容
    submitProcess() {
      const _this=this;
      if (!_this.processForm.content) {
        _this.$message.warning('请输入处理内容');
        return;
      }
      const param={
        "id":_this.currentTicket.id,
        "dealResult":_this.processForm.content
      }
      feedBack.update(param).then(function (result) {
        if (result && result.code === 200) {
          _this.findList()
          // 更新工单状态为“已处理”
          _this.currentTicket.status = '1';
          _this.processDialogVisible = false;
          // 提示处理成功
          _this.$message.success('工单已处理');

        }else {
          _this.$message.error(result.message)
        }
      }, function (err) {
        _this.$message.error(err.toString())
      })

    },
  },
};
</script>

<style scoped>
.ticket-management-container {
  text-align: center;
  padding: 20px;
}
</style>