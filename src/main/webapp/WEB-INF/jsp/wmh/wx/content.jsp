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
    <title>个人服务中心</title>
    <script type="text/javascript" src="http://cdn.hcharts.cn/jquery/jquery-1.8.3.min.js"></script>
    <style>
        html,body{padding: 0;margin: 0;}
    </style>
</head>
<body>
<iframe width="100%" height="200" frameborder="0" id="login"></iframe>
<iframe width="0" height="0" frameborder="0" id="loginout"></iframe>
<script type="text/javascript">
    var turl='http://218.29.223.194:43239/dmm_cas/login?username=2004023&password=c701b231eb5b3753163c5be29b5f3a59_external&auto=true&autoType=wechat&service=http://218.29.223.194:43236/personal/teacher/index.jsp';
    var surl='http://218.29.223.194:43239/dmm_cas/login?username=521507110318&password=925b6c0757191061930309f4bf785abb_external&auto=true&autoType=wechat&service=http://218.29.223.194:43236/personal/student/index.jsp';
    var ourl='http://218.29.223.194:43239/dmm_cas/logout';

    function getQueryString(name) {
        var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
        var r = window.location.search.substr(1).match(reg);
        if (r != null) return unescape(r[2]); return null;
    }
    $(function(){

        $("#login").height($(window).height() );
        $(window).resize(function() {
            $("#login").height($(window).height());
        });

        $("#loginout").attr("src",ourl);
        if('${type}'=="t"){
            $("#login").attr("src",turl);
        }
        if('${type}'=="s"){
            $("#login").attr("src",surl);
        }
    });


</script>
</body>
</html>