<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport"
	content="width=device-width,initial-scale=1,user-scalable=0">
<title>充值</title>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/resource/xlwapp/css/fonts/iconfont.css" />
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/resource/xlwapp/css/css.css" />
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/resource/xlwapp/css/style.css" />
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/resource/xlwapp/css/weui.css" />
<script type="text/javascript"
	src="<%=request.getContextPath()%>/resource/xlwapp/js/zepto.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/resource/xlwapp/js/validation_a.js"></script>
<script type="text/javascript">
$(document).ready(function(){
 
  $("input").focus(function(){
    $(".container").css("margin-top","-150px");
  });
  $("input").blur(function(){
    $(".container").css("margin-top","0");
  });
});
</script>

</head>
<body class="combg">
	<%@include file="../frontCommon/head.jsp" %>
	<div class="container">
		<div class="comtips">
			您当前的校币为：<em>${schoolMoney}</em>个。校币是校联网内使用的虚拟货币，您随时可以通过充值的方式增加校币，
			也可以随时通过提现的方式将校币转换为人民币。如果在充值或提现过程中遇到任何意外情况，请通过“投诉和建议”按钮向我们反映，或致电<em>0371-55688020</em>，我们会尽快核对解决。
		</div>
		<div class="paybox1">
			<h3 class="paytit">支付信息</h3>
			<div class="comform" style="margin: 0">
				<form id="rechargeForm" action="<%=request.getContextPath()%>/common/userRecharge/wx/toRecharge.htm">
					<dl>
						<dt>充值金额</dt>
						<dd>
							<input type="hidden" name="rechargeType" value="${rechargeType}" />
							<input id="rechargeMoney" name="rechargeMoney" type="text" placeholder="请输入充值金额" check-type="float3" required float3-message="请输入充值金额"/><em id="moneyVerify" class="iconfont icon-linewarning error" style="display:none;"></em>
						</dd>
					</dl>
				</form>
			</div>
		</div>
		<div class="m20">
			<a class="apply_btn bg2" href="javascript:void(0);" onclick="toRecharge();">立即充值</a>
		</div>
	</div>
	<div class="btmline"></div>
	<%@include file="../frontCommon/foot.jsp"%>
	<div class="overflowy"></div>
	<script>
	$("#rechargeForm").validation(
		function(obj,errmsg){
	},{reqmark:false});
	function toRecharge(){
		var flag = $("#rechargeForm").isSubmitFlag($("#rechargeForm"));
		if(flag){
			var rechargeMoney = $("#rechargeMoney").val();
			if(rechargeMoney < 0.01){
				alert_t("最少充值0.01元");
				return;
			}
			window.location.href = '<%=request.getContextPath()%>/common/userRecharge/wx/toRecharge.htm?rechargeType=${rechargeType}&rechargeMoney='+rechargeMoney;
		}
	}
	</script>
</body>
</html>