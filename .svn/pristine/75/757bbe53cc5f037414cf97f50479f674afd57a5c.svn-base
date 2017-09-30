<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta name="keywords" content="校联网,微门户" />
    <title>校联网微门户-后台管理</title>
    <link href="http://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/resource/wmh/web/commons/css/malfunctiondetails.css"/>
    <script src="http://cdn.bootcss.com/jquery/2.2.1/jquery.min.js"></script>
    <script src="http://cdn.bootcss.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
</head>
<body>

<div class="tabs">
    <div class="row" style="border-bottom: solid 2px gainsboro;">
    	<div class="col-md-10 col-sm-9 co-xs-6"></div>
    </div>
</div>
<!--故障报修详情-->
<div class="col-md-12" style="width: 100%;">
  <div class="h_xiangqing">
  	<div>故障报修详情</div>
  </div>
  <div class="h_difang" >
  	<span></span>
  	<span>在什么地方</span>
  	<span>${place }</span>
  </div>

  <div class="h_difang" style="margin-top: 30px;">
  	<span></span>
  	<span>什么故障</span>
  	<span>${faultInfo }</span>
  	<!--添加图片，加类名-->
     	<c:choose>
    		<c:when test="${not empty picPathList }">
    			<c:forEach items="${picPathList }" var="picPath">
    			<img src="${picPath }" alt=""/>
    		    </c:forEach>
    		</c:when>
    		<c:otherwise>
    				<img src="" alt=""/>
    		</c:otherwise>
    	</c:choose>
  </div>
  <div class="h_difang" style="margin-top: 30px;">
  	<span></span>
  	<span>可能故障原因</span>
  	<span>${faultReason }</span>
  </div>
</div>
<div class="clearfix"> </div>
<!--table end-->


</body>
</html>