package com.personal.weappadmin.bussiness.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.personal.weappadmin.core.service.CrudService;
import com.personal.weappadmin.bussiness.model.TraceLngLat;
import com.personal.weappadmin.bussiness.repo.TraceLngLatRepo;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.personal.weappadmin.exception.MyException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class TraceLngLatService extends CrudService<TraceLngLatRepo,TraceLngLat,Long> {

    public Object findList(Long orderId){
        JSONObject result=new JSONObject();
        List<double[]> list=curdRepo.findList(orderId);
        if(list.size()==0){
            throw new MyException("暂无通行轨迹");
        }
        result.put("list",list);
       return result;
    }
}

