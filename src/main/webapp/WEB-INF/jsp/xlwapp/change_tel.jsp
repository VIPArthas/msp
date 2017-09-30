<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=0">
    <title>修改手机号</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/resource/xlwapp/css/fonts/iconfont.css"/>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/resource/xlwapp/css/css.css"/>
	<link rel="stylesheet" href="<%=request.getContextPath()%>/resource/xlwapp/css/style.css"/>
	<link rel="stylesheet" href="<%=request.getContextPath()%>/resource/xlwapp/css/weui.css">
	<%--<script type="text/javascript" src="<%=request.getContextPath()%>/resource/xlwapp/js/jquery-1.9.1.min.js"></script>--%>
	<script type="text/javascript" src="<%=request.getContextPath()%>/resource/xlwapp/js/zepto.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/resource/xlwapp/js/validation_a.js"></script>
	<link rel="Shortcut Icon" href="<%=request.getContextPath()%>/resource/xlwapp/images/study.ico" />
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
					 	<dt>原手机号</dt>
						<dd><input type="text" placeholder="请输入原手机号" id="old_phone"></dd>
					 </dl>
					  <dl>
					 	<dt>新手机号</dt>
						<dd><input type="text" placeholder="请输入新手机号" id="new_phone"></dd>
					 </dl>
					   <dl>
					 	<dt>验证码</dt>
						<dd><a style="float:right" class="getyzm" href="javascript:void(0);" id="sendCode" onclick="sendCode();">获取验证码</a><input style="width:50%" type="text" id="code" placeholder="请输入验证码"></dd>
					 </dl>
	 
					
				  </div> 
		  <a class="apply_btn bg2 m20" href="javascript:void(0)" id="changeTel">确认修改</a>
			</div>
			 <div class="btmline"></div>
				<jsp:include page="../frontCommon/foot.jsp"></jsp:include>
				    
				    <div class="overflowy"></div>

<script>
	function sendCode() {
		var sendBtn = $("#sendCode");
		var new_phone = $("#new_phone").val();
		var regPhone = /^1[3|4|5|7|8]\d{9}$/;

		var time = 59;
		if(new_phone == null || new_phone.trim() == '') {
			alert_t("请输入新手机号");
			return;
		}else if(!new_phone.match(regPhone)) {
			alert_t("新手机号格式不正确");
			return;
		}

		$.post(
				"<%=request.getContextPath()%>/common/userRegister/wx/sendMsgCode.htm", {new_phone:new_phone,flag:"1"},
				function (data) {
					var returnJson = eval("("+data+")");
					console.log(returnJson);
					if(returnJson.code == 1){
						sendBtn.attr("onclick","");
						var id = setInterval(function(){
							sendBtn.html(time+"秒后重试");
							time--;
							if(time<=0){
								window.clearInterval(id);
								sendBtn.html("获取验证码");
								sendBtn.attr("onclick", "sendCode");
							}
						},1000);
					} else{
						alert_t(returnJson.msg);
					}


				}
		)

	}



	$(document).ready(function() {

		$("#changeTel").click(function(){
			var new_phone = $("#new_phone").val();
			var old_phone = $("#old_phone").val();
			var regPhone = /^1[3|4|5|7|8]\d{9}$/;
			var code = $("#code").val();

			if(old_phone == null || old_phone.trim() == '') {
				alert_x("请输入原手机号");
				return;
			}else if(!old_phone.match(regPhone)) {
				alert_x("原手机号格式不正确");
				return;
			}
			if(new_phone == null || new_phone.trim() == '') {
				alert_x("请输入新手机号");
				return;
			}else if(!new_phone.match(regPhone)) {
				alert_x("新手机号格式不正确");
				return;
			}

			if(code == null || code.trim() == '') {
				alert_x("请输入你收到的验证码");
				return;
			}
			$.post(
				"<%=request.getContextPath()%>/common/userFont/wx/changeTel.htm", {old_phone:old_phone,code:code},
				function (data) {
					console.log(data.indexOf("<html"));
					if(data.indexOf("<html") > -1){
						window.location.reload();
						return;
					}
					var returnJson = JSON.parse(data);
					if(returnJson.code == 1){
						conform_x("成功","修改成功，请重新登陆",function() {
							window.location.href = "<%=request.getContextPath()%>/common/userLogin/wx/toLogin.htm";
						})
					} else{
						alert_x(returnJson.msg);
					}


				}
			)


		});

	});

</script>
				</body>
				</html>
