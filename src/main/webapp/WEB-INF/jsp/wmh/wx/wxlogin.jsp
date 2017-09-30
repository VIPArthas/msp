<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
	<head>
		<meta http-equiv="Content-type" content="text/html; charset=utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge, chrome=1">
        <meta name="renderer" content="webkit">
        <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=0, minimal-ui">
        <meta http-equiv="Access-Control-Allow-Origin" content="*">
        <meta name="apple-mobile-web-app-capable" content="yes"/>
        <meta name="apple-mobile-web-app-status-bar-style" content="black"/>
		<title></title>
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/resource/msp/wx/css/com.css"/>
		<script type="text/javascript"> var urlPix = "<%=request.getContextPath() %>";</script>
		<script src="<%=request.getContextPath() %>/resource/msp/wx/js/jquery-3.1.1.js" type="text/javascript" charset="utf-8"></script>
		<script src="<%=request.getContextPath() %>/resource/msp/wx/js/layer_mobile/layer.js" type="text/javascript" charset="utf-8"></script>
		<script src="<%=request.getContextPath() %>/resource/msp/wx/js/style.js" type="text/javascript" charset="utf-8"></script>
		<script src="<%=request.getContextPath() %>/resource/msp/wx/js/path.js" type="text/javascript" charset="utf-8"></script>
		
	</head>
	<body class="Land_bg">
		<div class="box">
			<div class="box-main Land_logo"></div>
		</div>
		<div class="box">
			<div class="box-main Land_text">
				<input class="input" type="text" name="" id="name" value="" placeholder="学号/职工号" />
				<input class="input" type="password" name="" id="password" value="" placeholder="密码" />
				<p class="go" onclick="login()">登录</p>
			</div>
		</div>
	</body>
</html>
<script src="<%=request.getContextPath() %>/resource/msp/wx/js/ajax.js" type="text/javascript" charset="utf-8"></script>
