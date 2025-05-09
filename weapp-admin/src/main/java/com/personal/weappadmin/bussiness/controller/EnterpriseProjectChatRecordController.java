package com.personal.weappadmin.bussiness.controller;

import com.personal.weappadmin.bussiness.model.EnterpriseProjectChatRecord;
import com.personal.weappadmin.bussiness.service.EnterpriseProjectChatRecordService;
import com.personal.weappadmin.core.controller.BaseAuthController;
import com.personal.weappadmin.exception.OV;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 *  前端控制器
 *
 * @author author
 * @since 2025-02-12 23:46
 */
@RestController
@RequestMapping("/enterpriseProjectChatRecord")
public class EnterpriseProjectChatRecordController extends BaseAuthController {
    @Autowired
    EnterpriseProjectChatRecordService service;

    /*客户查询历史聊天记录*/
    @GetMapping("/findList")
    public Object findList(){
        return new OV().success(service.findList(getLoginUser().getId()));
    }

    /*客户查询未读消息*/
    @GetMapping("/findMyUnReadMessage")
    public Object findMyUnReadMessage(){
        return new OV().success( service.findMyUnReadMessage(getLoginUser().getId()));
    }

    /*客户回复*/
    @PostMapping("/add")
    public Object add(@RequestBody EnterpriseProjectChatRecord model){
        if("common".equals(model.getRoles())){
            model.setUserId(getLoginUser().getId());
            model.setToUserId(0L);
        }
        service.add(model);
        return new OV().success();
    }

    /*客服查询聊天列表及历史消息*/
    @GetMapping("/findWechatRcordAndHis")
    public Object findWechatRcordAndHis(){
        return new OV().success( service.findWechatRcordAndHis());
    }


    /*客服查询未读消息*/
    @GetMapping("/findSysUnReadMessage")
    public Object findSysUnReadMessage(@RequestParam("userId")Long userId){
        return new OV().success( service.findSysUnReadMessage(userId));
    }
}
