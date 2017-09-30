<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=0">
    <title>找回密码</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/resource/xlwapp/css/fonts/iconfont.css"/>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/resource/xlwapp/css/css.css"/>
	<link rel="stylesheet" href="<%=request.getContextPath()%>/resource/xlwapp/css/style.css"/>
	<link rel="stylesheet" href="<%=request.getContextPath()%>/resource/xlwapp/css/weui.css">
	<script type="text/javascript" src="<%=request.getContextPath()%>/resource/xlwapp/js/zepto.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/resource/xlwapp/js/validation_a.js"></script>
	<link rel="Shortcut Icon" href="<%=request.getContextPath()%>/resource/xlwapp/images/study.ico" />
</head>
<body class="combg">
    <header>
            <div class="header_licon"><a href="<%=request.getContextPath()%>/common/index/wx/toIndex.htm" onclick="m(1)"><span class="iconfont icon-lineprev"></span></a></div>
            	 <h2>找回密码</h2>
            </header>
			 <div class="container">
			 <form id="find">
				 <div class="comform">
				  	 <dl>
					 	<dt>手机号</dt>
						<dd><input type="text" placeholder="请输入注册的手机号" id="phone" onkeypress="onlyNumber(event);" check-type="required mobile" maxlength="11"></dd>
					 </dl>
					  <dl>
					 	<dt>新密码</dt>
						<dd><input type="password" placeholder="请输入新密码" id="new_password" onkeypress="charAndNumber(this, event);"  check-type="required charAndNumber" minlength="6" charOrNumber-message="请输入6-12位数字加字母的密码" required-message="请输入密码" type="password" maxlength="12"></dd>
					 </dl>
					   <dl>
					 	<dt>确认新密码</dt>
						<dd> <input  type="password" placeholder="请再次输入密码" id="new_password_2" onkeypress="charAndNumber(this, event);"  check-type="required charAndNumber" minlength="6" charOrNumber-message="请输入6-12位数字加字母的密码" required-message="请输入密码" type="password" maxlength="12"></dd>
					 </dl>
					  <dl>
						  <dt>验证码</dt>
						  <dd><a style="float:right" class="getyzm" href="javascript:void(0);" id="sendCode" onclick="sendCode();">获取验证码</a><input style="width:50%" type="text" id="code" check-type="required" placeholder="请输入验证码" onkeypress="onlyNumber(event);" maxlength="6"></dd>
					  </dl>
			 	</div>
			 </form>
		  <a class="apply_btn bg2 m20" href="javascript:void(0)" onclick="findpass();" id="changePassword">确认修改</a>
			</div>
			 <div class="btmline"></div>
			<div class="overflowy"></div>
			<script type="text/javascript" src="<%=request.getContextPath()%>/resource/xlwapp/js/base64.js"></script>
			<script>
				function sendCode() {
					var sendBtn = $("#sendCode");
					sendBtn.attr("onclick", "");
					var phone = $("#phone").val();
					var regPhone = /^1[3|4|5|7|8]\d{9}$/;

					var time = 59;
					if(phone == null || phone.trim() == '') {
						alert_t("请输入手机号");
						sendBtn.attr("onclick","sendCode()");
						return;
					}else if(!phone.match(regPhone)) {
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
							"<%=request.getContextPath()%>/common/userRegister/wx/sendMsgCode.htm", {new_phone:phone,flag:"2"},
							function (data) {
								var returnJson = eval("("+data+")");
								console.log(returnJson);
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

				$("#find").validation(function(obj,em){
						}
						,{reqmark:false}
				);
				function findpass() {
					var flag = $("#find").isSubmitFlag($("#find"));
					if(flag){
						var new_password = $("#new_password").val();
						var new_password_2 = $("#new_password_2").val();
						var code = $("#code").val();
						var regPassword = /^[A-Za-z0-9]+$/;
						var phone = $("#phone").val();
						var regPhone = /^1[3|4|5|7|8]\d{9}$/;
						if(phone == null || phone.trim() == '') {
							alert_t("请输入注册用的手机号");
							return;
						} else if(!phone.match(regPhone)) {
							alert_t("手机号格式不正确");
							return;
						}
						if( null == new_password || new_password.trim() =='' ) {
							alert_t("请输入一个新密码");
							return;
						} else if( !new_password.match(regPassword) ) {
							alert_t("请输入6-12位数字加字母的密码");
							return;
						}
						if(null == new_password_2 || new_password_2.trim() =='' ) {
							alert_t("请再次输入新密码");
							return;
						}else if( !new_password_2.match(regPassword) ) {
							alert_t("请输入6-12位数字加字母的密码");
							return;
						}
						if( null == code || code.trim() == '') {
							alert_t("请输入收到的验证码");
							return;
						}

						if(new_password.trim() != new_password_2.trim()) {
							alert_t("两次密码输入不一致");
							return;
						}
					 	var base64 = new Base64();
			            new_password = base64.encode(new_password);
			            new_password_2 = base64.encode(new_password_2);
						$.post(
								"<%=request.getContextPath()%>/common/userLogin/wx/findPassword.htm",
								{
									new_password : new_password,
									new_password_2 : new_password_2,
									code : code
								},
								function(data) {
									var returnJson = JSON.parse(data);
									if (returnJson.code == 1) {
										conform_x("成功","修改成功，点击确定去登陆",function() {
											window.location.href = "<%=request.getContextPath()%>/common/userLogin/wx/toLogin.htm"
										});

									}else{
										alert_t(returnJson.msg);
									}
								}
						)
					}
				}

			</script>
	</body>
</html>
