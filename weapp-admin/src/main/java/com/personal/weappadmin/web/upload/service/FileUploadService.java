package com.personal.weappadmin.web.upload.service;


import com.alibaba.fastjson.JSONObject;
import com.personal.weappadmin.pdfUtil.PdfUtil;
import com.personal.weappadmin.utils.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@Service
public class FileUploadService {
    @Value("${static.location}")
    private String path;
    @Value("${static.domain}")
    private String domain;
    @Autowired
    PdfUtil pdfUtil;
    public Object upload(MultipartFile file){
        JSONObject result=new JSONObject();
        // 上传文件路径
        String fileName =	file.getOriginalFilename();	 // 图片名字
        UUID uuid=UUID.randomUUID();
        String newfileName = uuid+fileName.substring(fileName.indexOf('.')); //文件重命名
        try {
            FileUtil.uploadFile(file.getBytes(),path, newfileName);//文件处理
        } catch (Exception e) {

        }
        result.put("imgUrl",domain+newfileName);
        return result;
    }
 public Object signpdf(MultipartFile file) throws IOException {
        JSONObject result=new JSONObject();
        // 上传文件路径
        String fileName =	file.getOriginalFilename();	 // 图片名字
        UUID uuid=UUID.randomUUID();
        String newfileName = uuid+fileName.substring(fileName.indexOf('.')); //文件重命名
        try {
            FileUtil.uploadFile(file.getBytes(),path, newfileName);//文件处理
        } catch (Exception e) {

        }
        String pdfUrl=pdfUtil.signpdf(path+newfileName);
        result.put("imgUrl",pdfUrl);
        return result;
    }

}
