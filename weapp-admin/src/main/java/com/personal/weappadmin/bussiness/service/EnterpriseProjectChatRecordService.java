package com.personal.weappadmin.bussiness.service;

import com.personal.weappadmin.bussiness.model.EnterpriseProjectChatRecord;
import com.personal.weappadmin.bussiness.repo.EnterpriseProjectChatRecordRepo;
import com.personal.weappadmin.core.service.CrudService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 *  服务类接口
 *
 * @author author
 * @since 2025-02-12 23:46
 */
@Service
public class EnterpriseProjectChatRecordService extends CrudService<EnterpriseProjectChatRecordRepo, EnterpriseProjectChatRecord,Long> {


    public Object findList(Long userId){
        curdRepo.setReadStatusByParam(userId);
        return curdRepo.findList(userId);
    }


  public Object findMyUnReadMessage(Long userId){
      List<Map<String,Object>> unreadMessageList=curdRepo.findMyUnReadMessage(userId);
      for (Map<String,Object> map:unreadMessageList) {
          curdRepo.setReadStatus(Long.parseLong(String.valueOf(map.get("id"))));
      }
      return unreadMessageList;
    }

  public Object findSysUnReadMessage(Long userId){
      List<Map<String,Object>> unreadMessageList=curdRepo.findSysUnReadMessage(userId);
      for (Map<String,Object> map:unreadMessageList) {
          curdRepo.setReadStatus(Long.parseLong(String.valueOf(map.get("id"))));
      }
      return unreadMessageList;
    }

  public Object findWechatRcordAndHis(){
        //先查询所有聊过天的用户
      List<Map<String,Object>> findAllRecordUsers=curdRepo.findAllRecordUsers();
        //轮训历史消息
      for (int i = 0; i < findAllRecordUsers.size(); i++) {
          //查询每个人的聊天记录
          findAllRecordUsers.get(i).put("messages",curdRepo.findAllRecordByUserId(Long.parseLong(String.valueOf(findAllRecordUsers.get(i).get("userId")))));
            //更新和每个人的聊天记录为已读
          curdRepo.setReadStatusByUserId(Long.parseLong(String.valueOf(findAllRecordUsers.get(i).get("userId"))));
      }
      return findAllRecordUsers;
    }
}
