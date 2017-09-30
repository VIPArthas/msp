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
    <title>请输入口令</title>
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/resource/wmh/wx/commons/css/style.css"  />
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/resource/wmh/wx/commons/css/font/iconfont.css"  />
    <script type="text/javascript" src="http://cdn.hcharts.cn/jquery/jquery-1.8.3.min.js"></script>
</head>
<body>
<div class="header"></div>
<div class="content">
    <div class="input_list">
        <div>
            <input type="password" placeholder="请输入您的口令" id="word" name="word"/>
        </div>
    </div>
    <div class="plr2 mt5"><a class="btn_block btn_blue" onclick="checkWord()" href="javascript:void(0);">提交</a></div>
</div>
<div class="footer"></div>
<script type="text/javascript">
	function checkWord(){
		var word = $("#word").val();
		if(word == null || word == '' || word == undefined){
			alert("请输入口令~");
			return;
		}
		$.post("<%=request.getContextPath() %>/wmh/wmhUser/wx/checkWord.htm",
				{word:word},
				function(response){
					var data = JSON.parse(response);
					if(data.code ==0){//验证通过
						window.location.href="<%=request.getContextPath()%>/wmh/userManage/web/goIndex1.htm";
					}else{
						alert(data.msg);
					}
		});
		
	}
</script>
</body>
</html>