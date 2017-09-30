<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=0">
<title>充值结果页面</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/resource/xlwapp/css/fonts/iconfont.css" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/resource/xlwapp/css/css.css" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/resource/xlwapp/css/style.css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/resource/xlwapp/js/jquery-1.9.1.min.js"></script>
</head>
<body class="combg">
	<%@include file="../frontCommon/head.jsp" %>
	<div class="container">
		<div class="rzsuccess bgpur">
			<dl>
				<dt>
					<img width="62" src="<%=request.getContextPath()%>/resource/xlwapp/images/chongzhi_icon.png">
				</dt>
				<dd style="padding-top: 10px">
					<h3>
						本次充值校币<span style="color: #FFD800"><fmt:formatNumber value="${rechargeMoney }" maxFractionDigits="0"/>  </span>个<br />您当前的校币为：<span style="color: #FFD800"><fmt:formatNumber value="${schoolMoney }" maxFractionDigits="0"/></span>个
						
					</h3>
					<div class="clear"></div>
					<p>如果在充值或提现过程中遇到任何意外情况，请通过“投诉和建议”按钮向我们反映，或致电0371-55688020，我们会尽快核对解决。</p>
				</dd>
			</dl>

		</div>
		<div class="m20">
			<c:choose>
				<c:when test="${not empty rechargeParam and not empty rechargeParam.returnUrl}">
					<a class="apply_btn bg2" href="<%=request.getContextPath()%>rechargeParam.returnUrl">返回上级页面</a>
				</c:when>
			</c:choose>
			<c:otherwise>
				<a class="apply_btn bg2" href="<%=request.getContextPath()%>/common/userFont/wx/user.htm">返回用户中心</a>
			</c:otherwise>
		</div>
	</div>
	<div class="btmline"></div>
	<%@include file="../frontCommon/foot.jsp"%>
	<div class="overflowy"></div>
</body>
</html>