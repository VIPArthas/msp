<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html lang="en" style="background-color: white;">
<head>
    <meta http-equiv="Content-type" content="text/html; charset=utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge, chrome=1">
    <meta name="renderer" content="webkit">
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=0, minimal-ui">
    <meta http-equiv="Access-Control-Allow-Origin" content="*">
    <meta name="apple-mobile-web-app-capable" content="yes"/>
    <meta name="apple-mobile-web-app-status-bar-style" content="black"/>

		<script>
        var urlPix = "<%=request.getContextPath()%>";
    	</script>
		<link rel="stylesheet" href="<%=request.getContextPath() %>/resource/wmh/wx/manage/commons/css/font/iconfont.css">
		<link rel="stylesheet" href="<%=request.getContextPath() %>/resource/wmh/wx/manage/commons/css/baes.css?v=48">
		<script src="http://libs.baidu.com/jquery/2.0.0/jquery.min.js"></script>
		<script src="<%=request.getContextPath() %>/resource/wmh/wx/manage/commons/js/rem.js" type="text/javascript" charset="utf-8"></script>

		<title>消息推送</title>
	</head>
	<body style="background-color: white;overflow-y:scroll;">

	
	<div class="content">
		<h3>模版选择:</h3>
		<div class="xialakuang">
			<p>
				<span data-template-type="1" class="messagebian">1.会议通知</span>
				<span class="icon iconfont icon-zhankai"></span>
			</p>
			<p class="opents opents1">1.会议通知</p>
			<p  class="opents opents2">2.薪资发放通知</p>
		</div>
		<!--会议通知页面-->
		<div class="message message1" style="margin-bottom: 1rem;">
			<h3>会议名称：</h3>
			<input id="parm2_1" type="text" class="messageinput"/>
			<h3>会议时间：</h3>
			<input id="parm3_1" type="text" class="messageinput"/>
			<h3>会议地点：</h3>
			<input id="parm4_1" type="text" class="messageinput"/>
			<h3>会议介绍：</h3>
			<input id="parm5_1" type="text" class="messageinput"/>
			<h4 class="fasong">发送方式选择:</h4>
		<div class="wrap">
			<input id="wx1" type="checkbox" class="checkbox1" class="che1"/>
			<p class=""></p>
			<p>微信公众平台</p>
			<input id="dx1" type="checkbox" />
			<p class="duanxin">短信</p>
			<input id="yj1" type="checkbox" />
			<span class="duanxin">邮件</span>
		</div>
		
		<p class="fasongyonghu">发送用户：</p>
		<div class="tianjiabiaoqian">
			<div id="labels"></div>
			<input type="text" name="" id="" value="" placeholder="添加标签" class="h_tianjiabiaoqian" maxlength="20"/>
			<input type="text" value="" placeholder="添加用户名" class="h_tianjiayonghu"  maxlength="20"/>
			<input type="text" class="zhizhu"/>
			<div class="h_wrap">
				<h3 class="">标签管理：</h3>
				<h3>常用标签：</h3>
				<p class="changyongbiaoqian">全部</p>
				<c:choose>
                    <c:when test="${not empty tags.often_use_tags }">
                        <c:forEach items="${tags.often_use_tags}" var="tag" varStatus="status">
                            <p class="changyongbiaoqian">${tag.tag_name }</p>
                        </c:forEach>
                    </c:when>
                    <c:otherwise>
                    </c:otherwise>
                </c:choose>
			</div>
		</div>
		<div class="zuijinshiyong">
			<h3>最近使用:</h3>
			<c:choose>
                 <c:when test="${not empty tags.last_use_tags }">
                     <c:forEach items="${tags.last_use_tags}" var="tag" varStatus="status">
                         <p class="changyongbiaoqian">${tag.tag_name }</p>
                     </c:forEach>
                 </c:when>
                 <c:otherwise>
                 </c:otherwise>
             </c:choose>
		</div>
		<div id="pushMsg1" class="fasong1">发送</div>
		</div>
		<!--薪资页面-->
		<div class="message message2" style="margin-bottom: 3rem;">
			<h3>用户姓名：</h3>
			<input id="userName"  type="text" class="messageinput"/>
			<h3>手机号：</h3>
			<input id="phone" type="text" class="messageinput"/>
			<h3>工资年月：</h3>
			<input id="parm2_2" type="text" class="messageinput"/>
			<h3>应付工资：</h3>
			<input id="parm3_2" type="text" class="messageinput"/>
			<h3>实发工资：</h3>
			<input id="parm4_2" type="text" class="messageinput"/>
			<h4 class="fasong">发送方式选择:</h4>
		<div class="wrap">
			<input id="wx2" type="checkbox" class="checkbox1"/>
			<p class=""></p>
			<p>微信公众平台</p>
			<input id="dx2" type="checkbox" />
			<p class="duanxin">短信</p>
			<input id="yj2" type="checkbox" />
			<span class="duanxin">邮件</span>
		</div>
		<div id="pushMsg2" class="fasong1">发送</div>
		</div>
	</div>
	
	</body>
</html>

<script src="<%=request.getContextPath() %>/resource/wmh/wx/manage/message/index.js?v=2"></script>
<script type="text/javascript">

    function stopDrop() {
    var lastY;//最后一次y坐标点
    $(document.html).on('touchstart', function(event) {
        lastY = event.originalEvent.changedTouches[0].clientY;//点击屏幕时记录最后一次Y度坐标。
    });
    $(document.html).on('touchmove', function(event) {
        var y = event.originalEvent.changedTouches[0].clientY;
        var st = $(this).scrollTop(); //滚动条高度  
        if (y >= lastY && st <= 1) {//如果滚动条高度小于0，可以理解为到顶了，且是下拉情况下，阻止touchmove事件。
            lastY = y;
            event.preventDefault();
        }
        lastY = y;
 
    });
}
stopDrop()；

</script>

