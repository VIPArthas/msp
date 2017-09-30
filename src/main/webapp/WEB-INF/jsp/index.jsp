<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>后台模板管理系统</title>
<link rel="Shortcut Icon" href="<%=request.getContextPath()%>/resource/images/favicon.ico" />
    <link href="http://libs.baidu.com/bootstrap/3.0.3/css/bootstrap.min.css" rel="stylesheet">
<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/resource/css/common.css" />
<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/resource/css/style.css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/resource/js/jquery-1.11.3.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/resource/js/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/resource/js/easyui/easyui-lang-zh_CN.js"></script>

<script type="text/javascript" src="<%=request.getContextPath()%>/resource/js/lightbox_me/jquery.lightbox_me.js"></script>

<script type="text/javascript" src="<%=request.getContextPath()%>/resource/js/global.js"></script>
<link href="<%=request.getContextPath()%>/resource/css/admin.css" rel="stylesheet" type="text/css">
<script>
	function clickMenu(obj) {
		var d_url = $(obj).next(".ul_sub").find("ul li:eq(0)").children("a").attr("href");
		$(obj).parent("li").addClass("active").siblings("li").removeClass("active");
		$(".left_menu").html("");
		$(".left_menu").html($(obj).next(".ul_sub").html());
		$("#m_frame").attr("src", d_url).ready();
		//右侧导航菜单
		$(".left_menu li:eq(0)").addClass("active").siblings().removeClass("active");
		$(".left_menu").on("click","li",function(){
			$(this).addClass("active").siblings().removeClass("active");
		});
	}
	function clickPwd(){
		debugger;
		$("#pwd_layer").lightbox_me({
			centered : true
		});
	}
	
	function submitPwd(){
		var pwd = $.trim($("#pwd").val());
		var newPwd = $.trim($("#newPwd").val());
		var newRepwd = $.trim($("#newRepwd").val());
		$.post("<%=request.getContextPath()%>/admin/system/manage/manageUser/updateChangePwd.htm", {
			"pwd" : pwd,
			"newPwd" : newPwd,
			"newRepwd" : newRepwd
		}, function(data) {
			if (data == "1") {
				$("#pwd_layer").trigger('close');
				$.messager.alert("提示","密码修改成功!","info",function(){
				});
			} else {
				$("#msg").html(data);
			}
		});
	}
</script>
</head>
<body>
	<!--头部-->
	<div class="header">
		<div class="h_a clearfix">
			<div class="logo fl">
				<a href="#"><img src="<%=request.getContextPath()%>/resource/tcm/images/logo_xlw.png"></a>
			</div>
			<div class="user_info fr">
				<i class="fl"></i>欢迎您：
				<c:if test="${user!=null}">
					<label class="txt_white">${user.userName}</label>&nbsp;&nbsp;
					[<a  href="javascript:void(0);" onClick="clickPwd();" class="changepwd">修改密码</a>] &nbsp;&nbsp;
					[<a href="<%=request.getContextPath()%>/admin/manageAdmin/loginOut.htm">注销</a>]
				</c:if>
			</div>
		</div>
		<div class="menu">
			<ul>
				<li class="active"><a href="#"><span class="glyphicon glyphicon-home"></span>首页</a></li>
				<c:if test="${menuList.size()!=0}">
					<c:forEach items="${menuList}" var="menu">
						<li>
							<a style="cursor: pointer;" id="${menu.id}" onclick="clickMenu(this);">  <span class="glyphicon glyphicon-cog"></span>${menu.modularName}</a>
							<div class="ul_sub">
								<ul class="xtgl">
									<c:forEach items="${menu.secondList}" var="menuSecond" varStatus="secondStatus">
										<c:if test="secondStatus == 0">
											<li>
												<a href="<%=request.getContextPath()%>${menuSecond.modularUrl}" target="m_frame">
                                                    <span class="glyphicon glyphicon-cog"></span>${menuSecond.modularName}
												</a>
											</li>
										</c:if>
										<li>
											<a href="<%=request.getContextPath()%>${menuSecond.modularUrl}" target="m_frame">
                                                ${menuSecond.modularName}
											</a>
										</li>
									</c:forEach>
								</ul>
							</div>
						</li>
					</c:forEach>
				</c:if>
			</ul>
		</div>
	</div>
	<!--头部 end-->
	<!--内容-->
	<div class="content">
		<!--左侧导航-->
		<div class="left_menu"></div>
		<!--左侧导航-->
		<!--切换-->
		<div class="switch"></div>
		<!--切换 end-->
		<!--右侧-->
		<div class="right_main">
			<iframe id="m_frame" name="m_frame" frameborder="0" width="100%" height="100%" src=""></iframe>
		</div>
		<!--右侧-->
	</div>
	<!--内容 end-->
	
	<!-- 修改密码弹出层 -->
	<div id="pwd_layer" style="display: none;">
		<div class="tanchu_body rel">
			<h3 class="tanchu_head">
				<span class="tanchu_title">
					<span class="tanchu_title_icon">修改密码</span>
				</span>
				<span class="tanchu_close_button close">X</span>
			</h3>
			<div style="margin: 20px auto 0;" class="dialog_content">
				<div class="lb_xzyh_xg">
					<ul>
						<li style="width: 100%">
							<span class="zcyhkd">原始密码：</span>
							<input type="password" name="pwd" id="pwd" class="cx_xzyh">
						</li>
						<li style="width: 100%">
							<span class="zcyhkd">新密码：</span> <input
							type="password" name="newPwd" id="newPwd" class="cx_xzyh">
						</li>
						<li style="width: 100%">
							<span class="zcyhkd">确认新密码：</span>
							<input type="password" name="newRepwd" id="newRepwd" class="cx_xzyh">
						</li>
						<li>
							<span style="color: red;" id="msg"></span>
						</li>
						<li style="width: 100%">
							<span class="zcyhkd">&nbsp;</span>
							<input type="button" onclick="submitPwd();" class="sctp_zlxg" value="确认修改">
							<input type="button" class="cancelbtn close" value="取消">
						</li>
					</ul>
				</div>
			</div>
		</div>
	</div>
	<!-- 修改密码弹出层 end -->
</body>
</html>
