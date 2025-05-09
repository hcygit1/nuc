package com.personal.weappadmin.bussiness.service;

import com.alibaba.fastjson.JSONObject;
import com.personal.weappadmin.bussiness.model.RentalCars;
import com.personal.weappadmin.bussiness.repo.RentalCarsRepo;
import com.personal.weappadmin.core.service.CrudService;
import com.personal.weappadmin.pdfUtil.PDFToImageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class RentalCarsService extends CrudService<RentalCarsRepo, RentalCars,Long> {
    @Value("${static.location}")
    private  String path;
    @Value("${static.domain}")
    private  String domain;


    public Object findList(){
        JSONObject result = new JSONObject();
        result.put("carList",curdRepo.findList());
        result.put("leftArray",curdRepo.leftArray());
        return result;
    }

    public Object getRightData(String classify,String minPrice,String maxPrice,String minEndurance,String maxEndurance,String vehicleModel){
        return curdRepo.getRightData(classify,minPrice,maxPrice,minEndurance,maxEndurance,vehicleModel);
    }
    public Object getvehicleModelArray(){
        return curdRepo.getvehicleModelArray();
    }
    public Object getPdf() throws IOException {
      return   PDFToImageConverter.convertPDFToImages(path+"租车合同.pdf",path,domain);
    }


    public Object getpoints(){
        return curdRepo.getpoints();
    }
}

