<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
    <script src="http://cdn.bootcss.com/jquery/2.2.1/jquery.min.js"></script>
    <script src="<%=request.getContextPath() %>/resource/wmh/web/user/editUser.js"></script>
    <script src="http://cdn.bootcss.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
    <script src="<%=request.getContextPath() %>/resource/wmh/web/commons/js/public.js" ></script>
</head>
<body>
<!--标签页开始-->
<div class="tabs">
    <div class="row">
        <div class="col-md-2 col-sm-3 co-xs-6">预览信息</div>
        <div class="col-md-10 col-sm-9 co-xs-6"></div>
    </div>
</div>
<!--标签页结束-->
<br>
<form class="form-horizontal">
    <div class="form-group">
    	<input type="hidden" id="id" value="${user.id }">
        <span class="col-md-2 col-sm-2 control-label text-black bt">姓名：</span>
        <div class="col-md-6 col-sm-10">
            <input type="text" class="form-control" id="realName"  placeholder="姓名" value="${user.real_name }">
        </div>
    </div>
    <div class="form-group">
        <span  class="col-md-2 col-sm-2 control-label bt">手机号：</span>
        <div class="col-md-6 col-sm-10">
            <input type="text" class="form-control" id="phone" placeholder="手机号" value="${user.phone }">
        </div>
    </div>
    <div class="form-group">
        <span class="col-md-2 col-sm-2 control-label text-black bt">邮箱：</span>
        <div class="col-md-6 col-sm-10">
            <input type="text" class="form-control" id="mail" placeholder="邮箱" value="${user.mail }">
        </div>
    </div>
    <div class="form-group">
        <span  class="col-md-2 col-sm-2 control-label">QQ号：</span>
        <div class="col-md-6 col-sm-10">
            <input type="text" class="form-control" id="qq" placeholder="QQ号" value="${user.qq }">
        </div>
    </div>
    <div class="form-group">
        <span  class="col-md-2 col-sm-2 control-label">标签：</span>
        <div class="col-md-6 col-sm-10">
            <div class="well">
                <div class="labellist_gray " id="labels">
                    <c:forEach items="${user.tagList }" var="tag">
                    	<mark class="cur">${tag.tag_name }</mark>
                    </c:forEach>
                    <input type="text" class="addinput" id="add" placeholder="添加标签" maxlength="30"  />
                </div>
                <div class="tips"></div>
            </div>
        </div>
    </div>
    <div class="form-group">
        <span  class="col-md-2 col-sm-2 control-label">标签管理：</span>
        <div class="col-md-6 col-sm-10">
            <div class="clearfix"></div><span class="tit mt10">常用标签：</span>
            <div class="labellist_gray labels">
               <c:forEach items="${list }" var="tag">
             		<mark class="cur">${tag.tagName }</mark>
             	</c:forEach>
            </div>
            <br>
            <div class="clearfix"></div>
        </div>
    </div>
    <div class="form-group">
        <div class="col-md-offset-2 col-sm-offset-2 col-md-6 col-sm-10">
            <a href="javascript:void(0);" class="btn btn_blue btn_bg" id="update" onclick="updateUser()">保存</a>
        </div>
    </div>
</form>
</body>
</html>