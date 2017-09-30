<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="refresh" content="3; URL=${action}" />
<title>消息中心 </title>
<script type="text/javascript" src="<%=basePath%>resource/js/jquery.min.js" charset="utf-8"></script>
<link rel="stylesheet" type="text/css" href="<%=basePath%>resource/admin/templates/public.css"/>
<script type="text/javascript">
//设定倒数秒数  
var t = 3;  
//显示倒数秒数  
function showTime(){
    $(".blue").html(t);  
    t -= 1;  
    if(t==0){
        javascript:history.back();  
    }  
    //每秒执行一次,showTime()  
    setTimeout("showTime()",1000);  
}  
//执行showTime()  
showTime();
</script>
</head>
<body>
<div id="outMsg">
 <h2><c:out value="${errorMsg}"></c:out></h2>
 <dl>
  <dt>如果您不做出选择，将在 <span class="blue">3</span> 秒后跳转到上一个链接地址。</dt>
  <dd><a href="${action}">返回上一页</a></dd>
 </dl>
</div>
</body>
</html>