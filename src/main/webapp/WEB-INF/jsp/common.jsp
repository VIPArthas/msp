<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<title>无标题文档</title>
<script type="text/javascript" src="<%=request.getContextPath()%>/resource/js/jquery.min.js"></script>
</head>
<body>
	<!--导航部分-->
	<%@ include file="./common/head.jsp"%> 


	<!--导航部分结束-->
	<table class="main_table" cellspacing="0" cellpadding="0" width="100%"
		border="0">
		<tbody>
			<tr>
				<!--左侧-->
				<td class="main_left" id="frmtitle" valign="top" align="middle"
					name="fmTitle"><iframe class="left_iframe" id="frmleft"
						name="frmleft" src="<%=request.getContextPath() %>/admin/system/menuinfo/menuLeft.htm" frameBorder="0"></iframe></td>
				<!--左侧 end-->
				<!-- 切换按钮-->
				<td>
					<table height="100%" cellspacing="0" cellpadding="0" border="0">
						<tbody>
							<tr>
								<td style="height: 100%" onclick="switchSysBar()"><span
									class="navPoint" id="switchPoint" title="关闭/打开左栏"><img
										src="<%=basePath%>resource/images/shousuo.jpg"></span></td>
							</tr>
						</tbody>
					</table>
				</td>
				<!--切换按钮 end-->
				<!--主体内容-->
				<td valign=top width="100%"><iframe class="main_iframe"
						id="frmright" name="frmright" src="<%=request.getContextPath() %>/admin/workOrder/storage/appStorage.htm?storageId=76216febc18940be9681be49753e4a75"
						frameborder="0" scrolling="yes"></iframe></td>
				<!--主体内容 end-->
			</tr>
		</tbody>
	</table>
	<%@ include file="./common/foot.jsp"%>
</body>
</html>