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
<script type="text/javascript"
	src="<%=request.getContextPath()%>/resource/xlwapp/js/jquery-1.9.1.min.js"></script>
</head>
<body class="combg">
	<%@include file="../frontCommon/head.jsp"%>
	<div class="container">
		<div class="comtips">
			您当前的校币为：<em>${schoolMoney }</em>个。校币是校联网内使用的虚拟货币，您随时可以通过充值的方式增加校币，
			也可以随时通过提现的方式将校币转换为人民币。如果在充值或提现过程中遇到任何意外情况，请通过“投诉和建议”按钮向我们反映，或致电<em>0371-55688020</em>，我们会尽快核对解决。
		</div>
		</strong>
		<form>
			<div class="comform fufei">
				<dl>
					<a href="<%=request.getContextPath()%>/common/userCash/wap/bankList.html">
						<dt>选择银行：</dt>
						<dd>
							<div class="chose_input">中国工商银行</div>
						</dd>
					</a>
				</dl>

				<dl>
					<dt>银行卡号：</dt>
					<dd>
						<input type="text" id="cashCard" name="cashCard" placeholder="请输入卡号">
					</dd>
				</dl>
				<dl>
					<dt>提现金额：</dt>
					<dd>
						<input type="text" id="cashMoney" name="cashMoney" placeholder="请输入金额">
					</dd>
				</dl>
				<dl>
					<dt>提现手续费为提现金额的0.2%，每笔最低手续费2元</dt>
				</dl>
			</div>
			<div class="m20">
				<a class="apply_btn bg2" href="javascript:void(0);" onclick="toCash();">立即提现</a>
			</div>
		</form>
	</div>
	<div class="btmline"></div>
	<%@include file="../frontCommon/foot.jsp"%>
	<div class="overflowy"></div>
	<script>
	function toCash(){
		
	}
	</script>
</body>
</html>