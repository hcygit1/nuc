package com.personal.weappadmin.bussiness.service;

import com.personal.weappadmin.bussiness.model.RentalCars;
import com.personal.weappadmin.bussiness.model.RentalOrders;
import com.personal.weappadmin.bussiness.repo.RentalCarsRepo;
import com.personal.weappadmin.bussiness.repo.RentalOrdersRepo;
import com.personal.weappadmin.core.service.CrudService;
import com.personal.weappadmin.pdfUtil.PDFToImageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;

@Service
public class RentalOrdersService extends CrudService<RentalOrdersRepo,RentalOrders,Long> {
    @Value("${static.location}")
    private  String path;
    @Value("${static.domain}")
    private  String domain;
    @Autowired
    RentalCarsRepo rentalCarsRepo;
    public Object findList(Long userId){
        return curdRepo.findList(userId);
    }
  public Object findListOrders(String mobile){
        return curdRepo.findListOrders(mobile);
    }

  public Object findListByCarId(Long carId){
        return curdRepo.findListByCarId(carId);
    }

    public Object getRentalOrderPdfImages(Long id) throws IOException {
        RentalOrders rentalOrders=curdRepo.get(id);
          return   PDFToImageConverter.convertPDFToImagesFromUrl(rentalOrders.getPdfUrl(),path,domain);
    }


    @Transactional
    public void insert(RentalOrders model,Long userId){
        model.setPayStatus("1");
        model.setUserId(userId);
        curdRepo.add(model);
        RentalCars rentalCars=rentalCarsRepo.get(model.getCarId());
        rentalCars.setStatus("1");
        rentalCarsRepo.update(rentalCars);
    }
}

