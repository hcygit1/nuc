package com.personal.weappadmin.utils;

import java.io.File;
import java.io.FileOutputStream;

public class FileUtil {
	
	public static final String AttachmentServerPath = "http://localhost:8081/fileServer";
	// 文件上传工具类服务方法
	public static void uploadFile(byte[] file, String filePath, String fileName) throws Exception {
		File targetFile = new File(filePath);
		if (!targetFile.exists()) {
			targetFile.mkdirs();
		}
		FileOutputStream out = new FileOutputStream(filePath + fileName);
		out.write(file);
		out.flush();
		out.close();
	}
}