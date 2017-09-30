<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html lang="en" >
<head>
    <meta http-equiv="Content-type" content="text/html; charset=utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge, chrome=1">
    <meta name="renderer" content="webkit">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=0, minimal-ui">
    <meta http-equiv="Access-Control-Allow-Origin" content="*">
    <meta name="apple-mobile-web-app-capable" content="yes"/>
    <meta name="apple-mobile-web-app-status-bar-style" content="black"/>
    <title>修改手机号</title>
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/resource/wmh/wx/commons/css/style.css?v=22"  />
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/resource/wmh/wx/commons/css/font/iconfont.css"  />
    <script type="text/javascript" src="http://cdn.hcharts.cn/jquery/jquery-1.8.3.min.js"></script>
</head>
<body>
<div class="header"></div>
<div class="content">
    <div class="input_list">
        <div>
            <input type="tel" id="new_phone" placeholder="请输入新手机号" />
        </div>
        <div class="flex_inline">
            <input type="tel" id="new_code" maxlength="6" placeholder="请输入验证码" />
            <a href="javascript:void(0);" id="newSendCode" onclick="newSendCode()" class="btn_sm btn_orange">获取验证码</a>
        </div>
    </div>
    <div class="plr2 mt5"><a class="btn_block btn_blue" href="javascript:void(0);" onclick="changePhone()">保存</a></div>
</div>
<div class="footer"></div>
<script type="text/javascript">
	function newSendCode(){
		var sendBtn = $("#newSendCode");
		sendBtn.attr("onclick", "");
		var phone = $("#new_phone").val();
		var regPhone = /^1[3|4|5|7|8]\d{9}$/;
		var time = 59;
		
		if(phone == null || phone.trim() == '') {
			 alert("请输入手机号");
			 sendBtn.attr("onclick","newSendCode()");
			 return;
		 }else if(!phone.match(regPhone)) {
			 alert("手机号格式不正确");
			 sendBtn.attr("onclick","newSendCode()");
			 return;
		 }
		 var id = setInterval(function(){
			sendBtn.html(time+"秒后重试");
			time--;
			if(time<=0){
				window.clearInterval(id);
				sendBtn.html("获取验证码");
				sendBtn.attr("onclick","newSendCode()");
			}
		 },1000);
		
		$.post(
				"<%=request.getContextPath()%>/wmh/wmhUser/wx/sendMsgCode.htm", 
				{new_phone:phone},
				 function (data) {
					 var returnJson = eval("("+data+")");
					 if(returnJson.code == 1){
					 } else{
						 alert(returnJson.msg);
						 window.clearInterval(id);
						sendBtn.html("获取验证码");
						sendBtn.attr("onclick","newSendCode()");
					 }
				 }
		)
	}
	
	function changePhone(){
		var phone = $("#new_phone").val();
		var code = $("#new_code").val();
		var regPhone = /^1[3|4|5|7|8]\d{9}$/;
		if(phone == null || phone.trim() == '') {
			 alert("请输入手机号");
			 return;
		 }else if(!phone.match(regPhone)) {
			 alert("手机号格式不正确");
			 return;
		 }
		if(code == null || code.trim() == '') {
			alert("请输入你收到的验证码");
			return;
		}
		//去验证所输入的手机号和验证码是否正确,正确则修改成功
		$.post("<%=request.getContextPath()%>/wmh/wmhUser/wx/changPhone.htm",
				{phone:phone,code:code},
			function (data){
				var returnJson = JSON.parse(data);
				if(returnJson.code == 0){
					//修改成功跳转到已绑定页面
					alert(returnJson.msg);
					window.location.href="<%=request.getContextPath()%>/wmh/wmhUser/wx/userInfo.htm";
					}else{
						alert(returnJson.msg);
					}
		})
	}
</script>
</body>
</html>