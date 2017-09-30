<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=0">
	<title>投诉与建议</title>
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
<header>
	<div class="header_licon"><a href="javascript:void(0)" onclick="history.go(-1)"><span class="iconfont icon-lineprev"></span></a></div>
	<h2>投诉与建议</h2>
	<div class="header_ricon"><a href="<%=request.getContextPath()%>/common/complaintSuggestions/wx/toList.htm"><img width="22" src="<%=request.getContextPath()%>/resource/xlwapp/images/top_righticon.png"></a></div>
</header>
<div class="container">
	<div class="comtips">
		您随时都可以将您在使用中的问题、意见和投诉建议通过本系统发送给我们的后台管理员，我们会在1个工作日之内做出回复。
	</div>
	<div class="tous">
		<div class="comnav">人岗匹配</div>
		<div class="tousc">
			<textarea placeholder="请输入您的建议。。。。" id="context"></textarea>
			<div class="clear"></div>
			<div class="loginbtnk">
				<a class="loginbtn fl" id="submit" href="javascript:void(0);">提 交</a>
				<a class="loginbtn bg2 fr" href="javascript:void(0);" onclick="history.go(-1)">返 回</a>
			</div>
		</div>
	</div>

</div>
<div class="btmline"></div>
<jsp:include page="../frontCommon/foot.jsp"></jsp:include>

<div class="overflowy"></div>
<script>
	$(document).ready(function(){
		$("#submit").click(function(){
			var context = $("#context").val()
			if( null == context || context == '') {
				alert_t("请输入投诉与建议内容");
				return;
			}
			$.post(
					'<%=request.getContextPath()%>/common/complaintSuggestions/wx/save.htm',
					{
						sugContent : context
					},
					function(data) {
						if(data.indexOf("<html") > -1){
							window.location.reload();
							return;
						}
						var returnJson = JSON.parse(data);
						if (returnJson.code == 1) {
							window.location.href = '<%=request.getContextPath()%>/common/complaintSuggestions/wx/toSaveSuccess.htm?sugNum='+returnJson.sugNum;
						}
					}
			)
		});
	});
</script>
</body>
</html>
