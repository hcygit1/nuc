package com.personal.weappadmin.bussiness.repo;

import com.personal.weappadmin.bussiness.model.EnterpriseProjectChatRecord;
import com.personal.weappadmin.config.mybatisConfig.MyBatisRepository;
import com.personal.weappadmin.core.repo.CrudRepo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
*  Mapper
*
* @author author
* @since 2025-02-12 23:46
*/
@MyBatisRepository
public interface EnterpriseProjectChatRecordRepo extends CrudRepo<EnterpriseProjectChatRecord,Long> {
    List<EnterpriseProjectChatRecord> findList(@Param("userId") Long userId);
    void setReadStatusByParam(@Param("userId") Long userId);
    void setReadStatus(@Param("id") Long id);
    void setReadStatusByUserId(@Param("userId") Long userId);
    List<Map<String,Object>> findSysUnReadMessage(@Param("userId") Long userId);
    List<Map<String,Object>> findMyUnReadMessage(@Param("userId") Long userId);
    List<Map<String,Object>> findAllRecordUsers();
    List<Map<String,Object>> findAllRecordByUserId(@Param("userId") Long userId);
}
