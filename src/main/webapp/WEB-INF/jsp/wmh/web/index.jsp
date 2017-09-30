<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge, chrome=1">
    <meta name="renderer" content="webkit">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=0, minimal-ui">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta http-equiv="Access-Control-Allow-Origin" content="*">
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">   
    <meta name="keywords" content="校联网,微门户" />
    <script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } </script>
    <title>校联网微门户-后台管理</title>
    <link href="<%=request.getContextPath() %>/resource/wmh/web/commons/css/font/iconfont.css" rel="stylesheet">
    <link href="http://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
    <link href="<%=request.getContextPath() %>/resource/wmh/web/commons/css/style.css" rel="stylesheet" type="text/css" media="all"/>
    <script src="http://libs.baidu.com/jquery/1.9.1/jquery.min.js"></script>
    <script src="http://cdn.bootcss.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
    <script src="<%=request.getContextPath() %>/resource/wmh/web/commons/js/public.js"></script>
 
</head>
<body>
<div class="navbar mybar">
<div class="mylogo"><img src="<%=request.getContextPath()%>/resource/wmh/web/commons/img/logo2.png"	alt=""></div>
<ul class="nav navbar-nav navbar-right myuser">
<li><a href="?"><i class="fa">&#xe607;</i> 通讯录管理</a></li>
<li><a href="?"><i class=fa>&#xe73f;</i> 管理员<i class=fa>&#xe758;</i></a>
<ul>
<li> <a href="<%=request.getContextPath() %>/wmh/userManage/web/goLogin.htm" class="dropdown-toggle"  aria-expanded="false"><i class="fa">&#xe603;</i> 注销</a></li>
</ul>
</li>
</ul>
</div>
<div class="page-container">
    <div class="left-content">
        <div class="mother-grid-inner">
            <!--header start here-->
    
            <!--heder end here-->
            <!-- script-for sticky-nav -->
            <!-- <script>
                $(document).ready(function() {
                    var navoffeset=$(".header-main").offset().top;
                    $(window).scroll(function(){
                        var scrollpos=$(window).scrollTop();
                        if(scrollpos >=navoffeset){
                            $(".header-main").addClass("fixed");
                        }else{
                            $(".header-main").removeClass("fixed");
                        }
                    });

                });
            </script> -->
            <!-- /script-for sticky-nav -->
            <!--inner block start here-->
            <div class="inner-block">
                <iframe id="myiframe" name="myiframe" src="<%=request.getContextPath() %>/wmh/userManage/web/goUserManageList.htm" scrolling="no" frameborder="0" width="100%" height="1000"></iframe>
 
            </div>
 
 
        </div>
    </div>
    <!--slider menu-->
    <div class="sidebar-menu">
 
        <ul class="menu">
        <li>我的应用</li>
         <li><a href="javascript:window.location.reload() "><i class="fa">&#xe618;</i>　通讯录</a></li>
        <li><a href="<%=request.getContextPath() %>/wmh/news/web/goNewsList.htm" target="myiframe"><i class="fa">&#xe618;</i>　综合新闻</a></li>
        <li><a href="<%=request.getContextPath() %>/wmh/message/web/toMessagePush.htm" target="myiframe"><i class="fa">&#xe6cf;</i>　消息推送</a>
        <ul>
        <li><a href="<%=request.getContextPath() %>/wmh/message/web/toMessagePush.htm" target="myiframe">发送消息</a></li>
        <li><a href="<%=request.getContextPath() %>/wmh/message/web/pushMessageHistory.htm" target="myiframe">查看历史消息</a></li>
        </ul>
        </li>
        <li><a href="<%=request.getContextPath() %>/msp/mspUser/web/goXiaoli.htm" target="myiframe"><i class="fa">&#xe676;</i>　校历</a></li>
        <li><a href="<%=request.getContextPath() %>/msp/mspUser/web/goSSP.htm" target="myiframe"><i class="fa">&#xe676;</i>　随手拍</a></li>
        <li><a href="/resource/msp/xiaoli/view.html" target="myiframe"><i class="fa">&#xe676;</i>　新校历</a></li>
        </ul>
 
    </div>
</div>
    
</body>
</html>