<%@page import="com.wh.util.ConfigUtil"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="com.wh.util.ConfigUtil"%>
<html lang="en">
<head>
<meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=0">
<title>首页</title>
<link rel="Shortcut Icon" href="<%=request.getContextPath()%>/resource/xlwapp/images/study.ico" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/resource/xlwapp/css/fonts/iconfont.css" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/resource/xlwapp/css/css.css" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/resource/xlwapp/css/style.css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/resource/xlwapp/js/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/resource/xlwapp/js/WapCircleImg.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/resource/xlwapp/js/public.js"></script>
<link rel="Shortcut Icon" href="<%=request.getContextPath()%>/resource/xlwapp/images/study.ico" />
</head>
<body class="combg">
	<header>
		<div class="header_licon">
			<a href="javascript:void(0)" onclick="m(1)"><span
				class="iconfont icon-linemenu3"></span></a>
		</div>
		<h2>首页</h2>
		<div class="header_ricon">
			<a
				href="<%=request.getContextPath()%>/common/complaintSuggestions/wx/toIndex.htm"><span
				class="iconfont icon-lineedit"></span></a>
		</div>
	</header>
	<div class="container">
		<div class="banner_box">
			<div id="Cimgf0d5c2216b8cbscroller_imglist" class="roll_img_mb_01">
				<div class="img_box" style="mix-height: 150px">
					<ul>
						<c:forEach items="${bannerImages}" var="banner">
							<c:choose>
								<c:when test="${banner.initLink == 1}">
									<li>
										<a href="<%=request.getContextPath()%>/${banner.linkUrl}">
											<img src="<%=request.getContextPath()%>${banner.bannerUrl}" title="">
										</a>
										<span></span>
									</li>
								</c:when>
								<c:when test="${banner.initLink == 0}">
									<li>
										<a href="http://${banner.linkUrl}">
											<img src="<%=request.getContextPath()%>${banner.bannerUrl}" title="">
										</a>
										<span></span>
									</li>
								</c:when>
							</c:choose>
						</c:forEach>
					</ul>
				</div>
				<div class="nav_box">
					<ul id="li_on_name">
						<c:forEach items="${bannerImages}" var="banner" varStatus="status">
							<li class="${status.index ==0 ? 'li_on':''}"></li>
						</c:forEach>
					</ul>
				</div>
			</div>
			<script type="text/javascript">
				WapCircleImg_01("Cimgf0d5c2216b8cbscroller_imglist", 3, true);
			</script>
		</div>
		<div class="index_menu">
			<ul>
				<li>
					<a href="javascript:void(0);" onClick="rgpp();">
						<img width="54" src="<%=request.getContextPath()%>/resource/xlwapp/images/index_icon1.png">
						<h3>人岗匹配</h3>
					</a>
					<!-- <div class="comc">
								<p></p>
								<h3>敬请期待！</h3>
							</div> -->
				</li>
				<li>
					<a href="javascript:void(0);" onClick="zhcx();">
						<img width="54" src="<%=request.getContextPath()%>/resource/xlwapp/images/index_icon2.png">
						<h3>智慧出行</h3>
					</a> <!-- <div class="comc">
								<p></p>
								<h3>敬请期待！</h3>
							</div> -->
				</li>
				<li>
					<a onClick="ptmManage();" href="javascript:void(0);">
						<img width="54" src="<%=request.getContextPath()%>/resource/xlwapp/images/index_icon3.png">
						<h3>顶岗实习</h3>
					</a> <!--  <div class="comc">
								<p></p>
								<h3>敬请期待！</h3>
							</div>-->
				</li>
				<li>
					<a onClick="rzpp();" href="javascript:void(0)">
						<img width="54" src="<%=request.getContextPath()%>/resource/xlwapp/images/index_icon4.png">
						<h3>人职匹配</h3>
					</a>
					<!-- <div class="comc">
						<p>4月20日上线</p>
						<h3>敬请期待！</h3>
					</div> -->
				</li>
				<li>
					<a  href="javascript:void(0)">
						<img width="54" src="<%=request.getContextPath()%>/resource/xlwapp/images/index_icon5.png">
						<h3>资源置换</h3>
					</a>
					<div class="comc">
						<p>4月20日上线</p>
						<h3>敬请期待！</h3>
					</div>
				</li>
				<li>
					<a href="javascript:void(0)">
						<img width="54" src="<%=request.getContextPath()%>/resource/xlwapp/images/index_icon6.png">
						<h3>在线课堂</h3>
					</a>
					<div class="comc">
						<p>4月20日上线</p>
						<h3>敬请期待！</h3>
					</div>
				</li>
				<li>
					<a href="javascript:void(0)">
						<img width="54" src="<%=request.getContextPath()%>/resource/xlwapp/images/index_icon7.png">
						<h3>同学帮帮忙</h3>
					</a>
					<div class="comc">
						<p>4月20日上线</p>
						<h3>敬请期待！</h3>
					</div></li>
				<li>
					<a href="<%=request.getContextPath()%>/jzxx/jzPartTime/wx/jzxxList.htm?focused=1">
						<img width="54" src="<%=request.getContextPath()%>/resource/xlwapp/images/index_icon8.png">
						<h3>全网兼职</h3>
					</a>
				</li>
				<li>
					<a href="javascript:void(0)">
						<img width="54" src="<%=request.getContextPath()%>/resource/xlwapp/images/index_icon9.png">
						<h3>敬请期待</h3>
					</a>
				</li>
			</ul>
		</div>
		<div class="btmline"></div>
	</div>
	<jsp:include page="../frontCommon/foot.jsp"></jsp:include>
	
	<div class="quickmenu">
		<div class="quick_user">
			<a href="<%=request.getContextPath()%>/common/userFont/wx/user.htm"><dl>
					<dt>
						<c:choose>
							<c:when test="${empty userMap.logo_url}">
								<img src="<%=request.getContextPath()%>/resource/xlwapp/images/user_icon1.png"><em>
							</c:when>
							<c:otherwise>
								<img src="<%=request.getContextPath()%>${userMap.logo_url}"><em>
							</c:otherwise>
						</c:choose>
						<img width="13" src="<%=request.getContextPath()%>/resource/xlwapp/images/vicon.png"></em>
					</dt>
					<dd>
						<h3>${userMap.nick_name != null? userMap.nick_name:"未设置昵称"}</h3>
						<span>等级：${userMap.level}</span>
					</dd>
				</dl> </a>
		</div>
		<div class="quick_info">
			<ul>
				<li>校币：${userMap.school_money != null ? userMap.school_money : 0 }</li>
				<li>积分：${userMap.score != null ? userMap.score : 0 }</li>
			</ul>
		</div>
		<div class="quicknav">
			<ul>
				<li>
					<a href="<%=request.getContextPath()%>/common/userFont/wx/user.htm">
						<span>
							<img width="24" src="<%=request.getContextPath()%>/resource/xlwapp/images/user_icon1.png">
						</span>个人资料
					</a>
				</li>
				<li>
					<a href="<%=request.getContextPath()%>/common/attestation/wx/toAttestation.htm">
						<span>
							<img width="24" src="<%=request.getContextPath()%>/resource/xlwapp/images/user_icon2.png">
						</span>实名认证
					</a>
				</li>
				<li>
					<a href="<%=request.getContextPath()%>/common/userRecharge/wap/choiseRecharge.htm">
						<span>
							<img width="24" src="<%=request.getContextPath()%>/resource/xlwapp/images/user_icon3.png">
						</span>充值
					</a>
				</li>
				<li>
					<a href="<%=request.getContextPath()%>/common/userCash/wap/viewCash.htm">
						<span>
							<img width="24" src="<%=request.getContextPath()%>/resource/xlwapp/images/user_icon4.png">
						</span>提现
					</a>
				</li>
				<li>
					<a href="<%=request.getContextPath()%>/common/userFont/wx/user.htm">
						<span>
							<img width="24" src="<%=request.getContextPath()%>/resource/xlwapp/images/user_icon5.png">
						</span>消费明细
					</a>
				</li>
			</ul>
			<div class="quit">
				<a href="<%=request.getContextPath()%>/common/userLogin/wx/loginOut.htm">
					退出登录
				</a>
			</div>
		</div>
	</div>
	
	<div class="overflowy"></div>
</body>
<script type="text/javascript">
	var userId = encodeURIComponent('${userId}');
	function ptmManage() {
		window.location.href = "http://sx.xiaolianwang.net/resource/wap/login.html";
	}
	function rzpp() {
		window.location.href = "http://pp.uni-uni.cn/systemMajor/index.do?userId=" + userId;
	}
	function rgpp() {
		window.location.href = "http://pp.uni-uni.cn/resume/viewMenu.do?userId=" + userId;
	}
	function zhcx() {
		window.location.href = "http://jt.iqilianwang.net/loginPage/2";
	}
</script>
</html>
