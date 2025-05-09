<template>
  <div class="rental-records">
    <el-table :data="rentalRecords" style="width: 80%; margin: 0 auto;height: 100%" border>
      <el-table-column prop="mobile" label="手机号" align="center"></el-table-column>
      <el-table-column prop="mail" label="邮箱" align="center"></el-table-column>
      <el-table-column prop="startTime" label="租赁开始时间" align="center">
        <template slot-scope="scope">
          {{ scope.row.rentalStartTime|global_time_filter}}
        </template>
      </el-table-column>
      <el-table-column prop="endTime" label="租赁结束时间" align="center">
        <template slot-scope="scope">
          {{ scope.row.rentalEndTime|global_time_filter}}
        </template>
      </el-table-column>
      <el-table-column prop="price" label="租赁费用(元)" align="center">
        <template slot-scope="scope">
          {{scope.row.totalMoney/100}}
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center">
        <template slot-scope="scope">
          <el-button type="primary" @click="viewVehicleTrack(scope.row)">查看车辆轨迹</el-button>
        </template>
      </el-table-column>
    </el-table>

    <el-dialog :visible.sync="dialogVisible" width="50%" :before-close="handleClose">
      <div id="map-container" style="height: 60vh;"></div>
    </el-dialog>
  </div>
</template>

<script>
import rentalOrder from '../../api/rental/rentalOrder'
import traceLngLat from '../../api/rental/traceLngLat'
export default {
  data() {
    return {
      carId:null,
      rentalRecords: [],
      dialogVisible: false,
      map: null
    };
  },
  created() {
    this.carId=this.$route.query.carId
    this.findList()
  },
  methods: {
    findList(){
      const _this=this;
      rentalOrder.findListByCarId({"carId":_this.carId}).then(function (result) {
        if (result && result.code === 200) {
          _this.rentalRecords=result.data
        }else {
          _this.$message.error(result.message)
        }
      }, function (err) {
        _this.$message.error(err.toString())
      })
    },
    viewVehicleTrack(record) {
      this.findListPoints(record.id)
    },
    initMap(record) {
      const firstItem = record.list.length > 0 ? [record.list[0].lng, record.list[0].lat] : null;
      // 使用高德地图 API 初始化地图并绘制轨迹
      this.map = new AMap.Map('map-container', {
        zoom: 13,
        center: firstItem //
       // center:record.length>0?record[0]:[] // 默认中心点，可以根据实际数据调整
      });
      console.log("==record.list="+record.list)
      // 假设 record 中有经纬度数据
      const path = record.list.map(item => [item.lng, item.lat]);
      const polyline = new AMap.Polyline({
        path: path,
        isOutline: true,
        outlineColor: '#ffeeff',
        borderWeight: 1,
        strokeColor: '#3366FF',
        strokeOpacity: 1,
        strokeWeight: 6,
        strokeStyle: 'solid',
        lineJoin: 'round',
        lineCap: 'round',
        zIndex: 50,
      });

      this.map.add(polyline);
      this.map.setFitView();
    },
    findListPoints(orderId){
      const _this=this;
      debugger
      traceLngLat.findList({"orderId":orderId}).then(function (result) {
        if (result && result.code === 200) {
          console.log("===result.data==="+result.data)
          _this.dialogVisible = true;
          _this.$nextTick(() => {
            _this.initMap(result.data);
          });

        }else {
          _this.$message.error(result.message)
        }
      }, function (err) {
        _this.$message.error(err.toString())
      })
    },
    handleClose(done) {
      this.dialogVisible = false;
      done();
    }
  }
};
</script>

<style scoped>
.rental-records {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100vh;
}
</style>