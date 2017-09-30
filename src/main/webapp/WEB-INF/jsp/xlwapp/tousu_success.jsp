<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=0">
	<title>投诉成功</title>
	<link rel="stylesheet" href="<%=request.getContextPath()%>/resource/xlwapp/css/fonts/iconfont.css"/>
	<link rel="stylesheet" href="<%=request.getContextPath()%>/resource/xlwapp/css/css.css"/>
	<link rel="stylesheet" href="<%=request.getContextPath()%>/resource/xlwapp/css/style.css"/>
	<script type="text/javascript" src="<%=request.getContextPath()%>/resource/xlwapp/js/jquery-1.9.1.min.js"></script>
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
	<h2>投诉成功</h2>
	<div class="header_ricon"><a href="<%=request.getContextPath()%>/common/complaintSuggestions/wx/toList.htm"><img width="25" src="<%=request.getContextPath()%>/resource/xlwapp/images/top_righticon.png"></a></div>
</header>
<div class="container">
	<div class="rzsuccess" style="padding:25px">
		<dl>
			<dt><img width="52" src="<%=request.getContextPath()%>/resource/xlwapp/images/renz_success.png"></dt>
			<dd>
				<h3>您的意见已经提交给了后台管理员,<br/>
					意见处理流水号是：<span style="color:#FF9000">${sugNum}</span> </h3>
				<div class="clear"></div>
				<p>感谢您的宝贵意见，我们会有1个工作日内回复您。请随时关注“我的中心”的投诉与建议栏目查看回复。</p>
			</dd>
		</dl>

	</div>
	<div class="m20">
		<a class="apply_btn bg2" href="<%=request.getContextPath()%>/common/userFont/wx/user.htm">返回用户中心</a>

	</div>
</div>
<div class="btmline"></div>
<jsp:include page="../frontCommon/foot.jsp"></jsp:include>

<div class="overflowy"></div>

</body>
</html>
