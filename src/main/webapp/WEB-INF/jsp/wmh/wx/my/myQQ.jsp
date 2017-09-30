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
    <title>我的QQ</title>
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/resource/wmh/wx/commons/css/style.css"  />
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/resource/wmh/wx/commons/css/font/iconfont.css"  />
    <script type="text/javascript" src="http://cdn.hcharts.cn/jquery/jquery-1.8.3.min.js"></script>
</head>
<body >
<div class="header"></div>
  <div class="content">
    <div class="input_list">
        <div>
            <input type="text" id="qq" value="${user.qq}" maxlength="15" placeholder="请输入新的QQ号" />
        </div>
    </div>
    <div class="plr2 mt5"><a class="btn_block btn_blue" href="javascript:void(0);" onclick="save()">保存</a></div>
   </div>
<div class="footer"></div>
<script type="text/javascript">
	function save(){
		var qq = $("#qq").val();
		if(qq != null && qq!= 'undefind' && qq!=''){
			if(!/^[1-9][0-9]{4,14}$/.test(qq)){
				alert("qq号码格式不正确");
				return;
			}
		}
		$.post("<%=request.getContextPath()%>/wmh/wmhUser/wx/updateUserQq.htm",{qq:qq},
				function(response){
					var jsonData = JSON.parse(response);
					if(jsonData.code == 0){
						//修改成功跳转到已绑定页面
						alert(jsonData.msg);
						window.location.href="<%=request.getContextPath()%>/wmh/wmhUser/wx/userInfo.htm";
					}else{
						alert("QQ号修改失败！");
					}
		});
	}
</script>
</body>
</html>