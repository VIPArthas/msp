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
    <title>个人服务中心登录</title>
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/resource/wmh/wx/commons/css/style.css"  />
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/resource/wmh/wx/commons/css/font/iconfont.css"  />
    <script type="text/javascript" src="http://cdn.hcharts.cn/jquery/jquery-1.8.3.min.js"></script>
    <style>
        .menu .box a i{font-size: 4rem;width:60px;height:60px;line-height: 60px;}
    </style>
</head>
<body>
<div class="header"></div>
<h3 class="shenfen">请选择登录角色</h3>
<div class="content">
    <div class="menu">
        <div class="flex_inline">
            <div class="box"><a href="javascript:gotoLogin('t')"><i class="iconfont icon-teacher"></i><br><span>教师登录</span></a></div>
            <div class="box"><a href="javascript:gotoLogin('s')"><i class="iconfont icon-student"></i><br><span>学生登录</span></a></div>
        </div>
        </div>
        </div>
    </div>
</div>
<div class="footer"></div>
<iframe width="0" height="0" frameborder="0" id="loginout"></iframe>
<script type="text/javascript">
    function gotoLogin(f){
            if(f=='t'){
                window.location.href="<%=request.getContextPath() %>/wmh/wmhUser/wx/goContentPage.htm?type=t";
            }else if(f=='s') {
                window.location.href = "<%=request.getContextPath() %>/wmh/wmhUser/wx/goContentPage.htm?type=s";
            }
    }
</script>
</body>
</html>