<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=0">
	<title>基础实名认证</title>
	<link rel="stylesheet" href="<%=request.getContextPath()%>/resource/xlwapp/css/fonts/iconfont.css"/>
	<link rel="stylesheet" href="<%=request.getContextPath()%>/resource/xlwapp/css/css.css"/>
	<link rel="stylesheet" href="<%=request.getContextPath()%>/resource/xlwapp/css/style.css"/>
	<link rel="stylesheet" href="<%=request.getContextPath()%>/resource/xlwapp/css/weui.css">
	<%--<script type="text/javascript" src="<%=request.getContextPath()%>/resource/xlwapp/js/jquery-1.9.1.min.js"></script>--%>
	<script type="text/javascript" src="<%=request.getContextPath()%>/resource/xlwapp/js/zepto.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/resource/xlwapp/js/validation_a.js"></script>
	<link rel="Shortcut Icon" href="<%=request.getContextPath()%>/resource/xlwapp/images/study.ico" />
	<script type="text/javascript">
		$(document).ready(function(){

			/*$(".apply_btn").mousedown(function(){
				$(".model").show();
				$(".overflowy").show();
			})

			$(".model_footer .bg3").mousedown(function(){
				$(".model").hide();
				$(".overflowy").hide();
			})*/

		})
	</script>
	<script>
		$(document).ready(function(){
			$("#footC").find("a").each(function() {
				if($(this).text() == "我的") {
					$(this).parent().addClass("cur");
				}else{
					$(this).parent().removeClass();
				}
			})
		})

	</script>

</head>
<body class="combg">
<%@include file="../frontCommon/head.jsp" %>
<div class="container">
	<div class="comform">
		<dl>
			<dt>真实姓名</dt>
			<dd><input type="text" placeholder="请输入真实姓名" value="${userMap.real_name}" readonly="${userMap.realname_status == 1|| userMap.realname_status == 2 ? 'true':'false'}" id="realName" maxlength="7"></dd>
		</dl>
		<dl>
			<dt>身份证号码</dt>
			<dd><input type="text" placeholder="请输入身份证号码" ${userMap.realname_status == 1|| userMap.realname_status == 2 ? 'readonly':''} value="${userMap.card_id}"  id="cardId" maxlength="18"></dd>
		</dl>

	</div>
	<c:if test="${userMap.realname_status == 0}">
		<a class="apply_btn bg2 m20" href="javascript:void(0)" id="submit">提交申请</a>
	</c:if>
	<c:if test="${userMap.realname_status == 1}">
		<a class="apply_btn bg2 m20" href="javascript:void(0)">已完成基础认证</a>
	</c:if>
	<c:if test="${userMap.realname_status == 2}">
		<a class="apply_btn bg2 m20" href="javascript:void(0)">已完成完整认证</a>
	</c:if>


</div>
<div class="btmline"></div>
<jsp:include page="../frontCommon/foot.jsp"></jsp:include>
<div class="model">

	<div class="model_body">

		<div class="renz_tips">
			<dl>
				<dt><img width="55" src="images/renztips.gif"></dt>
				<dd>实名认证需要扣费<em class="red">200</em>校币 ，<br/>您当前余额为<em class="red">120</em>校币</dd>
			</dl>
		</div>
	</div>
	<div class="model_footer">

		<a class="loginbtn bg2 fl" href="">去充值 </a>
		<a class="loginbtn bg3 fr" href="javascript:void(0)">关闭</a>

	</div>
</div>
<div class="overflowy"></div>

<script>
	$(document).ready(function() {
		$("#submit").click(function() {
			var realName = $("#realName").val();
			if(null == realName || realName == "") {
				alert_t("请输入姓名");
				return;
			}
			var cardId = $("#cardId").val();
			var regCard = /(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/
			if (null == cardId || cardId == "") {
				alert_t("请输入身份证号");
				return;
			}
			if(!cardId.match(regCard)) {
				alert_t("身份证号格式不合法");
				return;
			}
			$.post(
					'<%=request.getContextPath()%>/common/attestation/wx/checkAttestation.htm',
					{
						realName : realName,
						cardId : cardId
					},
					function(data) {
						if(data.indexOf("<html") > -1){
							window.location.reload();
							return;
						}
						var returnJson = JSON.parse(data);
						if(returnJson.code == 1) {
							conform_x("成功","恭喜你认证成功！",function() {
								window.location.href = "<%=request.getContextPath()%>/common/index/wx/toIndex.htm"
							});

						} else if(returnJson.code == -2) {
							conform_x("提示","登陆失效，请重新登陆",function() {
								window.location.href = "<%=request.getContextPath()%>/common/userLogin/wap/toLogin.htm"
							});

						}else{
							alert_t(returnJson.msg);
						}
					}
			)


		});

	});
</script>
</body>
</html>
