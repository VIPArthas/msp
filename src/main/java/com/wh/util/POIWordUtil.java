package com.wh.util;
import java.io.FileInputStream;  
import java.io.FileOutputStream;
import java.util.HashMap;  
import java.util.Map;  
  
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.usermodel.Paragraph;
import org.apache.poi.hwpf.usermodel.Range;  
import org.apache.poi.hwpf.usermodel.Table;  
import org.apache.poi.hwpf.usermodel.TableCell;  
import org.apache.poi.hwpf.usermodel.TableIterator;  
import org.apache.poi.hwpf.usermodel.TableRow;
  
public class POIWordUtil {  
 
    public static void main(String[] args) throws Exception {  
        Map<String, String> replaces = new HashMap<String, String>();  
  
        replaces.put("username", Text.getStr("郑爽"));
        replaces.put("password", Text.getStr("1123456"));  
        replaces.put("author", Text.getStr("lee"));  
  
        poiWordTableReplace("D:\\bbbb.doc", "D:\\t2.doc", replaces);
    }  
  
    public static void poiWordTableReplace(String sourceFile, String newFile,  
            Map<String, String> replaces) throws Exception {  	
        FileInputStream in = new FileInputStream(sourceFile);  
        HWPFDocument hwpf = new HWPFDocument(in);  
        Range range = hwpf.getRange();// 得到文档的读取范围  
        TableIterator it = new TableIterator(range);  
        // 迭代文档中的表格  
        while (it.hasNext()) {  
            Table tb = (Table) it.next();  
            // 迭代行，默认从0开始  
           for (int i = 0; i < tb.numRows(); i++) {  
                TableRow tr = tb.getRow(i);  
                // 迭代列，默认从0开始  
                for (int j = 0; j < tr.numCells(); j++) {  
                    TableCell td = tr.getCell(j);// 取得单元格  
                    // 取得单元格的内容  
                    for (int k = 0; k < td.numParagraphs(); k++) {  
                        Paragraph para = td.getParagraph(k);  
  
                        String s = para.text();
                        final String old = s;  
                        for (String key : replaces.keySet()) {  
                            if (s.contains(key)) {  
                                s = s.replace(key, replaces.get(key));  
                            }  
                        }  
                        if (!old.equals(s)) {// 有变化  
                            para.replaceText(old, s);  
                            s = para.text();  
                       }  
 
                    } // end for  
                } // end for  
            } // end for  
        } // end while  
  
        FileOutputStream out = new FileOutputStream(newFile);  
        hwpf.write(out);  
        in.close();
        out.flush();  
        out.close();  
  
    } 
    
   

}  

