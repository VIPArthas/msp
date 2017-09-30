package com.wh.util;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
/**
 * 
 * @Title:			UploadUtils.java
 */
public class UploadUtils {
	public static final String UPLOAD_FOLDER = "/pages/upload";
	public static final String[] TYPE_IMAGE = { "jpg", "gif", "jpeg", "png", "bmp" };
	public static final String[] TYPE_DOC = { "txt", "doc", "xls", "xml", "pdf", "htm", "html" };
	public static final String[] TYPE_ZIP = { "zip", "rar", "jar", "gzip", "war" };
	public static final String[] TYPE_DENIED = { "php", "php3", "php5", "phtml", "asp", "aspx", "ascx", "jsp", "jsf", "cfm", "cfc", "pl", "bat", "exe", "mdll", "reg", "cgi" };

	@SuppressWarnings({"rawtypes", "unused"})
    public static FileProperty upload(String fileName,String path) throws Exception, IOException {
		FileProperty property = new FileProperty();
		int dotPosition = fileName.lastIndexOf(".");
		String suffix = fileName.substring(dotPosition + 1, fileName.length());
		Map map = getFileType(fileName);
		String fileType = (String) map.get("fileType");
		String prefixName = (String) map.get("prefixName");
		property.setType(fileType);
		String suffixName = getRandomName();
		String newFileName = path+UPLOAD_FOLDER + File.separator + prefixName + suffixName + "." + suffix;
		String fileNames=prefixName + suffixName + "." + suffix;
		property.setFileNames(fileNames);
		property.setName(newFileName);
		FileInputStream input = new FileInputStream(fileName);
		int size = input.available();
		property.setSize((float) size / 1024);
		property.setUploadTime(new Date());
		byte[] data = new byte[size];
		int i = -1;
		FileOutputStream output = new FileOutputStream(newFileName);
		while ((i = input.read(data)) != -1) {
			output.write(data);
		}
		output.flush();
		output.close();
		input.close();
		return property;
	}

	@SuppressWarnings({"rawtypes", "unchecked"})
    public static Map getFileType(String fileName) throws Exception {
		Map map = new HashMap();
		int dotPosition = fileName.lastIndexOf(".");
		String fileType = "";
		String prefixName = "";
		String suffix = fileName.substring(dotPosition + 1, fileName.length());
		if (Arrays.toString(TYPE_DENIED).indexOf(suffix) != -1) {
			throw new Exception("The file you upload is denied!");
		} else if (Arrays.toString(TYPE_IMAGE).indexOf(suffix) != -1) {
			fileType = "图片文件";
			prefixName = "Image";
		} else if (Arrays.toString(TYPE_DOC).indexOf(suffix) != -1) {
			fileType = "文档文件";
			prefixName = "File";
		} else if (Arrays.toString(TYPE_ZIP).indexOf(suffix) != -1) {
			fileType = "压缩文件";
			prefixName = "Zip";
		} else {
			fileType = "其他文件";
			prefixName = "Other";
		}
        map.put("fileType", fileType);
		map.put("prefixName", prefixName);
		return map;
	}

	public static String getRandomName() {
		String fileName = "";
		Calendar calendar = GregorianCalendar.getInstance();
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH);
		int day = calendar.get(Calendar.DATE);
		String suffixName = String.valueOf(year) + String.valueOf(month) + String.valueOf(day);
		Random rand = new Random();
		int num = rand.nextInt(100);
		fileName = suffixName + "_" + String.valueOf(num);
		return fileName;
	}

	public static class FileProperty {
		private String name;
		private String fileNames;
		private String type;
		private String contentType;
		private float size;
		private Date uploadTime;

		public FileProperty() {
			super();
		}

		public FileProperty(String name,String fileNames, String type, String contentType, float size, Date uploadTime) {
			super();
			this.name = name;
			this.fileNames=fileNames;
			this.type = type;
			this.contentType = contentType;
			this.size = size;
			this.uploadTime = uploadTime;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public float getSize() {
			return size;
		}

		public void setSize(float size) {
			this.size = size;
		}

		public String getType() {
			return type;
		}

		public void setType(String type) {
			this.type = type;
		}

		public String getContentType() {
			return contentType;
		}

		public void setContentType(String contentType) {
			this.contentType = contentType;
		}

		public Date getUploadTime() {
			return uploadTime;
		}

		public void setUploadTime(Date uploadTime) {
			this.uploadTime = uploadTime;
		}

		public void setFileNames(String fileNames) {
			this.fileNames = fileNames;
		}

		public String getFileNames() {
			return fileNames;
		}
		
	}
	
	@SuppressWarnings("unused")
    public static void main(String[] args) {
        try {  
            FileProperty property = UploadUtils.upload("F://1.png","");  
        } catch (IOException e) {  
            e.printStackTrace();
        } catch (Exception e) {  
            e.printStackTrace();
        }  
    }  
}
