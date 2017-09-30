<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="charset" content="utf-8">
<title>无标题文档</title>
<%@ include file="base.jsp"%>
<link href="<%=basePath%>resource/css/style_left.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="<%=basePath%>resource/js/jquery.min.js"></script>

</head>
<body>
	<table height="100%" cellSpacing="0" cellPadding="0" width="100%" border="0">
		<tbody>
			<tr>
				<td valign="top">
					<table class="alpha" width="100%" cellSpacing="0" cellPadding="0">
						<tbody>
							<tr>
								<td class="menu" id="menubar" valign="top">
									<ul id="menuleft">
										<c:if test="${leftList.size()!=0}">
											<c:forEach items="${leftList}" var="menu" varStatus="menu_status">
												<c:if test="menu_status==0">
													<li class="active">
														<a id="${menu.menu_id}" href="<%=request.getContextPath()%>${menu.menu_url}"
															target="frmright"> ${menu.menu_name}</a>
													</li>
												</c:if>
												<li><a id="${menu.menu_id}" datahref="<%=request.getContextPath()%>${menu.menu_url}"
													target="frmright"> ${menu.menu_name}</a>
												</li>
											</c:forEach>
										</c:if>
									</ul>
								</td>
							</tr>
						</tbody>
					</table>
				</td>
			</tr>
		</tbody>
	</table>

<script>
	$(function() {
		$('#menubar li:eq(0)').addClass('active').siblings().removeClass("active");
		$('#menubar li').click(function() {
			$(this).addClass('active').siblings().removeClass('active');
		});
		$('#menubar li a').click(function() {
			$(window.parent.document).find("#frmright").attr("src",
					$(this).attr("datahref")).ready();
		});
	});
</script>
</body>
</html>