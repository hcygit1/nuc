package com.personal.weappadmin.pdfUtil;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.PDFRenderer;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class PDFToImageConverter {

    /*pdf每页拆分成图片进行预览*/


    public static  List<String> convertPDFToImages(String pdfPath, String outputDir,String domain) throws IOException {
        List<String> picsList = new ArrayList<String>();
        try (PDDocument document = PDDocument.load(new File(pdfPath))) {
            PDFRenderer renderer = new PDFRenderer(document);
            int numberOfPages = document.getNumberOfPages();
            for (int page = 0; page < numberOfPages; ++page) {
                BufferedImage img = renderer.renderImageWithDPI(page, 300); // 设置DPI为300
                String name=UUID.randomUUID().toString().replace("-","")+ ".png";
                String outputFilePath = outputDir +name;
                ImageIO.write(img, "PNG", new File(outputFilePath));
                System.out.println("Saved: " + outputFilePath);
                picsList.add(domain+name);
            }
        }
        return picsList;
    }

    public static  List<String> convertPDFToImagesFromUrl(String pdfUrl, String outputDir,String domain) throws IOException {
        List<String> picsList = new ArrayList<String>();
        try (InputStream inputStream = new URL(pdfUrl).openStream()) {
            PDDocument document = PDDocument.load(inputStream);
            PDFRenderer renderer = new PDFRenderer(document);
            int numberOfPages = document.getNumberOfPages();
            for (int page = 0; page < numberOfPages; ++page) {
                BufferedImage img = renderer.renderImageWithDPI(page, 300); // 设置DPI为300
                String name=UUID.randomUUID().toString().replace("-","")+ ".png";
                String outputFilePath = outputDir +name;
                ImageIO.write(img, "PNG", new File(outputFilePath));
                System.out.println("Saved: " + outputFilePath);
                picsList.add(domain+name);
            }
        }
        return picsList;
    }
 
//    public static void main(String[] args) {
//        String pdfPath = "D:\\mypic\\uploadPath\\upload1\\123.pdf";
//        String outputDir = "D:\\mypic\\uploadPath\\upload1\\";
//        try {
//            convertPDFToImages(pdfPath, outputDir);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
}