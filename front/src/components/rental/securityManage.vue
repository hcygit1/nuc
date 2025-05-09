<template>
  <div class="vehicle-list">
    <el-table :data="vehicles" style="width: 80%; margin: 0 auto;height: 100%" border>
      <el-table-column prop="model" label="型号" align="center"></el-table-column>
      <el-table-column prop="brand" label="品牌" align="center"></el-table-column>
      <el-table-column prop="price" label="租车价格/天" align="center"></el-table-column>
      <el-table-column prop="vehicleModel" label="车型" align="center"></el-table-column>
      <el-table-column prop="endurance" label="续航/km" align="center"></el-table-column>
      <el-table-column label="操作" align="center">
        <template slot-scope="scope">
          <el-button type="primary" @click="viewRentalRecords(scope.row)">查看租赁记录</el-button>
        </template>
      </el-table-column>
    </el-table>
  </div>
</template>

<script>
import rentalcar from '../../api/rental/rentalcar'
export default {
  data() {
    return {
      vehicles: []
    };
  },
  created() {
  this.findList()
  },
  methods: {
    findList(){
      const _this=this;
      rentalcar.findList().then(function (result) {
        if (result && result.code === 200) {
          _this.vehicles=result.data.carList
        }else {
          _this.$message.error(result.message)
        }
      }, function (err) {
        _this.$message.error(err.toString())
      })
    },
    viewRentalRecords(vehicle) {
      this.$router.push({ path: '/rentalRecord', query: { carId: vehicle.id } });
    }
  }
};
</script>

<style scoped>
.vehicle-list {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100vh;
}
</style>