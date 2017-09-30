<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html lang="en">
<head>
    <meta http-equiv="Content-type" content="text/html; charset=utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge, chrome=1">
    <meta name="renderer" content="webkit">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=0, minimal-ui">
    <meta http-equiv="Access-Control-Allow-Origin" content="*">
    <meta name="apple-mobile-web-app-capable" content="yes"/>
    <meta name="apple-mobile-web-app-status-bar-style" content="black"/>
    <title>个人中心</title>
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/resource/wmh/wx/commons/css/style.css?v=8"  />
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/resource/wmh/wx/commons/css/font/iconfont.css"  />
    <script type="text/javascript"> var urlPix = "<%=request.getContextPath() %>";</script>
    <script type="text/javascript" src="http://cdn.hcharts.cn/jquery/jquery-1.8.3.min.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath() %>/resource/wmh/wx/commons/js/rwdImageMaps.js"></script>
    <style >
        .me dl dt{width:2rem;}
        .me dl dd input{width: 100%;-webkit-appearance: none;border: 0;line-height: 2rem;color:#8A8A8A;text-indent: 6px;}
    </style>
</head>
<body>
<div class="header"></div>
<div class="content">
    <div class="me">
    	<input type="hidden" id="userId" value="${userId }">
        <dl class='tianxie-message'><a  class='bang_ding' href="javascript:void(0)"><dt><i class="iconfont icon-xingming1 t1"></i></dt><dd><input id="realName" name="realName" type="text" placeholder="请输入姓名" /></dd></a></dl>
        <dl class='tianxie-message'><a class='bang_ding' href="javascript:void(0)"><dt><i class="iconfont icon-youxiang t3"></i></dt><dd><input id="mail" name="mail" type="text" placeholder="请输入邮箱" /></dd></a></dl>
        <dl class='tianxie-message'><a class='bang_ding' href="javascript:void(0)"><dt><i class="iconfont icon-qq t1"></i></dt><dd><input id="qq" name="qq" type="text" placeholder="请输入QQ号" /></dd></a></dl>
    </div>
    <div class="plr2 mt5"><a class="btn_block btn_blue" href="javascript:void(0);" id="baocun" onclick="baocun()">完成</a></div>
</div>
<div class="footer"></div>
<script type="text/javascript">
	function baocun(){
		debugger;
		$("#baocun").attr("onclick","");
		var id = $("#userId").val();
		var realName = $.trim($("#realName").val());
		var mail = $.trim($("#mail").val());
		var qq = $.trim($("#qq").val());
		if(realName == null || realName ==undefined || realName == ''){
			alert("请输入姓名");
			$("#baocun").attr("onclick","baocun()");
			
		}
		else if(mail == null || mail ==undefined || mail == ''){
			alert("请输入邮箱");
			$("#baocun").attr("onclick","baocun()");
			
		}else{
			$.post("<%=request.getContextPath() %>/wmh/wmhUser/wx/updateWmhUserInfo.htm",
					{id:id,realName:realName,mail:mail,qq:qq},
					function(response){
						$("#baocun").attr("onclick","baocun()");
						var data = JSON.parse(response);
						if(data.code == 0){
							window.location.href="<%=request.getContextPath() %>/wmh/wmhUser/wx/userInfo.htm";
						}else{
							alert(data.msg);
						}
				
			});

		}
	
	}
</script>
</body>
</html>