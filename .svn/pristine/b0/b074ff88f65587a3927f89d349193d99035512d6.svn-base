<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
    <title>新闻详情</title>
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/resource/wmh/wx/manage/commons/css/style.css"  />
    <script type="text/javascript" src="http://cdn.hcharts.cn/jquery/jquery-1.8.3.min.js"></script>
</head>
<body style="background-color: white;height: 100%;">
<div class="header"></div>
<div class="content">
    <article>
        <h3>${news.newsTitle }<sub>来源：${news.sourceDiv } 时间：${news.createTime }</sub></h3>
        <p>
        <c:out value="${news.newsContent}"  escapeXml="false" />
        </p>
        <div class=" footer" >编辑：${news.bottomText }</div>
    </article>

</div>
<div class="footer"></div>

</body>
</html>