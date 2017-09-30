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
    <title>验证手机号</title>
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/resource/wmh/wx/commons/css/style.css"  />
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/resource/wmh/wx/commons/css/font/iconfont.css"  />
    <script type="text/javascript" src="http://cdn.hcharts.cn/jquery/jquery-1.8.3.min.js"></script>
</head>
<body>
<div class="header"></div>
<div class="content">
    <div class="input_list" style='padding-bottom:0'>
    <input type="hidden" id="phone" value="${phone}">
        <div style='padding-left:1.4rem'>
            手机号：${phone }
        </div>
        <div class="flex_inline" style='margin-top:0;'>
            <input type="tel" id="code" maxlength="6" placeholder="请输入验证码" style='width:50%;' />
            <a class="btn_sm btn_orange" href="javascript:void(0);" id="sendCode" onclick="sendCode()">获取验证码</a>
        </div>
    </div>
    <div class="plr2 mt5"><a href="javascript:void(0);" class="btn_block btn_blue" id="next" onclick="yanzheng()">下一步</a></div>
</div>
<div class="footer"></div>
<script type="text/javascript">
	function sendCode(){
		var sendBtn = $("#sendCode");
		sendBtn.attr("onclick", "");
		var phone = $("#phone").val();
		var regPhone = /^1[3|4|5|7|8]\d{9}$/;
		var time = 59;
		
		if(phone == null || phone.trim() == '') {
			 alert("请输入手机号");
			 sendBtn.attr("onclick","sendCode()");
			 return;
		 }else if(!phone.match(regPhone)) {
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
		
		$.post(
				"<%=request.getContextPath()%>/wmh/wmhUser/wx/sendMsgCode.htm", 
				{new_phone:phone},
				 function (data) {
					 var returnJson = eval("("+data+")");
					 if(returnJson.code == 1){
						 alert(returnJson.msg);
					 } else{
						 alert(returnJson.msg);
						 window.clearInterval(id);
						sendBtn.html("获取验证码");
						sendBtn.attr("onclick","sendCode()");
					 }
				 }
		)
	}



	function yanzheng(){
		var phone = $("#phone").val();
		var code = $("#code").val();
		var regPhone = /^1[3|4|5|7|8]\d{9}$/;
/* 		if(phone == null || phone.trim() == '') {
			 alert("请输入手机号");
			 return;
		 }else if(!phone.match(regPhone)) {
			 alert("手机号格式不正确");
			 return;
		 }
 */		if(code == null || code.trim() == '') {
			alert("请输入你收到的验证码");
			return;
		}
		//去验证所输入的手机号和验证码是否正确
		$.post("<%=request.getContextPath()%>/wmh/wmhUser/wx/yzPhone.htm",{phone:phone,code:code},
				function (data){
					var returnJson = JSON.parse(data);
					if(returnJson.code == 0){
						//进行操作，跳转到绑定操作部分
						/* $(".tab .cur").removeClass("cur").next().addClass("cur");
			            $(".box").css("left",'-100%'); */
			            window.location.href="<%=request.getContextPath()%>/wmh/wmhUser/wx/goUpdatePhonePage.htm";
					}else{
						alert(returnJson.msg);
					}
		})
	}
</script>
</body>
</html>