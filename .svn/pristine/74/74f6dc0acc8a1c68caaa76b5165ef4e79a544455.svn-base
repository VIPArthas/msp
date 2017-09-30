<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="com.wh.constants.Constants"%>
<script>
var prefix = "<%=request.getContextPath()%>";
var allowExt = [];
var imgArr = [];
var docArr = [];
var vodArr = [];
<%  
	String[] img_allow_type = Constants.img_allow_type;
	for(int i = 0;i < img_allow_type.length;i++){
%>
		imgArr.push('<%=img_allow_type[i]%>');
<%	}%>
<%  
	String[] doc_allow_type = Constants.doc_allow_type;
	for(int i = 0;i < doc_allow_type.length;i++){
%>
		docArr.push('<%=doc_allow_type[i]%>');
<%	}%>
<%  
	String[] video_allow_type = Constants.video_allow_type;
	for(int i = 0;i < video_allow_type.length;i++){
%>
		vodArr.push('<%=video_allow_type[i]%>');
<%	}%>
allowExt = allowExt.concat(imgArr, docArr, vodArr);
var docStr = docArr.join(",");
var vodStr = vodArr.join(",");
</script>