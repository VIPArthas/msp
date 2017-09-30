package com.wh.util.msp;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;

public class StringUtil {

	/**
	 * <P>判断是否为NULL或空字符换</P>
	 * @param arg
	 * @return
	 */
	public static boolean isNullEmpty(String arg) {
		if(arg == null) {
			return true;
		}
		if(arg.trim().equals("")) {
			return true;
		}
		return false;
	}
	
	/**
	 * <P>判断俩字符串是否相等</P>
	 * @param first
	 * @param second
	 * @return
	 */
	public static boolean isEquals(String first,String second) {
		if (first.trim().equals(second.trim())) {
			return true;
		}
		return false;
	}
	
	/**
	 * <P>集合返回字符串，以逗号分隔</P>
	 * @param list
	 * @return
	 */
	public static String getStringFromList(List<String> list) {
		if (null == list || list.size() <= 0) {
			return "";
		}
		
		StringBuffer buffer = new StringBuffer();
		for (String str : list) {
			buffer.append(str + ",");
		}
		
		String str = buffer.toString();
		return str.substring(0, str.length() - 1);
	}
	/**
	 * 采用正则表达 过滤SQL需要替换占位符
	 * @param source
	 */
	public static String[]  sqlPatternRender(String source){
		String newSql=source;
		StringBuffer sb =new StringBuffer("");
		Pattern p = Pattern.compile("(#\\{[^\\}]*\\})");  
		Matcher m = p.matcher(source);  
		while(m.find()){  
			String temp=m.group(1);
			sb.append(temp.replaceAll("\\}", "").replaceAll("#\\{", "")).append(";");
			newSql=newSql.replaceFirst(escapeExprSpecialWord(temp), "?");
			}
	     if(sb.length()>=1)
	return new String[]{newSql,sb.toString()};
	     else
		return  null;
	}
	/**
	 * 转义正则特殊字符 （$()*+.[]?\^{},|）
	 * 
	 * @param keyword
	 * @return 转义后字符串
	 */
	public static String escapeExprSpecialWord(String keyword) {
		if (StringUtils.isNotBlank(keyword)) {
			String[] fbsArr = { "\\", "$", "(", ")", "*", "+", ".", "[", "]", "?", "^", "{", "}", "|" };
			for (String key : fbsArr) {
				if (keyword.contains(key)) {
					keyword = keyword.replace(key, "\\" + key);
				}
			}
		}
		return keyword;
	}
	  /**
	   * 捕获异常信息并返回字符串
	   * @param e
	   */
	  public  static String getMysqlExceptionMes(Exception e){
	  	  //获取异常信息
	      ByteArrayOutputStream stream = new ByteArrayOutputStream();
	      e.printStackTrace(new PrintStream(stream));
	      return   stream.toString();
	 
	  }
}
