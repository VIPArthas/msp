package com.wh.util.base;

import java.util.ArrayList;
import java.util.List;

import org.springframework.ui.ModelMap;

/**
 * 分页util
 * @author Administrator	Leo
 *
 */
public class BaseUtil {
	
	
	/**
	 * 分页
	 * @param model
	 * @param pageNo	页码
	 * @param recordCount	总记录数
	 * @param column	每页条数
	 */
	public static void page( ModelMap model,int pageNo,Integer recordCount,int column){  
        int pages = 0;  
        if(recordCount%column==0){  
            pages=recordCount/column;  
        }else{  
            pages=recordCount/column+1;  
        }  
        int count = pageNo;  
        List<Integer> sss = new ArrayList<Integer>();  
        if(pages>10){  
            for(int i=1;i<10;i++){  
                if(pages==i){  
                      
                }else{  
                        if(pages-pageNo<4){  
                            sss.add(pages-9+i);  
                        }else if(pages-pageNo==4){  
                            sss.add(pages-9+i);  
                        }else if(pageNo-1<=4){  
                            sss.add(i+1);  
                        }else{  
                            sss.add(count-4+i-1);  
                        }  
                }  
            }  
        }else{  
            for (int j = 1; j < pages; j++) {  
                sss.add(j+1);  
            }  
        }  
        int aaa=0;  
        if(pageNo-1<=4){  
            aaa = 123;  
        }  
        model.addAttribute("aaa", aaa);  
        model.addAttribute("pages", pages);  
        model.addAttribute("sss", sss); 
        model.addAttribute("pageNo", pageNo); 
    }

}
