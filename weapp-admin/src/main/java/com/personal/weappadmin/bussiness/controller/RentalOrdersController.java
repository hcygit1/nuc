package com.personal.weappadmin.bussiness.controller;

import com.personal.weappadmin.bussiness.model.RentalOrders;
import com.personal.weappadmin.bussiness.service.RentalOrdersService;
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
@RequestMapping("/rentalOrders" )
public class RentalOrdersController extends BaseAuthController {

        @Autowired
        private RentalOrdersService rentalOrdersService;


        /**
         * 保存
         *
         * @param model
         * @return
         */
        @RequestMapping("/add")
        public Object add(@RequestBody RentalOrders model) {
                rentalOrdersService.insert(model, getLoginUser().getId());
                return new OV().success();
        }

        /**
         * 修改
         *
         * @param model
         * @return
         */
        @PostMapping(value = "/update")
        public Object update(@RequestBody RentalOrders model) {
                rentalOrdersService.update(model);
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
                return new OV().success(rentalOrdersService.get(id));
        }

        /**
         * 删除
         *
         * @param id
         * @return
         */
        @RequestMapping("/remove")
        public Object remove(@RequestParam("id") Long id) {
                rentalOrdersService.remove(id);
                return new OV().success();
        }

        /**
         * 列表查询（分页）
         *
         * @return
         */
        @RequestMapping("/findList")
        public Object findList() {
                return new OV().success(rentalOrdersService.findList(getLoginUser().getId()));
        }


        /**
         * 列表查询（分页）
         *
         * @return
         */
        @RequestMapping("/findListOrders")
        public Object findListOrders(@RequestParam("mobile") String mobile) {
                return new OV().success(rentalOrdersService.findListOrders(mobile));
        }



        @RequestMapping("/getRentalOrderPdfImages")
        public Object getRentalOrderPdfImages(@RequestParam("id")Long id) throws IOException {
                return new OV().success(rentalOrdersService.getRentalOrderPdfImages(id));
        }
        @RequestMapping("/findListByCarId")
        public Object findListByCarId(@RequestParam("carId")Long carId) {
                return new OV().success(rentalOrdersService.findListByCarId(carId));
        }



}
