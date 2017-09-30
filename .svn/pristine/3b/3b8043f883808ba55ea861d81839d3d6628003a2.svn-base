package com.wh.util.msp;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

public class CSVUtil {
	  
	  @SuppressWarnings("rawtypes")  
	  public static File createCSVFile(List exportData, LinkedHashMap map, String outPutPath,String fileName) {  
	    File csvFile = null;  
	    BufferedWriter csvFileOutputStream = null;  
	    try {  
	      File file = new File(outPutPath);  
	      if (!file.exists()) {  
	        file.mkdir();  
	      }  
	      //定义文件名格式并创建  
	      csvFile = File.createTempFile(fileName, ".csv", new File(outPutPath));  
	      System.out.println("csvFile：" + csvFile);  
	      // UTF-8使正确读取分隔符","   
	      csvFileOutputStream = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(csvFile), "UTF-8"), 1024);  
	      System.out.println("csvFileOutputStream：" + csvFileOutputStream);  
	      // 写入文件头部   
	      for (Iterator propertyIterator = map.entrySet().iterator(); propertyIterator.hasNext();) {  
	        java.util.Map.Entry propertyEntry = (java.util.Map.Entry) propertyIterator.next();  
	        csvFileOutputStream  
	          .write((String) propertyEntry.getValue() != null ? (String) propertyEntry.getValue() : "");  
	        if (propertyIterator.hasNext()) {  
	          csvFileOutputStream.write(",");  
	        }  
	      }  
	      csvFileOutputStream.newLine();  
	      // 写入文件内容   
	      for (Iterator iterator = exportData.iterator(); iterator.hasNext();) {  
	        Object row = (Object) iterator.next();  
	        for (Iterator propertyIterator = map.entrySet().iterator(); propertyIterator  
	          .hasNext();) {  
	          java.util.Map.Entry propertyEntry = (java.util.Map.Entry) propertyIterator  
	            .next();  
	          csvFileOutputStream.write((String) BeanUtils.getProperty(row,  
	            (String) propertyEntry.getKey()));  
	          if (propertyIterator.hasNext()) {  
	            csvFileOutputStream.write(",");  
	          }  
	        }  
	        if (iterator.hasNext()) {  
	          csvFileOutputStream.newLine();  
	        }  
	      }  
	      csvFileOutputStream.flush();  
	    } catch (Exception e) {  
	      e.printStackTrace();  
	    } finally {  
	      try {  
	        csvFileOutputStream.close();  
	      } catch (IOException e) {  
	        e.printStackTrace();  
	      }  
	    }  
	    return csvFile;  
	  }  
	    
	    
	  @SuppressWarnings("rawtypes")  
	  public static File createCSVFile2(List exportData, LinkedHashMap map, File file) {  
	    File csvFile = null;  
	    BufferedWriter csvFileOutputStream = null;  
	    try {  
	      //定义文件名格式并创建  
	      csvFile = file;  
	      System.out.println("csvFile：" + csvFile);  
	      // UTF-8使正确读取分隔符","   
	      csvFileOutputStream = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(csvFile), "UTF-8"), 1024);  
	      System.out.println("csvFileOutputStream：" + csvFileOutputStream);  
	      // 写入文件头部   
	      for (Iterator propertyIterator = map.entrySet().iterator(); propertyIterator.hasNext();) {  
	        java.util.Map.Entry propertyEntry = (java.util.Map.Entry) propertyIterator.next();  
	        csvFileOutputStream  
	          .write((String) propertyEntry.getValue() != null ? (String) propertyEntry.getValue() : "");  
	        if (propertyIterator.hasNext()) {  
	          csvFileOutputStream.write(",");  
	        }  
	      }  
	      csvFileOutputStream.newLine();  
	      // 写入文件内容   
	      for (Iterator iterator = exportData.iterator(); iterator.hasNext();) {  
	        Object row = (Object) iterator.next();  
	        for (Iterator propertyIterator = map.entrySet().iterator(); propertyIterator  
	          .hasNext();) {  
	          java.util.Map.Entry propertyEntry = (java.util.Map.Entry) propertyIterator  
	            .next();  
	          csvFileOutputStream.write((String) BeanUtils.getProperty(row,  
	            (String) propertyEntry.getKey()));  
	          if (propertyIterator.hasNext()) {  
	            csvFileOutputStream.write(",");  
	          }  
	        }  
	        if (iterator.hasNext()) {  
	          csvFileOutputStream.newLine();  
	        }  
	      }  
	      csvFileOutputStream.flush();  
	    } catch (Exception e) {  
	      e.printStackTrace();  
	    } finally {  
	      try {  
	        csvFileOutputStream.close();  
	      } catch (IOException e) {  
	        e.printStackTrace();  
	      }  
	    }  
	    return csvFile;  
	  }  
	    
	   
	  public static void exportFile(HttpServletResponse response, String csvFilePath, String fileName)  
	                                                  throws IOException {  
	    response.setContentType("application/csv;charset=UTF-8");  
	    response.setHeader("Content-Disposition",  
	      "attachment; filename=" + URLEncoder.encode(fileName, "UTF-8"));  
	   
	    InputStream in = null;  
	    try {  
	      in = new FileInputStream(csvFilePath);  
	      int len = 0;  
	      byte[] buffer = new byte[1024];  
	      response.setCharacterEncoding("UTF-8");  
	      OutputStream out = response.getOutputStream();  
	      while ((len = in.read(buffer)) > 0) {  
	        out.write(new byte[] { (byte) 0xEF, (byte) 0xBB, (byte) 0xBF });  
	        out.write(buffer, 0, len);  
	      }  
	    } catch (FileNotFoundException e) {  
	      System.out.println(e);  
	    } finally {  
	      if (in != null) {  
	        try {  
	          in.close();  
	        } catch (Exception e) {  
	          throw new RuntimeException(e);  
	        }  
	      }  
	    }  
	  }  
	   
	  public static void deleteFiles(String filePath) {  
	    File file = new File(filePath);  
	    if (file.exists()) {  
	      File[] files = file.listFiles();  
	      for (int i = 0; i < files.length; i++) {  
	        if (files[i].isFile()) {  
	          files[i].delete();  
	        }  
	      }  
	    }  
	  }  
	   
	  public static void deleteFile(String filePath, String fileName) {  
	    File file = new File(filePath);  
	    if (file.exists()) {  
	      File[] files = file.listFiles();  
	      for (int i = 0; i < files.length; i++) {  
	        if (files[i].isFile()) {  
	          if (files[i].getName().equals(fileName)) {  
	            files[i].delete();  
	            return;  
	          }  
	        }  
	      }  
	    }  
	  }  
	   
	  @SuppressWarnings({ "rawtypes", "unchecked" })  
	  public static void main(String[] args) {  
	    List exportData = new ArrayList<Map>();  
	    Map row1 = new LinkedHashMap<String, String>();  
	    row1.put("1", "万华教育");  
	    row1.put("2", "1");  
	    row1.put("3", "0");  
	    row1.put("4", ""); 
	    exportData.add(row1);  
	    row1 = new LinkedHashMap<String, String>();  
	    row1.put("1", "产品运营部");  
	    row1.put("2", "2");  
	    row1.put("3", "1"); 
	    row1.put("4", "");
	    exportData.add(row1);  
	    LinkedHashMap map = new LinkedHashMap();  
	    map.put("1", "部门名称");  
	    map.put("2", "部门ID");  
	    map.put("3", "父部门ID");  
	    map.put("4", "排序");  
	   
	    String path = "c:/export/";  
	    String fileName = "文件导出";  
	    File file = CSVUtil.createCSVFile(exportData, map, path, fileName);  
	    
	    String fileName2 = file.getName();  
	    System.out.println("文件名称：" + fileName2);  
	  }  
}
