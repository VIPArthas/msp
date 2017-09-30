<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=0">
	<title>投诉列表</title>
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
	<h2>投诉列表</h2>
	<div class="header_ricon"><a href=""><img width="22" src="<%=request.getContextPath()%>/resource/xlwapp/images/top_righticon.png"></a></div>
</header>
<div class="container">
	<div class="tous_list">
		<div class="comnav">近期投诉记录</div>
		<div class="tous_listc subk">
			<c:forEach items="${complaintSuggestionsList}" var="comSug">
				<a href="<%=request.getContextPath()%>/common/complaintSuggestions/wx/toDetail.htm?sugId=${comSug.id}"><dl>
					<dt><h3>流水号：${comSug.sugNum}</h3><span><fmt:formatDate value="${comSug.submitTime}" pattern="yyyy-MM-dd"/></span></dt>
					<dd>
						<p>${comSug.sugContent}</p>
						<c:if test="${comSug.isRead == 0}"><p><em>有新回复</em></p></c:if>
					</dd>
				</dl>
				</a>
			</c:forEach>

		</div>
	</div>
</div>
<div class="btmline"></div>
<jsp:include page="../frontCommon/foot.jsp"></jsp:include>

<div class="overflowy"></div>

</body>
</html>
