<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge, chrome=1">
<meta name="renderer" content="webkit">
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=0, minimal-ui">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="Access-Control-Allow-Origin" content="*">
<meta name="keywords" content="微门户-登录" />
<title>微门户-登录</title>
<link rel="stylesheet"	href="<%=request.getContextPath()%>/resource/wmh/web/commons/css/css.css">
<script src="http://libs.baidu.com/jquery/1.9.1/jquery.min.js"></script>
<script type="text/javascript">
	$(document).ready(function(){
		var d,w,h,o;
		d=document.documentElement;
		w=d.clientWidth;
		h=d.clientHeight;
		o=$('.div');
		o.css({left:(w-o.outerWidth())/2,top:(h-o.outerHeight())/2}).fadeIn("fast");
	})
</script>
</head>
<body>
 
		 
		<div class="div">
			<div class="title">
 
				<div class="h3"><img
							src="<%=request.getContextPath()%>/resource/wmh/web/commons/img/logo1.png"
							alt=""></div>
			</div>
			<div id="myForm" class="form">
				<div class="main">
					<div class="adminname">
						<img
							src="<%=request.getContextPath()%>/resource/wmh/web/commons/img/people.png"
							alt=""><input class="username" type="text" placeholder="帐号"
							name="account" id="account" value="admin">
					</div>
					<div class="adminpassword">
						<img
							src="<%=request.getContextPath()%>/resource/wmh/web/commons/img/lock.png"
							alt=""><input class="password" type="password"
							placeholder="密码" name="pwd" id="pwd">
					</div>
				</div>
				<button class="login" onclick="submitLogin1();">登&nbsp;&nbsp;&nbsp;录</button>
				<div class="find-pwd"><a href="<%=request.getContextPath()%>/wmh/userManage/web/findPassword.htm">修改密码</a></div>
			</div>
		</div>
 

	<script type="text/javascript">

		function submitLogin1() {
			var account = $.trim($("#account").val());
			var pwd = $.trim($("#pwd").val());
			
			if(account==""||pwd==""){
				alert("账号或密码不能为空!");
			}else{
			       
	<%-- 			$.ajax({ 
			        type: "GET", 
			        url: "/wmh/userManage/web/adminLogin.htm",
			        data :  {account:account, password:pwd},
			        dataType: "JSON", 
			        async: false, 
			        success: function(data) {
			        	if (data.success) {
							window.location.href = "<%=request.getContextPath()%>/wmh/userManage/web/goIndex.htm";
						}else{
							alert("账号或密码错误");
						}
			        }

			    }); --%>

			<%-- 	 $.post("<%=request.getContextPath()%>/wmh/userManage/web/adminLogin.htm",
						 {account:account, password:pwd},
							function(response) {
								var data = JSON.parse(response);
								if (data.success) {
									window.location.href = "<%=request.getContextPath()%>/resource/msp/agent/agent-new.html";
								}else{
									alert("账号或密码错误");
								}
							});	 
				  --%>
				 
				 
				 $.post("<%=request.getContextPath()%>/wmh/userManage/web/adminLogin.htm",
						 {account:account, password:pwd},
							function(response) {
								var data = JSON.parse(response);
								if (data.success) {
									window.location.href = "<%=request.getContextPath()%>/wmh/userManage/web/goIndex.htm";
								}else{
									alert("账号或密码错误");
								}
							});	 
							<%-- $.ajax({
						        url: "<%=request.getContextPath()%>/wmh/userManage/web/adminLogin.htm",
						        success: function (data) {
						            var data = JSON.parse(data);
						            if (data.success) {
						            	window.location.href ='<%=request.getContextPath()%>/wmh/userManage/web/goIndex.htm';
									}else{
										alert("账号或密码错误");
									}
						        }
						    }); --%>
			}
		}

	</script>
</body>
</html>