<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html lang="en">
<head>
    <meta http-equiv="Content-type" content="text/html; charset=utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge, chrome=1">
    <meta name="renderer" content="webkit">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=0, minimal-ui">
    <meta http-equiv="Access-Control-Allow-Origin" content="*">
    <meta name="apple-mobile-web-app-capable" content="yes"/>
    <meta name="apple-mobile-web-app-status-bar-style" content="black"/>
    <title>个人中心</title>
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/resource/wmh/wx/commons/css/style.css?v=12"  />
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/resource/wmh/wx/commons/css/font3/font_5y12bci07sbdquxr/iconfont.css"  />
    <script type="text/javascript" src="http://cdn.hcharts.cn/jquery/jquery-1.8.3.min.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath() %>/resource/wmh/wx/commons/js/rwdImageMaps.js"></script>
	 <style>
        .del_phone{
            width: 86%;
            margin-left: 7%;
            font-size: 4vw;
            margin-top: 10vw;
            color: white;
            background-color: #ff7a7e;
            line-height: 10vw;
            height: 10vw;
            text-align: center;

        }
    </style>
</head>
<body>
<div class="header"></div>
<div class="content">
    <div class="banner">
        <img src="<%=request.getContextPath() %>/resource/wmh/wx/commons/img/top_gr.jpg"  usemap="#myMap"/>
        <map name="myMap">
            <area href="<%=request.getContextPath() %>/wmh/wmhUser/wx/goWriteWordPage.htm" shape="rect" coords="240,0,500,135" style="cursor: pointer">
        </map>
    </div>
    <div class="me">
        <dl><a href="javascript:void(0)"><dt><i class="iconfont icon-xingming1 t1"></i><span>姓名</span></dt><dd>${user.realName }</dd></a></dl>
        <dl class="after" style="padding-top:1vw"><a href="<%=request.getContextPath() %>/wmh/wmhUser/wx/goChangePhone.htm"><dt><i class="iconfont icon-weibiaoti1 t2"></i><span>手机号</span></dt><dd><span>${user.phone }</span><i class="icon iconfont icon-jiantou1"></i></dd></a></dl>
        <dl class="after" style="padding-top:1vw"><a href="<%=request.getContextPath() %>/wmh/wmhUser/wx/goChangeEmail.htm"><dt><i class="iconfont icon-youxiang t3"></i><span>邮箱</span></dt><dd><span>${user.mail }</span><i class="icon iconfont icon-jiantou1"></i></dd></a></dl>
        <dl class="after" style="padding-top:1vw"><a href="<%=request.getContextPath() %>/wmh/wmhUser/wx/goChangeQQ.htm"><dt><i class="iconfont icon-qq t1"></i><span>QQ号</span></dt><dd><span>${user.qq }</span><i class="icon iconfont icon-jiantou1"></i></dd></a></dl>
        <dl>
	        <a href="javascript:void(0);">
		        <dt><i class="iconfont icon-tag t4"></i><span>标签</span></dt>
		        <dd>
			        <div class=" labels">
			        	<c:choose>
			        		<c:when test="${not empty userTags }">
			        			<c:forEach items="${userTags }" var="tag">
			        				<mark class="box">${tag.tag_name }</mark>
			        			</c:forEach>
			        		</c:when>
			        		<c:otherwise>
			        			暂无标签
			        		</c:otherwise>
			        	</c:choose>
			        
			        </div>
		        </dd>
	        </a>
        </dl>
    </div>
</div>
<div class="del_phone" onclick="delphone()">解除手机号</div>

<div class="footer"></div>
<script type="text/javascript">
	$(function(){
	    $('img[usemap]').rwdImageMaps();
		
		
		
	});
	function delphone(){
        if(confirm("确认解除手机号？")){
            $.get("<%=request.getContextPath() %>/wmh/wmhUser/wx/jiebangPhone.htm",
                    function(result){
            	console.log(result);
            	var data = JSON.parse(result);
                        if(data.success){                      	
                        	 window.location.href = "<%=request.getContextPath() %>/wmh/wmhUser/wx/userInfo.htm";
                        }else {
                            alert('解除失败！')
                        }
                    });
        }
    }
</script>
</body>
</html>