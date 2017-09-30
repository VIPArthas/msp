<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en" style="background-color: #f3f3f3">
<head>
    <meta http-equiv="Content-type" content="text/html; charset=utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge, chrome=1">
    <meta name="renderer" content="webkit">
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=0, minimal-ui">
    <meta http-equiv="Access-Control-Allow-Origin" content="*">
    <meta name="apple-mobile-web-app-capable" content="yes"/>
    <meta name="apple-mobile-web-app-status-bar-style" content="black"/>
    <title>消息推送历史</title>
    <link rel="stylesheet" href="<%=request.getContextPath() %>/resource/wmh/wx/manage/commons/css/mui.min.css">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/resource/wmh/wx/manage/commons/css/style.css"/>
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/resource/wmh/wx/manage/commons/css/font/iconfont.css"/>
    <script type="text/javascript" src="<%=request.getContextPath() %>/resource/wmh/wx/manage/commons/js/jquery-3.1.1.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath() %>/resource/wmh/wx/manage/commons/js/rem.js"></script>
    <style>

    </style>
</head>

<body style="" id="history">
<div id="pullrefresh" class="mui-content mui-scroll-wrapper" style="padding-bottom: 0.8rem">
    <div class="mui-scroll">
        <!--数据列表-->
        <div class="mui-table-view mui-table-view-chevron">
            <!-- <section class="history mui-table-view-cell">
                <div class="history-title"><span class="huiyi">[会议通知]</span>您有一个会议需要参加，会议名称666。您有一个会议需要参加，会议名称666</div>
                <div class="history-send">发送方式：<span>微信公众平台&nbsp;&nbsp;邮件&nbsp;&nbsp;短信</span></div>
                <div class="history-man">收件人：<span>男&nbsp;3人</span><span>女&nbsp;3人</span></div>
            </section>
            <section class="history mui-table-view-cell">
                <div class="history-title"><span class="xinzi">[薪资发放]</span>您有一个会议需要参加，会议名称666。您有一个会议需要参加，会议名称666</div>
                <div class="history-send">发送方式：<span>微信公众平台&nbsp;&nbsp;邮件&nbsp;&nbsp;短信</span></div>
                <div class="history-man">收件人：<span>男&nbsp;3人</span><span>女&nbsp;3人</span></div>
            </section> -->
        </div>
    </div>
</div>
<script src="<%=request.getContextPath() %>/resource/wmh/wx/manage/commons/js/mui.min.js"></script>
<script>
    mui.init({
        gestureConfig:{
            doubletap:true
        },
        pullRefresh: {
            container: '#pullrefresh',
            up: {
                contentrefresh: '正在加载...',
                callback: pullupRefresh
            }
        }
    });
    var currentPage = 0;
    var totalPageSize = ${totalPage};
    function pullupRefresh() {
        setTimeout(function () {
            mui('#pullrefresh').pullRefresh().endPullupToRefresh((++currentPage > totalPageSize)); //参数为true代表没有更多数据了  初始加载的不计入count  5代表可上拉的次数，即总数据/10向上取整。
            var table = document.body.querySelector('.mui-table-view');
            //var cells = document.body.querySelectorAll('.mui-table-view-cell');
			$.post(
				"<%=request.getContextPath() %>/wmh/message/web/goMessagesListData.htm",
		   		{
					currentpage:currentPage
				},
		   		function(response){
					var res = JSON.parse(response);
					var list = res.list;
					for (var i = 0;  i < list.length; i++){
						var msg = list[i];
						var sec = document.createElement('section');
		                sec.className = 'mui-table-view-cell history';
		                
		                var sendWays = "";
		                var wxSend = msg.wxSend;
		                if(wxSend == 1){
		                	sendWays = "微信公众平台&nbsp;&nbsp;";
		                }
		                var smsSend = msg.smsSend;
		                if(smsSend == 1){
		                	sendWays = sendWays + "短信&nbsp;&nbsp;";
		                }
		                var mailSend = msg.mailSend;
		                if(mailSend == 1){
		                	sendWays = sendWays + "邮件";
		                }
		                var tagSpan = ""; 
		                var tagList = msg.tagList;
		                for(var j = 0;  j < tagList.length; j++){
		                	tagSpan = tagSpan + "<span>" + tagList[j] + "</span>";
		                }
		                if(msg.templateType==1){
		                	sec.innerHTML = '<div class="history-title"><span class="huiyi">[会议通知]</span>您有一个会议需要参加，会议名称:"'+msg.parm2+'"会议时间:"'+msg.parm3+'"会议地点："'+msg.parm4+'"会议介绍："'+msg.parm5+'"</div>'
	                        + '<div class="history-send">发送方式：<span>'+sendWays+'</span></div>'
	                        + '<div class="history-man">收件人：'+tagSpan+'</div>';
		                }else if(msg.templateType==2){
		                	sec.innerHTML = '<div class="history-title"><span class="huiyi">[薪资发放]</span>您好，您的'+msg.parm2+'工资已经发放,应发工资'+msg.parm3+'元,实发工资'+msg.parm4+'元</div>'
	                        + '<div class="history-send">发送方式：<span>'+sendWays+'</span></div>'
	                        + '<div class="history-man">收件人：'+tagSpan+'</div>';
		                }
		                
		                table.appendChild(sec);
					}
		   		}	
		    );
        }, 10);
    }
        if (mui.os.plus) {
            mui.plusReady(function () {
                setTimeout(function () {
                    mui('#pullrefresh').pullRefresh().pullupLoading();
                }, 10);

            });
        } else {
            mui.ready(function () {
                mui('#pullrefresh').pullRefresh().pullupLoading();
            });
        }
</script>
</body>
</html>