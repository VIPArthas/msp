package com.wh.util;

import org.springframework.util.StringUtils;

import java.io.File;

/**
 * @author lp
 * @Description: ${todo}(判断图片是否真正存在 1.先判断fileName是否为空 2.判断这个fileName下是否存在该文件)
 * @date 2016/10/12 10:13
 * @return ${return_type}    返回类型
 */
public  class PictureUtil {

    public static boolean getPictureByUrl(String fileName){
        boolean exist=false;
        if (!StringUtils.isEmpty(fileName)) {
            fileName="/D:/workspace/xlwWeb/"+fileName;
            File file = new File(fileName);
            if (file.exists()) {
                exist=true;
            } else {
                exist=false;
            }
        } else {
            exist=false;
        }
        return exist;
    }
}
