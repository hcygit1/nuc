        package com.personal.weappadmin.bussiness.controller;

        import com.personal.weappadmin.bussiness.model.MaintenanceRecord;
        import com.personal.weappadmin.bussiness.service.MaintenanceRecordService;
        import com.personal.weappadmin.core.controller.BaseAuthController;
        import com.personal.weappadmin.exception.OV;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.web.bind.annotation.*;
        /**
        *  前端控制器
        * @author author
        * @since 2025-02-27
        */
        @RestController
        @RequestMapping("/maintenanceRecord" )
        public class MaintenanceRecordController extends BaseAuthController{

        @Autowired
        private MaintenanceRecordService maintenanceRecordService;

        /**
        * 保存
        *
        * @param model
        * @return
        */
        @RequestMapping("/add")
        public Object add(@RequestBody MaintenanceRecord model) {
        maintenanceRecordService.add(model);
        return new OV().success();
        }

        /**
        * 修改
        *
        * @param model
        * @return
        */
        @PostMapping(value = "/update")
        public Object update(@RequestBody MaintenanceRecord model) {
        maintenanceRecordService.update(model);
        return new OV().success();
        }

        /**
        * 查询详情
        *
        * @param id
        * @return
        */
        @RequestMapping("/get")
        public Object get( @RequestParam("id") Long id) {
        return new OV().success(maintenanceRecordService.get(id));
        }

        /**
        * 删除
        *
        * @param id
        * @return
        */
        @RequestMapping("/remove")
        public Object remove(@RequestParam("id") Long id) {
        maintenanceRecordService.remove(id);
        return new OV().success();
        }

        /**
        * 列表查询（分页）
        *
        * @return
        */
        @RequestMapping("/findList")
        public Object findList(@RequestParam("carId")Long carId) {
        return new OV().success(maintenanceRecordService.findList(carId));
        }

        }
