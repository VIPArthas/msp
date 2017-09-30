<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=0">
	<title>修改密码</title>
	<link rel="stylesheet" href="<%=request.getContextPath()%>/resource/xlwapp/css/fonts/iconfont.css"/>
	<link rel="stylesheet" href="<%=request.getContextPath()%>/resource/xlwapp/css/css.css"/>
	<link rel="stylesheet" href="<%=request.getContextPath()%>/resource/xlwapp/css/style.css"/>
	<link rel="stylesheet" href="<%=request.getContextPath()%>/resource/xlwapp/css/weui.css">
	<%--<script type="text/javascript" src="<%=request.getContextPath()%>/resource/xlwapp/js/jquery-1.9.1.min.js"></script>--%>
	<script type="text/javascript" src="<%=request.getContextPath()%>/resource/xlwapp/js/zepto.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/resource/xlwapp/js/validation_a.js"></script>
	<link rel="Shortcut Icon" href="<%=request.getContextPath()%>/resource/xlwapp/images/study.ico" />
	<script>
		$(document).ready(function() {
			$("#tel").val($("#tel").val().substring(0,3)+"****"+$("#tel").val().substring(8,11));
		});
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
	<form id="changeForm">
	<div class="comform">
		<dl>
			<dt>手机号</dt>
			<dd><input type="text" value="${phone}" readonly id="tel"></dd>
		</dl>
		<dl>
			<dt>原密码</dt>
			<dd><input type="password" placeholder="请输入原密码" id="old_password" check-type="charAndNumber" minlength="6" charOrNumber-message="请输入6-12位数字加字母的密码" required-message="请输入密码" type="password" maxlength="12"></dd>
		</dl>
		<dl>
			<dt>新密码</dt>
			<dd><input type="password" placeholder="请输入新密码" id="new_password" check-type="charAndNumber" minlength="6" charOrNumber-message="请输入6-12位数字加字母的密码" required-message="请输入密码" type="password" maxlength="12"></dd>
		</dl>
		<dl>
			<dt>确认新密码</dt>
			<dd><input  type="password" placeholder="请再次输入密码" id="new_password_2" check-type="charAndNumber" minlength="6" charOrNumber-message="请输入6-12位数字加字母的密码" required-message="请输入密码" type="password" maxlength="12"></dd>
		</dl>

	</div>
	</form>
	<a class="apply_btn bg2 m20" href="javascript:void(0)" onclick="changePassword();">确认修改</a>
</div>
<div class="btmline"></div>
<jsp:include page="../frontCommon/foot.jsp"></jsp:include>

<div class="overflowy"></div>
<script>
	$("#changeForm").validation(function(obj,em){
//				console.log(obj,em);
			}
			,{reqmark:false}
	);
	function changePassword() {
		var flag = $("#changeForm").isSubmitFlag($("#changeForm"));
		if(flag){

			var old_password = $("#old_password").val();
			var new_password = $("#new_password").val();
			var new_password_2 = $("#new_password_2").val();
			var regPassword = /^[A-Za-z0-9]+$/;

			if( null == old_password || old_password.trim() =='' ) {
				alert_t("请输入旧密码密码");
				return;
			} else if( !old_password.match(regPassword) ) {
				alert_t("旧密码格式不对");
				return;
			}

			if( null == new_password || new_password.trim() =='' ) {
				alert_t("请输入一个新密码");
				return;
			} else if( !new_password.match(regPassword) ) {
				alert_t("请输入6-12位数字加字母的密码");
				return;
			}
			if(null == new_password_2 || new_password_2.trim() =='' ) {
				alert_t("请再次输入新密码");
				return;
			}else if( !new_password_2.match(regPassword) ) {
				alert_t("请输入6-12位数字加字母的密码");
				return;
			}
			if(new_password.trim() != new_password_2.trim()) {
				alert_t("两次密码输入不一致");
				return;
			}

			$.post(
					"<%=request.getContextPath()%>/common/userFont/wx/changePassword.htm",
					{
						old_password : old_password,
						new_password : new_password,
						new_password_2 : new_password_2
					},
					function(data) {
						if(data.indexOf("<html") > -1){
							window.location.reload();
							return;
						}
						var returnJson = JSON.parse(data);
						if (returnJson.code == 1 || returnJson.code == -2) {
							conform_x("提示",returnJson.msg, function() {
								window.location.href = "<%=request.getContextPath()%>/common/userLogin/wx/toLogin.htm"
							})
						}else{
							alert_t(returnJson.msg);
						}
					}
			)
		}

	}

</script>
</body>
</html>
