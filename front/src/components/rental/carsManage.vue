<template>
  <div class="vehicle-list-container">
    <!-- 添加按钮 -->
    <div style="width: 100%;display: flex;justify-content: left;padding-left: 10%;padding-bottom: 20px">
      <el-button type="primary" @click="handleAdd" style="margin-bottom: 30px;">添加车辆</el-button>
      <el-button type="primary" @click="findList" style="margin-bottom: 30px;">刷新</el-button></div>


    <!-- 车辆列表表格 -->
    <el-table :data="vehicleList" style="width: 80%; margin: 0 auto;" border>
      <el-table-column prop="model" label="型号" align="center"></el-table-column>
      <el-table-column prop="brand" label="品牌" align="center"></el-table-column>
      <el-table-column prop="classify" label="分类" align="center"></el-table-column>
      <el-table-column prop="price" label="租车价格(元/天)" align="center">
        <template slot-scope="scope">
          {{scope.row.price/100}}
        </template>
      </el-table-column>
      <el-table-column prop="vehicleModel" label="车型" align="center"></el-table-column>
      <el-table-column prop="address" label="地址" align="center"></el-table-column>
      <el-table-column prop="lng" label="经度" align="center"></el-table-column>
      <el-table-column prop="lat" label="纬度" align="center"></el-table-column>
      <el-table-column prop="endurance" label="续航/km" align="center"></el-table-column>
      <el-table-column label="轮播图" align="center">
        <template slot-scope="scope">
          <el-button type="text" @click="handleViewPhotos(scope.row)">查看轮播图</el-button>
        </template>
      </el-table-column>
      <el-table-column prop="glbFileUrl" label="GLB文件" align="center">
        <template slot-scope="scope">
          <el-button type="text" @click="handleViewGLB(scope.row.glbFileUrl)">查看3D模型</el-button>
        </template>
      </el-table-column>
      <el-table-column prop="status" label="车辆状态" align="center">
        <template slot-scope="scope">
          <span v-if="scope.row.status==='0'">待出租</span>
          <span v-else>已租赁</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center">
        <template slot-scope="scope">
          <el-button type="text" @click="handleEdit(scope.row)">修改</el-button>
          <el-button type="text" @click="handleDelete(scope.row)">删除</el-button>
          <el-button type="text" @click="handleViewMaintenance(scope.row)">查看维护记录</el-button>
        </template>
      </el-table-column>
    </el-table>
     <el-dialog :title="dialogTitle" :visible.sync="dialogVisible" width="60%">
      <el-form :model="form" :label-position="left" label-width="100px">
        <el-row :gutter="20">
          <!-- 第一列 -->
          <el-col :span="12">
            <el-form-item label="型号" class="inline-form-item">
              <el-input v-model="form.model"></el-input>
            </el-form-item>
            <el-form-item label="品牌" class="inline-form-item">
              <el-input v-model="form.brand"></el-input>
            </el-form-item>
            <el-form-item label="租车价格/天" class="inline-form-item">
              <el-input v-model="form.price"></el-input>
            </el-form-item>
            <el-form-item label="车型" class="inline-form-item">
              <el-input v-model="form.vehicleModel"></el-input>
            </el-form-item>
            <el-form-item label="续航/km" class="inline-form-item">
              <el-input v-model="form.endurance"></el-input>
            </el-form-item>
            <el-form-item label="地址" class="inline-form-item">
              <el-input v-model="form.address"></el-input>
            </el-form-item>
            <el-form-item label="经度" class="inline-form-item">
              <el-input v-model="form.lng"></el-input>
            </el-form-item>
            <el-form-item label="纬度" class="inline-form-item">
              <el-input v-model="form.lat"></el-input>
            </el-form-item>
          </el-col>

          <!-- 第二列 -->
          <el-col :span="12">
            <el-form-item label="车辆状态" class="inline-form-item">
              <el-select clearable filterable v-model="form.status" placeholder="请选择">
                <el-option
                    v-for="item in statusList"
                    :key="item.status"
                    :label="item.name"
                    :value="item.status">
                </el-option>
              </el-select>
            </el-form-item>
            <el-form-item label="类型" class="inline-form-item">
              <el-select clearable filterable v-model="form.classify" placeholder="请选择">
                <el-option
                    v-for="item in classifyList"
                    :key="item.classify"
                    :label="item.classify"
                    :value="item.classify">
                </el-option>
              </el-select>
            </el-form-item>
            <el-form-item label="轮播图" class="inline-form-item">
              <el-upload
                  action="http://localhost:8082/uploadFile/upload"
                  list-type="picture-card"
                  :on-preview="handlePictureCardPreview"
                  :on-remove="handleRemove"
                  :on-success="onUpLoadSuccess"
                  :limit="4"
                  accept="image/png, image/jpeg"
                  :file-list="form.photosUrl">
                <i class="el-icon-plus"></i>
              </el-upload>
            </el-form-item>
            <el-form-item label="GLB文件" class="inline-form-item">
              <el-upload
                  action="http://localhost:8082/uploadFile/upload"
                  :limit="1"
                  accept=".glb"
                  :on-exceed="handleExceed"
                  :on-success="onUpLoadSuccessGlb"
                  :file-list="form.glbFileUrl">
                <el-button type="primary">点击上传</el-button>
              </el-upload>
            </el-form-item>
          </el-col>
        </el-row>
      </el-form>
      <span slot="footer" class="dialog-footer">
    <el-button @click="dialogVisible = false">取 消</el-button>
    <el-button type="primary" @click="handleSubmit">确 定</el-button>
  </span>
    </el-dialog>

    <!-- 查看轮播图弹窗 -->
    <el-dialog :visible.sync="photosVisible" width="30%">
      <el-carousel :interval="5000" arrow="always">
        <el-carousel-item v-for="(photo, index) in currentPhotos" :key="index">
          <img :src="photo" style="width: 100%; height: 100%;" />
        </el-carousel-item>
      </el-carousel>
    </el-dialog>

    <!-- 查看维护记录弹窗 -->
    <el-dialog :visible.sync="maintenanceVisible" width="30%">
<!--      <el-table :data="maintenanceList" border>-->
<!--        <el-table-column prop="time" label="时间" align="center">-->
<!--          <template slot-scope="scope">-->
<!--            <div>{{ scope.row.createTime|global_time_filter}}</div>-->
<!--          </template>-->
<!--        </el-table-column>-->
<!--        <el-table-column prop="remark" label="备注" align="center"></el-table-column>-->
<!--      </el-table>-->

      <!-- 表格 -->
      <el-table :data="maintenanceList" border style="width: 100%">
        <!-- 时间列 -->
        <el-table-column prop="time" label="时间" align="center" width="200">
          <template slot-scope="scope">
            <el-date-picker
                v-if="scope.row.isEditing"
                v-model="scope.row.mainTime"
                type="datetime"
                placeholder="选择日期时间"
                format="yyyy-MM-dd HH:mm:ss"
                value-format="timestamp"
                @keyup.enter.native="handleSubmitMaintenance(scope.$index)"
            />
            <div v-else>{{ scope.row.mainTime | global_time_filter }}</div>
          </template>
        </el-table-column>

        <!-- 备注列 -->
        <el-table-column prop="remark" label="备注" align="center">
          <template slot-scope="scope">
            <el-input
                v-if="scope.row.isEditing"
                v-model="scope.row.remark"
                placeholder="请输入备注"
                @keyup.enter.native="handleSubmitMaintenance(scope.$index)"
            />
            <span v-else>{{ scope.row.remark }}</span>
          </template>
        </el-table-column>
      </el-table>


      <el-button type="primary" @click="handleAddMaintenance" style="margin-top: 20px;">新增维护记录</el-button>
    </el-dialog>

    <!-- 3D 模型弹窗 -->
    <el-dialog :visible.sync="glbVisible" width="50%" title="3D 模型">
      <GLBViewer v-if="glbVisible" :glbUrl="currentGLBUrl" />
    </el-dialog>
  </div>
</template>

<script>
import rentalcar from '../../api/rental/rentalcar'
import maintenanceRecord from '../../api/rental/maintenanceRecord'
import GLBViewer from './GLBViewer.vue';
export default {
  data() {
    return {
      statusList:[{"status":"0","name":"待租赁"},{"status":"1","name":"已租赁"}],
      classifyList:[{"classify":"新能源一"},{"classify":"新能源二"},{"classify":"新能源三"}],
      item:{},
      glbVisible: false,
      left: "left",
      currentGLBUrl: '',
      vehicleList: [],
      dialogVisible: false,
      dialogTitle: '添加车辆',
      form: {
        model: '',
        brand: '',
        classify: '',
        price: '',
        vehicleModel: '',
        endurance: '',
        photosUrl: [],
        glbFileUrl: [],
        status: ''
      },
      selectedItem:{},
      photosVisible: false,
      currentPhotos: [],
      maintenanceVisible: false,
      maintenanceList: []
    };
  },
  components: {
    GLBViewer,
  },
  created() {
  this.findList()
 },
  methods: {
    onUpLoadSuccess: function(result){
      const _this=this;
      if (result && result.code === 200) {
        // 将新图片地址添加到 form.photosUrl
        this.form.photosUrl.push({ url: result.data.imgUrl });
      }else{
        _this.$message.error(result.message);
      }
    },
    onUpLoadSuccessGlb: function(result){
      const _this=this;
      if (result && result.code === 200) {
        // 将新图片地址添加到 form.photosUrl
        this.form.glbFileUrl.push({ url: result.data.imgUrl });
      }else{
        _this.$message.error(result.message);
      }
    },
    findList(){
      const _this=this;
      rentalcar.findList().then(function (result) {
        if (result && result.code === 200) {
          _this.vehicleList=result.data.carList
        }else {
          _this.$message.error(result.message)
        }
      }, function (err) {
        _this.$message.error(err.toString())
      })
    },
    handleViewGLB(url) {
      this.currentGLBUrl = url;
      this.glbVisible = true;
    },
    handleAdd() {
      this.dialogTitle = '添加车辆';
      this.form = {
        model: '',
        brand: '',
        price: '',
        vehicleModel: '',
        endurance: '',
        photosUrl: [],
        glbFileUrl: [],
        status: ''
      };
      this.dialogVisible = true;
    },
    handleEdit(row) {
      this.dialogTitle = '编辑车辆';
      this.form = { ...row };
      this.form.price=this.form.price/100
      this.form.photosUrl=row.photosUrl==''||row.photosUrl==null?[]:this.form.photosUrl.split(",").map(url => ({
        url: url.trim() // 去除可能的空格
      }));
      this.form.glbFileUrl=row.glbFileUrl==''||row.glbFileUrl==null?[]:this.form.glbFileUrl.split(",").map(url => ({
        url: url.trim() // 去除可能的空格
      }));
      console.log("==this.form=="+JSON.stringify(this.form))
      this.dialogVisible = true;
    },
    handleDelete(row) {
      const _this=this
      _this.$confirm('确定删除该车辆吗？', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        rentalcar.remove({"id":row.id}).then(function (result) {
          if (result && result.code === 200) {
            _this.findList()
            _this.$message({
              type: 'success',
              message: '删除成功!'
            });
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
    handleViewPhotos(row) {
      this.currentPhotos = row.photosUrl.split(",");
      this.photosVisible = true;
    },
    handleViewMaintenance(row) {
      const _this=this;
      _this.selectedItem=row
      maintenanceRecord.findList({"carId":row.id}).then(function (result) {
        if (result && result.code === 200) {
          _this.maintenanceList=result.data
          _this.maintenanceVisible = true;
        }else {
          _this.$message.error(result.message)
        }
      }, function (err) {
        _this.$message.error(err.toString())
      })
    },
    handleAddMaintenance() {
      // 在最前面插入一个空白行，并标记为编辑状态
      this.maintenanceList.unshift({
        createTime: null, // 初始时间为空
        remark: '', // 初始备注为空
        isEditing: true, // 标记为编辑状态
      });
    },
    // 提交数据
    handleSubmitMaintenance(index) {
      const _this=this;
      const row = _this.maintenanceList[index];
      // 检查数据是否为空
      if (!row.mainTime || !row.remark) {
        _this.$message.warning('请填写完整信息');
        return;
      }
      const param={
        "carId":_this.selectedItem.id,
        "mainTime":row.mainTime,
        "remark":row.remark,
      }
      maintenanceRecord.add(param).then(function (result) {
        if (result && result.code === 200) {
         _this.handleViewMaintenance(_this.selectedItem)
          // 提交后取消编辑状态
          row.isEditing = false;

          _this.$message.success('提交成功');
          console.log('提交的数据:', row);
        }else {
          _this.$message.error(result.message)
        }
      }, function (err) {
        _this.$message.error(err.toString())
      })

    },
    handleSubmit() {
      const _this=this;
      const formAdd= { ..._this.form };
       formAdd.photosUrl=_this.form.photosUrl.map(file => file.url).join(',');
       formAdd.glbFileUrl=_this.form.glbFileUrl.map(file => file.url).join(',');
       formAdd.price=_this.form.price*100;
      console.log("==formAdd=="+JSON.stringify(formAdd))
      console.log("==_this.form=="+JSON.stringify(_this.form))
      if (_this.dialogTitle === '添加车辆') {
        rentalcar.add(formAdd).then(function (result) {
          if (result && result.code === 200) {
            _this.findList()
            _this.$message({
              type: 'success',
              message: '添加成功!'
            });
          }else {
            _this.$message.error(result.message)
          }
        }, function (err) {
          _this.$message.error(err.toString())
        })
      } else {
        rentalcar.update(formAdd).then(function (result) {
          if (result && result.code === 200) {
            _this.findList()
            _this.$message({
              type: 'success',
              message: '修改成功!'
            });
          }else {
            _this.$message.error(result.message)
          }
        }, function (err) {
          _this.$message.error(err.toString())
        })
      }
      _this.dialogVisible = false;
    },


    handlePictureCardPreview(file) {
      this.currentPhotos = [file.url];
      this.photosVisible = true;
    },
    handleRemove(file, fileList) {
      this.form.photosUrl = fileList;
    },
    handleExceed(files, fileList) {
      this.$message.warning('只能上传一个GLB文件');
    }
  }
};
</script>

<style scoped>
.vehicle-list-container {
  text-align: center;
  padding: 20px;
}
/* 自定义样式，使 label 和输入框在同一行 */
.inline-form-item {
  display: flex;
  align-items: center;
}

.inline-form-item ::v-deep .el-form-item__label {
  flex: 0 0 auto; /* label 不伸缩 */
  text-align: left; /* label 左对齐 */
}

.inline-form-item ::v-deep .el-form-item__content {
  flex: 1; /* 输入框占据剩余空间 */
}
.el-form-item__content{
  width: 100% !important;
}
</style>