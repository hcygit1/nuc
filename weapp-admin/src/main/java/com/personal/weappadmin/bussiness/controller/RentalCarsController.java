package com.personal.weappadmin.bussiness.controller;

import com.personal.weappadmin.bussiness.model.RentalCars;
import com.personal.weappadmin.bussiness.service.RentalCarsService;
import com.personal.weappadmin.core.controller.BaseAuthController;
import com.personal.weappadmin.exception.OV;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

/**
 *  前端控制器
 * @author author
 * @since 2025-02-19
 */
@RestController
@RequestMapping("/rentalCars" )
public class RentalCarsController extends BaseAuthController {

        @Autowired
        private RentalCarsService rentalCarsService;

        /**
         * 保存
         *
         * @param model
         * @return
         */
        @RequestMapping("/add")
        public Object add(@RequestBody RentalCars model) {
                rentalCarsService.add(model);
                return new OV().success();
        }

        /**
         * 修改
         *
         * @param model
         * @return
         */
        @PostMapping(value = "/update")
        public Object update(@RequestBody RentalCars model) {
                rentalCarsService.update(model);
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
                return new OV().success(rentalCarsService.get(id));
        }

        /**
         * 删除
         *
         * @param id
         * @return
         */
        @RequestMapping("/remove")
        public Object remove(@RequestParam("id") Long id) {
                rentalCarsService.remove(id);
                return new OV().success();
        }


        @RequestMapping("/findList")
        public Object findList() {
                return new OV().success(rentalCarsService.findList());
        }


        @RequestMapping("/getRightData")
        public Object getRightData(@RequestParam("classify")String classify,@RequestParam("minPrice")String minPrice,@RequestParam("maxPrice")String maxPrice,
                                   @RequestParam("minEndurance")String minEndurance,@RequestParam("maxEndurance")String maxEndurance,@RequestParam("vehicleModel")String vehicleModel) {
                return new OV().success(rentalCarsService.getRightData(classify,minPrice,maxPrice,minEndurance,maxEndurance,vehicleModel));
        }

        @RequestMapping("/getvehicleModelArray")
        public Object getvehicleModelArray() {
                return new OV().success(rentalCarsService.getvehicleModelArray());
        }


        @RequestMapping("/getPdf")
        public Object getPdf() throws IOException {
                return new OV().success(rentalCarsService.getPdf());
        }

        @RequestMapping("/getpoints")
        public Object getpoints(){
                return new OV().success(rentalCarsService.getpoints());
        }

}
