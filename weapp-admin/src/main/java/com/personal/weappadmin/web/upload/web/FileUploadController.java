package com.personal.weappadmin.web.upload.web;


import com.personal.weappadmin.core.controller.BaseAuthController;
import com.personal.weappadmin.exception.OV;
import com.personal.weappadmin.web.upload.service.FileUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/uploadFile")
public class FileUploadController extends BaseAuthController {

    @Autowired
    FileUploadService fileUploadService;

    /**
     * @author: author
     * @Date: 2023/12/26
     * @Description: 上传图片
     * @Param: [file]
     * @return: java.lang.Object
     */
    @PostMapping(value = "/upload")
    public Object upload(@RequestParam MultipartFile file) {
        return new OV().success(fileUploadService.upload(file));
    }

    @PostMapping(value = "/signpdf")
    public Object signpdf(@RequestParam MultipartFile file) throws IOException {
        return new OV().success(fileUploadService.signpdf(file));
    }
}
