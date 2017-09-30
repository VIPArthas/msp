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
    <link href="//cdn.bootcss.com/weui/0.4.2/style/weui.min.css" rel="stylesheet">
    <link href="<%=request.getContextPath()%>/resource/schoolTask/css/iconfont/iconfont.css" rel="stylesheet"/>
    <script type="text/javascript" src="http://cdn.hcharts.cn/jquery/jquery-1.8.3.min.js"></script>
    <link href="<%=request.getContextPath()%>/resource/schoolTask/css/style.css" rel="stylesheet"/>
    <script type="text/javascript" src="<%=request.getContextPath()%>/resource/schoolTask/js/public.js"></script>
    <script src="//code.jquery.com/ui/1.10.4/jquery-ui.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/resource/schoolTask/js/WapCircleImg.js"></script>
    <title>充值</title>

    <div id='wx_pic' style='margin:0 auto;display:none;'>
        <img src='../static/image/pic300.jpg'/>
    </div>
</head>
<body onload="myfunction()">
<div class="header">
    <a href="javascript:void(0);" onclick="history.go(-1)">
        <div class="left"><i class="iconfont icon-arrow_right-copy"></i></div>
    </a>

    <div class="center">充值</div>
    <div class="right"></div>
</div>
<div class="content">
    <%--<form class="form-inline" method="post"--%>
            <%-->--%>
        <div class="rw_detail right mt10">
            <dl class="after no">
                <dt>充值金额:</dt>
                <dd><input type="number" class="inputw100" placeholder="请输入你的充值金额" title="请输入正确的钱数"
                           pattern="[0-9]{1,14}(\\.{0,1}[0-9]{1,2})?" required autocomplete="on" id="txje"/></dd>
            </dl>
            <br>
            <ul class="f4 nobor">
                <%--class="cur"--%>
                <li data-money="0.01"><span>0.01 元</span></li>
                <li data-money="0.1"><span>0.1 元</span></li>
                <li data-money="1"><span>1 元</span></li>
                <li data-money="5"><span>5 元</span></li>
                <li data-money="10"><span>10 元</span></li>
                <li data-money="20"><span>20 元</span></li>
                <li data-money="50"><span>50 元</span></li>
                <li data-money="100"><span>100 元</span></li>
            </ul>
            <br>
        </div>
        <%--<div class="rw_detail right mt10">--%>
            <%--<dl class="after no">--%>
                <%--&lt;%&ndash;<dt>请选择支付方式:</dt>&ndash;%&gt;--%>
            <%--</dl>--%>
            <%--<label>--%>
                <%--<dl class="after no">--%>
                    <%--&lt;%&ndash;<dt><i class="icon iconfont icon-weixin t_greenW"></i>微信支付</dt>&ndash;%&gt;--%>
                    <%--<dd>--%>
                        <%--<div class="checkgroup fr">--%>
                            <%--<div class="checkbox_g"><input type="radio" style="margin-top: 18px;" checked name="r1"/>--%>
                            <%--</div>--%>
                        <%--</div>--%>
                <%--</dl>--%>
            <%--</label>--%>
        <%--</div>--%>
        <div class="mlr20">
            <a href="javascript:void(0);" style="margin-top: 50px;" class="btn_bg btn_green" onclick="zhiFu();">确认支付</a>
        </div>
    <%--</form>--%>
</div>
<script>
    function myfunction() {
        var msg = '${msg}';
        if (null != msg && "" != msg) {
            alert(msg);
        }
    }
    window.onload = myfunction;
    $(".f4 li").on("click", function () {
        $(this).siblings(".cur").removeClass("cur").end().addClass("cur");
        $("#txje").val($(this).data("money"));
    });
    $("#txje").on("blur", function () {
        $(".f4 li").removeClass("cur");
        $(".f4 li[data-money=" + $(this).val() + "]").addClass("cur");
    });
    function zhiFu() {
        var jinE = $("#txje").val();
        if (jinE < 0.01) {
            alert("最少充值0.01元");
            return;
        }
        var where = '${where}';
        var userId='${userId}';
        var ua = navigator.userAgent.toLowerCase();
        if (where == 0) { //成功页面是我的余额
            var successUrl = "/schoolTask/mine/wx/accountBalance.htm?";
            var failedUrl = "/schoolTask/chongZhi/wx/czFailed.htm";
            if (ua.match(/MicroMessenger/i) == "micromessenger") {
                //是来自微信内置浏览器
                window.location.href = "<%=request.getContextPath()%>/common/userRecharge/wx/cz.htm?successUrl=" + successUrl + "&failedUrl=" + failedUrl + "&rechargeType=3&rechargeMoney=" + jinE + "&type=1";
            } else {
                //	不是来自微信内置浏览器
                <%--window.location.href = "<%=request.getContextPath()%>/common/userRecharge/wx/cz2.htm?successUrl=" + successUrl + "&failedUrl=" + failedUrl + "&rechargeType=3&rechargeMoney=" + jinE + "&type=1";--%>
                /*支付宝支付*/
                window.location.href = "<%=request.getContextPath()%>/common/userRecharge/wx/payByZfb.htm?rechargeMoney=" + jinE+"&url=" + successUrl+"&userId="+userId;
            }
        } else if (where == 1) { //成功页面是资金托管页面
            var id = '${id}';
            var url = "/schoolTask/stSendTask/wx/ziJinTuoGuan.htm?id=" + id;
            if (ua.match(/MicroMessenger/i) == "micromessenger") {
                window.location.href = "<%=request.getContextPath()%>/common/userRecharge/wx/cz.htm?successUrl=" + url + "&failedUrl=" + url + "&rechargeType=3&rechargeMoney=" + jinE + "&type=2";
            }else{
                window.location.href = "<%=request.getContextPath()%>/common/userRecharge/wx/payByZfb.htm?rechargeMoney=" + jinE+"&url=" + url+"&userId="+userId;
            }
        } else if (where == 2) { //成功页面是资金托管页面
            var id = '${id}';
            var receiveId = '${receiveId}';
//            alert(receiveId);
            var url = "/schoolTask/stReceiveTask/wx/toZjtg2.htm?id=" + id + "WOWreceiveId=" + receiveId;
            if (ua.match(/MicroMessenger/i) == "micromessenger") {
                window.location.href = "<%=request.getContextPath()%>/common/userRecharge/wx/cz.htm?successUrl=" + url + "&failedUrl=" + url + "&rechargeType=3&rechargeMoney=" + jinE + "&type=3";
            }else{
                window.location.href = "<%=request.getContextPath()%>/common/userRecharge/wx/payByZfb.htm?rechargeMoney=" + jinE+"&url=" + url+"&userId="+userId;
            }

        }
        <%--$.post(--%>
        <%--"<%=request.getContextPath()%>/common/userRecharge/wx/cz.htm",--%>
        <%--{--%>
        <%--rechargeType:3,--%>
        <%--rechargeMoney:jinE--%>
        <%--},--%>
        <%--function(response) {--%>
        <%--var retJson = JSON.parse(response);--%>

        <%--if(retJson.returnCode == '0') {--%>
        <%--alert(retJson.msg);--%>
        <%--}else{--%>
        <%--alert(retJson.msg);--%>
        <%--}--%>
        <%--}--%>
        <%--)--%>
    }
</script>
</body>
</html>