package com.wh.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.util.StringUtils;







public class SortUtil {

	public static void bubbleSort(Integer[] a) {
		Integer temp = 0;
        for (int i = a.length - 1; i > 0; --i) {
        	for (int j = 0; j < i; ++j) {
        		Integer temp1 = (null == a[j + 1]) ? 0 : a[j + 1];
        		Integer temp2 = (null == a[j]) ? 0 : a[j];
                if (temp1 < temp2) {
                    temp = a[j];
                    a[j] = temp1;
                    a[j + 1] = temp;
                }
            }
        }
    }
	
    /**
     * 将Double数组排序
     * 5.0  6.0  9.0  1.0  5.0  
     * 1.0  5.0  5.0  6.0  9.0  
     * @param a 带排序的数组
     */
    public static void bubbleSort(Double[] a) {
    	Double temp = 0.0;
        for (int i = a.length - 1; i > 0; --i) {
        	for (int j = 0; j < i; ++j) {
        		Double temp1 = (null == a[j + 1]) ? 0.0 : a[j + 1];
        		Double temp2 = (null == a[j]) ? 0.0 : a[j];
                if (temp1 < temp2) {
                    temp = a[j];
                    a[j] = temp1;
                    a[j + 1] = temp;
                }
            }
        }
    }
    
    /**
     * 将list<map>排序 倒序
     * @param sortMapList 需要排序的list<map> map中的排序主字段keyName=sort
     * 
     */
    public static void bubbleSort(List<Map<String, Object>> sortMapList) {
    	Collections.sort(sortMapList, new Comparator<Map<String, Object>>(){
			 public int compare(Map<String, Object> mapping1,Map<String, Object> mapping2){
				 	Object obj1 = mapping1.get("sort");
				 	Object obj2 = mapping2.get("sort");
				 	int map1value = 0;
				 	int map2value = 0;
				 	if(!StringUtils.isEmpty(obj1)){
				 		map1value = (Integer) obj1;
				 	}
				 	if(!StringUtils.isEmpty(obj2)){
				 		map2value = (Integer) obj2;
				 	}
	                return map2value - map1value;
			 }
		 });
    }
    
    public static void main(String[] args) {
    	Double[] a = new Double[]{5.0,6.0,9.0,1.0,5.0};
    	for(int i=0;i<a.length;i++){
    		System.out.print(a[i] + "  ");
    	}
    	System.out.println("");
    	SortUtil.bubbleSort(a);
    	for(int i=0;i<a.length;i++){
    		System.out.print(a[i] + "  ");
    	}
    	System.out.println("");
    	
    	List<Map<String, Object>> sortMapList = new ArrayList<Map<String, Object>>();
		Map<String, Object> scoreTable1 = new HashMap<String, Object>();
		scoreTable1.put("score", 5);
		//scoreTable1.put("sort", 5);
		scoreTable1.put("desc", "第一个");
		scoreTable1.put("id", "111111111");
		sortMapList.add(scoreTable1);
		Map<String, Object> scoreTable2 = new HashMap<String, Object>();
		scoreTable2.put("score", 9);
		scoreTable2.put("sort", 9);
		scoreTable2.put("desc", "第二个");
		scoreTable2.put("id", "2222222");
		sortMapList.add(scoreTable2);
		Map<String, Object> scoreTable3 = new HashMap<String, Object>();
		scoreTable3.put("score", 6);
		scoreTable3.put("sort", 6);
		scoreTable3.put("desc", "第三个");
		scoreTable3.put("id", "3333333");
		sortMapList.add(scoreTable3);
		Map<String, Object> scoreTable4 = new HashMap<String, Object>();
		scoreTable4.put("score", 5);
		scoreTable4.put("sort", 5);
		scoreTable4.put("desc", "第四个");
		scoreTable4.put("id", "44444444");
		sortMapList.add(scoreTable4);
		SortUtil.bubbleSort(sortMapList);
		System.out.println(sortMapList);
	}

}
