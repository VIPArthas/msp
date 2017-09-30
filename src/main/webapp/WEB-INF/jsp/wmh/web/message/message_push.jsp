<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta name="keywords" content="校联网,微门户" />
    <script>
        var urlPix = "<%=request.getContextPath()%>";
    </script>
    <script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } </script>
    <title>校联网微门户-后台管理</title>
    <link href="<%=request.getContextPath() %>/resource/wmh/web/commons/css/font/iconfont.css" rel="stylesheet">
    <link href="http://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
    <link href="<%=request.getContextPath() %>/resource/wmh/web/commons/css/style.css" rel="stylesheet" type="text/css" media="all"/>
    <script src="http://libs.baidu.com/jquery/1.9.1/jquery.min.js"></script>
    <script src="http://cdn.bootcss.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
    <script src="<%=request.getContextPath() %>/resource/wmh/web/commons/js/public.js" ></script>
    <script src="<%=request.getContextPath() %>/resource/wmh/web/commons/js/send_msg.js" ></script>
</head>
<body>
<!--标签页开始-->
<div class="tabs">
    <div class="row">
        <div class="col-md-2 col-sm-3 co-xs-6">发送消息</div>
        <div class="col-md-10 col-sm-9 co-xs-6"></div>
    </div>
</div>
<!--标签页结束-->
<br>
<form class="form-horizontal">
 
  <div class="form-group" id="form-group_1">
            <span  class="col-md-2 col-sm-2 control-label">消息内容：</span>
            <div class="col-md-6 col-sm-10">
               <textarea class="mytextarea"></textarea>
            </div>
            </div>
 
       
        <div class="form-group">
            <span  class="col-md-2 col-sm-2 control-label">发送用户：</span>
            <div class="col-md-6 col-sm-10">
                <div class="well"  style="overflow:hidden;">
                
                </div>
                <div class="tips"><a id="mask" href="javascript:void(0)">选择发送范围</a></div>
            </div>
        </div>
        
 


    <div class="form-group">
        <div class="col-md-offset-2 col-sm-offset-2 col-md-6 col-sm-10">
            <a class="btn btn_blue" href="javascript:void(0);" id="endSend"  style="width: 100%;">发送</a>
        </div>
    </div>
</form>
<div class="sendrange">
	<div class="send-title"><span id="selectRange">选择发送范围</span><i class="fa closerange">&#xe603;</i></div>
	<div class="send-content">
		<div class="send-left">
		<div class="searchbox">
			<input type="text" placeholder="搜索成员、部门或标签" /><button type="submit" id="search">搜索</button>
		</div>
<div class="open-tab">
<div class="loop-tab">
<div class="search-result">
  <ul class="nav nav-tabs mytabs" role="tablist">
    <li role="presentation" class="active"><a href="#range" aria-controls="range" role="tab" data-toggle="tab">组织架构</a></li>
    <!--<li role="presentation"><a href="#student" aria-controls="student" role="tab" data-toggle="tab">标签</a></li> -->
  </ul>

  <div class="tab-content">
    <div role="tabpanel" class="tab-pane active" id="range">
    	<ul class="result-list" id="normal">
    
    	</ul>
    </div>
   <!-- <div role="tabpanel" class="tab-pane" id="student">
        <ul class="result-list" id="select">
         
    	 </ul>
    </div> -->
  </div>
</div>
<div class="search-result-show">
<a href="javascript:void(0)" id="normalList">返回</a>
<ul class="result-list" id="searchList">
    
</ul>
</div>
</div>
</div>


		</div>
	<div class="send-right">已选择的成员、部门或标签
	<div class="select-list">
	<ul class="result-list" id="selectList">
    	</ul>
	</div>
	</div>	
	<div class="save-result">
     <button type="submit" id="endStep">提交</button><span class="closerange">取消</span>
    </div>
	</div>
	
</div>
<script src="<%=request.getContextPath() %>/resource/wmh/web/message/message_push.js"></script>
</body>
</html>