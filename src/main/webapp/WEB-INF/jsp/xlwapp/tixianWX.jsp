<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport"
	content="width=device-width,initial-scale=1,user-scalable=0">
<title>提现</title>
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
	<%@include file="../frontCommon/head.jsp"%>
	<div class="container">
		<div class="comtips">
			您当前的校币为：<em>${schoolMoney }</em>个。校币是校联网内使用的虚拟货币，您随时可以通过充值的方式增加校币，
			也可以随时通过提现的方式将校币转换为人民币。如果在充值或提现过程中遇到任何意外情况，请通过“投诉和建议”按钮向我们反映，或致电<em>0371-55688020</em>，我们会尽快核对解决。
		</div>
		</strong>
		<form id="cashForm">
			<div class="comform fufei">
				<dl>
					<dt>提现金额：</dt>
					<dd>
						<input type="text" id="cashMoney" name="cashMoney" placeholder="请输入金额" check-type="float2" required float2-message="请输入提现金额">
					</dd>
				</dl>
				<dl>
					<dt>提现手续费为提现金额的0.2%，每笔最低手续费2元</dt>
				</dl>
			</div>
			<div class="m20">
				<a class="apply_btn bg2" href="javascript:void(0);" onclick="toCash();">提现到微信零钱</a>
			</div>
		</form>
	</div>
	<div class="btmline"></div>
	<%@include file="../frontCommon/foot.jsp"%>
	<div class="overflowy"></div>
	<script>
	$("#cashForm").validation(
			function(obj,errmsg){
	},{reqmark:false});
	
	function toCash(){
		var flag = $("#cashForm").isSubmitFlag($("#cashForm"));
		if(flag){
			var cashMoney = $("#cashMoney").val();
			if(cashMoney < 1){
				alert_t("最少提现1元");
			}
			//window.location.href = '<%=request.getContextPath()%>/common/userCash/wx/toCash.htm?cashMoney='+cashMoney;
			$.post(
				'<%=request.getContextPath()%>/common/userCash/wx/toCash.htm',
		        {
					cashMoney: cashMoney
		        },
		        function(response){
		        	if(response.indexOf("<html") > -1){
		        		window.location.reload();
		        		return;
		        	}
		        	var data = JSON.parse(response);
		        	if(data.code == 1){
		        		conform_x("提示",data.msg,function(){
			        		
			        	});
		        	}else {
		        		conform_x("提示",data.msg);
		        	}
		        }
			);
		}
	}
	</script>
</body>
</html>