<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html lang="en">
<head>
    <meta http-equiv="Content-type" content="text/html; charset=utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge, chrome=1">
    <meta name="renderer" content="webkit">
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=0, minimal-ui">
    <meta http-equiv="Access-Control-Allow-Origin" content="*">
    <meta name="apple-mobile-web-app-capable" content="yes"/>
    <meta name="apple-mobile-web-app-status-bar-style" content="black"/>
    <title>爱心捐款</title>
	<style>
	.remark{
		border:none;
		color:#a8a8a8;
		text-align: right;
		height:2rem;
		width:20rem;
		font-size:1.3rem;
		background:white;
	}
	</style>
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/resource/wmh/wx/commons/css/style.css?v=2"/>
    <link rel="stylesheet" type="text/css"
          href="<%=request.getContextPath() %>/resource/wmh/wx/commons/css/font/iconfont.css"/>
    <script type="text/javascript" src="http://cdn.hcharts.cn/jquery/jquery-1.8.3.min.js"></script>
</head>
<body>
<div class="header"></div>
<div class="content">
    <div class="me r0 bnone">
        <dl><a href="javascript:void(0)">
            <dt><span>订单号：</span></dt>
            <dd>${orderInfo.payNum }</dd>
        </a></dl>
        <dl><a href="javascript:void(0)">
            <dt><span>提交时间：</span></dt>
            <dd>${orderInfo.submitTime }</dd>
        </a></dl>
        <dl><a href="javascript:void(0)">
            <dt><span>提交人：</span></dt>
            <dd>${orderInfo.realName }</dd>
        </a></dl>
        <dl><a href="javascript:void(0)">
            <dt><span>支付项目：</span></dt>
            <dd>爱心捐款</dd>
        </a></dl>
        <dl><a href="javascript:void(0)">
            <dt><span>支付金额：</span></dt>
            <dd><input type="text" class="itext_sm" id="money"/> 元</dd>
        </a></dl>
		<dl><a href="javascript:void(0)">
            <dt><span>备注：</span></dt>
            <dd><input type="text" placeholder="点击添加" class="remark"/></dd>
        </a></dl>
    </div>
    <div class="plr2 mt5"><a class="btn_block btn_blue"
                             href="javascript:voide(0);" onclick="zhiFu
            ('${orderInfo.orderId}','${orderInfo.successUrl}','${orderInfo.failedUrl}');
            ">立即支付</a></div>
</div>
<div class="footer"></div>
<script>
    function zhiFu(orderId, successUrl, failedUrl) {
		var remark = $(".remark").val();
        var rechargeMoney= $("#money").val();
        window.location.href = "<%=request.getContextPath()%>/common/userRecharge/wx/zfForWmh.htm?successUrl=" + successUrl +"&failedUrl=" + failedUrl + "&orderId=" + orderId + "&rechargeMoney=" +rechargeMoney +"&remark=" + remark;
    }

</script>
</body>
</html>