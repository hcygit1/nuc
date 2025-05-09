        package com.personal.weappadmin.bussiness.controller;

        import com.personal.weappadmin.bussiness.model.TraceLngLat;
        import com.personal.weappadmin.bussiness.service.TraceLngLatService;
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
        @RequestMapping("/traceLngLat" )
        public class TraceLngLatController extends BaseAuthController{

        @Autowired
        private TraceLngLatService traceLngLatService;

        /**
        * 保存
        *
        * @param model
        * @return
        */
        @RequestMapping("/add")
        public Object add(@RequestBody TraceLngLat model) {
        traceLngLatService.add(model);
        return new OV().success();
        }

        /**
        * 修改
        *
        * @param model
        * @return
        */
        @PostMapping(value = "/update")
        public Object update(@RequestBody TraceLngLat model) {
        traceLngLatService.update(model);
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
        return new OV().success(traceLngLatService.get(id));
        }

        /**
        * 删除
        *
        * @param id
        * @return
        */
        @RequestMapping("/remove")
        public Object remove(@RequestParam("id") Long id) {
        traceLngLatService.remove(id);
        return new OV().success();
        }

        /**
        * 列表查询（分页）
        *
        * @return
        */
        @RequestMapping("/findList")
        public Object findList(@RequestParam("orderId") Long orderId) {
        return new OV().success(traceLngLatService.findList(orderId));
        }

        }
