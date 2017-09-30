<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="page" uri="/WEB-INF/pageTag.tld"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@page import="com.wh.util.PrivilegeUtil"%>
<%@page import="com.wh.entity.ManageUser"%>
<%@page import="com.wh.constants.Constants"%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Insert title here</title>
	<script type="text/javascript" src="<%=request.getContextPath()%>/resource/js/jquery.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/resource/js/easyui/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/resource/js/easyui/easyui-lang-zh_CN.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/resource/js/lightbox_me/jquery.lightbox_me.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/resource/js/bank.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/resource/js/trexpand.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/resource/js/formexpand.js"></script>
	<link href="<%=request.getContextPath()%>/resource/css/admin.css" rel="stylesheet" type="text/css">
	<link href="<%=request.getContextPath()%>/resource/css/main.css" rel="stylesheet" type="text/css">
	<link href="<%=request.getContextPath()%>/resource/css/easyui/easyui.css"
		rel="stylesheet" type="text/css">
	<link href="<%=request.getContextPath()%>/resource/css/easyui/icon.css" rel="stylesheet"
		type="text/css">
</head>
<body>
	
<div class="main">

  <form id="UserInfo" method="POST" enctype="multipart/form-data">
  <div class="lb_bd" id="UserInfo">
    <table width="100%"  border="0" class="table" align="center" cellpadding="0" cellspacing="0">
    			<tr align="center">
					<th width="8%" align="center">账号</th>
					<th width="8%" align="center">姓名</th>
					<th width="6%" align="center">性别</th>
					<th width="10%" align="center">部门</th>
					<th width="10%" align="center">联系电话</th>
					<th width="10%" align="center">固定电话</th>
					<th width="" align="center">邮箱</th>
					<th width="" align="center">地址</th>
					<th width="" align="center">身份证号</th>
					
				</tr>
      			
     			<c:choose>
					<c:when test="${empty userList[0]}">
						<tr>
							<td colspan="10" align="center">没有符合条件的记录</td>
						</tr>
					</c:when>
					<c:otherwise>
						<c:forEach items="${userList[0]}" varStatus="status" var="user">
							<tr>
								<td align="center">${user.account }</td>
								<td align="center">${user.name }</td>
								
								<td align="center">
									<c:if test="${user.sex == 1 }">
											男
									</c:if>
									<c:if test="${user.sex == 2 }">
											女
									</c:if>
								</td>
								<td align="center">${user.department }</td>
								<td align="center">${user.mobile }</td>
								<td align="center">${user.phone }</td>
								<td align="center">${user.mail }</td>
								<td align="center">${user.address }</td>
								<td align="center">${user.id_card }</td>
								
							</tr>
						</c:forEach>	
					</c:otherwise>
					
				</c:choose>
     </table>
     <page:page currentpage="${userList[1]}" rscount="${userList[2]}"
             className="adminPage" pagesize="5" action="/admin/User/web/selectUserPageList.htm"/>
  </div>
  </form>
</div>
	
</body>
</html>