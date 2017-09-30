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
    <title>绑定手机号</title>
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/resource/wmh/wx/commons/css/style.css"  />
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/resource/wmh/wx/commons/css/font/iconfont.css"  />
    <script type="text/javascript" src="http://cdn.hcharts.cn/jquery/jquery-1.8.3.min.js"></script>
</head>
<body>
<div class="header"></div>
<div class="content">
    <div class="input_list" style='padding-bottom:0'>
        <div>
            <input style='padding-left:1.4rem' type="tel" id="phone" placeholder="请输入手机号" />
        </div>
        <div class="flex_inline" style='margin-top:0;'>
            <input type="tel" placeholder="请输入验证码" maxlength="6" id="code" style='width:50%;'/>
            <a href="javascript:void(0);" class="btn_sm btn_orange" id="sendCode" onclick="sendCode()">获取验证码</a>
        </div>
    </div>
    <div class="plr2 mt5"><a class="btn_block btn_blue" href="javascript:void(0);" id="save" onclick="bindingPhone()">保存</a></div>
</div>
<div class="footer"></div>
<script type="text/javascript">
	function sendCode(){
		var sendBtn =$("#sendCode");
		sendBtn.attr("onclick","");
		var phone = $.trim($("#phone").val());
		var code = $("#code").val();
		var phoneFormat = /^1[3|4|5|7|8]\d{9}$/;
		var time = 59;
		
		if(phone == null || phone == ''){
			alert("请填写手机号");
			sendBtn.attr("onclick","sendCode()");
			return;
		}
		if(!phone.match(phoneFormat)) {
			 alert("手机号格式不正确");
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
		
		$.post("<%=request.getContextPath()%>/wmh/wmhUser/wx/sendMsgCode.htm",
				{new_phone:phone},
				function(data){
					var returnJson = eval("("+data+")");
					if(returnJson.code == 1){
						alert(returnJson.msg);
					}else{
						alert(returnJson.msg);
						window.clearInterval(id);
						sendBtn.html("获取验证码");
						sendBtn.attr("onclick","sendCode()");
					}
			
		});
	}


	function bindingPhone(){
	
		var saveBtn =$("#save");
		saveBtn.attr("onclick","");
		var phone = $.trim($("#phone").val());
		var code = $("#code").val();
		var phoneFormat = /^1[3|4|5|7|8]\d{9}$/;
		
		if(phone == null || phone == ''){
			alert("请填写手机号");
			saveBtn.attr("onclick","bindingPhone()");
			return;
		}
		if(!phone.match(phoneFormat)) {
			 alert("手机号格式不正确");
			 saveBtn.attr("onclick","bindingPhone()");
			 return;
		 }
		if(code == null || code.trim() == '') {
			alert("请输入你收到的验证码");
			saveBtn.attr("onclick","bindingPhone()");
			return;
		}
		$.post("<%=request.getContextPath()%>/wmh/wmhUser/wx/bandingPhone.htm",
				{phone:phone,code:code},
				function(data){
					saveBtn.attr("onclick","bindingPhone()");
					var returnJson = eval("("+data+")");
				
					if(returnJson.code == 0){
						if(returnJson.exist ==0){
							window.location.href="<%=request.getContextPath()%>/wmh/wmhUser/wx/userInfo.htm";
						}else{//到用户信息填写页面
							window.location.href="<%=request.getContextPath()%>/wmh/wmhUser/wx/goWriteInfoPage.htm?userId="+returnJson.userId;
						}
					}else{
						alert("该手机号微信端已绑定");
					}
		});
		
	}
</script>
</body>
</html>