<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta name="keywords" content="校联网,微门户"/>
    <script>
        var urlPix = "<%=request.getContextPath()%>";
    </script>
    <script type="application/x-javascript"> addEventListener("load", function () {
        setTimeout(hideURLbar, 0);
    }, false);
    function hideURLbar() {
        window.scrollTo(0, 1);
    } </script>
    <title>校联网微门户-后台管理</title>
    <link href="<%=request.getContextPath() %>/resource/wmh/web/commons/css/font/iconfont.css" rel="stylesheet">
    <link href="http://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
    <link href="<%=request.getContextPath() %>/resource/wmh/web/commons/css/style.css" rel="stylesheet" type="text/css"
          media="all"/>
    <script src="http://libs.baidu.com/jquery/1.9.1/jquery.min.js"></script>
    <script src="http://cdn.bootcss.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
    <script src="<%=request.getContextPath() %>/resource/wmh/web/commons/js/public.js"></script>
</head>
<body>
<!--标签页开始-->
<div class="tabs">
    <div class="row">
        <div class="col-md-2 col-sm-3 co-xs-6">选择模板</div>
        <div class="col-md-10 col-sm-9 co-xs-6"></div>
    </div>
</div>
<!--标签页结束-->
<br>

<form class="form-horizontal">
    <div class="form-group">
        <span class="col-md-2 col-sm-2 control-label text-black">模板选择：</span>

        <div class="col-md-6 col-sm-10">
            <input type="text" readonly="readonly" class="form-control" value="薪资发放通知">
        </div>
    </div>

    <div class="tab" id="tab1">
        <div class="form-group">
            <span class="col-md-2 col-sm-2 control-label text-black ">用户姓名：</span>

            <div class="col-md-6 col-sm-10">
                <input type="text" readonly="readonly" class="form-control" value="${realName}">
            </div>
        </div>
        <div class="form-group">
            <span class="col-md-2 col-sm-2 control-label">手机号：</span>

            <div class="col-md-6 col-sm-10">
                <input type="text" readonly="readonly" class="form-control" value="${phone}">
            </div>
        </div>
        <div class="form-group">
            <span class="col-md-2 col-sm-2 control-label text-black">工资年月：</span>

            <div class="col-md-6 col-sm-10">
                <input type="text" readonly="readonly" class="form-control" value="${msg.parm2}">
            </div>
        </div>
        <div class="form-group">
            <span class="col-md-2 col-sm-2 control-label">应付工资：</span>

            <div class="col-md-6 col-sm-10">
                <input type="text" readonly="readonly" class="form-control" value="${msg.parm3}">
            </div>
        </div>
        <div class="form-group">
            <span class="col-md-2 col-sm-2 control-label">实发工资：</span>

            <div class="col-md-6 col-sm-10">
                <input type="text" class="form-control" readonly="readonly" value="${msg.parm4}">
            </div>
        </div>
        <div class="form-group" id="form-group_1">
            <span class="col-md-2 col-sm-2 control-label">发送方式选择：</span>

            <div class="col-md-6 col-sm-10">
                <div class="c-group">
                    <input type="checkbox" readonly="readonly" id="wx1"  ${msg.wxSend==1?'checked':''} /><label></label><span>微信公众平台</span>
                </div>
                <div class="c-group">
                    <input type="checkbox" readonly="readonly" id="dx1"  ${msg.smsSend==1?'checked':''} />
                    <label></label><span>短信</span></div>
                <div class="c-group">
                    <input type="checkbox" readonly="readonly" id="yj1"  ${msg.mailSend==1?'checked':''}/>
                    <label></label><span>邮件</span></div>
            </div>
        </div>
    </div>

    <div class="form-group">
        <div class="col-md-offset-2 col-sm-offset-2 col-md-6 col-sm-10">
            <a class="btn btn_blue" href="javascript:void(0);" onclick="history.go(-1)" style="width: 100%;">确定</a>
        </div>
    </div>
</form>
<script src="<%=request.getContextPath() %>/resource/wmh/web/message/message_push.js"></script>
</body>
</html>