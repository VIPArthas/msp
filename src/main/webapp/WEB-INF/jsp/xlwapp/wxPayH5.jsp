<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<html><head>
<meta http-equiv="content-type" content="text/html;charset=utf8">
<meta id="viewport" name="viewport" content="width=device-width; initial-scale=1.0; maximum-scale=1; user-scalable=no;">
<title>【微信支付V2.0】H5支付实例</title>
<!--
/****************************************
本文件是【微信支付V2.0】H5支付实例
需要用授权接口进入页面
****************************************/
-->
<style type="text/css">
/* 重置 [[*/
body,p,ul,li,h1,h2,form,input{margin:0;padding:0;}
h1,h2{font-size:100%;}
ul{list-style:none;}
body{-webkit-user-select:none;-webkit-text-size-adjust:none;font-family:Helvetica;background:#ECECEC;}
html,body{height:100%;}
a,button,input,img{-webkit-touch-callout:none;outline:none;}
a{text-decoration:none;}
/* 重置 ]]*/
/* 功能 [[*/
.hide{display:none!important;}
.cf:after{content:".";display:block;height:0;clear:both;visibility:hidden;}
/* 功能 ]]*/
/* 按钮 [[*/
a[class*="btn"]{display:block;height:42px;line-height:42px;color:#FFFFFF;text-align:center;border-radius:5px;}
.btn-blue{background:#3D87C3;border:1px solid #1C5E93;}
.btn-green{background-image:-webkit-gradient(linear, left top, left bottom, color-stop(0, #43C750), color-stop(1, #31AB40));border:1px solid #2E993C;box-shadow:0 1px 0 0 #69D273 inset;}
/* 按钮 [[*/
/* 充值页 [[*/
.charge{font-family:Helvetica;padding-bottom:10px;-webkit-user-select:none;}
.charge h1{height:44px;line-height:44px;color:#FFFFFF;background:#3D87C3;text-align:center;font-size:20px;-webkit-box-sizing:border-box;box-sizing:border-box;}
.charge h2{font-size:14px;color:#777777;margin:5px 0;text-align:center;}
.charge .content{padding:10px 12px;}
.charge .select li{position:relative;display:block;float:left;width:100%;margin-right:2%;height:150px;line-height:150px;text-align:center;border:1px solid #BBBBBB;color:#666666;font-size:16px;margin-bottom:5px;border-radius:3px;background-color:#FFFFFF;-webkit-box-sizing:border-box;box-sizing:border-box;overflow:hidden;}
.charge .price{border-bottom:1px dashed #C9C9C9;padding:10px 10px 15px;margin-bottom:20px;color:#666666;font-size:12px;}
.charge .price strong{font-weight:normal;color:#EE6209;font-size:26px;font-family:Helvetica;}
.charge .showaddr{border:1px dashed #C9C9C9;padding:10px 10px 15px;margin-bottom:20px;color:#666666;font-size:12px;text-align:center;}
.charge .showaddr strong{font-weight:normal;color:#9900FF;font-size:26px;font-family:Helvetica;}
.charge .copy-right{margin:5px 0; font-size:12px;color:#848484;text-align:center;}
/* 充值页 ]]*/
</style>
</head>
<body>
	<article class="charge">
		<h1>微信支付-H5支付-demo</h1>
		<section class="content">

生成预支付${prepayid}单成功，请点击购买支付
<div class="operation"><a class="btn-blue" id="getBrandWCPayRequest" href="${wxH5PayUrl }">立即购买</a></div>		</section>
	</article>

</body></html>