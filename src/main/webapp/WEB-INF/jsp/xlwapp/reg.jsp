<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=0">
    <title>注册</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/resource/xlwapp/css/fonts/iconfont.css"/>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/resource/xlwapp/css/css.css"/>
	<link rel="stylesheet" href="<%=request.getContextPath()%>/resource/xlwapp/css/style.css"/>
	<link rel="stylesheet" href="<%=request.getContextPath()%>/resource/xlwapp/css/weui.css">
	<script type="text/javascript" src="<%=request.getContextPath()%>/resource/xlwapp/js/zepto.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/resource/xlwapp/js/validation_a.js"></script>
	<link rel="Shortcut Icon" href="<%=request.getContextPath()%>/resource/xlwapp/images/study.ico" />
</head>
<body class="regbg">
<div class="login_logo"><img width="160" src="<%=request.getContextPath()%>/resource/xlwapp/images/logo.png"></div>
   	<form action="<%=request.getContextPath()%>/common/userRegister/wx/saveUser.htm" method="post" id="regForm">
	<div class="logink regk">
		<ul>
			<li><span class="iconfont icon-lineohonepay"></span><input type="text" id="phone" name="phone" placeholder="请输入手机号" onkeypress="onlyNumber(event);" check-type="required mobile" maxlength="11"></li>
			<li><span class="iconfont icon-lineuser"></span><input type="text" id="name" name="realName" placeholder="请输入真实姓名" check-type="required chinese" chinese-message="请输入汉字姓名" maxlength="50"></li>
			<li><span class="iconfont icon-lineyiy"></span><a style="float:right;  margin-top:10px; background:#FF8C00" class="getyzm" href="javascript:void(0);" id="sendCode" onclick="sendCode();" onkeypress="onlyNumber(event);" check-type="required number">获取验证码</a><input id="code" name="code" style="width:50%" type="text" placeholder="请输入验证码" maxlength="6"></li>
			<li><span class="iconfont icon-linelock"></span><input id="password" name="password" type="password" placeholder="请设置密码" onkeypress="charAndNumber(this, event);" check-type="required charAndNumber" required-message="请输入密码" minlength="6" charAndNumber-message="请输入6-12位数字加字母的密码" maxlength="12"></li>
		</ul>
		<div class="regkc">
		<div class="xieyi"><input id="tkCheckbox" type="checkbox" checked="checked">已阅读并同意<a href="javascript:void(0);">《校联网注册条款》</a></div>
		<div class="clear"></div>
		<div class="loginbtnk">
			<a class="loginbtn regbtn" href="javascript:void(0);" id="sub" onclick="register();">注 册</a>
		</div>
		</div>
	</div>
	</form>
<script type="text/javascript" src="<%=request.getContextPath()%>/resource/xlwapp/js/base64.js"></script>
 <script>
	 $("#regForm").validation(
		function(obj,em){}
		,{reqmark:false}
	 );

	 function register() {
		 var flag = $("#regForm").isSubmitFlag($("#regForm"));
		 if(flag){
		 	var tkCheckbox = $("#tkCheckbox").prop("checked");
		 	if(!tkCheckbox){
		 		alert_t("请同意协议并勾选");
		 		return;
		 	}
		 	var password = $("#password").val();
		 	var base64 = new Base64();
            password = base64.encode(password);
			 $("#sub").attr("onclick", "");
			 $.post(
					 "<%=request.getContextPath()%>/common/userRegister/wx/saveUser.htm",
					 {
						 phone:$("#phone").val(),
						 realName: $("#name").val(),
						 code:$("#code").val(),
						 password: password
					 },
					 function(data) {
						 var returnJson = JSON.parse(data);
						 if(returnJson.code == 1) {
							 conform_x("提示","注册成功，点击确定去登陆",function() {
								 window.location.href = "<%=request.getContextPath()%>/common/userLogin/wx/toLogin.htm"
							 });
							 $("#sub").attr("onclick", "register();");
						 }else{
							 alert_t(returnJson.msg);
							 $("#sub").attr("onclick", "register();");
						 }
					 }
			 );

		 }
	 }


	 function sendCode() {
	 	var sendBtn = $("#sendCode");
	 	sendBtn.attr("onclick", "");
		 var phone = $("#phone");
		 var regPhone = /^1[3|4|5|7|8]\d{9}$/;
		 var time = 59;
		 if(phone.val() == null || phone.val().trim() == '') {
			 alert_t("请输入手机号");
			 sendBtn.attr("onclick","sendCode()");
			 return;
		 }else if(!phone.val().match(regPhone)) {
			 alert_t("手机号格式不正确");
			 sendBtn.attr("onclick","sendCode()");
			 return;
		 }
		 var id = setInterval(function(){
			sendBtn.html(time+"秒后重试");
			time--;
			if(time<=0){
				window.clearInterval(id);
				sendBtn.html("获取验证码");
				sendBtn.attr("onclick","sendCode()");
			}
		 },1000);
		 $.post(
				 "<%=request.getContextPath()%>/common/userRegister/wx/sendMsgCode.htm", {new_phone:phone.val(),flag:"1"},
				 function (data) {
					 var returnJson = eval("("+data+")");
					 if(returnJson.code == 1){
					 } else{
						 alert_t(returnJson.msg);
						 window.clearInterval(id);
						sendBtn.html("获取验证码");
						sendBtn.attr("onclick","sendCode()");
					 }
				 }
		 )
	 }

 </script>
</body>
</html>
