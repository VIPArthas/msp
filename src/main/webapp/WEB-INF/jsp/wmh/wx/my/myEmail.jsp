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
    <title>修改邮箱</title>
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/resource/wmh/wx/commons/css/style.css"  />
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/resource/wmh/wx/commons/css/font/iconfont.css"  />
    <script type="text/javascript" src="http://cdn.hcharts.cn/jquery/jquery-1.8.3.min.js"></script>
</head>
<body >
<div class="header"></div>
<div class="content">
    <div class="input_list" style='padding-bottom:0'>
        <div>
            <input style='padding-left:1.4rem' type="text" id="email" value="${user.mail}" />
        </div>
        <div class="flex_inline" style='margin-top:0;'>
            <input type="text" placeholder="验证码" id="code" style='width:50%;'/>
            <a class="btn_sm btn_orange"  id="sendCode" onclick="sendCode()">获取验证码</a>
        </div>
    </div>
    <div class="plr2 mt5"><a class="btn_block btn_blue"  href="javascript:void(0);"  onclick="updateEmail()">保存</a></div>
</div>
<div class="footer"></div>
</body>
<script type="text/javascript">
	function sendCode(){	
		var sendBtn = $("#sendCode");
		sendBtn.attr("onclick", "");
		var new_mail = $("#email").val();			
		/* var regEmail = /^([a-z0-9A-Z]+[-|_|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$/; */
		var regEmail = /^(\w-*\.*)+@(\w-?)+(\.\w{2,})+$/;
		var time = 59;
		
		if(new_mail == null || new_mail.trim() == '') {
			 alert("请输入邮箱号");
			 sendBtn.attr("onclick","sendCode()");
			 return;
		 }else if(!new_mail.match(regEmail)) {
			 alert("邮箱格式不正确");
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
				"<%=request.getContextPath()%>/wmh/wmhUser/wx/sendMailCode.htm", {mail:new_mail,flag:"1"},
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
	function updateEmail(){
		var mail = $("#email").val();
		var code = $("#code").val();
		var regEmail = /^(\w-*\.*)+@(\w-?)+(\.\w{2,})+$/;
		
		if(mail == null || mail.trim() == '') {
			 alert("请输入邮箱号");
			 return;
		 }else if(!mail.match(regEmail)) {
			 alert("邮箱格式不正确");
			 return;
		 }
		if(code == null || code.trim() == '') {
			alert("请输入你收到的验证码");
			return;
		}
		
		$.post("<%=request.getContextPath()%>/wmh/wmhUser/wx/updateUserMail.htm",{mail:mail,code:code},
			function (data){
				var data = JSON.parse(data);
				if(data.code ==0){
					alert(data.msg);
					window.location.href="<%=request.getContextPath()%>/wmh/wmhUser/wx/userInfo.htm";
				}else{
					alert(data.msg);
				}
			}		
		)
	}
</script>
</html>