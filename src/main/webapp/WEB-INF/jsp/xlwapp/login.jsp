<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=0">
    <title>登录</title>
    <link rel="Shortcut Icon" href="<%=request.getContextPath()%>/resource/xlwapp/images/study.ico" />
    <link rel="stylesheet" href="<%=request.getContextPath()%>/resource/xlwapp/css/fonts/iconfont.css"/>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/resource/xlwapp/css/css.css"/>
	<link rel="stylesheet" href="<%=request.getContextPath()%>/resource/xlwapp/css/style.css"/>
	<link rel="stylesheet" href="<%=request.getContextPath()%>/resource/xlwapp/css/weui.css">
	<script type="text/javascript" src="<%=request.getContextPath()%>/resource/xlwapp/js/zepto.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/resource/xlwapp/js/validation_a.js"></script>
</head>
<body class="logbg">
<div class="login_logo"><img width="160" src="<%=request.getContextPath()%>/resource/xlwapp/images/logo.png"></div>
   <div class="login">
	   <form action="<%=request.getContextPath()%>/common/userLogin/wx/login.htm" method="post" id="login1">
		   <div class="logink">
				<ul>
					<li><span class="iconfont icon-lineuser"></span><input type="text" placeholder="请输入手机号" name="phone" id="phone" onkeypress="onlyNumber(event);" check-type="required mobile" check-type="required" maxlength="11" minlength="11"></li>
					<li><span class="iconfont icon-linelock"></span><input type="password" placeholder="请输入密码" name="password" id="password" onkeypress="charAndNumber(this, event);" check-type="required charAndNumber" required-message="请输入密码" minlength="6" maxlength="12" charAndNumber-message="请输入6-12位数字加字母的密码"></li>
				</ul>
				<a class="findpassword" href="<%=request.getContextPath()%>/common/userLogin/wx/toFindPassword.htm">找回密码</a>
				<div class="clear"></div>
				<div class="loginbtnk">
					<a class="loginbtn fl" href="javascript:void(0);" onclick="login();">登 录</a>
					<a class="loginbtn bg2 fr" href="<%=request.getContextPath()%>/common/userRegister/wx/reg.htm?platformId=1">注册新用户</a>
				</div>

		   </div>
	   </form>
	<div class="clear"></div>
   </div>
   <div class="login_footer">登录或注册表示同意<a href="javascript:void(0);">用户协议</a> </div>
<script type="text/javascript" src="<%=request.getContextPath()%>/resource/xlwapp/js/base64.js"></script>
<script>
	$("#login1").validation(function(obj,em){
		console.log(obj,em);
	}
		,{reqmark:false}
	);
	function login() {
		var flag = $("#login1").isSubmitFlag($("#login1"));
		if(flag){
			var password = $("#password").val();
		 	var base64 = new Base64();
            password = base64.encode(password);
			$.post(
					"<%=request.getContextPath()%>/common/userLogin/wx/login.htm",
					{
						phone: $.trim($("#phone").val()),
						password: password
					},
					function(data) {
						var returnJson = JSON.parse(data);
						if(returnJson.code == 1) {
							
							var refererUrl = returnJson.refererUrl;
							var notLoginPage = returnJson.notLoginPage;
							if("yes"==notLoginPage){
								window.location.href = refererUrl;
							}else{
								window.location.href = "<%=request.getContextPath()%>/common/index/wx/toIndex.htm";	
							}
						}else{
							alert_t(returnJson.msg);
						}
					}
			);
		}
	}
</script>
</body>
</html>