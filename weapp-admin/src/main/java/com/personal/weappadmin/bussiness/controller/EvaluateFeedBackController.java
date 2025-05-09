package com.personal.weappadmin.bussiness.controller;

import com.personal.weappadmin.core.controller.BaseAuthController;
import com.personal.weappadmin.exception.OV;
import com.personal.weappadmin.bussiness.model.EvaluateFeekBack;
import com.personal.weappadmin.bussiness.service.EvaluateFeekBackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RequestMapping("/evaluateFeekBack")
@RestController
public class EvaluateFeedBackController extends BaseAuthController {

    @Autowired
    EvaluateFeekBackService evaluateFeekBackService;


    @GetMapping("/findList")
    public Object findList(){
        return new OV().success(evaluateFeekBackService.findList());
    }

    @GetMapping("/findMyList")
    public Object findMyList(){
        return new OV().success(evaluateFeekBackService.findMyList(getLoginUser().getId()));
    }
    @GetMapping("/remove")
    public Object remove(@RequestParam("id") Long id){
        evaluateFeekBackService.remove(id);
        return new OV().success();
    }

    @PostMapping("/add")
    public Object add(@RequestBody EvaluateFeekBack evaluateFeekBack){
        evaluateFeekBack.setUserId(getLoginUser().getId());
        evaluateFeekBackService.add(evaluateFeekBack);
        return new OV().success();
    }

    @PostMapping("/update")
    public Object update(@RequestBody EvaluateFeekBack evaluateFeekBack){
        evaluateFeekBackService.update(evaluateFeekBack);
        return new OV().success();
    }


}
