package com.wh.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class ExportExcelUtil {

    /**
     * 生成excel文件
     * 
     * @param dataList
     * @param headList
     * @param excelName
     * @return
     */
    public HSSFWorkbook export(List<List<String>> dataList, List<String> headList, String excelName) {
        HSSFWorkbook wb = new HSSFWorkbook();
        HSSFSheet sheet = wb.createSheet(excelName);
        HSSFRow row = sheet.createRow((int)0);
        HSSFCellStyle style = wb.createCellStyle();
        style = this.getHeadStyle(style, wb);
        for (int i = 0; i < headList.size(); i++) {
            genRow(row, headList, style);
        }

        style = wb.createCellStyle();
        style = this.getDataStyle(style, wb);
        for (int i = 0; i < dataList.size(); i++) {
            row = sheet.createRow(i + 1);
            List<String> tempList = dataList.get(i);
            genRow(row, tempList, style);
        }
        return wb;
    }

    /**
     * 填充一行的数据
     * 
     * @param row
     * @param list
     * @param style
     */
    public void genRow(HSSFRow row, List<String> list, HSSFCellStyle style) {
        for (int i = 0; i < list.size(); i++) {
            @SuppressWarnings("deprecation")
            HSSFCell cell = row.createCell((short)i);
            HSSFRichTextString text = new HSSFRichTextString(list.get(i));
            cell.setCellValue(text);
            cell.setCellStyle(style);
        }
    }

    /**
     * 表头样式
     * 加粗、水平居中
     */
    public HSSFCellStyle getHeadStyle(HSSFCellStyle style, HSSFWorkbook wb) {
        HSSFFont font = wb.createFont();
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);// 加粗显示
        style.setFont(font);
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        return style;
    }

    public HSSFCellStyle getDataStyle(HSSFCellStyle style, HSSFWorkbook wb) {
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        return style;
    }
    
    
    /**
     * 根据HSSFCell类型设置数据
     * @param cell
     * @return
     */
    public static String getCellFormatValue(HSSFCell cell) {
        String cellvalue = "";
        if (cell != null) {
            // 判断当前Cell的Type
            int type = cell.getCellType();
            switch (type) {
                // 如果当前Cell的Type为NUMERIC
                case HSSFCell.CELL_TYPE_NUMERIC:
                case HSSFCell.CELL_TYPE_FORMULA: {
                    // 判断当前的cell是否为Date
                    if (HSSFDateUtil.isCellDateFormatted(cell)) {
                        // 如果是Date类型则，转化为Data格式

                        //方法1：这样子的data格式是带时分秒的：2011-10-12 0:00:00
                        //cellvalue = cell.getDateCellValue().toLocaleString();

                        //方法2：这样子的data格式是不带带时分秒的：2011-10-12
                        Date date = cell.getDateCellValue();
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                        cellvalue = sdf.format(date);

                    }
                    // 如果是纯数字
                    else {
                        // 取得当前Cell的数值
                        cellvalue = String.valueOf((long)cell.getNumericCellValue());
                    }

                    break;
                }
                // 如果当前Cell的Type为STRIN
                case HSSFCell.CELL_TYPE_STRING:
                    // 取得当前的Cell字符串
                    cellvalue = cell.getRichStringCellValue().getString();
                    break;
                // 默认的Cell值
                default:
                    cellvalue = cell.getStringCellValue();
            }
        } else {
            cellvalue = "";
        }
        return cellvalue;
    }
}
