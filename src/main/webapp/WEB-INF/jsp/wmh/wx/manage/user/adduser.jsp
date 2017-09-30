<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
	   <head>
	   <meta charset="UTF-8">
       <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
       <meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no"/>
       <meta name="apple-mobile-web-app-capable" content="yes">
       <meta name="apple-mobile-web-app-status-bar-style" content="black">
        <link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/resource/wmh/wx/manage/commons/css/baes.css?v=1"/>
        <link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/resource/wmh/wx/manage/commons/css/adduser.css"/>
		<link rel="stylesheet" href="<%=request.getContextPath() %>/resource/wmh/wx/manage/commons/css/font/iconfont.css">
		<script src="<%=request.getContextPath() %>/resource/wmh/wx/manage/commons/js/jquery-3.1.1.js"></script> 
		<script type="text/javascript">var urlPix = "<%=request.getContextPath()%>";</script>
    	<script type="text/javascript"
		src="<%=request.getContextPath()%>/resource/wmh/wx/manage/commons/js/edituser.js"  charset="UTF-8">
	</script>
		<title>添加用户</title>
	</head>
	<body style='overflow-y:scroll;'>
		<div class="tianjiawrap" style="margin-bottom: 1rem;">
			<div class="tianjiatop">
				<p>姓名</p>
				<input type="text" id="realName"/>
			</div>
			<div class="tianjiatop">
				<p>手机号</p>
				<input type="text" id="phone"/>
			</div>
			<div class="tianjiatop">
				<p>邮箱</p>
				<input type="text" id="mail"/>
			</div>
			<div class="tianjiatop">
				<p>QQ号</p>
				<input type="text" id="qq"/>
			</div>

				<div class="tianjiatop">
				<p>标签</p>
			</div>
			
				<div class="tianjiabiaoqian">
			<div></div>
			<input type="text" name="" id="" value="" placeholder="添加标签" class="h_tianjiabiaoqian" maxlength="20"/>
            <i></i>
			<input type="text" class="zhizhu"/>
			<div class="h_wrap">
				<!--<h3 class=""></h3>-->
				<h3>常用标签：</h3>
				    <c:choose>
             	<c:when test="${not empty list }">
             		<c:forEach items="${list }" var="tag">
             			
             		<p class="changyongbiaoqian">${tag.tagName }</p>
             		</c:forEach>
             	</c:when>
             	<c:otherwise>
             		<div>暂无标签</div>
             	</c:otherwise>
             </c:choose>

			</div>
			<div class="fasong1" id="add"  onclick="addUser()" style="margin-bottom:1.5rem;border-radius:0.12rem;">完成</div>
		</div>
		</div>
	</body>
</html>
<script src="<%=request.getContextPath() %>/resource/wmh/wx/manage/commons/js/jquery-3.1.1.js"></script>

<script src="<%=request.getContextPath() %>/resource/wmh/wx/manage/commons/js/rem.js" type="text/javascript" charset="utf-8"></script>
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
	$(".h_tianjiabiaoqian").blur(function(){
		var oa = true;
		var ob = false;
		var index11 = [];
		var biaoqian1 = true;
		console.log("aaa");
		$(".newdiv").each(function(i,v){
			index11 = $(v).text().split(" ");
			if(($(".h_tianjiabiaoqian").val()) == index11[0]){
				$(".h_tianjiabiaoqian").val("");
				console.log("11");
				biaoqian1 = false;
				return;
			}
		})
		if($(".h_tianjiabiaoqian").val() != "" && biaoqian1){
			console.log("aaa");
//		$("<p class='newdiv'></p>").text($(".h_tianjiayonghu").val()).appendTo($(".tianjiabiaoqian>div:nth-child(1)"));
//		$(".h_tianjiayonghu").val("");
			$("<p class='newdiv'></p>").text($(".h_tianjiabiaoqian").val()+" ").appendTo($(".tianjiabiaoqian>div:nth-child(1)")).append($("<span class='icon iconfont icon-houdongfangiconfont10'></span>").click(function(){
				if(ob){
					$(this).parent().remove();
				}
			})).click(function(){
				if(oa){
					$(this).css({"background":"#4bb1ff","color":"white"});
					oa = false;
					ob = true;

				}else{
					$(this).css({"background":"white","color":"#555555"});
					oa = true;
					ob = false;
				}
			});

			$(".h_tianjiabiaoqian").val("");
		}

	})
	//常用标签
	$(".changyongbiaoqian").click(function(){
		var oa = true;
		var ob = false;
		var index11 = [];
		var biaoqian = true
		var inntext = this;
		$(".newdiv").each(function(i,v){
			index11 = $(v).text().split(" ");
			if($(inntext).text() == index11[0]){
				console.log("11");
				biaoqian = false;
				return;
			}
		})
		if(biaoqian){
//          	$("<p class='newdiv'></p>").text($(this).text()+"()").appendTo($(".tianjiabiaoqian>div:nth-child(1)"));
			$("<p class='newdiv'></p>").text($(this).text()+" ").appendTo($(".tianjiabiaoqian>div:nth-child(1)")).append($("<span class='icon iconfont icon-houdongfangiconfont10'></span>").click(function(){
				if(ob){
					$(this).parent().remove();
				}
			})).click(function(){
				if(oa){
					$(this).css({"background":"#4bb1ff","color":"white"});
					oa = false;
					ob = true;

				}else{
					$(this).css({"background":"white","color":"#555555"});
					oa = true;
					ob = false;
				}
			});



		}
		console.log($(".newdiv").text().split(" "));

	});

</script>