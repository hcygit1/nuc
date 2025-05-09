package com.personal.weappadmin.pdfUtil;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import org.apache.pdfbox.util.Matrix;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.UUID;

@Service
public class PdfUtil {
    @Value("${static.location}")
    private String path;
    @Value("${static.domain}")
    private String domain;
    private  final  String heTongName ="租车合同.pdf"; // PDF文件路径
    //将图片覆盖至pdf
//    public static void main(String[] args) {
//        System.out.println(copyUrl("D:\\mypic\\uploadPath\\upload1\\123.pdf"));
//    }
//

    public  String signpdf(String imagePath) throws IOException {
        String newPth=copyUrl(path+heTongName);
        System.out.println("开始图章插入");
        operate(path+newPth, imagePath);
        return domain+newPth;
    }


    public  String copyUrl(String originPath){
        String names=UUID.randomUUID().toString()+".pdf";
        String newUrl=path+names;
        File originalPdf = new File(originPath);
        File copiedPdf = new File(newUrl);

        try {
            Files.copy(originalPdf.toPath(), copiedPdf.toPath(), StandardCopyOption.REPLACE_EXISTING);
            System.out.println("PDF copied successfully.");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return names;
    }

    public  void operate(String pdfPath,String image) throws IOException {
        File file = new File(pdfPath);
        PDDocument doc = PDDocument.load(file);
        String keyWords = "签章"; // 修改成你需要插入的位置
        PDImageXObject pdImage = PDImageXObject.createFromFile(image, doc);
        PdfBoxKeyWordPosition pdf = new PdfBoxKeyWordPosition(keyWords, pdfPath);
        PDPageContentStream contentStream = null;
        List<float[]> list = pdf.getCoordinate();
        // 多页pdf的处理
        int i=0;
        for (float[] fs : list) {
            i=i+1;
            PDPage page = doc.getPage((int)fs[2]-1);
            float x = fs[0];
            float y = fs[1];

            contentStream = new PDPageContentStream(doc, page, true, true);

            contentStream.transform(
                    new Matrix(0, 1, -1, 0, pdImage.getHeight(), 0) // 旋转 90 度
            );
            //contentStream.saveGraphicsState();
            contentStream.transform(
                    new Matrix(0, 1, -1, 0, pdImage.getHeight(), 0) // 旋转 90 度
            );
            contentStream.saveGraphicsState();
            if(i==list.size()){
                contentStream.drawImage(pdImage, 150, 600,100,100);
            }

            contentStream.close();
        }
        doc.save(pdfPath);
        doc.close();
    }

}
