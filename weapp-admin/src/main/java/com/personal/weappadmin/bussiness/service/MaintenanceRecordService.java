package com.personal.weappadmin.bussiness.service;

import com.personal.weappadmin.core.service.CrudService;
import com.personal.weappadmin.bussiness.model.MaintenanceRecord;
import com.personal.weappadmin.bussiness.repo.MaintenanceRecordRepo;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import org.springframework.stereotype.Service;

@Service
public class MaintenanceRecordService extends CrudService<MaintenanceRecordRepo,MaintenanceRecord,Long> {

public Object findList(Long carId){
return curdRepo.findList(carId);
}
}

