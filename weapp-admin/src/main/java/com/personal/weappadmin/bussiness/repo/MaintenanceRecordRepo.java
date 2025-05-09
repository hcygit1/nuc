package com.personal.weappadmin.bussiness.repo;

import com.personal.weappadmin.config.mybatisConfig.MyBatisRepository;
import com.personal.weappadmin.core.repo.CrudRepo;
import com.personal.weappadmin.bussiness.model.MaintenanceRecord;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import org.apache.ibatis.annotations.Param;

import java.util.List;
/**
* <p>
    *  Mapper 接口
    * </p>
*
* @author author
* @since 2025-02-27
*/
@MyBatisRepository
public interface MaintenanceRecordRepo extends CrudRepo<MaintenanceRecord,Long> {
List<MaintenanceRecord> findList(@Param("carId")Long carId);
}
