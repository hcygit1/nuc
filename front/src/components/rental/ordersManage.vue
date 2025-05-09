<template>
  <div class="order-list-container">
    <!-- 筛选输入框 -->
    <div class="filter-container">
      <el-input
          v-model="mobile"
          placeholder="请输入用户手机号"
          clearable
          @clear="findList"
          @keyup.enter.native="findList"
          style="width: 300px;">
        <el-button slot="append" icon="el-icon-search" @click="findList"></el-button>
      </el-input>
    </div>

    <!-- 订单列表表格 -->
    <el-table border
        :data="orderList"
        style="width: 80%;height: 100%; margin: 50px auto;"
        :header-cell-style="{ textAlign: 'center' }"
        :cell-style="{ textAlign: 'center' }"
    >
      <el-table-column prop="mobile" label="用户手机号"></el-table-column>
      <el-table-column prop="mail" width="300" label="用户邮箱"></el-table-column>
      <el-table-column prop="model" label="车辆型号"></el-table-column>
      <el-table-column prop="brand" label="车辆品牌"></el-table-column>
      <el-table-column label="租车费用(元)">
        <template slot-scope="scope">
          {{scope.row.totalMoney/100}}
        </template>
      </el-table-column>
      <el-table-column prop="vehicleModel" label="车型"></el-table-column>
      <el-table-column prop="endurance" label="续航"></el-table-column>
      <el-table-column  label="租赁时间"  width="300" >
        <template slot-scope="scope">
         <div>{{ scope.row.rentalStartTime|global_time_filter}}</div>
          至
          <div>{{ scope.row.rentalEndTime|global_time_filter}}</div>
        </template>
      </el-table-column>
      <el-table-column label="租赁合同PDF" fixed="right">
        <template slot-scope="scope">
          <el-button type="text" @click="viewPDF(scope.row.pdfUrl)">查看合同</el-button>
        </template>
      </el-table-column>
    </el-table>
  </div>
</template>

<script>
import rentalOrder from '../../api/rental/rentalOrder'
export default {
  data() {
    return {
      // 订单数据
      orderList: [],
      mobile: '', // 筛选手机号
    };
  },
  created() {
    // 初始化时显示全部订单
    this.findList();
  },
  methods: {
    findList(){
      const _this=this;
      rentalOrder.findListOrders({"mobile":_this.mobile}).then(function (result) {
        if (result && result.code === 200) {
          _this.orderList=result.data
        }else {
          _this.$message.error(result.message)
        }
      }, function (err) {
        _this.$message.error(err.toString())
      })
    },
    // 查看 PDF
    viewPDF(pdfUrl) {
      window.open(pdfUrl, '_blank');
    },
  }
};
</script>

<style scoped>
.order-list-container {
  width: 100%;
  text-align: center;
}

.filter-container {
  margin-left:10%;
  margin-top: 20px;
  margin-bottom: 50px; /* 距离列表 50px 高度 */
  display: flex;
  text-align: left;
}
</style>