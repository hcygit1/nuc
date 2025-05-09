package com.personal.weappadmin.bussiness.service;

import com.personal.weappadmin.core.service.CrudService;
import com.personal.weappadmin.bussiness.model.*;
import com.personal.weappadmin.bussiness.repo.*;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import org.springframework.stereotype.Service;


@Service
public class EvaluateFeekBackService extends CrudService<EvaluateFeekBackRepo, EvaluateFeekBack,Long> {

    public Object findList(){
        return curdRepo.findList();
    }
    public Object findMyList(Long userId){
        return curdRepo.findMyList(userId);
    }
}

