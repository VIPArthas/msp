<%@page import="com.wh.constants.Constants"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/WEB-INF/pageTag.tld" prefix="page" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta name="keywords" content="校联网,微门户" />
    <script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } </script>
    <title>校联网微门户-后台管理</title>
    <link href="<%=request.getContextPath() %>/resource/wmh/web/commons/css/font/iconfont.css" rel="stylesheet">
    <link href="http://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
    <link href="<%=request.getContextPath() %>/resource/wmh/web/commons/css/style.css" rel="stylesheet" type="text/css" media="all"/>
    <script type="text/javascript">var urlPix ="<%=request.getContextPath() %>"; </script>
    <script src="http://libs.baidu.com/jquery/1.9.1/jquery.min.js"></script>
    <script src="http://cdn.bootcss.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
    <script src="<%=request.getContextPath() %>/resource/wmh/web/commons/js/public.js" ></script>
    <script src="<%=request.getContextPath() %>/resource/wmh/web/commons/js/jquery.z-pager.js" ></script>
    <script src="<%=request.getContextPath() %>/resource/wmh/web/commons/js/slide.js" ></script>
     <script src="<%=request.getContextPath() %>/resource/wmh/web/commons/js/ssp.js" ></script>
     
</head>
<body id="user_body">
 <div class="photoWrap">
<div class="imgContainer">
<div class="fa close">&#xe603;</div>
	<!--大图-->
	<div class="detailImg" > 
		<a id="detailImg-pre">&lt;</a>
		<div id="detailImg-box" class="box"> </div>
		<a id="detailImg-next">&gt;</a> 
	</div>
	<!--小图-->
	<div class="smallImg"> 
		<a id="smallImg-pre"></a>
		<div id="smallImg-box" class="box">
			<ul id="smallImg-ul" class="imgUl">
			</ul>
		</div>
		<a id="smallImg-next"></a> 
	</div>
</div>
</div>
<div class="col-md-10 right-view">
         <div class="table-responsive">
         <div class="col-md-12 tstitle">随手拍</div>
         <div class="row rv-wrap">
        <!--  <div class="searchbox">
			<input type="text" placeholder="请输入您要搜索的姓名或手机号码" /><button type="submit" id="search">搜索</button>
		</div> -->
             <table class="table table_black thistable" id="thistable">
                 <thead>
                 <tr>
                     <th width="15%">序号</th>
                     <th width="15%">发布者 </th>
                     <th width="10%">标题信息</th>
                     <th width="20%">图片</th>
                     <th width="20%">创建时间</th>
                     <th width="20%">操作</th>
                 </tr>
                 </thead>
                 <tbody></tbody>
    </table>
    
        <div class="EX-pager">
     <div class="second-ul-ctn">
            <ul class="second-ul" id="ulProducts">
            </ul>
            <div class="pages">
                <input type="hidden" id="hideTotalCount" />
                <div id="Pagination" class="pagination">
                </div>
            </div>
        </div>
    </div>
    </div>
</div>
 </div>

<script type="text/javascript" src="/resource/wmh/web/user/index.js"></script>
</body>
</html>
